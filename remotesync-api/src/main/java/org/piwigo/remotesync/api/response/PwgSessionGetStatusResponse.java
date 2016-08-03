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
package org.piwigo.remotesync.api.response;

import org.piwigo.remotesync.api.Constants;
import org.simpleframework.xml.Element;

public class PwgSessionGetStatusResponse extends BasicResponse {
	@Element
	public String username;

	@Element
	public String status;

	@Element
	public String theme;

	@Element
	public String language;

	@Element
	public String pwg_token;

	@Element
	public String charset;

	@Element
	public String current_datetime;

	@Element(required=false)
	public String version;

	@Element(required=false)
	public String upload_file_types;

	@Element(required=false)
	public String upload_form_chunk_size;

	public boolean isAdmin() {
		return Constants.UserType.admin.toString().equals(status) || Constants.UserType.webmaster.toString().equals(status);
	}

	public boolean isGuest() {
		return Constants.UserType.guest.toString().equals(status);
	}

	public boolean isLogged() {
		return !isGuest();
	}
}
