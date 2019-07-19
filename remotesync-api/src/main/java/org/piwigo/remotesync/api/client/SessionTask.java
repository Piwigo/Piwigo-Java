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
package org.piwigo.remotesync.api.client;

import java.util.TimerTask;

import org.piwigo.remotesync.api.sync.LoginJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionTask extends TimerTask
{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionTask.class);
	
	private LoginJob loginJob = new LoginJob();
	
	public void run() {
		try {
			logger.debug("----------------------------");
			loginJob.doLogout();
			loginJob.execute();
			logger.debug("----------------------------");
		} catch (Exception e) {
			logger.error("Error while refreshing session.. !");
		}
	}
	
}
