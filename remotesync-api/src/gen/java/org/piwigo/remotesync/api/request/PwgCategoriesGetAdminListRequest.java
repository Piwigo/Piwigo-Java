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
import org.piwigo.remotesync.api.response.PwgCategoriesGetAdminListResponse;

/**
Get albums list as displayed on admin page.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesGetAdminListRequest extends AbstractRequest<PwgCategoriesGetAdminListResponse> {

	public PwgCategoriesGetAdminListRequest() {
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
		return "pwg.categories.getAdminList";
	}

	public Class<PwgCategoriesGetAdminListResponse> getReturnType() {
		return PwgCategoriesGetAdminListResponse.class;
	}
}
