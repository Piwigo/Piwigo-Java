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
import java.net.URL;

import junit.framework.TestCase;

import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.client.Client;
import org.piwigo.remotesync.api.conf.Config;
import org.piwigo.remotesync.api.conf.ConfigUtil;
import org.piwigo.remotesync.api.conf.GalleryConfig;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public abstract class AbstractTestCase extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(AbstractTestCase.class);
	
	static {
		((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(Level.INFO);
	}
	
	private static Client client;

	protected Client getClient() throws ClientServerException {
		if (client == null) {
			GalleryConfig galleryConfig = getTestConfig().getCurrentGalleryConfig();
			client = new AuthenticatedWSClient(galleryConfig.getUrl()).login(galleryConfig.getUsername(), galleryConfig.getPassword());
			
		}
		return client;
	}

	protected Config getTestConfig() {
		URL resource = AbstractTestCase.class.getResource("testConfig");
		if (resource == null) {
			IllegalStateException exception = new IllegalStateException("Unable to find testConfig file to configure tests, have a look at testConfig.example");
			logger.error(exception.getMessage(), exception);
			throw exception;
		}
		return ConfigUtil.INSTANCE.loadConfig(new File(resource.getPath()));
	}

	protected File getPictureFile() throws URISyntaxException {
		return new File(AbstractTestCase.class.getResource("picture.jpg").toURI());
	}
}
