/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 * </copyright>
 *
 * $Id: EAttributePropertyHandler.java,v 1.20 2010/11/12 14:08:17 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.property;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierCacheHandler;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.eclipse.emf.teneo.util.StoreUtil;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

/**
 * Is a getter and setter for EMF eattribute which uses eGet and eSet.Handles many==false
 * properties.
 * 
 * This class implements both the getter, setter and propertyaccessor interfaces. When the getGetter
 * and getSetter methods are called it returns itself.
 * 
 * This accessor also handles arrays of primitive types.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.20 $
 */
public class EAttributePropertyHandler implements Getter, Setter, PropertyAccessor {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 8953817672640618007L;

	/** The logger */
	private static Log log = LogFactory.getLog(EAttributePropertyHandler.class);

	/** The field name for which this elist getter operates */
	protected final EAttribute eAttribute;

	/** The instanceclass */
	protected final Class<?> instanceClass;

	private boolean handleUnsetAsNull = false;

	private boolean convertUnsetToNull = false;

	private boolean isObjectClass = false;

	private boolean isId = false;

	/** Constructor */
	public EAttributePropertyHandler(EAttribute eAttribute) {
		this.eAttribute = eAttribute;
		instanceClass = eAttribute.getEType().getInstanceClass();
		if (eAttribute.getEType().getInstanceClassName() != null) {
			isObjectClass = eAttribute.getEType().getInstanceClassName().contains(".");
		}
		AssertUtil.assertTrue(eAttribute.getName()
				+ " is a many feature which is not handled by this accessor ", !eAttribute.isMany());
		if (log.isDebugEnabled()) {
			log.debug("Created getter/setter for " + StoreUtil.toString(eAttribute));
		}
	}

	public EAttribute getEAttribute() {
		return eAttribute;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMember()
	 */
	public Member getMember() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.PropertyAccessor#getGetter(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.PropertyAccessor#getSetter(java.lang.Class, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#get(java.lang.Object)
	 */
	public Object get(Object owner) throws HibernateException {
		if (handleUnsetAsNull || convertUnsetToNull) {
			EObject eobj = (EObject) owner;
			if (!eobj.eIsSet(eAttribute) && eAttribute.isUnsettable()) {
				return null;
			}
		}
		return ((EObject) owner).eGet(eAttribute);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getForInsert(java.lang.Object, java.util.Map,
	 * org.hibernate.engine.SessionImplementor)
	 */
	@SuppressWarnings("rawtypes")
	public Object getForInsert(Object arg0, Map arg1, SessionImplementor arg2)
			throws HibernateException {
		return get(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethod()
	 */
	public Method getMethod() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethodName()
	 */
	public String getMethodName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getReturnType()
	 */
	@SuppressWarnings("rawtypes")
	public Class getReturnType() {
		return eAttribute.getEType().getInstanceClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Setter#set(java.lang.Object, java.lang.Object,
	 * org.hibernate.engine.SessionFactoryImplementor)
	 */
	public void set(Object target, Object value, SessionFactoryImplementor factory)
			throws HibernateException {

		EObject eobj = (EObject) target;
		if (value == null) {
			if (handleUnsetAsNull && eAttribute.isUnsettable()) {
				eobj.eUnset(eAttribute);
			} else if (isObjectClass || eAttribute instanceof EEnum) {
				eobj.eSet(eAttribute, value);
			}
			return;
		}

		if (isId()) {
			IdentifierCacheHandler.getInstance().setID(target, value);
		}

		final Object curValue = get(target);

		// do not set if not changed
		// note: this does not handle unsettable correctly
		// need to make this an option
		if (curValue != null && curValue.equals(value)) {
			return;
		}
		if (curValue == value) {
			return;
		}

		final Object setValue;
		if (value != null && instanceClass != null && value.getClass() != instanceClass) {
			final Class<?> valClass = value.getClass();
			if (valClass == Integer[].class) {
				setValue = convert((Integer[]) value);
			} else if (valClass == Byte[].class) {
				setValue = convert((Byte[]) value);
			} else if (valClass == Boolean[].class) {
				setValue = convert((Boolean[]) value);
			} else if (valClass == Double[].class) {
				setValue = convert((Double[]) value);
			} else if (valClass == Float[].class) {
				setValue = convert((Float[]) value);
			} else if (valClass == Long[].class) {
				setValue = convert((Long[]) value);
			} else if (valClass == Short[].class) {
				setValue = convert((Short[]) value);
			} else {
				setValue = convert(value);
			}
		} else {
			setValue = value;
		}
		eobj.eSet(eAttribute, setValue);
	}

	/** Convert to a primitive type */
	private Object convert(Integer[] arr) {
		if (instanceClass != int[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
			return arr;
		}
		final int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].intValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Boolean[] arr) {
		if (instanceClass != boolean[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
			return arr;
		}
		final boolean[] res = new boolean[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].booleanValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Byte[] arr) {
		if (instanceClass != byte[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
			return arr;
		}
		final byte[] res = new byte[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].byteValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Double[] arr) {
		if (instanceClass != double[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
			return arr;
		}
		final double[] res = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].doubleValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Float[] arr) {
		if (instanceClass != float[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
			return arr;
		}
		final float[] res = new float[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].floatValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Long[] arr) {
		if (instanceClass != long[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting [] as instance class but it is: " + instanceClass.getName());
			}
			return arr;
		}
		final long[] res = new long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].longValue();
		}
		return res;
	}

	/** Convert to a primitive type */
	private Object convert(Short[] arr) {
		if (instanceClass != short[].class) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting short[] as instance class but it is: " + instanceClass.getName());
			}
			return arr;
		}
		final short[] res = new short[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i].shortValue();
		}
		return res;
	}

	/** Capature all, do not convert */
	private Object convert(Object arr) {
		if (arr != null && instanceClass != null
				&& (!instanceClass.isPrimitive() || !arr.getClass().isPrimitive())) {
			if (log.isDebugEnabled()) {
				log.debug("Expecting " + instanceClass.getName() + " as instance class but it is: "
						+ arr.getClass().getName());
			}
		}
		return arr;
	}

	/**
	 * Pass in the persistion options.
	 */
	public void setPersistenceOptions(PersistenceOptions po) {
		handleUnsetAsNull = po.getHandleUnsetAsNull();
		convertUnsetToNull = po.getConvertUnsetToNull();
	}

	public boolean isId() {
		return isId;
	}

	public void setId(boolean isId) {
		this.isId = isId;
	}

}