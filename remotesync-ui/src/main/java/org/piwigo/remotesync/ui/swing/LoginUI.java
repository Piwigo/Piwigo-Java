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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.piwigo.remotesync.ui.swing.elements.PassField;
import org.piwigo.remotesync.ui.swing.elements.TextField;
import org.piwigo.remotesync.ui.swing.elements.TexturedButton;

public class LoginUI extends JPanel implements ActionListener, FocusListener
{

	private static final long serialVersionUID = 1L;

	private MainUI parent;
	
	private Image loginBg;
	
	private TextField serverField;
	private TextField usernameField;
	private PassField passwordField;
	private TexturedButton loginBtn;
	
	public LoginUI(MainUI mainUi)
	{        
        setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(null);

		parent = mainUi;
		
		loginBg = new ImageIcon(this.getClass().getResource("login.png")).getImage();
		
		serverField = new TextField("Server address");
		serverField.setBounds(418, 243, 209, 48);
		serverField.setFocusable(true);
		serverField.addFocusListener(this);
		this.add(serverField);
		
		usernameField = new TextField("Username");
		usernameField.setBounds(418, 318, 209, 48);
		usernameField.setFocusable(true);
		usernameField.addFocusListener(this);
		this.add(usernameField);
		
		passwordField = new PassField("Password");
		passwordField.setBounds(418, 393, 209, 48);
		passwordField.setFocusable(true);
		passwordField.addFocusListener(this);
		this.add(passwordField);
		
		loginBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("loginBtn.png")), new ImageIcon(this.getClass().getResource("loginBtn-hover.png")));
		loginBtn.setPosition(358, 477);
		loginBtn.addActionListener(this);
		this.add(loginBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.loginBtn) {
			if (this.parent.login()) {
				this.parent.save();
				this.parent.nextPanel();
			}
			else
				JOptionPane.showMessageDialog(new JFrame(), "Login failed. Please check your credentials.");
		}
	}
	
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.drawImage(this.loginBg, 316, 40, 367, 490, this);
    }

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getComponent() instanceof TextField)
			((TextField) e.getComponent()).setFocused(true);
		if (e.getComponent() instanceof PassField)
			((PassField) e.getComponent()).setFocused(true);
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getComponent() instanceof TextField)
			((TextField) e.getComponent()).setFocused(false);
		if (e.getComponent() instanceof PassField)
			((PassField) e.getComponent()).setFocused(false);
	}
	
	public TextField getServerField()
	{
		return (serverField);
	}
	
	public TextField getUsernameField()
	{
		return (usernameField);
	}
	
	public PassField getPasswordField()
	{
		return (passwordField);
	}

}
