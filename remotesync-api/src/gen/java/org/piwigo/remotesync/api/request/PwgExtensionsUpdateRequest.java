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
import org.piwigo.remotesync.api.response.PwgExtensionsUpdateResponse;

/**
<b>Webmaster only.</b>
**/
@org.piwigo.remotesync.generator.Generated
public class PwgExtensionsUpdateRequest extends AbstractRequest<PwgExtensionsUpdateResponse> {

	protected PwgExtensionsUpdateRequest() {}

	public PwgExtensionsUpdateRequest(Type type, String id, String revision) {
		setType(type);
		setId(id);
		setRevision(revision);
	}

	//info : plugins, languages, themes
 	protected PwgExtensionsUpdateRequest setType(Type type) {
		addParameterValue("type", org.piwigo.remotesync.api.type.Type.ENUM, null, type);
		return this;
	}	

 	protected PwgExtensionsUpdateRequest setId(String id) {
		addParameterValue("id", org.piwigo.remotesync.api.type.Type.MIXED, null, id);
		return this;
	}	

 	protected PwgExtensionsUpdateRequest setRevision(String revision) {
		addParameterValue("revision", org.piwigo.remotesync.api.type.Type.MIXED, null, revision);
		return this;
	}	

	public enum Type {
		PLUGINS,
		LANGUAGES,
		THEMES
	}

	public boolean isNeedPwgToken() {
		return true;
	}

	public boolean isAdminOnly() {
		return true;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "pwg.extensions.update";
	}

	public Class<PwgExtensionsUpdateResponse> getReturnType() {
		return PwgExtensionsUpdateResponse.class;
	}
}
