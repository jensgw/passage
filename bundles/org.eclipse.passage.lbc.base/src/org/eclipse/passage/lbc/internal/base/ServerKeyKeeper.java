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

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;

public final class ServerKeyKeeper implements KeyKeeper {

	private final LicensedProduct product;
	private final Supplier<Path> base;

	public ServerKeyKeeper(LicensedProduct product, Supplier<Path> base) {
		Objects.requireNonNull(product, "ServerKeyKeeper::product"); //$NON-NLS-1$
		Objects.requireNonNull(base, "ServerKeyKeeper::base"); //$NON-NLS-1$
		this.product = product;
		this.base = new PathFromLicensedProduct(base, product);
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		Path path = base.get().resolve(keyFile());
		try {
			return new FileInputStream(path.toFile());
		} catch (Exception e) {
			throw new LicensingException(BaseMessages.ServerKeyKeeper_input_stream_error, e);
		}
	}

	private String keyFile() {
		return new FileNameFromLicensedProduct(product, new PassageFileExtension.PublicKey()).get();
	}

}
