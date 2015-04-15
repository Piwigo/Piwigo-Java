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

import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.request.PwgImagesAddFileRequest;
import org.piwigo.remotesync.api.response.PwgImagesAddFileResponse;
import org.piwigo.remotesync.api.util.FileUtil;

public class PwgImagesAddFileWithChunkRequest extends ComposedRequest<PwgImagesAddFileResponse> {

	private PwgImagesAddFileRequest request;

	public PwgImagesAddFileWithChunkRequest(File file, Integer imageId) throws ClientException {
		addRequest(new PwgImagesAddAllChunksRequest(file));
		request = new PwgImagesAddFileRequest(imageId, FileUtil.getMD5Sum(file));
		addRequest(request);
	}
	
	//info : Must be "file", for backward compatiblity "high" and "thumb" are allowed.
	//defaultValue : file
 	public PwgImagesAddFileWithChunkRequest setType(String type) {
		request.setType(type);
		return this;
	}	
}
