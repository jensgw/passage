/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.operator.internal.gear;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.EvaluationType;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.oshi.HardwareEnvironment;
import org.eclipse.passage.loc.internal.api.OperatorGear;

@SuppressWarnings("restriction")
final class DefaultGear implements OperatorGear {

	final static DefaultGear gear = new DefaultGear();

	private final Registry<EvaluationType, RuntimeEnvironment> environments;

	private DefaultGear() {
		this.environments = new ReadOnlyRegistry<>(Arrays.asList(//
				new HardwareEnvironment() //
		));
	}

	@Override
	public Optional<StreamCodec> codec(LicensedProduct product) {
		Objects.requireNonNull(product, "DefaultGear::codec -> product"); //$NON-NLS-1$
		return Optional.of(new BcStreamCodec(() -> product));
	}

	@Override
	public RuntimeEnvironmentRegistry environments() {
		return () -> environments;
	}

}