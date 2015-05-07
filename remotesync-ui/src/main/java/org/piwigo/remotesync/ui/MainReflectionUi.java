/*******************************************************************************
 * Copyright (c) 2014 Matthieu Helleboid.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Matthieu Helleboid - initial API and implementation
 ******************************************************************************/
package org.piwigo.remotesync.ui;


public class MainReflectionUi extends org.piwigo.remotesync.api.Main {
	public static void main(String[] args) {
		new MainReflectionUi().run(args);
	}

	protected void start() {
		org.piwigo.remotesync.ui.pivot.ReflectionUI.run();
	};
}
