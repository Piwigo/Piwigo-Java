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
import org.piwigo.remotesync.api.response.PwgGroupsGetListResponse;

/**
Retrieves a list of all groups. The list can be filtered.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgGroupsGetListRequest extends AbstractRequest<PwgGroupsGetListResponse> {

	public PwgGroupsGetListRequest() {
	}

 	public PwgGroupsGetListRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

	public PwgGroupsGetListRequest setGroupId(Integer... group_id) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(group_id));
		return this;
	}

	public PwgGroupsGetListRequest setGroupId(java.util.List<Integer> group_idList) {
		addParameterValueList("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_idList);
		return this;
	}

	//info : Use "%" as wildcard.
 	public PwgGroupsGetListRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

	//defaultValue : 100
	//maxValue : 1000
 	public PwgGroupsGetListRequest setPerPage(Integer per_page) {
		addParameterValue("per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 1000, per_page);
		return this;
	}	

	//defaultValue : 0
 	public PwgGroupsGetListRequest setPage(Integer page) {
		addParameterValue("page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, page);
		return this;
	}	

	//info : id, name, nb_users, is_default
	//defaultValue : name
 	public PwgGroupsGetListRequest setOrder(Order order) {
		addParameterValue("order", org.piwigo.remotesync.api.type.Type.ENUM, null, order);
		return this;
	}	

	public enum Order {
		ID,
		NAME,
		NB_USERS,
		IS_DEFAULT
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
		return "pwg.groups.getList";
	}

	public Class<PwgGroupsGetListResponse> getReturnType() {
		return PwgGroupsGetListResponse.class;
	}
}
