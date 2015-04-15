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
package org.piwigo.remotesync.ui.pivot.reflection;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.FileBrowserSheet.Mode;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.skin.terra.TerraPushButtonSkin;
import org.piwigo.remotesync.api.util.FileUtil;

public class FileInput extends BoxPane {
	private File file;
	private PushButton pushButton;
	private boolean valid;

	public FileInput() {
		//TODO is there a better way
		pushButton = new PushButton() {
			Color defaultColor;

			@Override
			public void paint(Graphics2D graphics) {
				TerraPushButtonSkin terraPushButtonSkin = (TerraPushButtonSkin) getSkin();
				if (defaultColor == null)
					defaultColor = terraPushButtonSkin.getBackgroundColor();
				if (valid)
					terraPushButtonSkin.setBackgroundColor(defaultColor);
				else
					terraPushButtonSkin.setBackgroundColor(Color.red);
				super.paint(graphics);
			}
		};
		pushButton.setButtonData("...");
		pushButton.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button button) {
				final FileBrowserSheet fileBrowserSheet = new FileBrowserSheet();
				fileBrowserSheet.setMode(Mode.OPEN);
				fileBrowserSheet.open(getWindow(), new SheetCloseListener() {
					@Override
					public void sheetClosed(Sheet sheet) {
						file = fileBrowserSheet.getSelectedFile();
						setValid(FileUtil.isImage(file));
						pushButton.setTooltipText(file.getAbsolutePath());
					}
				});
			}
		});

		add(pushButton);
		setValid(false);
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
		pushButton.repaint();
	}

	public File getFile() {
		return file;
	}
}
