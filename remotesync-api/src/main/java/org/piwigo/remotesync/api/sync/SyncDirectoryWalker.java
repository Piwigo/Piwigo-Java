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
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.DirectoryWalker;
import org.piwigo.remotesync.api.Constants;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.cache.LegacyCache;
import org.piwigo.remotesync.menalto.Importer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SyncDirectoryWalker extends DirectoryWalker<File> {

	private static final Logger logger = LoggerFactory.getLogger(SyncDirectoryWalker.class);

	protected ISyncConfiguration syncConfiguration;
	protected File startDirectory;
	protected Map<File, LegacyCache> legacyCaches = new HashMap<File, LegacyCache>();
	private Importer importer;

	protected SyncDirectoryWalker(ISyncConfiguration syncConfiguration) {
		super(null, Constants.IMAGE_EXTENSIONS_FILTER, -1);
		this.syncConfiguration = syncConfiguration;
		importer = new Importer(syncConfiguration.getDirectory());
	}

	@Override
	protected void handleDirectoryStart(File directory, int depth, Collection<File> results) throws IOException {
		LegacyCache legacyCache = new LegacyCache(syncConfiguration.getUrl(), LegacyCache.getLegacyCacheFile(directory)).parseFile();
		legacyCaches.put(directory, legacyCache);

		// do not create an album for root directory
		if (directory.equals(startDirectory))
			return;

		if( !importer.shouldImport(directory.getAbsolutePath()))
			return;

		if (legacyCache.getAlbumCacheElement() != null) {
			logger.debug("Album is already in cache : " + directory);
		} else {
			Integer parentAlbumId = null;
			try {
				parentAlbumId = legacyCaches.get(directory.getParentFile()).getAlbumCacheElement().getId();
			} catch (Exception e) {
				// FIXME : ignore me
			}
			logger.info("Creating album for " + directory);
			Integer albumId = createAlbum(directory, parentAlbumId);
			legacyCache.addAlbum(albumId);
			importer.addAlbum(directory.getAbsolutePath(), albumId);
		}
	}

	protected void handleFile(File file, int depth, java.util.Collection<File> results) throws IOException {
		LegacyCache legacyCache = legacyCaches.get(file.getParentFile());

		if( !importer.shouldImport(file.getAbsolutePath()))
			return;

		if (legacyCache.containsImage(file)) {
			logger.debug("Image already in cache : " + file);
		} else {
			Integer albumId = null;
			try {
				albumId = legacyCache.getAlbumCacheElement().getId();
			} catch (Exception e) {
				// FIXME : ignore me
			}
			logger.info("Uploading " + file.getName() + " in album with ID " + albumId);
			Integer imageId = createImage(file, albumId);
			legacyCache.addImage(file, imageId);
			importer.addImage(file.getAbsolutePath(), albumId, imageId);
		}
	}

	public void walk() throws IOException {
		this.startDirectory = new File(syncConfiguration.getDirectory());
		walk(startDirectory, null);
	}

	protected abstract Integer createAlbum(File directory, Integer parentAlbumId) throws IOException;

	protected abstract Integer createImage(File file, Integer albumId) throws IOException;
}
