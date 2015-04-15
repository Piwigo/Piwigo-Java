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
package org.piwigo.remotesync.api;

import org.piwigo.remotesync.api.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends AbstractMain {
	static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		new Main().run(args);
	}
	
	protected void start(Config config) {
		logger.debug("will start batch Remotesync");
		new Sync(config.getCurrentGalleryConfig()).sync();
	}

//	// TODO implement dry run
//	@Option(name = "-dryrun", usage = "do nothing")
//	public boolean dryrun = false;

}
