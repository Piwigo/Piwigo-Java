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

import org.piwigo.remotesync.api.exception.XMLRuntimeException;
import org.piwigo.remotesync.api.reflection.ReflectionRegistry;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ReflectionGetMethodDetailsResponse;
import org.piwigo.remotesync.api.xml.PersisterFactory;
import org.piwigo.remotesync.generator.Generated;

public class MethodReflection {
	private String methodName;
	private String xmlFileName;

	private Class<? extends AbstractRequest<? extends BasicResponse>> requestClass;
	private Class<? extends BasicResponse> responseClass;

	public MethodReflection(String methodName, 
			String xmlFileName, 
			Class<? extends AbstractRequest<? extends BasicResponse>> requestClass,
			Class<? extends BasicResponse> responseClass) {
		this.methodName = methodName;
		this.xmlFileName = xmlFileName;
		this.requestClass = requestClass;
		this.responseClass = responseClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class<? extends AbstractRequest<? extends BasicResponse>> getRequestClass() {
		return requestClass;
	}

	public Class<? extends BasicResponse> getResponseClass() {
		return responseClass;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}
	
	public ReflectionGetMethodDetailsResponse getMethodDetails() {
		try {
			ReflectionGetMethodDetailsResponse methodDetail = PersisterFactory.createPersister().read(ReflectionGetMethodDetailsResponse.class, ReflectionRegistry.class.getResourceAsStream(xmlFileName));
			ReflectionCustomization.customizeMethodDetail(methodDetail);
			return methodDetail;
		} catch (Exception e) {
			throw new XMLRuntimeException("Cannot parse " + xmlFileName, e);
		}
	}

	public boolean isRequestClassCustomized() {
		return requestClass.getAnnotation(Generated.class) == null;
	}

	public boolean isResponseClassCustomized() {
		return responseClass.getAnnotation(Generated.class) == null;
	}
}
