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
import org.piwigo.remotesync.api.response.PwgGroupsAddResponse;

/**
Creates a group and returns the new group record.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgGroupsAddRequest extends AbstractRequest<PwgGroupsAddResponse> {

	protected PwgGroupsAddRequest() {}

	public PwgGroupsAddRequest(String name) {
		setName(name);
	}

 	protected PwgGroupsAddRequest setName(String name) {
		addParameterValue("name", org.piwigo.remotesync.api.type.Type.MIXED, null, name);
		return this;
	}	

	//defaultValue : 0
 	public PwgGroupsAddRequest setIsDefault(Boolean is_default) {
		addParameterValue("is_default", org.piwigo.remotesync.api.type.Type.BOOL, null, is_default);
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
		return "pwg.groups.add";
	}

	public Class<PwgGroupsAddResponse> getReturnType() {
		return PwgGroupsAddResponse.class;
	}
}
