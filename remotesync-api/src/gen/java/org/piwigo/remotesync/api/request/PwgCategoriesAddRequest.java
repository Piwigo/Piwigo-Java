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
import org.piwigo.remotesync.api.response.PwgCategoriesAddResponse;

/**
Adds an album.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesAddRequest extends AbstractRequest<PwgCategoriesAddResponse> {

	protected PwgCategoriesAddRequest() {}

	public PwgCategoriesAddRequest(String name) {
		setName(name);
	}

 	protected PwgCategoriesAddRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgCategoriesAddRequest setParent(Integer parent) {
		addParameterValue("parent", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, parent);
		return this;
	}	

 	public PwgCategoriesAddRequest setComment(String comment) {
		addParameterValue("comment", org.piwigo.remotesync.api.type.Type.MIXED, null, comment);
		return this;
	}	

	//defaultValue : 1
 	public PwgCategoriesAddRequest setVisible(Boolean visible) {
		addParameterValue("visible", org.piwigo.remotesync.api.type.Type.BOOL, null, visible);
		return this;
	}	

	//info : public, private
 	public PwgCategoriesAddRequest setStatus(Status status) {
		addParameterValue("status", org.piwigo.remotesync.api.type.Type.ENUM, null, status);
		return this;
	}	

	//defaultValue : 1
 	public PwgCategoriesAddRequest setCommentable(Boolean commentable) {
		addParameterValue("commentable", org.piwigo.remotesync.api.type.Type.BOOL, null, commentable);
		return this;
	}	

	public enum Status {
		PUBLIC,
		PRIVATE
	}

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "pwg.categories.add";
	}

	public Class<PwgCategoriesAddResponse> getReturnType() {
		return PwgCategoriesAddResponse.class;
	}
}
