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
package org.piwigo.remotesync.api.reflection;

import java.util.ArrayList;
import java.util.List;

import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse.Parameter;
import org.piwigo.remotesync.api.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionCustomization {

	private static final Logger logger = LoggerFactory.getLogger(ReflectionCustomization.class);

	public static void customizeMethodDetails(List<ReflectionGetMethodDetailsResponse> methodDetails) {
		for (ReflectionGetMethodDetailsResponse methodDetail : methodDetails)
			customizeMethodDetail(methodDetail);
	}

	public static void customizeMethodDetail(ReflectionGetMethodDetailsResponse methodDetail) {
		if (methodDetail.name.equals("pwg.images.addSimple")) {
			Parameter parameter = new ReflectionGetMethodDetailsResponse.Parameter();
			parameter.acceptArray = false;
			parameter.name = "image";
			parameter.optional = false;
			parameter.type = Type.FILE;
			parameter.commit();
			methodDetail.parameters.add(parameter);
			methodDetail.commit();
		}

		for (Parameter parameter : new ArrayList<Parameter>(methodDetail.parameters)) {
			if (parameter.name.equals("pwg_token")) {
				methodDetail.parameters.remove(parameter);
				methodDetail.needPwgToken = true;
				methodDetail.commit();
			}

			String string = methodDetail.name + "." + parameter.name;
			if (string.equals("pwg.categories.delete.category_id"))
				parameter.type = Type.INT_POSITIVE_NOTNULL;
			if (string.equals("pwg.categories.move.category_id"))
				parameter.type = Type.INT_POSITIVE_NOTNULL;
			if (string.equals("pwg.images.add.tag_ids"))
				parameter.type = Type.INT_POSITIVE_NOTNULL;
			if (string.equals("pwg.images.delete.image_id"))
				parameter.type = Type.INT_POSITIVE_NOTNULL;
			if (string.equals("pwg.images.setInfo.tag_ids"))
				parameter.type = Type.INT_POSITIVE_NOTNULL;
			if (string.equals("pwg.images.addChunk.position"))
				parameter.type = Type.INT;

			if (parameter.type.equals(Type.MIXED) && (parameter.name.toLowerCase().contains("id") || parameter.name.toLowerCase().contains("tag") || parameter.name.toLowerCase().contains("image") || parameter.name.toLowerCase().contains("cat")))
				logger.debug("MSG 1 - " + methodDetail.name + "." + parameter.name + " should use int ?");

			if (parameter.type.equals(Type.MIXED) && parameter.name.toLowerCase().contains("id") && (parameter.name.toLowerCase().contains("tag") || parameter.name.toLowerCase().contains("image") || parameter.name.toLowerCase().contains("cat")))
				logger.debug("MSG 2 - " + methodDetail.name + "." + parameter.name + " should really use int ?");
			
//			if (parameter.maxValue != null)
//				logger.debug("MSG 3 - " + methodDetail.name + "." + parameter.name + " has default value of type " + parameter.type.name);

			if (parameter.info != null && parameter.info.matches("\\w+(,\\s?\\w+)*")) {
//				logger.debug("MSG 4 - " + methodDetail.name + "." + parameter.name + " is enum : " + parameter.info);
				parameter.type = Type.createEnumType(parameter);
			}
		}


		// if (methodDetail.options.admin_only && !methodDetail.needPwgToken)
		// logger.debug("MSG 13 - " + methodDetail.name +
		// " is admin but no pwg_token");
		if (!methodDetail.options.admin_only && methodDetail.needPwgToken)
			logger.debug("MSG 14 - " + methodDetail.name + " need pwg_token but not admin");
	}
}
