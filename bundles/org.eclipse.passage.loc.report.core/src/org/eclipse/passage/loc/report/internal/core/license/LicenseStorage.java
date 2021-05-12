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
package org.eclipse.passage.loc.report.internal.core.license;

import java.util.List;
import java.util.Optional;

import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.yars.internal.api.Storage;

/**
 * @since 0.2
 */
@SuppressWarnings("restriction")
public interface LicenseStorage extends Storage<LicensePackDescriptor> {

	List<LicensePackDescriptor> licenses(String plan);

	List<LicensePlanDescriptor> plans();

	Optional<LicensePlanDescriptor> plan(String plan);

}
