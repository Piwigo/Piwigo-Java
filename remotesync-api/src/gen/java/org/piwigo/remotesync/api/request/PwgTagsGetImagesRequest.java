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
import org.piwigo.remotesync.api.response.PwgTagsGetImagesResponse;

/**
Returns elements for the corresponding tags. Fill at least tag_id, tag_url_name or tag_name.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgTagsGetImagesRequest extends AbstractRequest<PwgTagsGetImagesResponse> {

	public PwgTagsGetImagesRequest() {
	}

 	public PwgTagsGetImagesRequest setTagId(Integer tag_id) {
		addParameterValue("tag_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, tag_id);
		return this;
	}	

	public PwgTagsGetImagesRequest setTagId(Integer... tag_id) {
		addParameterValueList("tag_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, java.util.Arrays.asList(tag_id));
		return this;
	}

	public PwgTagsGetImagesRequest setTagId(java.util.List<Integer> tag_idList) {
		addParameterValueList("tag_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, tag_idList);
		return this;
	}

 	public PwgTagsGetImagesRequest setTagUrlName(String tag_url_name) {
		addParameterValue("tag_url_name", org.piwigo.remotesync.api.type.Type.MIXED, null, tag_url_name);
		return this;
	}	

	public PwgTagsGetImagesRequest setTagUrlName(String... tag_url_name) {
		addParameterValueList("tag_url_name", org.piwigo.remotesync.api.type.Type.MIXED, null, java.util.Arrays.asList(tag_url_name));
		return this;
	}

	public PwgTagsGetImagesRequest setTagUrlName(java.util.List<String> tag_url_nameList) {
		addParameterValueList("tag_url_name", org.piwigo.remotesync.api.type.Type.MIXED, null, tag_url_nameList);
		return this;
	}

 	public PwgTagsGetImagesRequest setTagName(String tag_name) {
		addParameterValue("tag_name", org.piwigo.remotesync.api.type.Type.MIXED, null, tag_name);
		return this;
	}	

	public PwgTagsGetImagesRequest setTagName(String... tag_name) {
		addParameterValueList("tag_name", org.piwigo.remotesync.api.type.Type.MIXED, null, java.util.Arrays.asList(tag_name));
		return this;
	}

	public PwgTagsGetImagesRequest setTagName(java.util.List<String> tag_nameList) {
		addParameterValueList("tag_name", org.piwigo.remotesync.api.type.Type.MIXED, null, tag_nameList);
		return this;
	}

	//defaultValue : 0
 	public PwgTagsGetImagesRequest setTagModeAnd(Boolean tag_mode_and) {
		addParameterValue("tag_mode_and", org.piwigo.remotesync.api.type.Type.BOOL, null, tag_mode_and);
		return this;
	}	

	//defaultValue : 100
	//maxValue : 500
 	public PwgTagsGetImagesRequest setPerPage(Integer per_page) {
		addParameterValue("per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 500, per_page);
		return this;
	}	

	//defaultValue : 0
 	public PwgTagsGetImagesRequest setPage(Integer page) {
		addParameterValue("page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, page);
		return this;
	}	

	//info : id, file, name, hit, rating_score, date_creation, date_available, random
 	public PwgTagsGetImagesRequest setOrder(Order order) {
		addParameterValue("order", org.piwigo.remotesync.api.type.Type.ENUM, null, order);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMinRate(Float f_min_rate) {
		addParameterValue("f_min_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_min_rate);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxRate(Float f_max_rate) {
		addParameterValue("f_max_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_max_rate);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMinHit(Integer f_min_hit) {
		addParameterValue("f_min_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_min_hit);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxHit(Integer f_max_hit) {
		addParameterValue("f_max_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_hit);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMinRatio(Float f_min_ratio) {
		addParameterValue("f_min_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_min_ratio);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxRatio(Float f_max_ratio) {
		addParameterValue("f_max_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_max_ratio);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxLevel(Integer f_max_level) {
		addParameterValue("f_max_level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_level);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMinDateAvailable(String f_min_date_available) {
		addParameterValue("f_min_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_available);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxDateAvailable(String f_max_date_available) {
		addParameterValue("f_max_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_available);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMinDateCreated(String f_min_date_created) {
		addParameterValue("f_min_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_created);
		return this;
	}	

 	public PwgTagsGetImagesRequest setFMaxDateCreated(String f_max_date_created) {
		addParameterValue("f_max_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_created);
		return this;
	}	

	public enum Order {
		ID,
		FILE,
		NAME,
		HIT,
		RATING_SCORE,
		DATE_CREATION,
		DATE_AVAILABLE,
		RANDOM
	}

	public boolean isNeedPwgToken() {
		return false;
	}

	public boolean isAdminOnly() {
		return false;
	};

	public boolean isPostOnly() {
		return false;
	};

	public String getWSMethodName() {
		return "pwg.tags.getImages";
	}

	public Class<PwgTagsGetImagesResponse> getReturnType() {
		return PwgTagsGetImagesResponse.class;
	}
}
