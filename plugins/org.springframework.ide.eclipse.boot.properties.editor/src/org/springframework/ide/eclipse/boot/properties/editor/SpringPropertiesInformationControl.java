/*******************************************************************************
 * Copyright (c) 2015 Pivotal, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Pivotal, Inc. - initial API and implementation
 *******************************************************************************/
package org.springframework.ide.eclipse.boot.properties.editor;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.internal.text.html.BrowserInformationControlInput;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class SpringPropertiesInformationControl extends BrowserInformationControl {

	public SpringPropertiesInformationControl(Shell parent, String appearanceJavadocFont, ToolBarManager toolbar) {
		super(parent, appearanceJavadocFont, toolbar);
	}

	public SpringPropertiesInformationControl(Shell parent, String appearanceJavadocFont, String statusText) {
		super(parent, appearanceJavadocFont, statusText);
	}

	@Override
	protected void createContent(Composite parent) {
		super.createContent(parent);
		addLocationListener(new LocationListener() {

			@Override
			public void changing(LocationEvent event) {
				BrowserInformationControlInput input = getInput();
				if (input instanceof HoverInfo) {
					if (((HoverInfo) input).handleActionLink(event.location)) {
						getShell().close();
						event.doit = false;
					}
				}
			}

			@Override
			public void changed(LocationEvent event) {
			}
		});
	}
}
