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

import java.util.Map;

import org.eclipse.passage.lic.internal.base.BaseNamedData;

public final class Port extends BaseNamedData<Integer> {

	public Port(Map<String, Object> arguments) {
		super(key -> Integer.class.isInstance(arguments.get(key)) ? (int) arguments.get(key) : 8090);
	}

	@Override
	public String key() {
		return "port"; //$NON-NLS-1$
	}

}