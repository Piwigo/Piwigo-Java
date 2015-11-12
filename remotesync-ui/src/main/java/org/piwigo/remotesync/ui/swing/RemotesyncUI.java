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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.conf.SyncConfiguration;
import org.piwigo.remotesync.api.sync.SyncJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;

public class RemotesyncUI {

	private static Logger logger = LoggerFactory.getLogger(RemotesyncUI.class);

	private JFrame frame;
	private JTextField urlText;
	private JTextField usernameText;
	private JTextField passwordText;
	private JTextField directoryText;
	private JButton syncButton;
	private JButton directoryButton;
	private JTextArea textArea;
	private JButton optionsButton;

	private SyncConfiguration syncConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration().getCurrentSyncConfiguration();
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void run() {
		// logger.debug("RemotesyncUI loaded");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// logger.debug("Create RemotesyncUI window");
					final RemotesyncUI window = new RemotesyncUI();
					window.configureLogger();
					
					window.frame.addWindowListener(new WindowAdapter(){
			            public void windowClosing(WindowEvent e){
			            	window.save();
			            	ConfigurationUtil.INSTANCE.saveUserConfig();
			            }
			        });
					
					window.load();
					window.frame.setVisible(true);
					logger.debug("RemotesyncUI window created");
				} catch (Exception e) {
					logger.error("Exception in RemotesyncUI window", e);
					throw new RuntimeException(e);
				}
			}
		});
	}

	private void configureLogger() {
		LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

		Appender<ILoggingEvent> textAreaAppender = new AppenderBase<ILoggingEvent>() {

			@Override
			protected void append(final ILoggingEvent eventObject) {
				textArea.append(eventObject.toString() + "\n");
				try {
					textArea.scrollRectToVisible(textArea.modelToView(textArea.getDocument().getLength()));
				} catch (BadLocationException e) {
				}
			}

		};
		textAreaAppender.setContext(logCtx);
		textAreaAppender.setName("textArea");
		textAreaAppender.start();

		((ch.qos.logback.classic.Logger) LoggerFactory.getILoggerFactory().getLogger("ROOT")).addAppender(textAreaAppender);
	}

	/**
	 * Create the application.
	 */
	private RemotesyncUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger.debug("Initialize RemotesyncUI window");
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 723, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Piwigo Remote Sync");
		frame.getContentPane().setLayout(null);

		JLabel urlLabel = new JLabel("Piwigo URL : ");
		urlLabel.setBounds(25, 26, 136, 36);
		frame.getContentPane().add(urlLabel);

		urlText = new JTextField();
		urlText.setBounds(149, 26, 558, 36);
		frame.getContentPane().add(urlText);
		urlText.setColumns(10);

		JLabel usernameLabel = new JLabel("Username : ");
		usernameLabel.setBounds(25, 74, 136, 36);
		frame.getContentPane().add(usernameLabel);

		usernameText = new JTextField();
		usernameText.setBounds(149, 75, 558, 36);
		frame.getContentPane().add(usernameText);
		usernameText.setColumns(10);

		JLabel passwordLabel = new JLabel("Password : ");
		passwordLabel.setBounds(25, 122, 136, 36);
		frame.getContentPane().add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(149, 123, 558, 36);
		frame.getContentPane().add(passwordText);
		passwordText.setColumns(10);

		directoryButton = new JButton("Select");
		directoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDirectory();
			}
		});
		directoryButton.setBounds(25, 170, 94, 36);
		frame.getContentPane().add(directoryButton);

		directoryText = new JTextField();
		directoryText.setBounds(149, 171, 558, 36);
		frame.getContentPane().add(directoryText);
		directoryText.setColumns(10);

		optionsButton = new JButton("Options");
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proxy();
			}
		});
		optionsButton.setBounds(25, 257, 94, 36);
		frame.getContentPane().add(optionsButton);

		syncButton = new JButton("Sync");
		syncButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sync();
			}
		});
		syncButton.setBounds(25, 323, 94, 36);
		frame.getContentPane().add(syncButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 219, 559, 179);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		logger.debug("RemotesyncUI window initialized");
	}

	protected void proxy() {
		logger.debug("RemotesyncUI proxy button pressed");
		OptionsUI.run(syncConfiguration);
	}

	protected void selectDirectory() {
		logger.debug("RemotesyncUI select directory button pressed");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(directoryText.getText()));
		chooser.setDialogTitle("Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		directoryText.setText(chooser.getCurrentDirectory().getAbsolutePath());
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			directoryText.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	protected void sync() {
		logger.debug("RemotesyncUI sync button pressed");

		save();

		new SyncJob().executeInThread();

		logger.debug("RemotesyncUI sync button action finished");
	}
	
	protected void load() {
		urlText.setText(syncConfiguration.getUrl());
		usernameText.setText(syncConfiguration.getUsername());
		passwordText.setText(syncConfiguration.getPassword());
		directoryText.setText(syncConfiguration.getDirectory());
	}

	protected void save() {
		syncConfiguration.setUrl(urlText.getText());
		syncConfiguration.setUsername(usernameText.getText());
		syncConfiguration.setPassword(passwordText.getText());
		syncConfiguration.setDirectory(directoryText.getText());
	}
}
