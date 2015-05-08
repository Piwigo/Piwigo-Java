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
import org.piwigo.remotesync.api.Job;
import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.conf.ConfigUtil;
import org.piwigo.remotesync.api.conf.GalleryConfig;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddSimpleRequest;
import org.piwigo.remotesync.api.sync.SyncCache.FileCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncJob extends Job {

	private static abstract class SyncDirectoryWalker extends DirectoryWalker<File> {

		protected GalleryConfig config;
		protected File startDirectory; 
		protected Map<File, FileCache> fileCaches = new HashMap<File, FileCache>();

		private SyncDirectoryWalker(GalleryConfig config) {
			super(null, Constants.IMAGE_EXTENSIONS_FILTER, -1);
			this.config = config;
			startDirectory = new File(config.getDirectory());
		}
		
		@Override
		protected void handleDirectoryStart(File directory, int depth, Collection<File> results) throws IOException {
			FileCache fileCache = new SyncCache.FileCache(config.getUrl(), SyncCache.getLegacyCacheFile(directory)).parseFile();
			fileCaches.put(directory, fileCache);

			//do not create an album for root directory
			if (directory.equals(startDirectory))
				return;
			
			if (fileCache.albumCache != null) {
				logger.debug("album already in cache : " + directory);
			} else {
				Integer parentAlbumId = null;
				try {
					parentAlbumId = fileCaches.get(directory.getParentFile()).albumCache.id;
				} catch (Exception e) {
					// FIXME : ignore me
				}
				logger.info("will create album for " + directory);
				fileCache.addAlbum(createAlbum(directory, parentAlbumId));
			}
		}
		

		protected void handleFile(File file, int depth, java.util.Collection<File> results) throws IOException {
			FileCache fileCache = fileCaches.get(file.getParentFile());
			
			if (fileCache.containsPicture(file)) {
				logger.debug("picture already in cache : " + file);
			} else {
				Integer albumId = null;
				try {
					albumId = fileCache.albumCache.id;
				} catch (Exception e) {
					// FIXME : ignore me
				}
				logger.info("will upload image for " + file + " in album with id " + albumId);
				fileCache.addPicture(file, createPicture(file, albumId));
			}
		}

		public void walk() throws IOException {
			walk(startDirectory, null);
		}
		
		protected abstract Integer createAlbum(File directory, Integer parentAlbumId);

		protected abstract Integer createPicture(File file, Integer albumId);
	}

//	private static class DummyRunWalker extends SyncDirectoryWalker {
//		
//		private int album_id;
//		private int picture_id;
//		
//		public DummyRunWalker(GalleryConfig config) {
//			super(config);
//		}
//
//		@Override
//		protected Integer createAlbum(File directory, Integer parentAlbumId) {
//			return ++album_id;
//		}
//
//		@Override
//		protected Integer createPicture(File file, Integer albumId) {
//			return ++picture_id;
//		}
//	}
	
	private static class ConnectedWalker extends SyncDirectoryWalker {
		
		private AuthenticatedWSClient client;

		public ConnectedWalker(GalleryConfig config) {
			super(config);
		}
		
		protected void connect() throws IOException {
			try {
				logger.info("Connecting... ");
				client = new AuthenticatedWSClient(config.getUrl()).login(config.getUsername(), config.getPassword());
				logger.info("Connect successful");
			} catch (ClientServerException e) {
				client = null;
				logger.error("Unable to connect : " + e.getMessage());
				throw new CancelException("Unable to connect", startDirectory, 0);
			}
		}

		protected void disconnect() {
			try {
				logger.info("Disconnecting... ");
				client.logout();
				logger.info("Disconnect successful");
			} catch (ClientServerException e) {
				logger.error("Unable to disconnect : " + e.getMessage());
			}
		}
		
		@Override
		protected void handleStart(File startDirectory, Collection<File> results) throws IOException {
			super.handleStart(startDirectory, results);
			connect();
		}
		
		@Override
		protected void handleEnd(Collection<File> results) throws IOException {
			super.handleEnd(results);
			disconnect();
		}
		
		@Override
		protected Integer createAlbum(File directory, Integer parentAlbumId) {
			try {
				return client.sendRequest(new PwgCategoriesAddRequest(directory.getName()).setParent(parentAlbumId)).id;
			} catch (ClientServerException e) {
				logger.error("Cannot create album for " + directory, e);
				return null;
			}
		}

		@Override
		protected Integer createPicture(File file, Integer albumId) {
			try {
				PwgImagesAddSimpleRequest request = new PwgImagesAddSimpleRequest(file);
				//FIXME should we upload a picture without album?
				if (albumId != null)
					request.setCategory(albumId);
				return client.sendRequest(request).image_id;
			} catch (ClientServerException e) {
				logger.error("Cannot updload image for " + file, e);
				return null;
			}
		}
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SyncJob.class);

	protected void doExecute() throws Exception {
		GalleryConfig config = ConfigUtil.INSTANCE.getUserConfig().getCurrentGalleryConfig();

		//TODO shoud validate config
//		GalleryConfigValidator.INSTANCE.validate(config);
		
		logger.info("User {} will sync gallery {} with directory {}", config.getUsername(), config.getUrl(), config.getDirectory());

		try {
			new ConnectedWalker(config).walk();
		} catch (IOException e) {
			logger.error("Error in sync", e);
		}
		
		logger.info("Finished");
	}
}
