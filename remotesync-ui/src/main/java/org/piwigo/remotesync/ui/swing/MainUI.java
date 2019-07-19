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

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.piwigo.remotesync.api.conf.ConfigurationUtil;
import org.piwigo.remotesync.api.conf.SyncConfiguration;
import org.piwigo.remotesync.api.sync.LoginJob;
import org.piwigo.remotesync.api.sync.SyncJob;
import org.piwigo.remotesync.ui.swing.elements.TexturedButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainUI extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(MainUI.class);
	private SyncConfiguration syncConfiguration = ConfigurationUtil.INSTANCE.getUserConfiguration().getCurrentSyncConfiguration();
	private LoginJob loginJob;
	private SyncJob syncJob;
	private RemotesyncUI parent;

	/**
     * INTERFACES
     */
	private JPanel cards = new JPanel();
    private LoginUI loginInterface;
    private UploadUI uploadInterface;
	private ProgressUI progressInterface;
	private FinishUI finishInterface;
    
    /**
     * ELEMENTS
     */
    private Image frameBg;
    private TexturedButton optionsBtn;
    private TexturedButton closeBtn;
    
    public MainUI(RemotesyncUI rsUi)
    {
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(true);
        setLayout(null);
        
        parent = rsUi;

        frameBg = new ImageIcon(this.getClass().getResource("frame.png")).getImage();

        optionsBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("optionsBtn.png")), new ImageIcon(this.getClass().getResource("optionsBtn-hover.png")));
        optionsBtn.setPosition(8, 8);
        optionsBtn.addActionListener(this);
		this.add(optionsBtn);
		
		closeBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("closeBtn.png")), new ImageIcon(this.getClass().getResource("closeBtn-hover.png")));
		closeBtn.setPosition(976, 8);
		closeBtn.addActionListener(this);
		this.add(closeBtn);
		
		cards.setLayout(new CardLayout());
		
        loginInterface = new LoginUI(this);
        loginInterface.setBounds(316, 72, 370, 490);
        cards.add(loginInterface);
        
        uploadInterface = new UploadUI(this);
        uploadInterface.setBounds(330, 91, 342, 455);
        cards.add(uploadInterface);
        
        progressInterface = new ProgressUI(this);
        progressInterface.setBounds(215, 86, 428, 5);
		cards.add(progressInterface);
		
		finishInterface = new FinishUI(this);
		cards.add(finishInterface);
		
        cards.setBounds(1, 33, 998, 567);
        this.add(cards);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.closeBtn)
			System.exit(0);
		if (e.getSource() == this.optionsBtn)
			showOptions();
	}
	
	protected void nextPanel()
	{
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.next(cards);
	}
    
	protected boolean login()
	{
		save();
		loginJob = new LoginJob();
		loginJob.execute();

		while (loginJob.isRunning());
		if (loginJob.hasLogged()) 
			return (true);
		return (false);
	}
	
	protected void sync()
	{
		logger.info("Starting to sync..");
		syncJob = new SyncJob(loginJob);
		syncJob.executeInThread();
		logger.info("Sync is done !");
	}
	
	protected void showOptions()
	{
		OptionsUI.run(syncConfiguration);
	}
	
	protected void save() 
	{
		syncConfiguration.setUrl(this.getLoginInterface().getServerField().getText());
		syncConfiguration.setUsername(this.getLoginInterface().getUsernameField().getText());
		syncConfiguration.setPassword(this.getLoginInterface().getPasswordField().getText());
		ConfigurationUtil.INSTANCE.saveUserConfig();
	}
	
	protected void saveFolder() { 
		syncConfiguration.setDirectory(this.getUploadInterface().getPath());
		ConfigurationUtil.INSTANCE.saveUserConfig();
	}
	
	protected void selectDirectory() 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(this.getUploadInterface().getPath()));
		chooser.setDialogTitle("Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		this.getUploadInterface().setShownPath(chooser.getCurrentDirectory().getAbsolutePath());
		if (chooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
			this.getUploadInterface().setShownPath(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	protected void openLogFile()
	{
		if (Desktop.isDesktopSupported())
		try {
			Desktop.getDesktop().edit(this.getRemoteParent().getLogFile());
		} catch (IOException e1) {}
	}

	protected void sendToSync()
	{
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.previous(cards);
		cardLayout.previous(cards);
		getSyncJob().getConnectedWalker().getProgressLinker().resetStats();
	}
    
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.drawImage(this.frameBg, 0, 0, getWidth(), getHeight(), this);
    }
    
    public RemotesyncUI getRemoteParent()
    {
    	return (parent);
    }
    
    public SyncJob getSyncJob()
    {
    	return (syncJob);
    }

    public LoginUI getLoginInterface()
    {
    	return (loginInterface);
    }

    public UploadUI getUploadInterface()
    {
    	return (uploadInterface);
    }
    
    public ProgressUI getProgressInterface()
    {
    	return (progressInterface);
	}
	
	public FinishUI getFinishInterface()
    {
    	return (finishInterface);
    }
}
