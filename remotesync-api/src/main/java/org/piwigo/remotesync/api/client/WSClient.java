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
package org.piwigo.remotesync.api.client;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.exception.ServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ServerResponse;
import org.piwigo.remotesync.api.xml.PersisterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO standard authentication ? close httpclient on logout logout before login
 * response.close(); httpclient.close();
 * System.out.println(response.getStatusLine().getStatusCode());
 * System.out.println(response.getStatusLine().getReasonPhrase());
 * System.out.println(response.getStatusLine().toString());
 *
 * TODO handle proxy
 */
public class WSClient extends AbstractClient {

	private static Logger logger = LoggerFactory.getLogger(WSClient.class);

	private String url;
	private CloseableHttpClient httpClient;

	public WSClient(String url) {
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T extends BasicResponse> T doSendRequest(AbstractRequest<T> request) throws ClientServerException {
		checkRequestAuthorization(request);

		if (httpClient == null)
			httpClient = HttpClients.createDefault();

		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost method = new HttpPost(url + "/ws.php");

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addTextBody("method", request.getWSMethodName());
			for (Entry<String, Object> entry : request.getParameters().entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof File)
					multipartEntityBuilder.addBinaryBody(key, (File) value);
				else if (value == null)
					multipartEntityBuilder.addTextBody(key, "");
				else if (value instanceof List) {
					for (Object object : (List<? extends Object>) value)
						if (object != null)
							multipartEntityBuilder.addTextBody(key + "[]", object.toString());
				} else if (value instanceof Enum)
					multipartEntityBuilder.addTextBody(key, value.toString().toLowerCase());
				else
					multipartEntityBuilder.addTextBody(key, value.toString());
			}
			method.setEntity(multipartEntityBuilder.build());

			httpResponse = httpClient.execute(method);
		} catch (Exception e) {
			throw new ClientException("Unable to send request", e);
		}

		if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			throw new ServerException(httpResponse.getStatusLine().getReasonPhrase() + " (code " + httpResponse.getStatusLine().getStatusCode() + ")");

		String content;
		try {
			content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
		} catch (Exception e) {
			throw new ClientException("Unable to read response content", e);
		}

		try {
			if (httpResponse != null)
				httpResponse.close();
		} catch (IOException e) {
			logger.error("cannot close post", e);
		}

		// basic parsing
		ServerResponse errorResponse = parseResponse(httpResponse, content, ServerResponse.class, false);

		if ("fail".equals(errorResponse.status)) {
			logger.debug(content);
			throw new ServerException(errorResponse.error.toString());
		} else if (!"ok".equals(errorResponse.status))
			throw new NotImplementedException();

		// complete parsing
		T response = parseResponse(httpResponse, content, request.getReturnType(), true);
		response.setHttpStatusCode(httpResponse.getStatusLine().getStatusCode());
		response.setXmlContent(content);

		return response;
	}

	private <T extends BasicResponse> T parseResponse(HttpResponse httpResponse, String content, Class<T> type, boolean strict) throws ClientException {
		try {
			return (T) PersisterFactory.createPersister().read(type, content, strict);
		} catch (Exception e) {
			logger.debug("XmlContent : {}", content);
			logger.debug("Class : {} ", type.getName());

			logger.error("Unable to parse response", e);
			throw new ClientException("Unable to parse response", e);
		}
	}

	protected <T extends BasicResponse> void checkRequestAuthorization(AbstractRequest<T> request) throws ClientServerException {
		if (request.isAdminOnly() || request.isNeedPwgToken())
			throw new ClientException("Not logged in");
	}

	@Override
	public Client login(String username, String password) throws ClientServerException {
		throw new IllegalStateException("Cannot login, use " + AuthenticatedWSClient.class.getName());
	}

	@Override
	public Client logout() throws ClientServerException {
		throw new IllegalStateException("Cannot logout, use " + AuthenticatedWSClient.class.getName());
	}
}
