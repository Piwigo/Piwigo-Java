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
package org.piwigo.remotesync.api.sync;

import org.apache.commons.io.DirectoryWalker.CancelException;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.Job;
import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginJob extends Job 
{

	private static final Logger logger = LoggerFactory.getLogger(LoginJob.class);
	
	private boolean loggedIn = false;
	
	private ConnectedWalker connectedWalker;
	
	private ISyncConfiguration syncConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration().getCurrentSyncConfiguration();
	
	protected void doExecute() throws Exception 
	{

		// TODO shoud validate config
		//SyncConfigurationValidator.INSTANCE.validate(syncConfiguration);

		if (loggedIn)
			return;
		
		logger.info("Logging in..");
		try
		{
			connectedWalker = new ConnectedWalker(syncConfiguration);
			connectedWalker.connect();
			loggedIn = true;
			logger.info("Login succeeded.");
		} 
		catch (CancelException e)
		{
			logger.error("Login failed.");
		}
	}
	
	public void doLogout() throws Exception
	{
		logger.info("Logging out..");
		new ConnectedWalker(syncConfiguration).disconnect();
		loggedIn = false;
		logger.info("Logout succesful.");
	}
	
	public ConnectedWalker getConnectedWalker()
	{
		return (connectedWalker);
	}
	
	public boolean hasLogged()
	{
		return (loggedIn);
	}
}
