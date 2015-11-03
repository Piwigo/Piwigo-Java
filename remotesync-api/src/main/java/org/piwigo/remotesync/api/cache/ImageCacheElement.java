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
package org.piwigo.remotesync.api.cache;
public class ImageCacheElement extends AbstractCacheElement {
	
	protected String filePathMD5;

	public ImageCacheElement() {
	}

	public ImageCacheElement(String url, Integer id, String filePathMD5) {
		super(url, id);
		this.filePathMD5 = filePathMD5;
	}

	public String getFilePathMD5() {
		return filePathMD5;
	}
	
	@Override
	public String writeToString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(url);
		stringBuilder.append(" ");
		stringBuilder.append(filePathMD5);
		stringBuilder.append(" [id=");
		stringBuilder.append(id);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
