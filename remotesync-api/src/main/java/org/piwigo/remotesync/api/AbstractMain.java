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

import org.kohsuke.args4j.ClassParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;
import org.piwigo.remotesync.api.conf.Config;
import org.piwigo.remotesync.api.conf.ConfigUtil;
import org.piwigo.remotesync.api.conf.GalleryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public abstract class AbstractMain {
	private static final Logger logger = LoggerFactory.getLogger(AbstractMain.class);

	protected void run(String[] args) {
		GalleryConfig parsedGalleryConfig = new GalleryConfig();
		CmdLineParser cmdLineParser = createParser(parsedGalleryConfig);

		try {
			cmdLineParser.parseArgument(args);

			configureLog(debug);
			
			createConfig(parsedGalleryConfig);
			
			if (help) {
				System.out.println("Piwigo Remote Sync : java -jar remotesync.jar");
				cmdLineParser.printUsage(System.out);
				return;
			}

			start();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			System.err.println("Piwigo Remote Sync : java -jar remotesync.jar");
			cmdLineParser.printUsage(System.err);
			System.err.println();
			System.err.println(" Example: java -jar remotesync.jar" + cmdLineParser.printExample(OptionHandlerFilter.ALL));
		}
	}

	protected abstract void start();

	@Option(name = "-debug", usage = "enable debug messages")
	protected boolean debug = false;
	
	@Option(name = "-help", usage = "help")
	protected boolean help = false;

	protected CmdLineParser createParser(GalleryConfig galleryConfig) {
		CmdLineParser cmdLineParser = new CmdLineParser(null);
		new ClassParser().parse(this, cmdLineParser);
		new ClassParser().parse(galleryConfig, cmdLineParser);
		return cmdLineParser;
	}

	protected void createConfig(GalleryConfig parsedGalleryConfig) {
		Config config = ConfigUtil.INSTANCE.getUserConfig();
		
		//if some gallery configuration was provided through parameters
		if (!parsedGalleryConfig.isEmpty()) 
			config.setCurrentGalleryConfig(parsedGalleryConfig);
	}

	protected void configureLog(boolean debug) {
		if (debug) {
			((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(Level.DEBUG);
			logger.debug("Debug is enabled");
		} else
			((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(Level.INFO);
	}

}