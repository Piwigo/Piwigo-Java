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
import org.piwigo.remotesync.api.response.PwgImagesAddCommentResponse;

/**
Adds a comment to an image.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesAddCommentRequest extends AbstractRequest<PwgImagesAddCommentResponse> {

	protected PwgImagesAddCommentRequest() {}

	public PwgImagesAddCommentRequest(Integer image_id, String content, String key) {
		setImageId(image_id);
		setContent(content);
		setKey(key);
	}

 	protected PwgImagesAddCommentRequest setImageId(Integer image_id) {
		addParameterValue("image_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, image_id);
		return this;
	}	

	//defaultValue : guest
 	public PwgImagesAddCommentRequest setAuthor(String author) {
		addParameterValue("author", org.piwigo.remotesync.api.type.Type.MIXED, null, author);
		return this;
	}	

 	protected PwgImagesAddCommentRequest setContent(String content) {
		addParameterValue("content", org.piwigo.remotesync.api.type.Type.MIXED, null, content);
		return this;
	}	

 	protected PwgImagesAddCommentRequest setKey(String key) {
		addParameterValue("key", org.piwigo.remotesync.api.type.Type.MIXED, null, key);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return false;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.images.addComment";
	}

	public Class<PwgImagesAddCommentResponse> getReturnType() {
		return PwgImagesAddCommentResponse.class;
	}
}
