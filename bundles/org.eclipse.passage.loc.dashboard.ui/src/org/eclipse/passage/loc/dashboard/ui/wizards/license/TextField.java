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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Optional;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

abstract class TextField<T> implements Field<T> {

	protected final Optional<T> source;
	private final Runnable modified;
	private final LabelProvider labels;
	protected final MandatoryService context;
	private Text text;

	protected TextField(Optional<T> source, Runnable modified, LabelProvider labels, MandatoryService context) {
		this.source = source;
		this.modified = modified;
		this.labels = labels;
		this.context = context;
	}

	@Override
	public final void installControll(Composite parent) {
		installLabel(parent);
		installText(parent);
		installSelectButton(parent);
		installData(source);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Optional<T> data() {
		return Optional.ofNullable((T) text.getData());
	}

	@Override
	public Optional<String> error() {
		return data().isEmpty() ? Optional.of(errorMessage()) : Optional.empty();
	}

	private void installLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(label());
		label.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	private void installText(Composite parent) {
		text = new Text(parent, SWT.READ_ONLY | SWT.BORDER);
		text.addModifyListener(m -> modified.run());
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
	}

	private void installSelectButton(Composite parent) {
		Button select = new Button(parent, SWT.PUSH);
		select.setText(IssueLicensePageMessages.IssueLicenseRequestPage_btn_select_text);
		select.addSelectionListener(widgetSelectedAdapter(event -> installData(select(text))));
		select.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	private void installData(Optional<T> data) {
		data.ifPresent(d -> {
			text.setData(d);
			text.setText(labels.getText(d));
		});
	}

	protected Shell shell() {
		return text.getShell();
	}

	protected abstract String label();

	protected abstract String errorMessage();

	protected abstract Optional<T> select(Text control);

}