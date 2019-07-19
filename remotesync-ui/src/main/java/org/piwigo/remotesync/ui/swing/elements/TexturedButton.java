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
package org.piwigo.remotesync.ui.swing.elements;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TexturedButton extends JButton implements MouseListener
{
	
	private static final long serialVersionUID = 1L;

	private ImageIcon texture;
	private ImageIcon textureHover;
	
	public TexturedButton(ImageIcon tex, ImageIcon hoverTex) 
	{
		texture = tex;
		textureHover = hoverTex;
		
		this.setIcon(tex);
		this.setPressedIcon(hoverTex);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.addMouseListener(this);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void setPosition(int x, int y)
	{
		this.setBounds(x, y, texture.getIconWidth(), texture.getIconHeight());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
        if (e.getSource() == this)
            this.setIcon(textureHover);
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		if (e.getSource() == this)
			this.setIcon(texture);
	}
	
	
}
