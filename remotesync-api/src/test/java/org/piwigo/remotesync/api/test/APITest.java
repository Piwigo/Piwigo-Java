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
import org.piwigo.remotesync.api.IClient;
import org.piwigo.remotesync.api.ISyncConfiguration;
import org.piwigo.remotesync.api.client.WSClient;
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
		ComposedResponse<PwgImagesAddResponse> imagesAddWithChunkRequest = api.imagesAddWithChunkRequest(new PwgImagesAddWithChunkRequest(getImageFile()).setAuthor("remote sync"));
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
		IClient client = getClient();
		PwgCategoriesAddResponse categoryResponse = client.sendRequest(new PwgCategoriesAddRequest("test").setStatus(Status.PRIVATE));
		client.sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
	}

	public void testLoginLogout() throws Exception {
		ISyncConfiguration syncConfiguration = getTestConfiguration().getCurrentSyncConfiguration();
		IClient client = new WSClient(syncConfiguration);
		client.sendRequest(new PwgSessionLoginRequest(syncConfiguration.getUsername(), syncConfiguration.getPassword()));
		assertTrue(client.sendRequest(new PwgSessionGetStatusRequest()).isAdmin());
		client.sendRequest(new PwgSessionLogoutRequest());
		assertFalse(client.sendRequest(new PwgSessionGetStatusRequest()).isLogged());
	}

	public void testWrongLogin() {
		try {
			ISyncConfiguration syncConfiguration = getTestConfiguration().getCurrentSyncConfiguration();
			new WSClient(syncConfiguration).sendRequest(new PwgSessionLoginRequest("unknown", "unknown"));
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
		doTestFakePasswordConverter("a");
		doTestFakePasswordConverter("1");
		doTestFakePasswordConverter("password");
		doTestFakePasswordConverter("1234567890&й(-и_за)^$щ*,;:!?./§µ%MP");
	}

	private void doTestFakePasswordConverter(String password) throws ParseException {
		assertFalse(password.equals(StringUtil.fakeEncryptPassword(password)));
		assertEquals(password, StringUtil.fakeDecryptPassword(StringUtil.fakeEncryptPassword(password)));
	}
	
	public void testRot13() {
		assertEquals("aB123", StringUtil.rot13(StringUtil.rot13("aB123")));
		assertEquals("this is my 1st rot13 automatic test", StringUtil.rot13("guvf vf zl 1fg ebg13 nhgbzngvp grfg"));
	}
}
