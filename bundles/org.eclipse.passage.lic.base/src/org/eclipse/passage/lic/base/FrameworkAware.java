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
package org.eclipse.passage.lic.base;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.ServiceInvocationResult;

/**
 * @since 2.1
 */
public interface FrameworkAware {

	<T> ServiceInvocationResult<T> withFrameworkService(Function<Framework, ServiceInvocationResult<T>> invoke);

	<T> Optional<T> withFramework(Function<Framework, T> invoke);

}