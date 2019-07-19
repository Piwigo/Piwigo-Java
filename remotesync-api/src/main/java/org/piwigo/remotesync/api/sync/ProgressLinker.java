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
package org.piwigo.remotesync.api.sync;

import java.io.File;

public class ProgressLinker 
{
	
	private String imageName;
	
	private File currentImage;
	
	private int imageId = 0;

	private int imageCount = 0;

	private int albumCount = 0;
	
	public String getImageName()
	{
		return (imageName);
	}

	public File getImagePreview()
	{
		return (currentImage);
	}
	
	public int getImageId()
	{
		return (imageId);
	}

	public int getImageCount()
	{
		return (imageCount);
	}

	public int getAlbumCount()
	{
		return (albumCount);
	}
	
	public void setImageName(String name)
	{
		imageName = name;
	}
	
	public void setCurrentImage(File img)
	{
		currentImage = img;
	}
	
	public void fillImageDetails(File img)
	{
		setCurrentImage(img);
		setImageName(img.getName());
		imageId++;
	}

	public void addAlbum()
	{
		albumCount++;
	}

	public void addImage()
	{
		imageCount++;
	}

	public void removeAlbum()
	{
		albumCount--;
	}

	public void removeImage()
	{
		imageCount--;
	}

	public void resetStats()
	{
		imageCount = 0;
		albumCount = 0;
	}
}
