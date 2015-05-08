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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Job {

	private static final Logger logger = LoggerFactory.getLogger(Job.class);
	private static boolean running;

	public synchronized void execute() {
		logger.debug("running " + running);
		if (running) {
			logger.info("Already running");
			return;
		}
		running = true;
	
		try {
			doExecute();
		} catch (Exception e) {
			logger.error("Error in job " + this, e);
		} finally {
			running = false;
		}
	}

	public void executeInThread() {
		new Thread(new Runnable() {
	
			public void run() {
				execute();
			}
		}).start();
	}

	protected abstract void doExecute() throws Exception;

	public boolean isRunning() {
		return running;
	}

}