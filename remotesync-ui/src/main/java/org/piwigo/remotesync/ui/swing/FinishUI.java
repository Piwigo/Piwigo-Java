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
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.piwigo.remotesync.ui.swing.elements.TexturedButton;

public class FinishUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4376162330315554530L;

	private MainUI parent;

	private Image uploadBg;

	private TexturedButton syncBtn;
	private TexturedButton logBtn;

	private JLabel photosUploaded;
	private JLabel albumsCreated;

	public FinishUI(MainUI mainUi) {
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(null);

		parent = mainUi;

		uploadBg = new ImageIcon(this.getClass().getResource("sync.png")).getImage();

		syncBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("backBtn.png")),
				new ImageIcon(this.getClass().getResource("backBtn-hover.png")));
		syncBtn.setPosition(187, 460);
		syncBtn.addActionListener(this);
		this.add(syncBtn);

		logBtn = new TexturedButton(new ImageIcon(this.getClass().getResource("logBtn.png")),
				new ImageIcon(this.getClass().getResource("logBtn-hover.png")));
		logBtn.setPosition(527, 460);
		logBtn.addActionListener(this);
		this.add(logBtn);

		photosUploaded = new JLabel("");
		photosUploaded.setHorizontalAlignment(SwingConstants.CENTER);
		photosUploaded.setFont(photosUploaded.getFont().deriveFont(24f));
		photosUploaded.setBounds(340, 337, 320, 40);
		this.add(photosUploaded);

		albumsCreated = new JLabel("");
		albumsCreated.setHorizontalAlignment(SwingConstants.CENTER);
		albumsCreated.setFont(albumsCreated.getFont().deriveFont(24f));
		albumsCreated.setBounds(340, 387, 320, 40);
		this.add(albumsCreated);
	}

	public void getUploadStats() {
		photosUploaded.setText(
				this.parent.getSyncJob().getConnectedWalker().getProgressLinker().getImageCount() + " photos uploaded");
		albumsCreated.setText(
				this.parent.getSyncJob().getConnectedWalker().getProgressLinker().getAlbumCount() + " albums created");
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(this.uploadBg, 378, 55, 241, 242, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.logBtn) {
			if (Desktop.isDesktopSupported())
				try {
					Desktop.getDesktop().edit(this.parent.getRemoteParent().getLogFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		if (e.getSource() == this.syncBtn)
			this.parent.sendToSync();
	}
}
