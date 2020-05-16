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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.util.Map;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.NamedData;

/**
 * Encapsulate reading of a {@code feature identifier} from a
 * {@code Capability}'s attributes.
 * 
 * @see NamedData
 */
@SuppressWarnings("restriction")
public final class CapabilityLicFeatureId extends CapabilityLicFeatureInfo {

	public CapabilityLicFeatureId(Requirement requirement) {
		super(requirement.feature().identifier());
	}

	public CapabilityLicFeatureId(Map<String, Object> container) {
		super(container);
	}

	@Override
	public String key() {
		return "licensing.feature"; //$NON-NLS-1$
	}

}
