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
import org.piwigo.remotesync.api.response.PwgGetMissingDerivativesResponse;

/**
Returns a list of derivatives to build.
**/
public class PwgGetMissingDerivativesRequest extends AbstractRequest<PwgGetMissingDerivativesResponse> {

	public PwgGetMissingDerivativesRequest() {
	}

	//info : square, thumb, 2small, xsmall, small, medium, large, xlarge, xxlarge
 	public PwgGetMissingDerivativesRequest setTypes(Types types) {
		addParameterValue("types", org.piwigo.remotesync.api.type.Type.ENUM, null, types);
		return this;
	}	

	public PwgGetMissingDerivativesRequest setTypes(java.util.List<Types> typesList) {
		addParameterValueList("types", org.piwigo.remotesync.api.type.Type.ENUM, null, typesList);
		return this;
	}

 	public PwgGetMissingDerivativesRequest setIds(Integer ids) {
		addParameterValue("ids", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, ids);
		return this;
	}	

	public PwgGetMissingDerivativesRequest setIds(java.util.List<Integer> idsList) {
		addParameterValueList("ids", org.piwigo.remotesync.api.type.Type.INT_POSITIVE_NOTNULL, null, idsList);
		return this;
	}

	//defaultValue : 200
 	public PwgGetMissingDerivativesRequest setMaxUrls(Integer max_urls) {
		addParameterValue("max_urls", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, max_urls);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setPrevPage(Integer prev_page) {
		addParameterValue("prev_page", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, prev_page);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMinRate(Float f_min_rate) {
		addParameterValue("f_min_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_min_rate);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxRate(Float f_max_rate) {
		addParameterValue("f_max_rate", org.piwigo.remotesync.api.type.Type.FLOAT, null, f_max_rate);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMinHit(Integer f_min_hit) {
		addParameterValue("f_min_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_min_hit);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxHit(Integer f_max_hit) {
		addParameterValue("f_max_hit", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_hit);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMinRatio(Float f_min_ratio) {
		addParameterValue("f_min_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_min_ratio);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxRatio(Float f_max_ratio) {
		addParameterValue("f_max_ratio", org.piwigo.remotesync.api.type.Type.FLOAT_POSITIVE, null, f_max_ratio);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxLevel(Integer f_max_level) {
		addParameterValue("f_max_level", org.piwigo.remotesync.api.type.Type.INT_POSITIVE, null, f_max_level);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMinDateAvailable(String f_min_date_available) {
		addParameterValue("f_min_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_available);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxDateAvailable(String f_max_date_available) {
		addParameterValue("f_max_date_available", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_available);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMinDateCreated(String f_min_date_created) {
		addParameterValue("f_min_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_min_date_created);
		return this;
	}	

 	public PwgGetMissingDerivativesRequest setFMaxDateCreated(String f_max_date_created) {
		addParameterValue("f_max_date_created", org.piwigo.remotesync.api.type.Type.MIXED, null, f_max_date_created);
		return this;
	}	

	public enum Types {
		SQUARE,
		THUMB,
		TWOSMALL,
		XSMALL,
		SMALL,
		MEDIUM,
		LARGE,
		XLARGE,
		XXLARGE;
		
		public String toString() {
			if (this.equals(TWOSMALL))
				return "2small";
			return super.toString();
		};
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
		return "pwg.getMissingDerivatives";
	}

	public Class<PwgGetMissingDerivativesResponse> getReturnType() {
		return PwgGetMissingDerivativesResponse.class;
	}
}
