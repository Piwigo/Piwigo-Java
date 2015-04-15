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
import org.piwigo.remotesync.api.response.PwgImagesSearchResponse;

/**
Returns elements for the corresponding query search.
**/
@org.piwigo.remotesync.generator.Generated
public class PwgImagesSearchRequest extends AbstractRequest<PwgImagesSearchResponse> {

	protected PwgImagesSearchRequest() {}

	public PwgImagesSearchRequest(String query) {
		setQuery(query);
	}

 	protected PwgImagesSearchRequest setQuery(String query) {
		addParameterValue("query", org.piwigo.remotesync.api.type.Type.MIXED, null, query);
		return this;
	}	

	//defaultValue : 100
	//maxValue : 500
 	public PwgImagesSearchRequest setPerPage(Integer per_page) {
		addParameterValue("per_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, 500, per_page);
		return this;
	}	

	//defaultValue : 0
 	public PwgImagesSearchRequest setPage(Integer page) {
		addParameterValue("page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, page);
		return this;
	}	

	//info : id, file, name, hit, rating_score, date_creation, date_available, random
 	public PwgImagesSearchRequest setOrder(Order order) {
		addParameterValue("order", org.piwigo.remotesync.api.type.Type.ENUM, null, order);
		return this;
	}	

 	public PwgImagesSearchRequest setFMinRate(Float f_min_rate) {
		addParameterValue("f_min_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_min_rate);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxRate(Float f_max_rate) {
		addParameterValue("f_max_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_max_rate);
		return this;
	}	

 	public PwgImagesSearchRequest setFMinHit(Integer f_min_hit) {
		addParameterValue("f_min_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_min_hit);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxHit(Integer f_max_hit) {
		addParameterValue("f_max_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_hit);
		return this;
	}	

 	public PwgImagesSearchRequest setFMinRatio(Float f_min_ratio) {
		addParameterValue("f_min_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_min_ratio);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxRatio(Float f_max_ratio) {
		addParameterValue("f_max_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_max_ratio);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxLevel(Integer f_max_level) {
		addParameterValue("f_max_level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_level);
		return this;
	}	

 	public PwgImagesSearchRequest setFMinDateAvailable(String f_min_date_available) {
		addParameterValue("f_min_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_available);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxDateAvailable(String f_max_date_available) {
		addParameterValue("f_max_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_available);
		return this;
	}	

 	public PwgImagesSearchRequest setFMinDateCreated(String f_min_date_created) {
		addParameterValue("f_min_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_created);
		return this;
	}	

 	public PwgImagesSearchRequest setFMaxDateCreated(String f_max_date_created) {
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
		return "pwg.images.search";
	}

	public Class<PwgImagesSearchResponse> getReturnType() {
		return PwgImagesSearchResponse.class;
	}
}
