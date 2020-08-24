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
package org.eclipse.passage.lbc.internal.base;

import org.eclipse.passage.lbc.internal.api.BackendFloatingServer;
import org.eclipse.passage.lbc.internal.api.BackendServerConfiguration;
import org.eclipse.passage.lbc.internal.api.CertificateRequestData;
import org.eclipse.passage.lbc.internal.api.ConditionRequestData;
import org.eclipse.passage.lbc.internal.base.chains.Acquire;
import org.eclipse.passage.lbc.internal.base.chains.CanTake;
import org.eclipse.passage.lbc.internal.base.chains.Release;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * @since 1.0
 */
public final class PassageFloatingServer implements BackendFloatingServer {

	private final BackendServerConfiguration configuration;

	public PassageFloatingServer(BackendServerConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public ServiceInvocationResult<Boolean> canTake(ConditionRequestData request) {
		return new CanTake().apply(request);
	}

	@Override
	public ServiceInvocationResult<ExaminationCertificate> take(CertificateRequestData request) {
		return new Acquire().apply(request);
	}

	@Override
	public ServiceInvocationResult<Boolean> release(CertificateRequestData request) {
		return new Release().apply(request);
	}

}
