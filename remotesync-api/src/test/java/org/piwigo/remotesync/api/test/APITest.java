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
package org.piwigo.remotesync.api.test;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.piwigo.remotesync.api.API;
import org.piwigo.remotesync.api.client.Client;
import org.piwigo.remotesync.api.client.WSClient;
import org.piwigo.remotesync.api.conf.GalleryConfig;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.reflection.MethodReflection;
import org.piwigo.remotesync.api.reflection.ReflectionRegistry;
import org.piwigo.remotesync.api.request.ComposedRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest.Status;
import org.piwigo.remotesync.api.request.PwgCategoriesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgGetVersionRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddWithChunkRequest;
import org.piwigo.remotesync.api.request.PwgImagesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgSessionGetStatusRequest;
import org.piwigo.remotesync.api.request.PwgSessionLoginRequest;
import org.piwigo.remotesync.api.request.PwgSessionLogoutRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.piwigo.remotesync.api.response.ComposedResponse;
import org.piwigo.remotesync.api.response.PwgCategoriesAddResponse;
import org.piwigo.remotesync.api.response.PwgGetVersionResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddResponse;
import org.piwigo.remotesync.api.util.StringUtil;

public class APITest extends AbstractTestCase {

	public void testLoginPossible() {
		assertFalse(new PwgSessionGetStatusRequest().isAdminOnly());
		assertFalse(new PwgSessionGetStatusRequest().isNeedPwgToken());
		assertFalse(new PwgSessionLoginRequest(null, null).isAdminOnly());
		assertFalse(new PwgSessionLoginRequest(null, null).isNeedPwgToken());
		assertFalse(new PwgSessionLogoutRequest().isAdminOnly());
		assertFalse(new PwgSessionLogoutRequest().isNeedPwgToken());
	}
	
	public void testAPI() throws ClientServerException, URISyntaxException {
		API api = new API(getClient());
		int initial = api.galleryGetInfos().nb_elements;
		ComposedResponse<PwgImagesAddResponse> imagesAddWithChunkRequest = api.imagesAddWithChunkRequest(new PwgImagesAddWithChunkRequest(getPictureFile()).setAuthor("remote sync"));
		assertEquals(initial + 1, api.galleryGetInfos().nb_elements.intValue());
		api.imagesDelete(new PwgImagesDeleteRequest(imagesAddWithChunkRequest.getResponse().image_id));
		assertEquals(initial, api.galleryGetInfos().nb_elements.intValue());
	}
	
	public void testComposedRequest() throws ClientServerException {
		ComposedRequest<PwgGetVersionResponse> composedRequest = new ComposedRequest<PwgGetVersionResponse>();
		composedRequest.addRequest(new PwgGetVersionRequest());
		composedRequest.addRequest(new PwgGetVersionRequest());
		ComposedResponse<PwgGetVersionResponse> composedResponse = getClient().sendRequest(composedRequest);
		assertEquals(2, composedResponse.getAllResponses().size());
		for (BasicResponse response : composedResponse.getAllResponses())
			assertTrue(response instanceof PwgGetVersionResponse);
		assertTrue(composedResponse.getResponse() instanceof PwgGetVersionResponse);
		PwgGetVersionResponse versionResponse = composedResponse.getResponse();
		versionResponse.status.equals("ok");
	}

	public void testEnum() throws Exception {
		Client client = getClient();
		PwgCategoriesAddResponse categoryResponse = client.sendRequest(new PwgCategoriesAddRequest("test").setStatus(Status.PRIVATE));
		client.sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
	}

	public void testLoginLogout() throws Exception {
		GalleryConfig galleryConfig = getTestConfig().getCurrentGalleryConfig();
		Client client = new WSClient(galleryConfig.getUrl());
		client.sendRequest(new PwgSessionLoginRequest(galleryConfig.getUsername(), galleryConfig.getPassword()));
		assertTrue(client.sendRequest(new PwgSessionGetStatusRequest()).isAdmin());
		client.sendRequest(new PwgSessionLogoutRequest());
		assertFalse(client.sendRequest(new PwgSessionGetStatusRequest()).isLogged());
	}

	public void testWrongLogin() {
		try {
			GalleryConfig galleryConfig = getTestConfig().getCurrentGalleryConfig();
			new WSClient(galleryConfig.getUrl()).sendRequest(new PwgSessionLoginRequest("unknown", "unknown"));
			fail("Should not login with wrong login/password");
		} catch (Exception e) {
		}
	}

	public void testReflection() {
		for (MethodReflection methodReflection : ReflectionRegistry.methodDetails) {
			assertEquals(methodReflection.getMethodName(), methodReflection.getMethodDetails().name);
			if (methodReflection.getMethodName().equals("reflection.getMethodList")) {
				assertTrue(!methodReflection.isRequestClassCustomized());
				assertTrue(methodReflection.isResponseClassCustomized());
			}
			if (methodReflection.getMethodDetails().needPwgToken)
				assertTrue(methodReflection.getMethodDetails().options.admin_only);
		}
	}

	public void testDateConverter() throws ParseException {
		Date date = StringUtil.parseDate("2005-05-17 20:15:42");
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
		assertEquals(2005, cal.get(Calendar.YEAR));
	}

	public void testFakePasswordConverter() throws ParseException {
		String password = "password";
		assertFalse(password.equals(StringUtil.fakeEncryptPassword(password)));
		assertEquals(password, StringUtil.fakeDecryptPassword(StringUtil.fakeEncryptPassword(password)));
	}
}
