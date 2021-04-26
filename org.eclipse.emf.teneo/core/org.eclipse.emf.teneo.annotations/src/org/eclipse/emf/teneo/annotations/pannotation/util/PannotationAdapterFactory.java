/**
 * <copyright>
 * </copyright>
 *
 * $Id: PannotationAdapterFactory.java,v 1.36 2011/02/21 06:40:10 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.annotations.pannotation.*;
import org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride;
import org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride;
import org.eclipse.emf.teneo.annotations.pannotation.Basic;
import org.eclipse.emf.teneo.annotations.pannotation.Cacheable;
import org.eclipse.emf.teneo.annotations.pannotation.CollectionTable;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue;
import org.eclipse.emf.teneo.annotations.pannotation.EAVMapping;
import org.eclipse.emf.teneo.annotations.pannotation.ElementCollection;
import org.eclipse.emf.teneo.annotations.pannotation.Embeddable;
import org.eclipse.emf.teneo.annotations.pannotation.Embedded;
import org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId;
import org.eclipse.emf.teneo.annotations.pannotation.Entity;
import org.eclipse.emf.teneo.annotations.pannotation.Enumerated;
import org.eclipse.emf.teneo.annotations.pannotation.External;
import org.eclipse.emf.teneo.annotations.pannotation.ForeignKey;
import org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue;
import org.eclipse.emf.teneo.annotations.pannotation.Id;
import org.eclipse.emf.teneo.annotations.pannotation.IdClass;
import org.eclipse.emf.teneo.annotations.pannotation.Inheritance;
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
import org.eclipse.emf.teneo.annotations.pannotation.Transient;
import org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint;
import org.eclipse.emf.teneo.annotations.pannotation.Version;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage
 * @generated
 */
public class PannotationAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static PannotationPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PannotationAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PannotationPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is
	 * an instance object of the model. <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected PannotationSwitch<Adapter> modelSwitch = new PannotationSwitch<Adapter>() {
			@Override
			public Adapter casePAnnotation(PAnnotation object) {
				return createPAnnotationAdapter();
			}
			@Override
			public Adapter caseAttributeOverride(AttributeOverride object) {
				return createAttributeOverrideAdapter();
			}
			@Override
			public Adapter caseAssociationOverride(AssociationOverride object) {
				return createAssociationOverrideAdapter();
			}
			@Override
			public Adapter caseBasic(Basic object) {
				return createBasicAdapter();
			}
			@Override
			public Adapter caseCacheable(Cacheable object) {
				return createCacheableAdapter();
			}
			@Override
			public Adapter caseCollectionTable(CollectionTable object) {
				return createCollectionTableAdapter();
			}
			@Override
			public Adapter caseColumn(Column object) {
				return createColumnAdapter();
			}
			@Override
			public Adapter caseDiscriminatorColumn(DiscriminatorColumn object) {
				return createDiscriminatorColumnAdapter();
			}
			@Override
			public Adapter caseDiscriminatorValue(DiscriminatorValue object) {
				return createDiscriminatorValueAdapter();
			}
			@Override
			public Adapter caseElementCollection(ElementCollection object) {
				return createElementCollectionAdapter();
			}
			@Override
			public Adapter caseEmbeddable(Embeddable object) {
				return createEmbeddableAdapter();
			}
			@Override
			public Adapter caseEmbedded(Embedded object) {
				return createEmbeddedAdapter();
			}
			@Override
			public Adapter caseEmbeddedId(EmbeddedId object) {
				return createEmbeddedIdAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter caseEnumerated(Enumerated object) {
				return createEnumeratedAdapter();
			}
			@Override
			public Adapter caseGeneratedValue(GeneratedValue object) {
				return createGeneratedValueAdapter();
			}
			@Override
			public Adapter caseId(Id object) {
				return createIdAdapter();
			}
			@Override
			public Adapter caseIdClass(IdClass object) {
				return createIdClassAdapter();
			}
			@Override
			public Adapter caseInheritance(Inheritance object) {
				return createInheritanceAdapter();
			}
			@Override
			public Adapter caseJoinColumn(JoinColumn object) {
				return createJoinColumnAdapter();
			}
			@Override
			public Adapter caseJoinTable(JoinTable object) {
				return createJoinTableAdapter();
			}
			@Override
			public Adapter caseLob(Lob object) {
				return createLobAdapter();
			}
			@Override
			public Adapter caseManyToMany(ManyToMany object) {
				return createManyToManyAdapter();
			}
			@Override
			public Adapter caseManyToOne(ManyToOne object) {
				return createManyToOneAdapter();
			}
			@Override
			public Adapter caseMapKey(MapKey object) {
				return createMapKeyAdapter();
			}
			@Override
			public Adapter caseMapKeyClass(MapKeyClass object) {
				return createMapKeyClassAdapter();
			}
			@Override
			public Adapter caseMapKeyColumn(MapKeyColumn object) {
				return createMapKeyColumnAdapter();
			}
			@Override
			public Adapter caseMapKeyEnumerated(MapKeyEnumerated object) {
				return createMapKeyEnumeratedAdapter();
			}
			@Override
			public Adapter caseMapKeyJoinColumn(MapKeyJoinColumn object) {
				return createMapKeyJoinColumnAdapter();
			}
			@Override
			public Adapter caseMapKeyTemporal(MapKeyTemporal object) {
				return createMapKeyTemporalAdapter();
			}
			@Override
			public Adapter caseMappedSuperclass(MappedSuperclass object) {
				return createMappedSuperclassAdapter();
			}
			@Override
			public Adapter caseMapsId(MapsId object) {
				return createMapsIdAdapter();
			}
			@Override
			public Adapter caseOneToMany(OneToMany object) {
				return createOneToManyAdapter();
			}
			@Override
			public Adapter caseOneToOne(OneToOne object) {
				return createOneToOneAdapter();
			}
			@Override
			public Adapter caseOrderBy(OrderBy object) {
				return createOrderByAdapter();
			}
			@Override
			public Adapter caseOrderColumn(OrderColumn object) {
				return createOrderColumnAdapter();
			}
			@Override
			public Adapter casePrimaryKeyJoinColumn(PrimaryKeyJoinColumn object) {
				return createPrimaryKeyJoinColumnAdapter();
			}
			@Override
			public Adapter caseSecondaryTable(SecondaryTable object) {
				return createSecondaryTableAdapter();
			}
			@Override
			public Adapter caseSequenceGenerator(SequenceGenerator object) {
				return createSequenceGeneratorAdapter();
			}
			@Override
			public Adapter caseTable(Table object) {
				return createTableAdapter();
			}
			@Override
			public Adapter caseTableGenerator(TableGenerator object) {
				return createTableGeneratorAdapter();
			}
			@Override
			public Adapter caseTemporal(Temporal object) {
				return createTemporalAdapter();
			}
			@Override
			public Adapter caseTransient(Transient object) {
				return createTransientAdapter();
			}
			@Override
			public Adapter caseUniqueConstraint(UniqueConstraint object) {
				return createUniqueConstraintAdapter();
			}
			@Override
			public Adapter caseVersion(Version object) {
				return createVersionAdapter();
			}
			@Override
			public Adapter caseForeignKey(ForeignKey object) {
				return createForeignKeyAdapter();
			}
			@Override
			public Adapter caseSequenceStyleGenerator(SequenceStyleGenerator object) {
				return createSequenceStyleGeneratorAdapter();
			}
			@Override
			public Adapter caseExternal(External object) {
				return createExternalAdapter();
			}
			@Override
			public Adapter caseEAVMapping(EAVMapping object) {
				return createEAVMappingAdapter();
			}
			@Override
			public Adapter caseNoEAVMapping(NoEAVMapping object) {
				return createNoEAVMappingAdapter();
			}
			@Override
			public Adapter caseListIndexColumn(ListIndexColumn object) {
				return createListIndexColumnAdapter();
			}
			@Override
			public Adapter caseNoAuditing(NoAuditing object) {
				return createNoAuditingAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.PAnnotation <em>PAnnotation</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PAnnotation
	 * @generated
	 */
	public Adapter createPAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride <em>Attribute Override</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride
	 * @generated
	 */
	public Adapter createAttributeOverrideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Basic <em>Basic</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Basic
	 * @generated
	 */
	public Adapter createBasicAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Cacheable <em>Cacheable</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Cacheable
	 * @generated
	 */
	public Adapter createCacheableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.CollectionTable <em>Collection Table</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.CollectionTable
	 * @generated
	 */
	public Adapter createCollectionTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Column <em>Column</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column
	 * @generated
	 */
	public Adapter createColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn <em>Discriminator Column</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn
	 * @generated
	 */
	public Adapter createDiscriminatorColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue <em>Discriminator Value</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue
	 * @generated
	 */
	public Adapter createDiscriminatorValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.ElementCollection <em>Element Collection</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ElementCollection
	 * @generated
	 */
	public Adapter createElementCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Embeddable <em>Embeddable</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Embeddable
	 * @generated
	 */
	public Adapter createEmbeddableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass <em>Mapped Superclass</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass
	 * @generated
	 */
	public Adapter createMappedSuperclassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.MapsId <em>Maps Id</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapsId
	 * @generated
	 */
	public Adapter createMapsIdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Embedded <em>Embedded</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Embedded
	 * @generated
	 */
	public Adapter createEmbeddedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId <em>Embedded Id</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId
	 * @generated
	 */
	public Adapter createEmbeddedIdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Enumerated <em>Enumerated</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Enumerated
	 * @generated
	 */
	public Adapter createEnumeratedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Entity <em>Entity</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue <em>Generated Value</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue
	 * @generated
	 */
	public Adapter createGeneratedValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.Id <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Id
	 * @generated
	 */
	public Adapter createIdAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.IdClass <em>Id Class</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.IdClass
	 * @generated
	 */
	public Adapter createIdClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Inheritance <em>Inheritance</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Inheritance
	 * @generated
	 */
	public Adapter createInheritanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn <em>Join Column</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn
	 * @generated
	 */
	public Adapter createJoinColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable <em>Join Table</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable
	 * @generated
	 */
	public Adapter createJoinTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.Lob <em>Lob</em>}'.
	 * <!-- begin-user-doc
	 * --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Lob
	 * @generated
	 */
	public Adapter createLobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany <em>Many To Many</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany
	 * @generated
	 */
	public Adapter createManyToManyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne <em>Many To One</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne
	 * @generated
	 */
	public Adapter createManyToOneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.MapKey <em>Map Key</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKey
	 * @generated
	 */
	public Adapter createMapKeyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.MapKeyClass <em>Map Key Class</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKeyClass
	 * @generated
	 */
	public Adapter createMapKeyClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKeyColumn <em>Map Key Column</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKeyColumn
	 * @generated
	 */
	public Adapter createMapKeyColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKeyEnumerated <em>Map Key Enumerated</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKeyEnumerated
	 * @generated
	 */
	public Adapter createMapKeyEnumeratedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKeyJoinColumn <em>Map Key Join Column</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKeyJoinColumn
	 * @generated
	 */
	public Adapter createMapKeyJoinColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKeyTemporal <em>Map Key Temporal</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKeyTemporal
	 * @generated
	 */
	public Adapter createMapKeyTemporalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany <em>One To Many</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany
	 * @generated
	 */
	public Adapter createOneToManyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne <em>One To One</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne
	 * @generated
	 */
	public Adapter createOneToOneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.OrderBy <em>Order By</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OrderBy
	 * @generated
	 */
	public Adapter createOrderByAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.OrderColumn <em>Order Column</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OrderColumn
	 * @generated
	 */
	public Adapter createOrderColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn <em>Primary Key Join Column</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn
	 * @generated
	 */
	public Adapter createPrimaryKeyJoinColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable <em>Secondary Table</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable
	 * @generated
	 */
	public Adapter createSecondaryTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator <em>Sequence Generator</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator
	 * @generated
	 */
	public Adapter createSequenceGeneratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Table <em>Table</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table
	 * @generated
	 */
	public Adapter createTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator <em>Table Generator</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator
	 * @generated
	 */
	public Adapter createTableGeneratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Temporal <em>Temporal</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Temporal
	 * @generated
	 */
	public Adapter createTemporalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Transient <em>Transient</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Transient
	 * @generated
	 */
	public Adapter createTransientAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint
	 * @generated
	 */
	public Adapter createUniqueConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.Version <em>Version</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Version
	 * @generated
	 */
	public Adapter createVersionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.ForeignKey <em>Foreign Key</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ForeignKey
	 * @generated
	 */
	public Adapter createForeignKeyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator <em>Sequence Style Generator</em>}'.
	 * <!-- begin-user-doc --> This default implementation
	 * returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator
	 * @generated
	 */
	public Adapter createSequenceStyleGeneratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.External <em>External</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.External
	 * @generated
	 */
	public Adapter createExternalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.emf.teneo.annotations.pannotation.EAVMapping <em>EAV Mapping</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EAVMapping
	 * @generated
	 */
	public Adapter createEAVMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.NoEAVMapping <em>No EAV Mapping</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.NoEAVMapping
	 * @generated
	 */
	public Adapter createNoEAVMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.ListIndexColumn <em>List Index Column</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch
	 * all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ListIndexColumn
	 * @generated
	 */
	public Adapter createListIndexColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.NoAuditing <em>No Auditing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.NoAuditing
	 * @generated
	 */
	public Adapter createNoAuditingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride <em>Association Override</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns
	 * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride
	 * @generated
	 */
	public Adapter createAssociationOverrideAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This default implementation
	 * returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // PannotationAdapterFactory
