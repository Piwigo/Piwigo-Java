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

import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.client.AuthenticatedWSClient;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddSimpleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectedWalker extends SyncDirectoryWalker {

	private static final Logger logger = LoggerFactory.getLogger(ConnectedWalker.class);

	private AuthenticatedWSClient client;

	public ConnectedWalker(ISyncConfiguration syncConfiguration) {
		super(syncConfiguration);
	}

	protected void connect() throws IOException {
		try {
			logger.info("Connecting... ");
			client = new AuthenticatedWSClient(syncConfiguration).login();
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
		//TODO only connect when needed
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
	protected Integer createImage(File file, Integer albumId) {
		try {
			PwgImagesAddSimpleRequest request = new PwgImagesAddSimpleRequest(file);
			// FIXME should we upload an image without album?
			if (albumId != null)
				request.setCategory(albumId);
			return client.sendRequest(request).image_id;
		} catch (ClientServerException e) {
			logger.error("Cannot updload image for " + file, e);
			return null;
		}
	}

}
