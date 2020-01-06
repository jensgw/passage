/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.loc.products.emfforms.renderers;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.workbench.emfforms.renderers.FileContentRenderer;

public abstract class ProductVersionKeyRenderer extends FileContentRenderer<ProductVersion> {

	@Inject
	public ProductVersionKeyRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider,
				ProductVersion.class);
	}

	@Override
	protected String extractFilePath(String value, ProductVersion observed) {
		String productIdentifier = observed.getProduct().getIdentifier();
		String productVersion = observed.getVersion();
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("user.home")); //$NON-NLS-1$
		sb.append(File.separator).append(LicensingPaths.FOLDER_LICENSING_BASE);
		sb.append(File.separator).append(productIdentifier);
		sb.append(File.separator).append(productVersion);
		sb.append(File.separator).append(productIdentifier).append('_').append(productVersion);
		sb.append(getFileExtension());
		return sb.toString();
	}

	protected abstract String getFileExtension();

}
