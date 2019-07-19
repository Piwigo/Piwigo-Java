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

import org.kohsuke.args4j.Option;
import org.piwigo.remotesync.api.Main;

public class MainUi extends Main {
	public static void main(String[] args) {
		new MainUi().start();
	}

	@Option(name = "-batch", usage = "batch mode (no ui)")
	protected boolean batch = false;

	@Option(name = "-testclient", usage = "test api client ui")
	protected boolean testclient = false;

	@Option(name = "-swing", usage = "old swing ui")
	protected boolean swing = true;

	protected void start() {
		if (batch)
			super.start();
		else if (testclient)
			org.piwigo.remotesync.ui.pivot.ReflectionUI.run();
		else if (swing)
			org.piwigo.remotesync.ui.swing.RemotesyncUI.run();
		else
			org.piwigo.remotesync.ui.pivot.RemotesyncUI.run();
	};
}
