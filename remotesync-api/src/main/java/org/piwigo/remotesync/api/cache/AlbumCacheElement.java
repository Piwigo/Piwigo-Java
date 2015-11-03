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

public class AlbumCacheElement extends AbstractCacheElement {

	public AlbumCacheElement() {
		super();
	}

	public AlbumCacheElement(String url, Integer id) {
		super(url, id);
	}

	@Override
	public String writeToString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(url);
		stringBuilder.append(" album_id = ");
		stringBuilder.append(id);
		return stringBuilder.toString();
	}
}
