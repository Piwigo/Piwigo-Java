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
package org.piwigo.remotesync.api.test;

import java.io.File;
import java.net.URISyntaxException;

import org.piwigo.remotesync.api.IClient;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.cache.LegacyCache;
import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.conf.UserConfiguration;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.util.FileUtil;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import junit.framework.TestCase;

public abstract class AbstractTestCase extends TestCase {

	static {
		((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(RemotesyncAPIAllTests.TEST_LOG_LEVEL);
	}
	
	private static IClient client;

	protected IClient getClient() throws ClientServerException {
		if (client == null) {
			ISyncConfiguration syncConfiguration = getTestConfiguration().getCurrentSyncConfiguration();
			client = new AuthenticatedWSClient(syncConfiguration).login();
		}
		return client;
	}

	protected UserConfiguration getTestConfiguration() {
		File file = FileUtil.getFile(this.getClass(), "testConfig", false);
		if (file == null)
			throw new IllegalStateException("Unable to find testConfig file to configure tests, have a look at testConfig.example");;
		ConfigurationUtil.INSTANCE.loadConfig(file);
		return ConfigurationUtil.INSTANCE.getUserConfiguration();
	}

	protected File getImageFile() throws URISyntaxException {
		return FileUtil.getFile(AbstractTestCase.class, "image.jpg");
	}
	
	protected File getPiwigoImportTreeFile() throws URISyntaxException {
		return FileUtil.getFile(AbstractTestCase.class, LegacyCache.LEGACY_CACHE_FILE_NAME);
	}
}
