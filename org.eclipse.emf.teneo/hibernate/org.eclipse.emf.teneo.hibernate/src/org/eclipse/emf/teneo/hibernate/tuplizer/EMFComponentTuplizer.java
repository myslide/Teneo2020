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
 * $Id: EMFComponentTuplizer.java,v 1.14 2010/08/18 11:50:38 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.tuplizer;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.ERuntime;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.Property;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.component.AbstractComponentTuplizer;

/**
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.14 $
 */

public class EMFComponentTuplizer extends AbstractComponentTuplizer {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 6316160569897347041L;

	private EClass eClass;

	/** The logger */
	// private static Log log = LogFactory.getLog(EMFComponentTuplizer.class);
	/** Constructor */
	public EMFComponentTuplizer(Component component) {
		super(component);
	}

	private EClass getEClass(Component component) {
		if (eClass == null) {
			final HbDataStore ds = HbHelper.INSTANCE.getDataStore(component);
			eClass = ds.toEClass(component.getComponentClassName());
			if (eClass == null) {
				eClass = ERuntime.INSTANCE.getEClass(component.getComponentClass());
			}
			if (eClass == null) {
				eClass = HbUtil.getEClassFromMeta(component);
			}
		}
		if (eClass == null) {
			throw new HbMapperException("No eclass found for entityname: "
					+ component.getComponentClassName());
		}
		return eClass;
	}

	/** Creates an EMF Instantiator */
	protected Instantiator buildInstantiator(Component component, Property property) {
		return buildInstantiator(component);
	}

	/** Creates an EMF Instantiator */
	@Override
	protected Instantiator buildInstantiator(Component component) {
		return new EMFInstantiator(getEClass(component), component);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.AbstractEntityTuplizer#buildPropertyGetter(org.hibernate
	 * .mapping.Property, org.hibernate.mapping.PersistentClass)
	 */
	@Override
	protected Getter buildGetter(Component component, Property mappedProperty) {
		return getPropertyAccessor(mappedProperty, component).getGetter(null, mappedProperty.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.AbstractEntityTuplizer#buildPropertySetter(org.hibernate
	 * .mapping.Property, org.hibernate.mapping.PersistentClass)
	 */
	@Override
	protected Setter buildSetter(Component component, Property mappedProperty) {
		return getPropertyAccessor(mappedProperty, component).getSetter(null, mappedProperty.getName());
	}

	/** Returns the correct accessor on the basis of the type of property */
	public PropertyAccessor getPropertyAccessor(Property mappedProperty, Component comp) {
		final HbDataStore ds = HbHelper.INSTANCE.getDataStore(comp);
		return HbUtil.getPropertyAccessor(mappedProperty, ds, comp.getComponentClassName(),
				getEClass(comp));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.Tuplizer#getMappedClass()
	 */
	public Class<?> getMappedClass() {
		return EObject.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.ComponentTuplizer#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object component) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.ComponentTuplizer#hasParentProperty()
	 */
	@Override
	public boolean hasParentProperty() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.ComponentTuplizer#isMethodOf(java.lang.reflect.Method)
	 */
	@Override
	public boolean isMethodOf(Method method) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.ComponentTuplizer#setParent(java.lang.Object, java.lang.Object,
	 * org.hibernate.engine.SessionFactoryImplementor)
	 */
	@Override
	public void setParent(Object component, Object parent, SessionFactoryImplementor factory) {
	}
}