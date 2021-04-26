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
 * $Id: EClassAnnotator.java,v 1.20 2009/10/31 07:10:35 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue;
import org.eclipse.emf.teneo.annotations.pannotation.Entity;
import org.eclipse.emf.teneo.annotations.pannotation.Inheritance;
import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory;
import org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable;
import org.eclipse.emf.teneo.annotations.pannotation.Table;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.mapping.strategy.StrategyUtil;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Sets the annotation on an eclass.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.20 $
 */

public class EClassAnnotator extends AbstractAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(EClassAnnotator.class);

	private InheritanceType optionDefaultInheritanceMapping = InheritanceType.SINGLE_TABLE;

	// The list of processed eclasses, is used to ensure that a superclass is
	// done before a subclass
	private final ArrayList<PAnnotatedEClass> processedAClasses = new ArrayList<PAnnotatedEClass>();

	private EFeatureAnnotator eFeatureAnnotator = null;

	/**
	 * Returns the annotated version of an EClass, Returns false if no efeatures of this eclass should
	 * be annotated, true if its features can be annotated.
	 */
	protected boolean annotate(PAnnotatedEClass aClass) {
		if (aClass == null) {
			throw new StoreAnnotationsException(
					"Mapping Exception, no Annotated Class for EClass, "
							+ "a common cause is that you did not register all EPackages in the DataStore/Helper Class. "
							+ "When there are references between EClasses in different EPackages then they need to be handled in one DataStore/Helper Class.");
		}

		final EClass eclass = (EClass) aClass.getModelElement();

		// check if already processed
		if (processedAClasses.contains(aClass)) {
			return false;
		}

		// do not process the document root
		if (!getPersistenceOptions().isMapDocumentRoot()
				&& ExtendedMetaData.INSTANCE.isDocumentRoot(eclass)) {
			return false;
		}

		log.debug("Creating mapping for eclass " + eclass.getName());

		// first do the superclasses
		for (EClass superEclass : aClass.getModelEClass().getESuperTypes()) {
			final PAnnotatedEClass superAClass = aClass.getPaModel().getPAnnotated(superEclass);
			if (superAClass == null) {
				throw new StoreAnnotationsException(
						"Mapping Exception, no Annotated Class for EClass: "
								+ superEclass.getName()
								+ " a common cause is that you did not register all EPackages in the DataStore/Helper Class. "
								+ "When there are references between EClasses in different EPackages then they need to be handled in one DataStore/Helper Class.");
			}
			if (!processedAClasses.contains(superAClass)) {
				annotate(superAClass);
				if (superAClass.getEavMapping() != null) {
					aClass.setEavMapping(PannotationFactory.eINSTANCE.createEAVMapping());
				}
			}
		}

		if (getPersistenceOptions().isEAVMapping() && aClass.getNoEAVMapping() == null) {
			aClass.setEavMapping(PannotationFactory.eINSTANCE.createEAVMapping());
		}

		// prevent eav mapping for audit entry classes
		if (StoreUtil.isAuditEntryEClass(eclass)) {
			aClass.setNoEAVMapping(PannotationFactory.eINSTANCE.createNoEAVMapping());
			aClass.setEavMapping(null);
		}

		if (log.isDebugEnabled()) {
			log.debug(" Adding default annotations for EClass: " + aClass.getModelElement().getName());
		}

		processedAClasses.add(aClass);

		if (log.isDebugEnabled()) {
			log.debug("Setting the superentity of the eclass");
		}
		setSuperEntity(aClass);
		final boolean isInheritanceRoot = aClass.getPaSuperEntity() == null
				|| aClass.getPaSuperEntity().getMappedSuperclass() != null; // last

		// force single table
		if (isInheritanceRoot && aClass.getEavMapping() != null) {
			final Inheritance inheritance = PannotationFactory.eINSTANCE.createInheritance();
			inheritance.setStrategy(InheritanceType.SINGLE_TABLE);
			aClass.setInheritance(inheritance);
		}

		// A not mappable type will not get an entity annotation.
		// Even the features of non-mappable types are mapped because
		// the efeatures can be inherited through multiple inheritance
		final boolean mappable = isMappableAnnotatedClass(aClass);

		// add entity or set entity name
		if (mappable && aClass.getEntity() == null && aClass.getEmbeddable() == null) {
			Entity entity = getFactory().createEntity();
			entity.setEModelElement(eclass);
			aClass.setEntity(entity);
		}
		if (aClass.getEntity() != null && aClass.getEntity().getName() == null) {
			aClass.getEntity().setName(getEntityNameStrategy().toEntityName(eclass));
		}
		// if (aClass.getEavMapping() != null &&
		// EModelResolver.instance().getJavaClass(aClass.getModelEClass()) !=
		// null) {
		// aClass.getEntity().setName(EModelResolver.instance().getJavaClass(aClass.getModelEClass()).getName());
		// }

		// get the inheritance from the supertype or use the global inheritance
		// setting
		// Note only an 'entitied' root gets an inheritance annotation. This is
		// according to the spec.
		final InheritanceType inheritanceType;
		if (aClass.getInheritance() != null) {
			inheritanceType = aClass.getInheritance().getStrategy();
		} else {
			// get the inheritance from the supers, if defined there
			final Inheritance inheritanceFromSupers = getInheritanceFromSupers(aClass);
			inheritanceType = inheritanceFromSupers != null ? inheritanceFromSupers.getStrategy()
					: optionDefaultInheritanceMapping;
			// if this is the root then add a specific inheritance annotation
			if (isInheritanceRoot) {
				final Inheritance inheritance = getFactory().createInheritance();
				inheritance.setStrategy(inheritanceType);
				inheritance.setEModelElement(eclass);
				aClass.setInheritance(inheritance);
			}
		}

		// add PrimaryKeyJoinColumn in case of a joined
		if (!isInheritanceRoot && inheritanceType.equals(InheritanceType.JOINED)
				&& aClass.getPrimaryKeyJoinColumns().size() == 0) {
			ArrayList<String> idFeatures = new ArrayList<String>();
			PAnnotatedEClass aSuperClass = null;
			for (EClass eSuperClass : aClass.getModelEClass().getESuperTypes()) {
				aSuperClass = getAnnotatedModel().getPAnnotated(eSuperClass);
				idFeatures.addAll(StrategyUtil.getIDFeaturesNames(aSuperClass, getPersistenceOptions()
						.getDefaultIDFeatureName(), getPersistenceOptions()));
				if (!idFeatures.isEmpty()) {
					break;
				}
			}

			for (String idFeature : idFeatures) {
				final PrimaryKeyJoinColumn pkjc = getFactory().createPrimaryKeyJoinColumn();
				pkjc.setName(getSqlNameStrategy().getPrimaryKeyJoinColumnName(aSuperClass, idFeature));
				aClass.getPrimaryKeyJoinColumns().add(pkjc);
			}
		}

		// add the table annotation or the name annotation of the table
		// only do this if this is the root in case of singletable or when this
		// is the joined table strategy
		if (aClass.getTable() == null
				&& ((isInheritanceRoot && inheritanceType.equals(InheritanceType.SINGLE_TABLE))
						|| inheritanceType.equals(InheritanceType.JOINED) || inheritanceType
							.equals(InheritanceType.TABLE_PER_CLASS))) {

			// check if there is a table annotation on a higher level
			Table table = getTableFromMappedSuperClass(aClass);
			if (table == null) {
				table = getFactory().createTable();
			}
			table.setEModelElement(eclass);
			// name is set in next step
			aClass.setTable(table);
		}
		if (aClass.getTable() != null && aClass.getTable().getName() == null) {
			aClass.getTable().setName(getSqlNameStrategy().getTableName(aClass));
		}

		// set the schema
		if (aClass.getTable() != null
				&& aClass.getTable().getSchema() == null
				&& (eclass.getName().equals("TeneoAuditEntry")
						|| eclass.getName().equals("TeneoAuditCommitInfo") || StoreUtil
							.isAuditEntryEClass(eclass))
				&& getPersistenceOptions().getAuditingDBSchema() != null
				&& getPersistenceOptions().getAuditingDBSchema().length() > 0) {
			aClass.getTable().setSchema(getPersistenceOptions().getAuditingDBSchema());
		}

		if (addDiscriminator(aClass)) {
			// For hibernate as well as jpox the discriminator column is only
			// required for single table, the ejb3 spec does not make a clear
			// statement about the requirement to also have a discriminator
			// column for joined
			if (isInheritanceRoot && aClass.getDiscriminatorColumn() == null
					&& inheritanceType.equals(InheritanceType.SINGLE_TABLE)) {
				// note defaults of primitive types are all defined in the model
				final DiscriminatorColumn dc = getFactory().createDiscriminatorColumn();
				dc.setEModelElement(eclass);
				dc.setName(getSqlNameStrategy().getDiscriminatorColumnName());
				aClass.setDiscriminatorColumn(dc);
			}
			if (aClass.getDiscriminatorColumn() != null) {
				if (aClass.getDiscriminatorColumn().getColumn() == null) {
					final DiscriminatorColumn dc = aClass.getDiscriminatorColumn();
					final Column col = getFactory().createColumn();
					dc.setColumn(col);
					col.setName(dc.getName());
					col.setIndex(aClass.getTable().getName() + dc.getName());
					col.setNullable(false);
				}
				if (aClass.getDiscriminatorColumn().getColumn().getName() == null) {
					aClass.getDiscriminatorColumn().getColumn()
							.setName(aClass.getDiscriminatorColumn().getName());
				}
			}

			// add a discriminator value
			if (aClass.getDiscriminatorValue() == null
					&& inheritanceType.equals(InheritanceType.SINGLE_TABLE)) {
				final DiscriminatorValue dv = getFactory().createDiscriminatorValue();

				final DiscriminatorColumn dc = getDiscriminatorColumn(aClass);
				if (dc != null && dc.getDiscriminatorType() != null
						&& dc.getDiscriminatorType().getValue() == DiscriminatorType.INTEGER_VALUE) {

					// use the entityname to translate to an int value,
					// hopefully hashcode is more or less unique...
					final String entityName = getEntityName(eclass);
					log.warn("Generating an integer discriminator value for entity "
							+ entityName
							+ ". The hashcode of the entityName is used as the discriminatorvalue. This may not be unique! To ensure uniques you should set a @DiscriminatorValue annotation");
					dv.setValue("" + entityName.hashCode());
				} else {
					dv.setValue(getEntityName(eclass));
				}
				dv.setEModelElement(eclass);
				aClass.setDiscriminatorValue(dv);
			}
		}

		// Add default PkJoinColumns for SecondaryTables.
		for (SecondaryTable secondaryTable : aClass.getSecondaryTables()) {
			final EList<PrimaryKeyJoinColumn> pkJoinColumns = secondaryTable.getPkJoinColumns();
			if (pkJoinColumns.size() == 0) {
				// No PkJoinColumns configured for this secondary table, so
				// populate with defaults based on the ID
				// attributes of the primary table.
				final List<PAnnotatedEStructuralFeature> aIdFeatures = aClass.getPaIdFeatures();
				for (PAnnotatedEStructuralFeature idef : aIdFeatures) {
					final PrimaryKeyJoinColumn pkJoinColumn = PannotationFactory.eINSTANCE
							.createPrimaryKeyJoinColumn();
					pkJoinColumn
							.setName(getSqlNameStrategy().getSecondaryTablePrimaryKeyJoinColumnName(idef));
					pkJoinColumns.add(pkJoinColumn);
				}
			}
		}

		for (PAnnotatedEStructuralFeature aStructuralFeature : aClass.getPaEStructuralFeatures()) {
			eFeatureAnnotator.annotate(aStructuralFeature);
		}
		return true;
	}

	protected Table getTableFromMappedSuperClass(PAnnotatedEClass aClass) {
		if (aClass == null) {
			return null;
		}

		PAnnotatedEClass paSuperClass = aClass.getPaSuperEntity();
		if (paSuperClass == null && aClass.getModelEClass().getESuperTypes().size() > 0) {
			final PAnnotatedEClass superAClass = aClass.getPaModel().getPAnnotated(
					aClass.getModelEClass().getESuperTypes().get(0));
			if (superAClass.getMappedSuperclass() != null) {
				paSuperClass = superAClass;
			}
		}

		if (aClass.getMappedSuperclass() == null) {
			return getTableFromMappedSuperClass(paSuperClass);
		}
		if (aClass.getTable() != null) {
			final Table table = EcoreUtil.copy(aClass.getTable());
			table.setName(null);
			return table;
		}
		return getTableFromMappedSuperClass(paSuperClass);
	}

	protected boolean addDiscriminator(PAnnotatedEClass aClass) {
		return true;
	}

	// finds the DiscriminatorColumn in the aClass or its super entities
	protected DiscriminatorColumn getDiscriminatorColumn(PAnnotatedEClass aClass) {
		if (aClass.getDiscriminatorColumn() != null) {
			return aClass.getDiscriminatorColumn();
		}

		// or use aClass.getPaMappedSupers()?
		if (aClass.getPaSuperEntity() != null) {
			return getDiscriminatorColumn(aClass.getPaSuperEntity());
		}
		return null;
	}

	/** Sets the {@link EFeatureAnnotator} */
	@Override
	protected void initialize() {
		super.initialize();
		eFeatureAnnotator = createAnnotator(EFeatureAnnotator.class);
	}

	/**
	 * Returns the inheritance of the passed annotated class or from one of its super annotated class
	 */
	protected Inheritance getInheritanceFromSupers(PAnnotatedEClass childPA) {
		if (childPA == null) {
			return null;
		}
		if (childPA.getInheritance() != null) {
			return childPA.getInheritance();
		}
		return getInheritanceFromSupers(childPA.getPaSuperEntity());
	}

	/** Set the super entity */
	protected void setSuperEntity(PAnnotatedEClass aClass) {
		assert (aClass.getPaSuperEntity() == null);
		final EClass eclass = aClass.getModelEClass();
		if (eclass.getESuperTypes().size() == 0) {
			return;
		}
		// check for overridden using extends
		if (aClass.getEntity() != null && aClass.getEntity().getExtends() != null) {
			final EClass superEClass = aClass.getPaModel().getEClass(aClass.getEntity().getExtends());
			final PAnnotatedEClass superAClass = aClass.getPaModel().getPAnnotated(superEClass);
			if (!processedAClasses.contains(superAClass)) {
				annotate(superAClass);
			}
			aClass.setPaSuperEntity(superAClass);
			return;
		}

		final PAnnotatedEClass superAClass = aClass.getPaModel().getPAnnotated(
				eclass.getESuperTypes().get(0));
		if (superAClass.getEntity() != null || superAClass.getMappedSuperclass() != null) {
			aClass.setPaSuperEntity(superAClass);
		}
	}

	/** Returns fals for jpox and true for hibernate */
	protected boolean isMappableAnnotatedClass(PAnnotatedEClass aClass) {

		final EClass eclass = aClass.getModelEClass();

		if (!mapInterfaceEClass() && eclass.isInterface()) {
			if (log.isDebugEnabled()) {
				log.debug("Not mapping interfaces and this is an interface eclass, ignore it");
			}
			return false;
		}

		if (aClass.getTransient() != null) {
			return false; // not mappable
		}

		if (!getPersistenceOptions().isSetEntityAutomatically() && aClass.getEntity() == null
				&& aClass.getEmbeddable() == null) {
			if (log.isDebugEnabled()) {
				log.debug("Entities are not added automatically and this eclass: "
						+ aClass.getModelEClass().getName() + " does not have an entity/embeddable annotation.");
			}
			return false;
		}

		// ignore these
		if (!mapMappedSuperEClass() && aClass.getMappedSuperclass() != null) {
			if (aClass.getEntity() != null) {
				log.warn("EClass "
						+ eclass.getName()
						+ " has entity as well as mappedsuperclass annotation, following mappedsuperclass annotation, therefore ignoring it for the mapping");
			}
			return false;
		}

		return true;
	}

	/**
	 * Map Interface EClasses, default false, overridden by hibernate to return true
	 */
	protected boolean mapInterfaceEClass() {
		return false;
	}

	/** Map a mapped superclass, this differs for jpox and hibernate */
	protected boolean mapMappedSuperEClass() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.emf.teneo.annotations.mapper.AbstractAnnotator#
	 * setPersistenceOptions(org.eclipse .emf.teneo.PersistenceOptions)
	 */
	@Override
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		super.setPersistenceOptions(persistenceOptions);
		if (persistenceOptions.getInheritanceMapping() != null) {
			InheritanceType it = InheritanceType.get(persistenceOptions.getInheritanceMapping());
			if (it == InheritanceType.JOINED) {
				optionDefaultInheritanceMapping = InheritanceType.JOINED;
				if (log.isDebugEnabled()) {
					log.debug("Option inheritance: joined");
				}
			} else if (it == InheritanceType.SINGLE_TABLE) {
				optionDefaultInheritanceMapping = InheritanceType.SINGLE_TABLE;
				if (log.isDebugEnabled()) {
					log.debug("Option inheritance: single");
				}
			} else if (it == InheritanceType.TABLE_PER_CLASS) {
				optionDefaultInheritanceMapping = InheritanceType.TABLE_PER_CLASS;
				if (log.isDebugEnabled()) {
					log.debug("Option inheritance: table per class");
				}
			} else {
				throw new IllegalArgumentException("Inheritance mapping option: "
						+ persistenceOptions.getInheritanceMapping() + " is not supported");
			}
		}
	}

}
