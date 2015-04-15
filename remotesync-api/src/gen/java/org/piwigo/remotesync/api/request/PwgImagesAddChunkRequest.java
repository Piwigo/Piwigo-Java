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

import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.PwgImagesAddChunkResponse;

/**
Add a chunk of a file.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesAddChunkRequest extends AbstractRequest<PwgImagesAddChunkResponse> {

	protected PwgImagesAddChunkRequest() {}

	public PwgImagesAddChunkRequest(String data, String original_sum, Integer position) {
		setData(data);
		setOriginalSum(original_sum);
		setPosition(position);
	}

 	protected PwgImagesAddChunkRequest setData(String data) {
		addParameterValue("data", org.piwigo.remotesync.api.type.Type.MIXED, null, data);
		return this;
	}	

 	protected PwgImagesAddChunkRequest setOriginalSum(String original_sum) {
		addParameterValue("original_sum", org.piwigo.remotesync.api.type.Type.MIXED, null, original_sum);
		return this;
	}	

	//info : Must be "file", for backward compatiblity "high" and "thumb" are allowed.
	//defaultValue : file
 	public PwgImagesAddChunkRequest setType(String type) {
		addParameterValue("type", org.piwigo.remotesync.api.type.Type.MIXED, null, type);
		return this;
	}	

 	protected PwgImagesAddChunkRequest setPosition(Integer position) {
		addParameterValue("position", org.piwigo.remotesync.api.type.Type.INT, null, position);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.images.addChunk";
	}

	public Class<PwgImagesAddChunkResponse> getReturnType() {
		return PwgImagesAddChunkResponse.class;
	}
}
