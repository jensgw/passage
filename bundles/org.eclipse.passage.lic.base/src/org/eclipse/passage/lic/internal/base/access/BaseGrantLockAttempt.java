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
package org.eclipse.passage.lic.internal.base.access;

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

abstract class BaseGrantLockAttempt implements GrantLockAttempt {

	private final ExaminationCertificate certificate;

	protected BaseGrantLockAttempt(ExaminationCertificate certificate) {
		Objects.requireNonNull(certificate, "BaseGrantLock::certificate"); //$NON-NLS-1$
		this.certificate = certificate;
	}

	@Override
	public ExaminationCertificate certificate() {
		return certificate;
	}

	final static class Successful extends BaseGrantLockAttempt {

		private final GrantAcqisition grant;

		Successful(ExaminationCertificate certificate, GrantAcqisition grant) {
			super(certificate);
			Objects.requireNonNull(grant, "BaseGrantLock::grant"); //$NON-NLS-1$
			this.grant = grant;
		}

		@Override
		public boolean successful() {
			return true;
		}

		@Override
		public GrantAcqisition grant() {
			return grant;
		}

	}

	final static class Failed extends BaseGrantLockAttempt {

		protected Failed(ExaminationCertificate certificate) {
			super(certificate);
		}

		@Override
		public boolean successful() {
			return false;
		}

		@Override
		public GrantAcqisition grant() {
			throw new IllegalStateException("Failed lock is not supposed to supply grant"); //$NON-NLS-1$
		}

	}

}
