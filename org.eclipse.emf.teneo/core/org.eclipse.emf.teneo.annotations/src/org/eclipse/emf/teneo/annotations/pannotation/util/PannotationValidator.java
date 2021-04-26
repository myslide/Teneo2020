/**
 * <copyright>
 * </copyright>
 *
 * $Id: PannotationValidator.java,v 1.37 2011/02/21 06:40:10 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride;
import org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride;
import org.eclipse.emf.teneo.annotations.pannotation.Basic;
import org.eclipse.emf.teneo.annotations.pannotation.Cacheable;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.CollectionTable;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue;
import org.eclipse.emf.teneo.annotations.pannotation.EAVMapping;
import org.eclipse.emf.teneo.annotations.pannotation.ElementCollection;
import org.eclipse.emf.teneo.annotations.pannotation.Embeddable;
import org.eclipse.emf.teneo.annotations.pannotation.Embedded;
import org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId;
import org.eclipse.emf.teneo.annotations.pannotation.Entity;
import org.eclipse.emf.teneo.annotations.pannotation.EnumType;
import org.eclipse.emf.teneo.annotations.pannotation.Enumerated;
import org.eclipse.emf.teneo.annotations.pannotation.External;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.ForeignKey;
import org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue;
import org.eclipse.emf.teneo.annotations.pannotation.GenerationType;
import org.eclipse.emf.teneo.annotations.pannotation.Id;
import org.eclipse.emf.teneo.annotations.pannotation.IdClass;
import org.eclipse.emf.teneo.annotations.pannotation.Inheritance;
import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.ListIndexColumn;
import org.eclipse.emf.teneo.annotations.pannotation.Lob;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToMany;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToOne;
import org.eclipse.emf.teneo.annotations.pannotation.MapKey;
import org.eclipse.emf.teneo.annotations.pannotation.MapKeyClass;
import org.eclipse.emf.teneo.annotations.pannotation.MapKeyColumn;
import org.eclipse.emf.teneo.annotations.pannotation.MapKeyEnumerated;
import org.eclipse.emf.teneo.annotations.pannotation.MapKeyJoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.MapKeyTemporal;
import org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass;
import org.eclipse.emf.teneo.annotations.pannotation.MapsId;
import org.eclipse.emf.teneo.annotations.pannotation.NoEAVMapping;
import org.eclipse.emf.teneo.annotations.pannotation.OneToMany;
import org.eclipse.emf.teneo.annotations.pannotation.OneToOne;
import org.eclipse.emf.teneo.annotations.pannotation.OptimizerType;
import org.eclipse.emf.teneo.annotations.pannotation.OrderBy;
import org.eclipse.emf.teneo.annotations.pannotation.OrderColumn;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.Table;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.Temporal;
import org.eclipse.emf.teneo.annotations.pannotation.TemporalType;
import org.eclipse.emf.teneo.annotations.pannotation.Transient;
import org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint;
import org.eclipse.emf.teneo.annotations.pannotation.Version;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage
 * @generatedNOT
 */
@SuppressWarnings("unused")
public class PannotationValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final PannotationValidator INSTANCE = new PannotationValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
	 * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.teneo.annotations.pannotation";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written
	 * constants. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written
	 * constants in a derived class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PannotationValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return PannotationPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		switch (classifierID) {
		case PannotationPackage.PANNOTATION:
			return validatePAnnotation((PAnnotation) value, diagnostics, context);
		case PannotationPackage.ATTRIBUTE_OVERRIDE:
			return validateAttributeOverride((AttributeOverride) value, diagnostics, context);
		case PannotationPackage.ASSOCIATION_OVERRIDE:
			return validateAssociationOverride((AssociationOverride) value, diagnostics, context);
		case PannotationPackage.BASIC:
			return validateBasic((Basic) value, diagnostics, context);
		case PannotationPackage.CACHEABLE:
			return validateCacheable((Cacheable) value, diagnostics, context);
		case PannotationPackage.COLLECTION_TABLE:
			return validateCollectionTable((CollectionTable) value, diagnostics, context);
		case PannotationPackage.COLUMN:
			return validateColumn((Column) value, diagnostics, context);
		case PannotationPackage.DISCRIMINATOR_COLUMN:
			return validateDiscriminatorColumn((DiscriminatorColumn) value, diagnostics, context);
		case PannotationPackage.DISCRIMINATOR_VALUE:
			return validateDiscriminatorValue((DiscriminatorValue) value, diagnostics, context);
		case PannotationPackage.ELEMENT_COLLECTION:
			return validateElementCollection((ElementCollection) value, diagnostics, context);
		case PannotationPackage.EMBEDDABLE:
			return validateEmbeddable((Embeddable) value, diagnostics, context);
		case PannotationPackage.EMBEDDED:
			return validateEmbedded((Embedded) value, diagnostics, context);
		case PannotationPackage.EMBEDDED_ID:
			return validateEmbeddedId((EmbeddedId) value, diagnostics, context);
		case PannotationPackage.ENTITY:
			return validateEntity((Entity) value, diagnostics, context);
		case PannotationPackage.ENUMERATED:
			return validateEnumerated((Enumerated) value, diagnostics, context);
		case PannotationPackage.GENERATED_VALUE:
			return validateGeneratedValue((GeneratedValue) value, diagnostics, context);
		case PannotationPackage.ID:
			return validateId((Id) value, diagnostics, context);
		case PannotationPackage.ID_CLASS:
			return validateIdClass((IdClass) value, diagnostics, context);
		case PannotationPackage.INHERITANCE:
			return validateInheritance((Inheritance) value, diagnostics, context);
		case PannotationPackage.JOIN_COLUMN:
			return validateJoinColumn((JoinColumn) value, diagnostics, context);
		case PannotationPackage.JOIN_TABLE:
			return validateJoinTable((JoinTable) value, diagnostics, context);
		case PannotationPackage.LOB:
			return validateLob((Lob) value, diagnostics, context);
		case PannotationPackage.MANY_TO_MANY:
			return validateManyToMany((ManyToMany) value, diagnostics, context);
		case PannotationPackage.MANY_TO_ONE:
			return validateManyToOne((ManyToOne) value, diagnostics, context);
		case PannotationPackage.MAP_KEY:
			return validateMapKey((MapKey) value, diagnostics, context);
		case PannotationPackage.MAP_KEY_CLASS:
			return validateMapKeyClass((MapKeyClass) value, diagnostics, context);
		case PannotationPackage.MAP_KEY_COLUMN:
			return validateMapKeyColumn((MapKeyColumn) value, diagnostics, context);
		case PannotationPackage.MAP_KEY_ENUMERATED:
			return validateMapKeyEnumerated((MapKeyEnumerated) value, diagnostics, context);
		case PannotationPackage.MAP_KEY_JOIN_COLUMN:
			return validateMapKeyJoinColumn((MapKeyJoinColumn) value, diagnostics, context);
		case PannotationPackage.MAP_KEY_TEMPORAL:
			return validateMapKeyTemporal((MapKeyTemporal) value, diagnostics, context);
		case PannotationPackage.MAPPED_SUPERCLASS:
			return validateMappedSuperclass((MappedSuperclass) value, diagnostics, context);
		case PannotationPackage.MAPS_ID:
			return validateMapsId((MapsId) value, diagnostics, context);
		case PannotationPackage.ONE_TO_MANY:
			return validateOneToMany((OneToMany) value, diagnostics, context);
		case PannotationPackage.ONE_TO_ONE:
			return validateOneToOne((OneToOne) value, diagnostics, context);
		case PannotationPackage.ORDER_BY:
			return validateOrderBy((OrderBy) value, diagnostics, context);
		case PannotationPackage.ORDER_COLUMN:
			return validateOrderColumn((OrderColumn) value, diagnostics, context);
		case PannotationPackage.PRIMARY_KEY_JOIN_COLUMN:
			return validatePrimaryKeyJoinColumn((PrimaryKeyJoinColumn) value, diagnostics, context);
		case PannotationPackage.SECONDARY_TABLE:
			return validateSecondaryTable((SecondaryTable) value, diagnostics, context);
		case PannotationPackage.SEQUENCE_GENERATOR:
			return validateSequenceGenerator((SequenceGenerator) value, diagnostics, context);
		case PannotationPackage.TABLE:
			return validateTable((Table) value, diagnostics, context);
		case PannotationPackage.TABLE_GENERATOR:
			return validateTableGenerator((TableGenerator) value, diagnostics, context);
		case PannotationPackage.TEMPORAL:
			return validateTemporal((Temporal) value, diagnostics, context);
		case PannotationPackage.TRANSIENT:
			return validateTransient((Transient) value, diagnostics, context);
		case PannotationPackage.UNIQUE_CONSTRAINT:
			return validateUniqueConstraint((UniqueConstraint) value, diagnostics, context);
		case PannotationPackage.VERSION:
			return validateVersion((Version) value, diagnostics, context);
		case PannotationPackage.FOREIGN_KEY:
			return validateForeignKey((ForeignKey) value, diagnostics, context);
		case PannotationPackage.SEQUENCE_STYLE_GENERATOR:
			return validateSequenceStyleGenerator((SequenceStyleGenerator) value, diagnostics, context);
		case PannotationPackage.EXTERNAL:
			return validateExternal((External) value, diagnostics, context);
		case PannotationPackage.EAV_MAPPING:
			return validateEAVMapping((EAVMapping) value, diagnostics, context);
		case PannotationPackage.NO_EAV_MAPPING:
			return validateNoEAVMapping((NoEAVMapping) value, diagnostics, context);
		case PannotationPackage.LIST_INDEX_COLUMN:
			return validateListIndexColumn((ListIndexColumn) value, diagnostics, context);
		case PannotationPackage.CASCADE_TYPE:
			return validateCascadeType((CascadeType) value, diagnostics, context);
		case PannotationPackage.DISCRIMINATOR_TYPE:
			return validateDiscriminatorType((DiscriminatorType) value, diagnostics, context);
		case PannotationPackage.ENUM_TYPE:
			return validateEnumType((EnumType) value, diagnostics, context);
		case PannotationPackage.FETCH_TYPE:
			return validateFetchType((FetchType) value, diagnostics, context);
		case PannotationPackage.GENERATION_TYPE:
			return validateGenerationType((GenerationType) value, diagnostics, context);
		case PannotationPackage.INHERITANCE_TYPE:
			return validateInheritanceType((InheritanceType) value, diagnostics, context);
		case PannotationPackage.TEMPORAL_TYPE:
			return validateTemporalType((TemporalType) value, diagnostics, context);
		case PannotationPackage.OPTIMIZER_TYPE:
			return validateOptimizerType((OptimizerType) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotation(PAnnotation pAnnotation, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(pAnnotation, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(pAnnotation, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(pAnnotation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the CompatibleEModelElementType constraint of '<em>PAnnotation</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotation_CompatibleEModelElementType(PAnnotation pAnnotation,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "CompatibleEModelElementType",
								getObjectLabel(pAnnotation, context) }, new Object[] { pAnnotation }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the AnnotationIsSupported constraint of '<em>PAnnotation</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotation_AnnotationIsSupported(PAnnotation pAnnotation,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AnnotationIsSupported",
								getObjectLabel(pAnnotation, context) }, new Object[] { pAnnotation }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttributeOverride(AttributeOverride attributeOverride,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(attributeOverride, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(attributeOverride, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(attributeOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(attributeOverride, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(attributeOverride, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAssociationOverride(AssociationOverride associationOverride,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(associationOverride, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(associationOverride, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(associationOverride, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(associationOverride, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(associationOverride, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBasic(Basic basic, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(basic, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(basic, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateBasic_AllowedElementType(basic, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Basic</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateBasic_AllowedElementType(Basic basic, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
								getObjectLabel(basic, context) }, new Object[] { basic }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCacheable(Cacheable cacheable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(cacheable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(cacheable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(cacheable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCollectionTable(CollectionTable collectionTable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(collectionTable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(collectionTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(collectionTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(collectionTable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColumn(Column column, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(column, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateColumn_ScalePrecisionForNumeric(column, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateColumn_LengthForString(column, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ScalePrecisionForNumeric constraint of '<em>Column</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColumn_ScalePrecisionForNumeric(Column column,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "ScalePrecisionForNumeric",
								getObjectLabel(column, context) }, new Object[] { column }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the LengthForString constraint of '<em>Column</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColumn_LengthForString(Column column, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "LengthForString", getObjectLabel(column, context) },
						new Object[] { column }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDiscriminatorColumn(DiscriminatorColumn discriminatorColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(discriminatorColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(discriminatorColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(discriminatorColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(discriminatorColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(discriminatorColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDiscriminatorValue(DiscriminatorValue discriminatorValue,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(discriminatorValue, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(discriminatorValue, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(discriminatorValue, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(discriminatorValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateDiscriminatorValue_NoAbstract(discriminatorValue, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoAbstract constraint of '<em>Discriminator Value</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDiscriminatorValue_NoAbstract(DiscriminatorValue discriminatorValue,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "NoAbstract", getObjectLabel(discriminatorValue, context) },
						new Object[] { discriminatorValue }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateElementCollection(ElementCollection elementCollection,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(elementCollection, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(elementCollection, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(elementCollection, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(elementCollection, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(elementCollection, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbeddable(Embeddable embeddable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(embeddable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(embeddable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEmbeddable_NoInterface(embeddable, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoInterface constraint of '<em>Embeddable</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbeddable_NoInterface(Embeddable embeddable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "NoInterface", getObjectLabel(embeddable, context) },
						new Object[] { embeddable }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbedded(Embedded embedded, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(embedded, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(embedded, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEmbedded_EmbedByValue(embedded, diagnostics, context);
		return result;
	}

	/**
	 * Validates the EmbedByValue constraint of '<em>Embedded</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbedded_EmbedByValue(Embedded embedded, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "EmbedByValue", getObjectLabel(embedded, context) },
						new Object[] { embedded }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbeddedId(EmbeddedId embeddedId, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(embeddedId, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(embeddedId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEmbeddedId_EmbedByValue(embeddedId, diagnostics, context);
		return result;
	}

	/**
	 * Validates the EmbedByValue constraint of '<em>Embedded Id</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEmbeddedId_EmbedByValue(EmbeddedId embeddedId,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "EmbedByValue", getObjectLabel(embeddedId, context) },
						new Object[] { embeddedId }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEntity(Entity entity, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(entity, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEntity_NoInterface(entity, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEntity_NoReservedName(entity, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoInterface constraint of '<em>Entity</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEntity_NoInterface(Entity entity, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "NoInterface", getObjectLabel(entity, context) },
						new Object[] { entity }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the NoReservedName constraint of '<em>Entity</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEntity_NoReservedName(Entity entity, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "NoReservedName", getObjectLabel(entity, context) },
						new Object[] { entity }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEnumerated(Enumerated enumerated, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(enumerated, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(enumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEnumerated_AllowedElementType(enumerated, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Enumerated</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEnumerated_AllowedElementType(Enumerated enumerated,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
								getObjectLabel(enumerated, context) }, new Object[] { enumerated }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateGeneratedValue(GeneratedValue generatedValue, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(generatedValue, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(generatedValue, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(generatedValue, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(generatedValue, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateId(Id id, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(id, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(id, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateId_AllowedElementType(id, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Id</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateId_AllowedElementType(Id id, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
								getObjectLabel(id, context) }, new Object[] { id }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIdClass(IdClass idClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(idClass, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(idClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(idClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateInheritance(Inheritance inheritance, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(inheritance, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(inheritance, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(inheritance, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateJoinColumn(JoinColumn joinColumn, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(joinColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(joinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(joinColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateJoinTable(JoinTable joinTable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(joinTable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(joinTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(joinTable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateLob(Lob lob, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(lob, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(lob, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateLob_AllowedType(lob, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedType constraint of '<em>Lob</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateLob_AllowedType(Lob lob, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "AllowedType", getObjectLabel(lob, context) }, new Object[] { lob },
						context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateManyToMany(ManyToMany manyToMany, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(manyToMany, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(manyToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateManyToMany_IsManyValued(manyToMany, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IsManyValued constraint of '<em>Many To Many</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateManyToMany_IsManyValued(ManyToMany manyToMany,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "IsManyValued", getObjectLabel(manyToMany, context) },
						new Object[] { manyToMany }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateManyToOne(ManyToOne manyToOne, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(manyToOne, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(manyToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateManyToOne_IsNotManyValued(manyToOne, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IsNotManyValued constraint of '<em>Many To One</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateManyToOne_IsNotManyValued(ManyToOne manyToOne,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "IsNotManyValued", getObjectLabel(manyToOne, context) },
						new Object[] { manyToOne }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKey(MapKey mapKey, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKey, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKey, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyClass(MapKeyClass mapKeyClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKeyClass, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKeyClass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKeyClass, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyColumn(MapKeyColumn mapKeyColumn, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKeyColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMapKeyColumn_ScalePrecisionForNumeric(mapKeyColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMapKeyColumn_LengthForString(mapKeyColumn, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ScalePrecisionForNumeric constraint of '<em>Map Key Column</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyColumn_ScalePrecisionForNumeric(MapKeyColumn mapKeyColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "ScalePrecisionForNumeric",
								getObjectLabel(mapKeyColumn, context) }, new Object[] { mapKeyColumn }, context));
			}
			return false;
		}
		return validateColumn_ScalePrecisionForNumeric(mapKeyColumn, diagnostics, context);
	}

	/**
	 * Validates the LengthForString constraint of '<em>Map Key Column</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyColumn_LengthForString(MapKeyColumn mapKeyColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "LengthForString", getObjectLabel(mapKeyColumn, context) },
						new Object[] { mapKeyColumn }, context));
			}
			return false;
		}
		return validateColumn_LengthForString(mapKeyColumn, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyEnumerated(MapKeyEnumerated mapKeyEnumerated,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKeyEnumerated, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKeyEnumerated, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKeyEnumerated, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateEnumerated_AllowedElementType(mapKeyEnumerated, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyJoinColumn(MapKeyJoinColumn mapKeyJoinColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKeyJoinColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKeyJoinColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKeyJoinColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyTemporal(MapKeyTemporal mapKeyTemporal, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapKeyTemporal, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapKeyTemporal, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapKeyTemporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMapKeyTemporal_AllowedElementType(mapKeyTemporal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Map Key Temporal</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapKeyTemporal_AllowedElementType(MapKeyTemporal mapKeyTemporal,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
										getObjectLabel(mapKeyTemporal, context) }, new Object[] { mapKeyTemporal },
								context));
			}
			return false;
		}
		return validateTemporal_AllowedElementType(mapKeyTemporal, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMappedSuperclass(MappedSuperclass mappedSuperclass,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mappedSuperclass, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mappedSuperclass, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mappedSuperclass, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateMappedSuperclass_NoInterface(mappedSuperclass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the NoInterface constraint of '<em>Mapped Superclass</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMappedSuperclass_NoInterface(MappedSuperclass mappedSuperclass,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "NoInterface", getObjectLabel(mappedSuperclass, context) },
						new Object[] { mappedSuperclass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMapsId(MapsId mapsId, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(mapsId, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(mapsId, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(mapsId, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOneToMany(OneToMany oneToMany, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oneToMany, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(oneToMany, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOneToMany_IsManyValued(oneToMany, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IsManyValued constraint of '<em>One To Many</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOneToMany_IsManyValued(OneToMany oneToMany, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "IsManyValued", getObjectLabel(oneToMany, context) },
						new Object[] { oneToMany }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOneToOne(OneToOne oneToOne, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(oneToOne, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(oneToOne, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOneToOne_IsNotManyValued(oneToOne, diagnostics, context);
		return result;
	}

	/**
	 * Validates the IsNotManyValued constraint of '<em>One To One</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOneToOne_IsNotManyValued(OneToOne oneToOne, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "IsNotManyValued", getObjectLabel(oneToOne, context) },
						new Object[] { oneToOne }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderBy(OrderBy orderBy, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(orderBy, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOrderBy_WellFormedOrder(orderBy, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOrderBy_IsManyValued(orderBy, diagnostics, context);
		return result;
	}

	/**
	 * Validates the WellFormedOrder constraint of '<em>Order By</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderBy_WellFormedOrder(OrderBy orderBy, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "WellFormedOrder", getObjectLabel(orderBy, context) },
						new Object[] { orderBy }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the IsManyValued constraint of '<em>Order By</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderBy_IsManyValued(OrderBy orderBy, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "IsManyValued", getObjectLabel(orderBy, context) },
						new Object[] { orderBy }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderColumn(OrderColumn orderColumn, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(orderColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOrderColumn_ScalePrecisionForNumeric(orderColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateOrderColumn_LengthForString(orderColumn, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ScalePrecisionForNumeric constraint of '<em>Order Column</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderColumn_ScalePrecisionForNumeric(OrderColumn orderColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "ScalePrecisionForNumeric",
								getObjectLabel(orderColumn, context) }, new Object[] { orderColumn }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the LengthForString constraint of '<em>Order Column</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOrderColumn_LengthForString(OrderColumn orderColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic",
						new Object[] { "LengthForString", getObjectLabel(orderColumn, context) },
						new Object[] { orderColumn }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePrimaryKeyJoinColumn(PrimaryKeyJoinColumn primaryKeyJoinColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(primaryKeyJoinColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(primaryKeyJoinColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(primaryKeyJoinColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(primaryKeyJoinColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(primaryKeyJoinColumn, diagnostics,
					context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSecondaryTable(SecondaryTable secondaryTable, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(secondaryTable, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(secondaryTable, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(secondaryTable, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(secondaryTable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSequenceGenerator(SequenceGenerator sequenceGenerator,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(sequenceGenerator, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(sequenceGenerator, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(sequenceGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(sequenceGenerator, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(sequenceGenerator, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTable(Table table, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(table, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(table, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(table, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTableGenerator(TableGenerator tableGenerator, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tableGenerator, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(tableGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(tableGenerator, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(tableGenerator, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTemporal(Temporal temporal, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(temporal, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(temporal, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateTemporal_AllowedElementType(temporal, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Temporal</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTemporal_AllowedElementType(Temporal temporal,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
								getObjectLabel(temporal, context) }, new Object[] { temporal }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTransient(Transient transient_, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(transient_, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(transient_, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(transient_, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateUniqueConstraint(UniqueConstraint uniqueConstraint,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(uniqueConstraint, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(uniqueConstraint, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(uniqueConstraint, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(uniqueConstraint, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersion(Version version, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(version, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(version, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateVersion_AllowedElementType(version, diagnostics, context);
		return result;
	}

	/**
	 * Validates the AllowedElementType constraint of '<em>Version</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersion_AllowedElementType(Version version, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] { "AllowedElementType",
								getObjectLabel(version, context) }, new Object[] { version }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateForeignKey(ForeignKey foreignKey, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(foreignKey, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(foreignKey, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(foreignKey, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSequenceStyleGenerator(SequenceStyleGenerator sequenceStyleGenerator,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(sequenceStyleGenerator, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(sequenceStyleGenerator, diagnostics,
				context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(sequenceStyleGenerator, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(sequenceStyleGenerator, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(sequenceStyleGenerator,
					diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(sequenceStyleGenerator, diagnostics,
					context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateExternal(External external, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(external, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(external, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(external, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAVMapping(EAVMapping eavMapping, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eavMapping, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(eavMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(eavMapping, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNoEAVMapping(NoEAVMapping noEAVMapping, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (!validate_NoCircularContainment(noEAVMapping, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(noEAVMapping, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(noEAVMapping, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateListIndexColumn(ListIndexColumn listIndexColumn,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(listIndexColumn, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(listIndexColumn, diagnostics, context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_CompatibleEModelElementType(listIndexColumn, diagnostics,
					context);
		if (result || diagnostics != null)
			result &= validatePAnnotation_AnnotationIsSupported(listIndexColumn, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateCascadeType(CascadeType cascadeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDiscriminatorType(DiscriminatorType discriminatorType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEnumType(EnumType enumType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateFetchType(FetchType fetchType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateGenerationType(GenerationType generationType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateInheritanceType(InheritanceType inheritanceType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTemporalType(TemporalType temporalType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateOptimizerType(OptimizerType optimizerType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's
	 * diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // PannotationValidator
