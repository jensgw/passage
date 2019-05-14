/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.equinox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lbc.api.BackendCluster;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component
public class ServerRunnerImpl implements BackendCluster {

	private List<BackendLauncher> backendLaunchers = new ArrayList<>();
	private LoggerFactory loggerFactory;
	private Logger logger;

	@Activate
	public void activate() {
	}

	@Deactivate
	public void deactivate() {
		for (BackendLauncher launcher : backendLaunchers) {
			launcher.terminate();
		}
		backendLaunchers.clear();
	}

	@Reference
	public void bindLogger(LoggerFactory factory) {
		this.loggerFactory = factory;
		this.logger = factory.getLogger(ServerRunnerImpl.class);
	}

	public void unbindLogger(LoggerFactory factory) {
		if (this.loggerFactory == factory) {
			this.loggerFactory = null;
			this.logger = null;
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindServerHandler(BackendLauncher serverHandler, Map<String, Object> context) {
		if (serverHandler != null) {
			logger.info(String.format("Bind BackendLauncher %s", serverHandler)); //$NON-NLS-1$
			serverHandler.launch(context);
			backendLaunchers.add(serverHandler);
		}
	}

	public void unbindServerHandler(BackendLauncher serverHandler) {
		if (serverHandler != null) {
			logger.info(String.format("Unbind BackendLauncher %s", serverHandler)); //$NON-NLS-1$
			serverHandler.terminate();
			backendLaunchers.remove(serverHandler);
		}
	}

	@Override
	public List<BackendLauncher> getBackendLaunchers() {
		return backendLaunchers;
	}
}