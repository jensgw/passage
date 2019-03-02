/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.runtime.licenses;

import org.eclipse.passage.lic.runtime.registry.DescriptorRegistry;

public interface LicensesRegistry extends DescriptorRegistry {
	
	Iterable<? extends LicensePackDescriptor> getLicensePacks();

	Iterable<? extends LicensePackDescriptor> getUserLicensePacks(String userId);

	Iterable<? extends LicensePackDescriptor> getProductVersionLicensePacks(String productId, String version);

	LicensePackDescriptor getLicensePack(String licensePackId);

	void registerLicensePack(LicensePackDescriptor licensePack);
	
	void unregisterLicensePack(String identifier);
	
}
