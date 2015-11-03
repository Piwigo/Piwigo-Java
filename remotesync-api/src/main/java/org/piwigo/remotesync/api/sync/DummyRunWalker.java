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

import org.piwigo.remotesync.api.conf.SyncConfiguration;

public class DummyRunWalker extends SyncDirectoryWalker {

	private int album_id;
	private int image_id;

	public DummyRunWalker(SyncConfiguration syncConfiguration) {
		super(syncConfiguration);
	}

	@Override
	protected Integer createAlbum(File directory, Integer parentAlbumId) {
		return ++album_id;
	}

	@Override
	protected Integer createImage(File file, Integer albumId) {
		return ++image_id;
	}
}