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
package org.eclipse.passage.loc.internal.products.core;

import java.util.Optional;

import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.emf.EObjectToBytes;
import org.eclipse.passage.lic.keys.model.api.KeyPair;
import org.eclipse.passage.loc.internal.api.workspace.Keys;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;

@SuppressWarnings("restriction")
public final class KeyPairStored {

	private final KeyPair pair;

	public KeyPairStored(KeyPair pair) {
		this.pair = pair;
	}

	public Optional<String> store() throws LicensingException {
		return new OperatorGearAware().withGear(gear -> store(gear.workspace().keys()));
	}

	private Optional<String> store(Keys keys) throws LicensingException {
		String product = pair.getProduct().getIdentifier();
		String version = pair.getProduct().getVersion();
		ResourceHandle locator = keys.located(product, version);
		// FIXME:AF: should be done via factory
		// FIXME:AF: generate XMI factory for keys
		byte[] content = new EObjectToBytes(pair, XMIResourceImpl::new).get();
		try {
			locator.write(content);
		} catch (Exception e) {
			throw new LicensingException(//
					String.format("Failed to store keys for %s %s", product, version), // //$NON-NLS-1$
					e);
		}
		return Optional.of(locator.info());
	}

}