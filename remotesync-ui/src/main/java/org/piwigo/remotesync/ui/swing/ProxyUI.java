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

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.piwigo.remotesync.api.conf.GalleryConfig;

public class ProxyUI extends JFrame {

	private static final long serialVersionUID = -7945236553585527567L;

	private JPanel contentPane;
	private JTextField proxyUrltextField;
	private JTextField proxyPorttextField;
	private JTextField proxyLogintextField;
	private JTextField proxyPasswordtextField;
	private JCheckBox chckbxUseProxy;

	public static void run(final GalleryConfig config) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProxyUI frame = new ProxyUI(config);
					frame.chckbxUseProxy.setSelected(false);
					frame.disableTextFields();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param config
	 */
	public ProxyUI(final GalleryConfig config) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chckbxUseProxy = new JCheckBox("Use proxy");
		chckbxUseProxy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				disableTextFields();
			}
		});
		chckbxUseProxy.setBounds(8, 8, 129, 23);
		contentPane.add(chckbxUseProxy);

		JLabel proxyUrlLabel = new JLabel("Url : ");
		proxyUrlLabel.setBounds(12, 49, 94, 15);
		contentPane.add(proxyUrlLabel);

		proxyUrltextField = new JTextField();
		proxyUrltextField.setBounds(123, 47, 311, 19);
		contentPane.add(proxyUrltextField);
		proxyUrltextField.setColumns(10);

		JLabel proxyPortLabel = new JLabel("Port : ");
		proxyPortLabel.setBounds(12, 76, 94, 15);
		contentPane.add(proxyPortLabel);

		proxyPorttextField = new JTextField();
		proxyPorttextField.setBounds(123, 74, 311, 19);
		contentPane.add(proxyPorttextField);
		proxyPorttextField.setColumns(10);

		JLabel proxyLoginLabel = new JLabel("Login : ");
		proxyLoginLabel.setBounds(12, 103, 94, 15);
		contentPane.add(proxyLoginLabel);

		proxyLogintextField = new JTextField();
		proxyLogintextField.setBounds(123, 101, 311, 19);
		contentPane.add(proxyLogintextField);
		proxyLogintextField.setColumns(10);

		JLabel proxyPasswordLabel = new JLabel("Password : ");
		proxyPasswordLabel.setBounds(12, 130, 94, 15);
		contentPane.add(proxyPasswordLabel);

		proxyPasswordtextField = new JPasswordField();
		proxyPasswordtextField.setBounds(123, 128, 311, 19);
		contentPane.add(proxyPasswordtextField);
		proxyPasswordtextField.setColumns(10);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				config.setUsesProxy(Boolean.toString(chckbxUseProxy.isSelected()));
				config.setProxyUrl(proxyUrltextField.getText());
				config.setProxyPort(proxyPorttextField.getText());
				config.setProxyUsername(proxyLogintextField.getText());
				config.setProxyPassword(proxyPasswordtextField.getText());
			}

			@Override
			public void windowOpened(WindowEvent e) {
				super.windowOpened(e);
				chckbxUseProxy.setSelected(config.getUsesProxyBoolean());
				proxyUrltextField.setText(config.getProxyUrl());
				proxyPorttextField.setText(config.getProxyPort() + "");
				proxyLogintextField.setText(config.getProxyUsername());
				proxyPasswordtextField.setText(config.getProxyPassword());
			}

		});
	}

	protected void disableTextFields() {
		proxyUrltextField.setEnabled(chckbxUseProxy.isSelected());
		proxyPorttextField.setEnabled(chckbxUseProxy.isSelected());
		proxyLogintextField.setEnabled(chckbxUseProxy.isSelected());
		proxyPasswordtextField.setEnabled(chckbxUseProxy.isSelected());
	}
}
