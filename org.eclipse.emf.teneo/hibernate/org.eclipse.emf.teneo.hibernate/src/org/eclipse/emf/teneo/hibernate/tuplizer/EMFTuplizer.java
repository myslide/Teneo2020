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
 * $Id: EMFTuplizer.java,v 1.26 2011/02/21 05:06:13 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.tuplizer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.teneo.Constants;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbStoreException;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.eclipse.emf.teneo.hibernate.mapping.eav.EAVInstantiator;
import org.eclipse.emf.teneo.hibernate.mapping.eav.EAVPropertyHandler;
import org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierCacheHandler;
import org.eclipse.emf.teneo.hibernate.mapping.internal.TeneoInternalEObject;
import org.hibernate.EntityMode;
import org.hibernate.EntityNameResolver;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Subclass;
import org.hibernate.metamodel.binding.AttributeBinding;
import org.hibernate.metamodel.binding.EntityBinding;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.entity.AbstractEntityTuplizer;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.type.CompositeType;

/**
 * Overrides the get and setidentifier methods to get the identifier from an internal cache instead
 * of from the EMF object itself. The same behavior for the getVersion methods. Also a specific
 * object instantiator is used to make use of the emf efactories.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.26 $
 */

public class EMFTuplizer extends AbstractEntityTuplizer {

	/** The logger */
	private static Log log = LogFactory.getLog(EMFTuplizer.class);

	/**
	 * The mapped class, defaults to EObject for entities and to the real impl class for mapped
	 * classes
	 */
	private Class<?> mappedClass;

	private PersistentClass persistentClass;

	/** The entitymetamodel for which this is all done */
	// private final EntityMetamodel theEntityMetamodel;
	/** Constructor */
	public EMFTuplizer(EntityMetamodel entityMetamodel, PersistentClass mappedEntity) {
		super(entityMetamodel, mappedEntity);
		// theEntityMetamodel = entityMetamodel;
		if (mappedEntity.getMappedClass() != null) {
			mappedClass = mappedEntity.getMappedClass();
		} else {
			mappedClass = EObject.class;
		}
		persistentClass = mappedEntity;
	}

	/**
	 * First checks the id cache and if not found uses the superclass.
	 */
	@Override
	public Serializable getIdentifier(Object object) throws HibernateException {
		Serializable id = (Serializable) IdentifierCacheHandler.getInstance().getID(object);
		if (id != null) {
			return id;
		}
		return super.getIdentifier(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.entity.EntityTuplizer#determineConcreteSubclassEntityName
	 * (java.lang.Object, org.hibernate.engine.SessionFactoryImplementor)
	 */
	public String determineConcreteSubclassEntityName(Object entityInstance,
			SessionFactoryImplementor factory) {

		if (entityInstance instanceof EObject) {
			EObject instance = (EObject) entityInstance;
			final HbDataStore ds = HbHelper.INSTANCE.getDataStore(persistentClass);
			return ds.toEntityName(instance.eClass());
		}

		final Class<?> concreteEntityClass = entityInstance.getClass();
		if (concreteEntityClass == getMappedClass()) {
			return getEntityName();
		} else {
			String entityName = getEntityMetamodel().findEntityNameByEntityClass(concreteEntityClass);
			if (entityName == null) {
				throw new HibernateException("Unable to resolve entity name from Class ["
						+ concreteEntityClass.getName() + "]" + " expected instance/subclass of ["
						+ getEntityName() + "]");
			}
			return entityName;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.entity.EntityTuplizer#getEntityNameResolvers()
	 */
	public EntityNameResolver[] getEntityNameResolvers() {
		final HbDataStore ds = HbHelper.INSTANCE.getDataStore(persistentClass);
		return new EntityNameResolver[] { ds.getEntityNameResolver() };
	}

	/**
	 * Uses the identifiercache to get the version.
	 */
	@Override
	public Object getVersion(Object object) throws HibernateException {
		final Object version = super.getVersion(object);
		if (version != null) {
			return version;
		}

		return IdentifierCacheHandler.getInstance().getVersion(object);
	}

	/**
	 * Sets the identifier in the cache.
	 */
	@Override
	public void setIdentifier(Object object, Serializable id) throws HibernateException {
		IdentifierCacheHandler.getInstance().setID(object, id);
		super.setIdentifier(object, id);
	}

	/** Creates an EMF Instantiator */
	@Override
	protected Instantiator buildInstantiator(PersistentClass persistentClass) {
		if (persistentClass.getEntityName().equals(Constants.EAV_EOBJECT_ENTITY_NAME)) {
			return new EAVInstantiator();
		}
		final HbDataStore ds = HbHelper.INSTANCE.getDataStore(persistentClass);
		final EClass eclass = ds.toEClass(persistentClass.getEntityName());
		if (eclass == null) {
			throw new HbMapperException("No eclass found for entityname: "
					+ persistentClass.getEntityName());
		}
		return new EMFInstantiator(eclass, persistentClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.AbstractEntityTuplizer#buildPropertyGetter(org.hibernate
	 * .mapping.Property , org.hibernate.mapping.PersistentClass)
	 */
	@Override
	protected Getter buildPropertyGetter(Property mappedProperty, PersistentClass mappedEntity) {
		if (HbUtil.isEAVMapped(mappedEntity)
				&& mappedProperty.getName().equals(Constants.EAV_EOBJECT_VALUES)) {
			final HbDataStore ds = HbHelper.INSTANCE.getDataStore(mappedEntity);
			final Getter getter = mappedProperty.getGetter(EObjectImpl.class);
			if (getter instanceof EAVPropertyHandler) {
				((EAVPropertyHandler) getter).setHbDataStore(ds);
			}
			return getter;
		}
		return getPropertyAccessor(mappedProperty, mappedEntity).getGetter(null,
				mappedProperty.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.AbstractEntityTuplizer#buildPropertySetter(org.hibernate
	 * .mapping.Property , org.hibernate.mapping.PersistentClass)
	 */
	@Override
	protected Setter buildPropertySetter(Property mappedProperty, PersistentClass mappedEntity) {
		if (HbUtil.isEAVMapped(mappedEntity)
				&& mappedProperty.getName().equals(Constants.EAV_EOBJECT_VALUES)) {
			final HbDataStore ds = HbHelper.INSTANCE.getDataStore(mappedEntity);
			final Setter setter = mappedProperty.getSetter(EObjectImpl.class);
			if (setter instanceof EAVPropertyHandler) {
				((EAVPropertyHandler) setter).setHbDataStore(ds);
			}
			return setter;
		}
		return getPropertyAccessor(mappedProperty, mappedEntity).getSetter(null,
				mappedProperty.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.hibernate.tuple.AbstractEntityTuplizer#buildProxyFactory(org. hibernate.mapping.
	 * PersistentClass, org.hibernate.property.Getter, org.hibernate.property.Setter)
	 */
	@Override
	protected ProxyFactory buildProxyFactory(PersistentClass persistentClass, Getter idGetter,
			Setter idSetter) {
		if (persistentClass.getProxyInterface() == null) { // an entity, no
			// proxy
			return null;
		}

		final HbDataStore ds = HbHelper.INSTANCE.getDataStore(persistentClass);
		final EClass eclass = ds.toEClass(persistentClass.getEntityName());
		if (eclass == null
				&& !persistentClass.getEntityName().equals(Constants.EAV_EOBJECT_ENTITY_NAME)) {
			throw new HbMapperException("No eclass found for entityname: "
					+ persistentClass.getEntityName());
		}

		// get all the interfaces from the main class, add the real interface
		// first
		final Set<Class<?>> proxyInterfaces = new LinkedHashSet<Class<?>>();
		final Class<?> pInterface = persistentClass.getProxyInterface();
		if (pInterface != null && pInterface.isInterface()) {
			proxyInterfaces.add(pInterface);
		}
		Class<?> mappedClass = persistentClass.getMappedClass();
		if (mappedClass == null) {
			mappedClass = DynamicEObjectImpl.class;
		}
		if (mappedClass.isInterface()) {
			proxyInterfaces.add(mappedClass);
		}
		proxyInterfaces.add(HibernateProxy.class);
		proxyInterfaces.add(TeneoInternalEObject.class);

		for (Class<?> interfaces : mappedClass.getInterfaces()) {
			proxyInterfaces.add(interfaces);
		}

		// iterate over all subclasses and add them also
		final Iterator<?> iter = persistentClass.getSubclassIterator();
		while (iter.hasNext()) {
			final Subclass subclass = (Subclass) iter.next();
			final Class<?> subclassProxy = subclass.getProxyInterface();
			final Class<?> subclassClass = subclass.getMappedClass();
			if (subclassProxy != null && subclassClass != null && !subclassClass.equals(subclassProxy)) {
				proxyInterfaces.add(subclassProxy);
			}
		}

		// get the idgettters/setters
		final Method theIdGetterMethod = idGetter == null ? null : idGetter.getMethod();
		final Method theIdSetterMethod = idSetter == null ? null : idSetter.getMethod();

		final Method proxyGetIdentifierMethod = theIdGetterMethod == null || pInterface == null ? null
				: ReflectHelper.getMethod(pInterface, theIdGetterMethod);
		final Method proxySetIdentifierMethod = theIdSetterMethod == null || pInterface == null ? null
				: ReflectHelper.getMethod(pInterface, theIdSetterMethod);

		ProxyFactory pf = Environment.getBytecodeProvider().getProxyFactoryFactory()
				.buildProxyFactory();
		try {
			pf.postInstantiate(getEntityName(), mappedClass, proxyInterfaces, proxyGetIdentifierMethod,
					proxySetIdentifierMethod,
					persistentClass.hasEmbeddedIdentifier() ? (CompositeType) persistentClass.getIdentifier()
							.getType() : null);
		} catch (HbStoreException e) {
			log.warn("could not create proxy factory for:" + getEntityName(), e);
			pf = null;
		}
		return pf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.AbstractEntityTuplizer#getEntityMode()
	 */
	public EntityMode getEntityMode() {
		return EntityMode.POJO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.EntityTuplizer#getConcreteProxyClass()
	 */
	public Class<?> getConcreteProxyClass() {
		return EObject.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.EntityTuplizer#isInstrumented()
	 */
	public boolean isInstrumented() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.tuple.Tuplizer#getMappedClass()
	 */
	public Class<?> getMappedClass() {
		return mappedClass;
	}

	/** Returns the correct accessor on the basis of the type of property */
	protected PropertyAccessor getPropertyAccessor(Property mappedProperty, PersistentClass pc) {
		final HbDataStore ds = HbHelper.INSTANCE.getDataStore(pc);
		return HbUtil.getPropertyAccessor(mappedProperty, ds, pc.getEntityName(), null);
	}

	@Override
	protected Getter buildPropertyGetter(AttributeBinding mappedProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Setter buildPropertySetter(AttributeBinding mappedProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Instantiator buildInstantiator(EntityBinding mappingInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProxyFactory buildProxyFactory(EntityBinding mappingInfo, Getter idGetter,
			Setter idSetter) {
		// TODO Auto-generated method stub
		return null;
	}

}