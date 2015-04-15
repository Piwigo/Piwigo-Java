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
package org.piwigo.remotesync.api.request;

import java.io.File;
import java.util.Iterator;

import org.piwigo.remotesync.api.conf.ConfigUtil;
import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.request.PwgImagesAddChunkRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.util.FileUtil;

public class PwgImagesAddAllChunksRequest extends ComposedRequest<BasicResponse> {

	private File file;
	//TODO store it
	private final int chunkSize = 1024 * ConfigUtil.INSTANCE.getUserConfig().getCurrentGalleryConfig().getChunkSize();

	public PwgImagesAddAllChunksRequest(File file) throws ClientException {
		this.file = file;
	}

	@Override
	public Iterator<AbstractRequest<? extends BasicResponse>> iterator() {
		final int chunkNumber = FileUtil.getChunkNumber(file, chunkSize);
		final String md5Sum = FileUtil.getMD5Sum(file);

		return new ComposedRequestIterator(requests) {

			private int chunkId = 0;

			@Override
			public boolean hasNext() {
				return chunkId < chunkNumber;
			}

			@Override
			public AbstractRequest<? extends BasicResponse> next() {
				String data = FileUtil.getBase64String(file, chunkSize, chunkId);
				return new PwgImagesAddChunkRequest(data, md5Sum, chunkId++);
			}
		};
	}
}
