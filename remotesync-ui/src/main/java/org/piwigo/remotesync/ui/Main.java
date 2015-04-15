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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends org.piwigo.remotesync.api.Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		new Main().run(args);
	}

	@Option(name = "-batch", usage = "batch mode (no ui)")
	public boolean batch = false;

//	@Option(name = "-swing", usage = "old swing ui")
//	public boolean swing = false;

	protected void start(org.piwigo.remotesync.api.conf.Config config) {
		if (batch) {
			super.start(config);
		} else {
			logger.debug("will start RemotesyncUI");
			// TODO load user config

//			if (swing) {
				org.piwigo.remotesync.ui.swing.RemotesyncUI.run(config.getCurrentGalleryConfig());
//			} else {
//				org.piwigo.remotesync.ui.pivot.RemotesyncUI.run(config.getCurrentGalleryConfig());
//			}
		}
	};
}
