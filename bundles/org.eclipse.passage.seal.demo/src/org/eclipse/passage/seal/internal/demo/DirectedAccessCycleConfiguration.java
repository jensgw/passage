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
package org.eclipse.passage.seal.internal.demo;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.base.acquire.PathLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.base.conditions.mining.PathResidentConditions;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public final class DirectedAccessCycleConfiguration extends BaseAccessCycleConfiguration {

	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;

	public DirectedAccessCycleConfiguration(Supplier<LicensedProduct> product, Path source, Supplier<Bundle> bundle) {
		super(product, bundle);
		conditions = new ReadOnlyRegistry<>(new PathResidentConditions(source, miningEquipment()));
		acquirers = new ReadOnlyRegistry<>(new PathLicenseAcquisitionService(source));
	}

	@Override
	public final MinedConditionsRegistry conditionMiners() {
		return () -> conditions;
	}

	@Override
	public final LicenseAcquisitionServicesRegistry acquirers() {
		return () -> acquirers;
	}

}