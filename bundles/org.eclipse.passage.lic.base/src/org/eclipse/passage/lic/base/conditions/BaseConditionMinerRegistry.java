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
package org.eclipse.passage.lic.base.conditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.passage.lic.base.BaseMessages;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.base.io.NullStreamCodec;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;

public class BaseConditionMinerRegistry implements ConditionMinerRegistry {

	private final List<ConditionMiner> conditionMiners = new ArrayList<>();

	@Override
	public Iterable<ConditionMiner> getConditionMiners() {
		return Collections.unmodifiableList(conditionMiners);
	}

	@Override
	public void registerConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		conditionMiners.add(conditionMiner);
	}

	@Override
	public void unregisterConditionMiner(ConditionMiner conditionMiner, Map<String, Object> properties) {
		conditionMiners.remove(conditionMiner);
	}

	@Override
	public LicensingResult importConditions(String source, LicensingConfiguration configuration) {
		String property = System.getProperty("user.home"); //$NON-NLS-1$
		String value = new File(property).getAbsolutePath();
		Path from = Paths.get(value, LicensingPaths.FOLDER_LICENSING_BASE);
		Path configurationPath = LicensingPaths.resolveConfigurationPath(from, configuration);
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS", Locale.ENGLISH); //$NON-NLS-1$
		String fileName = dateFormat.format(new Date()) + LicensingPaths.EXTENSION_LICENSE_ENCRYPTED;
		File dest = configurationPath.resolve(fileName).toFile();
		try (FileInputStream fis = new FileInputStream(source); FileOutputStream fos = new FileOutputStream(dest)) {
			NullStreamCodec.transfer(fis, fos);
			return LicensingResults.createOK();
		} catch (Exception e) {
			String message = String.format(BaseMessages.getString("BaseConditionMinerRegistry_lic_conditions_import_failed"), source); //$NON-NLS-1$
			return LicensingResults.createError(message, source, e);
		}

	}

}
