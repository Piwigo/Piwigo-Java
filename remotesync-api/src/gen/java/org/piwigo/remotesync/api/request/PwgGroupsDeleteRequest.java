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
import org.piwigo.remotesync.api.response.PwgGroupsDeleteResponse;

/**
Deletes a or more groups. Users and photos are not deleted.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgGroupsDeleteRequest extends AbstractRequest<PwgGroupsDeleteResponse> {

	protected PwgGroupsDeleteRequest() {}

	public PwgGroupsDeleteRequest(Integer group_id) {
		setGroupId(group_id);
	}

	public PwgGroupsDeleteRequest(Integer... group_id) {
		setGroupId(java.util.Arrays.asList(group_id));
	}

	public PwgGroupsDeleteRequest(java.util.List<Integer> group_idList) {
		setGroupId(group_idList);
	}

 	protected PwgGroupsDeleteRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

	protected PwgGroupsDeleteRequest setGroupId(Integer... group_id) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(group_id));
		return this;
	}

	protected PwgGroupsDeleteRequest setGroupId(java.util.List<Integer> group_idList) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_idList);
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
		return "pwg.groups.delete";
	}

	public Class<PwgGroupsDeleteResponse> getReturnType() {
		return PwgGroupsDeleteResponse.class;
	}
}
