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
import org.piwigo.remotesync.api.response.PwgPermissionsAddResponse;

/**
Adds permissions to an album.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgPermissionsAddRequest extends AbstractRequest<PwgPermissionsAddResponse> {

	protected PwgPermissionsAddRequest() {}

	public PwgPermissionsAddRequest(Integer cat_id) {
		setCatId(cat_id);
	}

	public PwgPermissionsAddRequest(Integer... cat_id) {
		setCatId(java.util.Arrays.asList(cat_id));
	}

	public PwgPermissionsAddRequest(java.util.List<Integer> cat_idList) {
		setCatId(cat_idList);
	}

 	protected PwgPermissionsAddRequest setCatId(Integer cat_id) {
		addParameterValue("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, cat_id);
		return this;
	}	

	protected PwgPermissionsAddRequest setCatId(Integer... cat_id) {
		addParameterValueList("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(cat_id));
		return this;
	}

	protected PwgPermissionsAddRequest setCatId(java.util.List<Integer> cat_idList) {
		addParameterValueList("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, cat_idList);
		return this;
	}

 	public PwgPermissionsAddRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

	public PwgPermissionsAddRequest setGroupId(Integer... group_id) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(group_id));
		return this;
	}

	public PwgPermissionsAddRequest setGroupId(java.util.List<Integer> group_idList) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_idList);
		return this;
	}

 	public PwgPermissionsAddRequest setUserId(Integer user_id) {
		addParameterValue("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_id);
		return this;
	}	

	public PwgPermissionsAddRequest setUserId(Integer... user_id) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(user_id));
		return this;
	}

	public PwgPermissionsAddRequest setUserId(java.util.List<Integer> user_idList) {
		addParameterValueList("user_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, user_idList);
		return this;
	}

	//defaultValue : 0
 	public PwgPermissionsAddRequest setRecursive(Boolean recursive) {
		addParameterValue("recursive", org.piwigo.remotesync.api.type.Type.BOOL, null, recursive);
		return this;
	}	

	public boolean isNeedPwgToken() {
		return true;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return true;
	};

	public String getWSMethodName() {
		return "pwg.permissions.add";
	}

	public Class<PwgPermissionsAddResponse> getReturnType() {
		return PwgPermissionsAddResponse.class;
	}
}
