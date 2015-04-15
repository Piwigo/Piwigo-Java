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
import org.piwigo.remotesync.api.response.PwgImagesExistResponse;

/**
Checks existence of images.
<br>Give <b>md5sum_list</b> if $conf[uniqueness_mode]==md5sum. Give <b>filename_list</b> if $conf[uniqueness_mode]==filename.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesExistRequest extends AbstractRequest<PwgImagesExistResponse> {

	public PwgImagesExistRequest() {
	}

 	public PwgImagesExistRequest setMd5sumList(String md5sum_list) {
		addParameterValue("md5sum_list", org.piwigo.remotesync.api.type.Type.MIXED, null, md5sum_list);
		return this;
	}	

 	public PwgImagesExistRequest setFilenameList(String filename_list) {
		addParameterValue("filename_list", org.piwigo.remotesync.api.type.Type.MIXED, null, filename_list);
		return this;
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
		return "pwg.images.exist";
	}

	public Class<PwgImagesExistResponse> getReturnType() {
		return PwgImagesExistResponse.class;
	}
}
