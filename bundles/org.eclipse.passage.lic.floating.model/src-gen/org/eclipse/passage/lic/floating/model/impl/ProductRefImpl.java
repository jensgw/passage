/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.floating.model.impl;

import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.passage.lic.floating.model.api.ProductRef;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Product
 * Ref</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.passage.lic.floating.model.impl.ProductRefImpl#getProduct
 * <em>Product</em>}</li>
 * <li>{@link org.eclipse.passage.lic.floating.model.impl.ProductRefImpl#getVersion
 * <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductRefImpl extends MinimalEObjectImpl.Container implements ProductRef {
	/**
	 * The default value of the '{@link #getProduct() <em>Product</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected static final String PRODUCT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	private String product = PRODUCT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	private String version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProductRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FloatingPackage.eINSTANCE.getProductRef();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getProduct() {
		return product;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProduct(String newProduct) {
		String oldProduct = product;
		product = newProduct;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.PRODUCT_REF__PRODUCT, oldProduct,
					product));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, FloatingPackage.PRODUCT_REF__VERSION, oldVersion,
					version));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FloatingPackage.PRODUCT_REF__PRODUCT:
			return getProduct();
		case FloatingPackage.PRODUCT_REF__VERSION:
			return getVersion();
		default:
			return super.eGet(featureID, resolve, coreType);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FloatingPackage.PRODUCT_REF__PRODUCT:
			setProduct((String) newValue);
			return;
		case FloatingPackage.PRODUCT_REF__VERSION:
			setVersion((String) newValue);
			return;
		default:
			super.eSet(featureID, newValue);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case FloatingPackage.PRODUCT_REF__PRODUCT:
			setProduct(PRODUCT_EDEFAULT);
			return;
		case FloatingPackage.PRODUCT_REF__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		default:
			super.eUnset(featureID);
			return;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case FloatingPackage.PRODUCT_REF__PRODUCT:
			return !Objects.equals(PRODUCT_EDEFAULT, product);
		case FloatingPackage.PRODUCT_REF__VERSION:
			return !Objects.equals(VERSION_EDEFAULT, version);
		default:
			return super.eIsSet(featureID);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}
		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (product: "); //$NON-NLS-1$
		result.append(product);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} // ProductRefImpl