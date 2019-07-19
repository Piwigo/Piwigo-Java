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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ProgressUI extends JPanel
{
	
	private static final long serialVersionUID = 2785727874232650217L;

	private MainUI parent;
	
	private BufferedImage currentImage;
	
	private File imageFile;
	
	private JLabel uploadLabel;
	
	private int imageId = 0;
	
	public ProgressUI(MainUI mainUi)
	{
        setBackground(new Color(120, 255, 120, 120));
		setOpaque(false);
		setLayout(null);

		parent = mainUi;
		
		uploadLabel = new JLabel("");
		uploadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uploadLabel.setFont(uploadLabel.getFont().deriveFont(24f));
		uploadLabel.setBounds(296, 489, 407, 40);
		this.add(uploadLabel);
	}
	
	public void checkForSync()
	{
		this.parent.sync();
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (parent.getSyncJob().isRunning()) {
					repaint();
					if (parent.getSyncJob().getConnectedWalker().getProgressLinker().getImageId() != imageId) {
						imageFile = parent.getSyncJob().getConnectedWalker().getProgressLinker().getImagePreview();
						resizeImg(imageFile, 725, 408);
						uploadLabel.setText("Uploading " + parent.getSyncJob().getConnectedWalker().getProgressLinker().getImageName());
						imageId++;
						repaint();
					}
				}
				parent.nextPanel();
				parent.getFinishInterface().getUploadStats();
			}
		});
		t.start();
	}
	
	private void resizeImg(File imgFile, int width, int height) {
	    int type = BufferedImage.TYPE_INT_ARGB;
	    BufferedImage current = null;
		try 
		{
			if (imgFile == null)
				return;
			current = ImageIO.read(imgFile);
		} catch (IOException e) {
			return;
		}
		if (current.getWidth() < width || current.getHeight() < height) {
			currentImage = current;
			return;
		}
	    BufferedImage resizedImage = new BufferedImage(width, height, type);
	    Graphics2D g = resizedImage.createGraphics();

	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.drawImage(current, 0, 0, width, height, this);
	    g.dispose();
	    currentImage = resizedImage;
	}

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        if (this.currentImage != null)
        	graphics.drawImage(currentImage, 499 - (currentImage.getWidth() / 2), 247 - (currentImage.getHeight() / 2), currentImage.getWidth(), currentImage.getHeight(), this);
    }
}
