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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class TextField extends JTextField
{

	private static final long serialVersionUID = 1L;
	private String placeholder;
	private boolean isFocused = false;
	
	public TextField(String pHolder)
	{
		this.setPlaceholder(pHolder);
		this.setForeground(Color.BLACK);
		this.setCaretPosition(this.getText().length());
		this.setCaretColor(Color.BLACK);
		this.setOpaque(false);
		this.setBorder(null);
	}
	
	public void setFocused(boolean focused) 
	{
		isFocused = focused;
		this.repaint();
	}
	
	private void setPlaceholder(String pHolder)
	{
		placeholder = pHolder;
	}
	
	public String getPlaceholder()
	{
		return (placeholder);
	}
	
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (placeholder.length() == 0 || getText().length() > 0 || isFocused) {
            return;
        }
        final Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top + 15);
    }
}
