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
import org.piwigo.remotesync.api.response.PwgCategoriesGetImagesResponse;

/**
Returns elements for the corresponding categories.
<br><b>cat_id</b> can be empty if <b>recursive</b> is true.
<br><b>order</b> comma separated fields for sorting
**/
@org.piwigo.remotesync.generator.Generated
public class PwgCategoriesGetImagesRequest extends AbstractRequest<PwgCategoriesGetImagesResponse> {

	public PwgCategoriesGetImagesRequest() {
	}

 	public PwgCategoriesGetImagesRequest setCatId(Integer cat_id) {
		addParameterValue("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, cat_id);
		return this;
	}	

	public PwgCategoriesGetImagesRequest setCatId(Integer... cat_id) {
		addParameterValueList("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, java.util.Arrays.asList(cat_id));
		return this;
	}

	public PwgCategoriesGetImagesRequest setCatId(java.util.List<Integer> cat_idList) {
		addParameterValueList("cat_id", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, cat_idList);
		return this;
	}

	//defaultValue : 0
 	public PwgCategoriesGetImagesRequest setRecursive(Boolean recursive) {
		addParameterValue("recursive", org.piwigo.remotesync.api.type.Type.BOOL, null, recursive);
		return this;
	}	

	//defaultValue : 100
	//maxValue : 500
 	public PwgCategoriesGetImagesRequest setPerPage(Integer per_page) {
		addParameterValue("per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 500, per_page);
		return this;
	}	

	//defaultValue : 0
 	public PwgCategoriesGetImagesRequest setPage(Integer page) {
		addParameterValue("page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, page);
		return this;
	}	

	//info : id, file, name, hit, rating_score, date_creation, date_available, random
 	public PwgCategoriesGetImagesRequest setOrder(Order order) {
		addParameterValue("order", org.piwigo.remotesync.api.type.Type.ENUM, null, order);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMinRate(Float f_min_rate) {
		addParameterValue("f_min_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_min_rate);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxRate(Float f_max_rate) {
		addParameterValue("f_max_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_max_rate);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMinHit(Integer f_min_hit) {
		addParameterValue("f_min_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_min_hit);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxHit(Integer f_max_hit) {
		addParameterValue("f_max_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_hit);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMinRatio(Float f_min_ratio) {
		addParameterValue("f_min_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_min_ratio);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxRatio(Float f_max_ratio) {
		addParameterValue("f_max_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_max_ratio);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxLevel(Integer f_max_level) {
		addParameterValue("f_max_level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_level);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMinDateAvailable(String f_min_date_available) {
		addParameterValue("f_min_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_available);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxDateAvailable(String f_max_date_available) {
		addParameterValue("f_max_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_available);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMinDateCreated(String f_min_date_created) {
		addParameterValue("f_min_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_created);
		return this;
	}	

 	public PwgCategoriesGetImagesRequest setFMaxDateCreated(String f_max_date_created) {
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
		return "pwg.categories.getImages";
	}

	public Class<PwgCategoriesGetImagesResponse> getReturnType() {
		return PwgCategoriesGetImagesResponse.class;
	}
}
