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
package org.piwigo.remotesync.api.conf;

import java.io.File;

import org.piwigo.remotesync.api.xml.PersisterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigUtil {

	private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	public static final ConfigUtil INSTANCE = new ConfigUtil();

	protected Config userConfig;

	public File getUserCurrentDirectory() {
		return new File(System.getProperty("user.dir"));
	}

	public File getUserHomeDirectory() {
		return new File(System.getProperty("user.home"));
	}

	public File getUserConfigFile() {
		return new File(getUserHomeDirectory(), ".config/piwigo/remotesync");
	}

	public Config getUserConfig() {
		if (userConfig == null)
			userConfig = loadUserConfig();
		return userConfig;
	}

	public Config loadUserConfig() {
		userConfig = loadConfig(getUserConfigFile());
		return userConfig;
	}

	public void saveUserConfig() {
		saveConfig(getUserConfigFile(), userConfig);
	}

	public Config loadConfig(File configFile) {
		if (configFile.exists()) {
			logger.debug("found userConfig file " + configFile.getAbsolutePath());
			try {
				userConfig = PersisterFactory.createPersister().read(Config.class, configFile);
				logger.debug("configuration loaded");
				return userConfig;
			} catch (Exception e) {
				logger.error("unable to read userConfig file", e);
			}
		} else
			logger.debug("no userConfig file found");

		return new Config();
	}

	public void saveConfig(File configFile, Config config) {
		File configDirectory = configFile.getParentFile();

		if (!configDirectory.exists())
			if (!configDirectory.mkdirs()) {
				logger.debug("unable to create userConfig directory {}", configDirectory.getAbsolutePath());
				return;
			} else
				logger.debug("userConfig directory {} created", configDirectory.getAbsolutePath());

		if (getUserConfigFile().exists())
			logger.debug("found userConfig file " + configFile.getAbsolutePath());

		try {
			PersisterFactory.createPersister().write(config, configFile);
			logger.debug("configuration written");
		} catch (Exception e) {
			logger.error("unable to write userConfig file", e);
		}
	}
}
