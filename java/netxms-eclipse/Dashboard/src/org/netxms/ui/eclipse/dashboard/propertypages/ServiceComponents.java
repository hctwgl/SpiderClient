/**
 * NetXMS - open source network management system
 * Copyright (C) 2003-2017 Raden Solutions
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.netxms.ui.eclipse.dashboard.propertypages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.dialogs.PropertyPage;
import org.netxms.client.objects.AbstractObject;
import org.netxms.ui.eclipse.dashboard.widgets.internal.ServiceComponentsConfig;
import org.netxms.ui.eclipse.objectbrowser.dialogs.ObjectSelectionDialog;
import org.netxms.ui.eclipse.objectbrowser.widgets.ObjectSelector;
import org.netxms.ui.eclipse.widgets.LabeledText;

/**
 * Service component map properties
 */
public class ServiceComponents extends PropertyPage {
	private ServiceComponentsConfig config;
	private ObjectSelector objectSelector;
	private LabeledText title;
	private Scale zoomLevelScale;
	private Spinner zoomLevelSpinner;
	private Button enableObjectDoubleClick;

	@Override
	protected Control createContents(Composite parent) {
		config = (ServiceComponentsConfig) getElement().getAdapter(
				ServiceComponentsConfig.class);

		Composite dialogArea = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		dialogArea.setLayout(layout);

		objectSelector = new ObjectSelector(dialogArea, SWT.NONE, false);
		objectSelector.setLabel("Container");
		objectSelector.setClassFilter(ObjectSelectionDialog
				.createContainerSelectionFilter());
		objectSelector.setObjectClass(AbstractObject.class);
		objectSelector.setObjectId(config.getObjectId());
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		objectSelector.setLayoutData(gd);

		title = new LabeledText(dialogArea, SWT.NONE);
		title.setLabel("Title");
		title.setText(config.getTitle());
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		title.setLayoutData(gd);

		Label label = new Label(dialogArea, SWT.NONE);
		label.setText("Zoom level (%)");
		gd = new GridData();
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		zoomLevelScale = new Scale(dialogArea, SWT.HORIZONTAL);
		zoomLevelScale.setMinimum(10);
		zoomLevelScale.setMaximum(400);
		zoomLevelScale.setSelection(config.getZoomLevel());
		gd = new GridData();
		gd.horizontalAlignment = SWT.FILL;
		gd.grabExcessHorizontalSpace = true;
		zoomLevelScale.setLayoutData(gd);
		zoomLevelScale.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				zoomLevelSpinner.setSelection(zoomLevelScale.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		zoomLevelSpinner = new Spinner(dialogArea, SWT.BORDER);
		zoomLevelSpinner.setMinimum(10);
		zoomLevelSpinner.setMaximum(400);
		zoomLevelSpinner.setSelection(config.getZoomLevel());
		zoomLevelSpinner.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				zoomLevelScale.setSelection(zoomLevelSpinner.getSelection());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		enableObjectDoubleClick = new Button(dialogArea, SWT.CHECK);
		enableObjectDoubleClick
				.setText("Enable double click action on objects");
		enableObjectDoubleClick.setSelection(config
				.isObjectDoubleClickEnabled());

		return dialogArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		config.setObjectId(objectSelector.getObjectId());
		config.setTitle(title.getText());
		config.setZoomLevel(zoomLevelSpinner.getSelection());
		config.setObjectDoubleClickEnabled(enableObjectDoubleClick
				.getSelection());
		return true;
	}

}
