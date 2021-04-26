/**
 * <copyright>
 * </copyright>
 *
 * $Id: JoinTableImpl.java,v 1.7 2007/11/14 16:37:54 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Join Table</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getJoinColumns <em>Join Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getInverseJoinColumns <em>Inverse Join Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl#getUniqueConstraints <em>Unique Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JoinTableImpl extends PAnnotationImpl implements JoinTable {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCatalog() <em>Catalog</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCatalog()
	 * @generated
	 * @ordered
	 */
	protected static final String CATALOG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCatalog() <em>Catalog</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getCatalog()
	 * @generated
	 * @ordered
	 */
	protected String catalog = CATALOG_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchema() <em>Schema</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected static final String SCHEMA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected String schema = SCHEMA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getJoinColumns() <em>Join Columns</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getJoinColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<JoinColumn> joinColumns;

	/**
	 * The cached value of the '{@link #getInverseJoinColumns() <em>Inverse Join Columns</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInverseJoinColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<JoinColumn> inverseJoinColumns;

	/**
	 * The cached value of the '{@link #getUniqueConstraints() <em>Unique Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getUniqueConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<UniqueConstraint> uniqueConstraints;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PannotationPackage.Literals.JOIN_TABLE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.JOIN_TABLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getCatalog() {
		return catalog;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCatalog(String newCatalog) {
		String oldCatalog = catalog;
		catalog = newCatalog;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.JOIN_TABLE__CATALOG, oldCatalog, catalog));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(String newSchema) {
		String oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.JOIN_TABLE__SCHEMA, oldSchema, schema));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JoinColumn> getJoinColumns() {
		if (joinColumns == null) {
			joinColumns = new EObjectContainmentEList<JoinColumn>(JoinColumn.class, this, PannotationPackage.JOIN_TABLE__JOIN_COLUMNS);
		}
		return joinColumns;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JoinColumn> getInverseJoinColumns() {
		if (inverseJoinColumns == null) {
			inverseJoinColumns = new EObjectContainmentEList<JoinColumn>(JoinColumn.class, this, PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS);
		}
		return inverseJoinColumns;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UniqueConstraint> getUniqueConstraints() {
		if (uniqueConstraints == null) {
			uniqueConstraints = new EObjectContainmentEList<UniqueConstraint>(UniqueConstraint.class, this, PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS);
		}
		return uniqueConstraints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
			NotificationChain msgs) {
		switch (featureID) {
			case PannotationPackage.JOIN_TABLE__JOIN_COLUMNS:
				return ((InternalEList<?>)getJoinColumns()).basicRemove(otherEnd, msgs);
			case PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS:
				return ((InternalEList<?>)getInverseJoinColumns()).basicRemove(otherEnd, msgs);
			case PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS:
				return ((InternalEList<?>)getUniqueConstraints()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PannotationPackage.JOIN_TABLE__NAME:
				return getName();
			case PannotationPackage.JOIN_TABLE__CATALOG:
				return getCatalog();
			case PannotationPackage.JOIN_TABLE__SCHEMA:
				return getSchema();
			case PannotationPackage.JOIN_TABLE__JOIN_COLUMNS:
				return getJoinColumns();
			case PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS:
				return getInverseJoinColumns();
			case PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS:
				return getUniqueConstraints();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PannotationPackage.JOIN_TABLE__NAME:
				setName((String)newValue);
				return;
			case PannotationPackage.JOIN_TABLE__CATALOG:
				setCatalog((String)newValue);
				return;
			case PannotationPackage.JOIN_TABLE__SCHEMA:
				setSchema((String)newValue);
				return;
			case PannotationPackage.JOIN_TABLE__JOIN_COLUMNS:
				getJoinColumns().clear();
				getJoinColumns().addAll((Collection<? extends JoinColumn>)newValue);
				return;
			case PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS:
				getInverseJoinColumns().clear();
				getInverseJoinColumns().addAll((Collection<? extends JoinColumn>)newValue);
				return;
			case PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS:
				getUniqueConstraints().clear();
				getUniqueConstraints().addAll((Collection<? extends UniqueConstraint>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PannotationPackage.JOIN_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PannotationPackage.JOIN_TABLE__CATALOG:
				setCatalog(CATALOG_EDEFAULT);
				return;
			case PannotationPackage.JOIN_TABLE__SCHEMA:
				setSchema(SCHEMA_EDEFAULT);
				return;
			case PannotationPackage.JOIN_TABLE__JOIN_COLUMNS:
				getJoinColumns().clear();
				return;
			case PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS:
				getInverseJoinColumns().clear();
				return;
			case PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS:
				getUniqueConstraints().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PannotationPackage.JOIN_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PannotationPackage.JOIN_TABLE__CATALOG:
				return CATALOG_EDEFAULT == null ? catalog != null : !CATALOG_EDEFAULT.equals(catalog);
			case PannotationPackage.JOIN_TABLE__SCHEMA:
				return SCHEMA_EDEFAULT == null ? schema != null : !SCHEMA_EDEFAULT.equals(schema);
			case PannotationPackage.JOIN_TABLE__JOIN_COLUMNS:
				return joinColumns != null && !joinColumns.isEmpty();
			case PannotationPackage.JOIN_TABLE__INVERSE_JOIN_COLUMNS:
				return inverseJoinColumns != null && !inverseJoinColumns.isEmpty();
			case PannotationPackage.JOIN_TABLE__UNIQUE_CONSTRAINTS:
				return uniqueConstraints != null && !uniqueConstraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", catalog: ");
		result.append(catalog);
		result.append(", schema: ");
		result.append(schema);
		result.append(')');
		return result.toString();
	}

} // JoinTableImpl
