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
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.piwigo.remotesync.ui.swing.elements.TexturedButton;

public class UploadUI extends JPanel implements ActionListener
{
	
	private static final long serialVersionUID = -2881611672773022566L;

	private MainUI parent;
	
	private Image uploadBg;
	
	private TexturedButton pickBtn;
	private TexturedButton uploadBtn;
	
	private JLabel directoryLabel;
	private String directoryPath;
	
	public UploadUI(MainUI mainUi)
	{
        setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(null);

		parent = mainUi;
		
		uploadBg = new ImageIcon(this.getClass().getResource("upload.png")).getImage();
		
		pickBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("pickBtn.png")), new ImageIcon(this.getClass().getResource("pickBtn-hover.png")));
		pickBtn.setPosition(594, 374);
		pickBtn.addActionListener(this);
		this.add(pickBtn);
		
		uploadBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("uploadBtn.png")), new ImageIcon(this.getClass().getResource("uploadBtn-hover.png")));
		uploadBtn.setPosition(359, 463);
		uploadBtn.addActionListener(this);
		this.add(uploadBtn);
		
		directoryLabel = new JLabel("");
		directoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		directoryLabel.setFont(directoryLabel.getFont().deriveFont(18f));
		directoryLabel.setBounds(401, 377, 190, 40);
		this.add(directoryLabel);
	}
	
	public void setShownPath(String absolutePath)
	{
		directoryLabel.setText(Paths.get(absolutePath).getName(Paths.get(absolutePath).getNameCount() - 1).toString());
		directoryPath = absolutePath;
	}
	
	public String getPath()
	{
		return (directoryPath);
	}
	
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.drawImage(this.uploadBg, 329, 59, 342, 455, this);
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.pickBtn)
		{
			this.parent.selectDirectory();
		}
		if (e.getSource() == this.uploadBtn)
		{
			this.parent.saveFolder();
			this.parent.nextPanel();
			this.parent.getProgressInterface().checkForSync();
		}
	}
}
