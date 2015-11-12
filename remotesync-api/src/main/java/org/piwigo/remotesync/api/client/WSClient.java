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
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.piwigo.remotesync.api.IClient;
import org.piwigo.remotesync.api.IClientConfiguration;
import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.exception.ServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.request.ComposedRequest;
import org.piwigo.remotesync.api.request.IChunkable;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;
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
 */
public class WSClient extends AbstractClient {

	private static Logger logger = LoggerFactory.getLogger(WSClient.class);

	protected IClientConfiguration clientConfiguration;
	protected CloseableHttpClient httpClient;
	protected RequestConfig requestConfig;

	public WSClient(IClientConfiguration clientConfiguration) {
		this.clientConfiguration = clientConfiguration;
	}

	@Override
	public <T extends BasicResponse> T sendRequest(AbstractRequest<T> request) throws ClientServerException {
		handleChunkable(request);
		return super.sendRequest(request);
	}
	
	@Override
	public <T extends BasicResponse> ComposedResponse<T> sendRequest(ComposedRequest<T> composedRequest) throws ClientServerException {
		handleChunkable(composedRequest);
		return super.sendRequest(composedRequest);
	}
	
	protected <T extends BasicResponse> void handleChunkable(AbstractRequest<T> request) {
		if (request instanceof IChunkable)
			((IChunkable) request).setChunkSize(clientConfiguration.getChunkSize());
	}

	@Override
	protected <T extends BasicResponse> T doSendRequest(AbstractRequest<T> request) throws ClientServerException {
		checkRequestAuthorization(request);

		String content = getXmlResponse(request);

		// basic parsing
		ServerResponse errorResponse = parseResponse(content, ServerResponse.class, false);

		if ("ok".equals(errorResponse.status)) {
			// complete parsing
			T response = parseResponse(content, request.getReturnType(), true);
			response.setXmlContent(content);
			return response;
		} else if ("fail".equals(errorResponse.status)) {
			logger.debug(content);
			throw new ServerException(errorResponse.error.toString());
		} else {
			throw new NotImplementedException();
		}
	}

	protected <T extends BasicResponse> String getXmlResponse(AbstractRequest<T> request) throws ClientException, ServerException {
		CloseableHttpResponse httpResponse = null;
		
		try {
			httpResponse = getHttpResponse(request);

			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
				throw new ServerException(httpResponse.getStatusLine().getReasonPhrase() + " (code " + httpResponse.getStatusLine().getStatusCode() + ")");

			return IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
		} catch (Exception e) {
			throw new ClientException("Unable to read response content", e);
		} finally {
			try {
				if (httpResponse != null)
					httpResponse.close();
			} catch (IOException e) {
				logger.error("cannot close post", e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T extends BasicResponse> CloseableHttpResponse getHttpResponse(AbstractRequest<T> request) throws ClientException {
		try {
			HttpPost method = new HttpPost(clientConfiguration.getUrl() + "/ws.php");
			method.setConfig(requestConfig);

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

			return getHttpClient().execute(method);
		} catch (Exception e) {
			throw new ClientException("Unable to send request", e);
		}
	}

	protected CloseableHttpClient getHttpClient() throws Exception {
		if (httpClient == null) {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

			if (clientConfiguration.getUsesProxy()) {
				String proxyUrl = clientConfiguration.getProxyUrl();
				int proxyPort = clientConfiguration.getProxyPort();
				
				String proxyUsername = clientConfiguration.getProxyUsername();
				String proxyPassword = clientConfiguration.getProxyPassword();

				if (proxyUsername != null && proxyUsername.length() > 0 && proxyPassword != null && proxyPassword.length() > 0) {
					CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
					credentialsProvider.setCredentials(new AuthScope(proxyUrl, proxyPort), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
					httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
				}

				HttpHost proxy = new HttpHost(proxyUrl, proxyPort);
				requestConfig = RequestConfig.custom().setProxy(proxy).build();
			}
			
			if (clientConfiguration.getIgnoreSelfSignedSSLCertificate()) {
				SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
				sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			    httpClientBuilder.setSSLSocketFactory(new SSLConnectionSocketFactory(sslContextBuilder.build()));
			}

			httpClient = httpClientBuilder.build();
		}
		
		return httpClient;
	}

	protected <T extends BasicResponse> T parseResponse(String content, Class<T> type, boolean strict) throws ClientException {
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
	public IClient login() throws ClientServerException {
		throw new IllegalStateException("Cannot login, use " + AuthenticatedWSClient.class.getName());
	}

	@Override
	public IClient logout() throws ClientServerException {
		throw new IllegalStateException("Cannot logout, use " + AuthenticatedWSClient.class.getName());
	}
}
