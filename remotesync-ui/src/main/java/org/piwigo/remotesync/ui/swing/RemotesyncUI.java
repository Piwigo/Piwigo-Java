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
package org.piwigo.remotesync.ui.swing;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Timer;

import javax.swing.JFrame;

import org.piwigo.remotesync.api.client.SessionTask;
import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.conf.SyncConfiguration;
import org.piwigo.remotesync.ui.utils.WindowMover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemotesyncUI {

	private static Logger logger = LoggerFactory.getLogger(RemotesyncUI.class);

	private JFrame frame;
	private MainUI mainUi;

	private File logFile;

	private SyncConfiguration syncConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration().getCurrentSyncConfiguration();

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final RemotesyncUI window = new RemotesyncUI();

					window.frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							window.mainUi.save();
							window.mainUi.saveFolder();
							ConfigurationUtil.INSTANCE.saveUserConfig();
						}
					});

					window.load();
					window.frame.setVisible(true);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private RemotesyncUI() {
		initialize();
		setCustomOut();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger.debug("Initialize RemotesyncUI window");
		frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setSize(1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Piwigo Remote Sync");
		frame.setLocationRelativeTo(null);
		frame.setContentPane(mainUi = new MainUI(this));

		WindowMover wm = new WindowMover(frame);
		frame.addMouseListener(wm);
		frame.addMouseMotionListener(wm);

		Timer sessionTimer = new Timer();
		sessionTimer.schedule(new SessionTask(), 60000 * 5, 60000 * 10);
	}

	private void setCustomOut() {
		logFile = new File(ConfigurationUtil.INSTANCE.getUserHomeDirectory(), "log.txt");
		PrintStream ps = null;
		try  {
			ps = new PrintStream(logFile);
		}  catch (FileNotFoundException e) {
			return;
		}
		System.setOut(ps);
	}

	public File getLogFile()
	{
		return (logFile);
	}

	protected void proxy() {
		logger.debug("RemotesyncUI proxy button pressed");
		OptionsUI.run(syncConfiguration);
	}
	
	protected void load() {
		this.mainUi.getLoginInterface().getServerField().setText(syncConfiguration.getUrl());
		this.mainUi.getLoginInterface().getUsernameField().setText(syncConfiguration.getUsername());
		this.mainUi.getLoginInterface().getPasswordField().setText(syncConfiguration.getPassword());
		this.mainUi.getUploadInterface().setShownPath(syncConfiguration.getDirectory());
	}
}
