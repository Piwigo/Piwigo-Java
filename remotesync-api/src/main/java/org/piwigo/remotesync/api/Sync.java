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
package org.piwigo.remotesync.api;

import java.io.File;

import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.conf.GalleryConfig;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesGetImagesRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesGetListRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddSimpleRequest;
import org.piwigo.remotesync.api.response.PwgCategoriesGetImagesResponse;
import org.piwigo.remotesync.api.response.PwgCategoriesGetListResponse;
import org.piwigo.remotesync.api.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sync {

	private static final Logger logger = LoggerFactory.getLogger(Sync.class);
	private GalleryConfig config;

	// FIXME move in UI
	private static boolean running;
	private AuthenticatedWSClient client;

	public Sync(GalleryConfig config) {
		this.config = config;
	}

	protected void doSync() {
		logger.info("User {} will sync gallery {} with directory {}", config.getUsername(), config.getUrl(), config.getDirectory());
		File file = new File(config.getDirectory());
		if (connect()) {
			searchPictures(file);
			countCategories();
			countImages();
			try {
				uploadPictures(file, null);
			} catch (Exception e) {
				logger.error("cannot upload", e);
			}
			countCategories();
			countImages();
		}
	}

	private boolean connect() {
		try {
			logger.info("Connecting... ");
			client = new AuthenticatedWSClient(config.getUrl()).login(config.getUsername(), config.getPassword());
			logger.info("Connect successful");
			return true;
		} catch (ClientServerException e) {
			logger.error("Unable to connect : " + e.getMessage());
		}
		return false;
	}

	private void uploadPictures(File parentFile, Integer idCategory) throws Exception {
		// /tmp/jloader/jloader_dataset/Croome Court
		String albumName = parentFile.getName();
		logger.info("creating album " + albumName);
		idCategory = client.sendRequest(new PwgCategoriesAddRequest(albumName).setParent(idCategory)).id;
		for (File file : parentFile.listFiles()) {
			if (FileUtil.isImage(file)) {
				logger.info("uploading image " + file.getName());
				client.sendRequest(new PwgImagesAddSimpleRequest(file).setCategory(idCategory));
			} else if (file.isDirectory())
				uploadPictures(file, idCategory);
		}
	}

	private void countCategories() {
		try {
			PwgCategoriesGetListResponse categoriesGetListResponse = client.sendRequest(new PwgCategoriesGetListRequest().setRecursive(true));
			logger.info("found {} categories", categoriesGetListResponse.categories.size());
		} catch (ClientServerException e) {
		}
	}

	private void countImages() {
		try {
			PwgCategoriesGetImagesResponse getImagesResponse = client.sendRequest(new PwgCategoriesGetImagesRequest().setRecursive(false));
			logger.info("found {} categories", getImagesResponse);
		} catch (ClientServerException e) {
		}
	}

	private void searchPictures(File file) {
		for (File child : file.listFiles()) {
			logger.info("Listing " + child);
		}
	}

	public synchronized void sync() {
		logger.debug("running " + running);
		if (running) {
			logger.info("Already synchronizing");
			return;
		}
		running = true;

		try {
			doSync();
		} catch (Exception e) {
			// FIXME
		} finally {
			running = false;
		}
	}

	public void syncInThread() {
		new Thread(new Runnable() {

			public void run() {
				sync();
			}
		}).start();
	}

	public boolean isRunning() {
		return running;
	}

}
