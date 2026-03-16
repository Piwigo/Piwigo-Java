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

import org.piwigo.remotesync.api.AbstractMain;
import org.piwigo.remotesync.api.sync.LoginJob;
import org.piwigo.remotesync.api.sync.SyncJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends AbstractMain {
	static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		new Main().run(args);
	}

	protected void start() {
		logger.debug("will start batch Remotesync");
		LoginJob preJob = new LoginJob();
		preJob.execute();
		new SyncJob(preJob).execute();
	}

//	// TODO implement dry run
//	@Option(name = "-dryrun", usage = "do nothing")
//	public boolean dryrun = false;

}
