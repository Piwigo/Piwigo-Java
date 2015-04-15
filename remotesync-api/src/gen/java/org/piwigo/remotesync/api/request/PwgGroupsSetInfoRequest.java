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
import org.piwigo.remotesync.api.response.PwgGroupsSetInfoResponse;

/**
Updates a group. Leave a field blank to keep the current value.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgGroupsSetInfoRequest extends AbstractRequest<PwgGroupsSetInfoResponse> {

	protected PwgGroupsSetInfoRequest() {}

	public PwgGroupsSetInfoRequest(Integer group_id) {
		setGroupId(group_id);
	}

 	protected PwgGroupsSetInfoRequest setGroupId(Integer group_id) {
		addParameterValue("group_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, group_id);
		return this;
	}	

 	public PwgGroupsSetInfoRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

 	public PwgGroupsSetInfoRequest setIsDefault(Boolean is_default) {
		addParameterValue("is_default", org.piwigo.remotesync.api.type.Type.BOOL, null, is_default);
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
		return "pwg.groups.setInfo";
	}

	public Class<PwgGroupsSetInfoResponse> getReturnType() {
		return PwgGroupsSetInfoResponse.class;
	}
}
