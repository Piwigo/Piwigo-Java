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
import java.util.Arrays;
import java.util.Date;

import org.piwigo.remotesync.api.API;
import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.model.Plugin;
import org.piwigo.remotesync.api.request.PwgCaddieAddRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesGetAdminListRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesGetImagesRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesGetListRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesMoveRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesSetInfoRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesSetRepresentativeRequest;
import org.piwigo.remotesync.api.request.PwgExtensionsCheckUpdatesRequest;
import org.piwigo.remotesync.api.request.PwgGetInfosRequest;
import org.piwigo.remotesync.api.request.PwgGetMissingDerivativesRequest;
import org.piwigo.remotesync.api.request.PwgGetVersionRequest;
import org.piwigo.remotesync.api.request.PwgGroupsAddRequest;
import org.piwigo.remotesync.api.request.PwgGroupsAddUserRequest;
import org.piwigo.remotesync.api.request.PwgGroupsDeleteRequest;
import org.piwigo.remotesync.api.request.PwgGroupsDeleteUserRequest;
import org.piwigo.remotesync.api.request.PwgGroupsGetListRequest;
import org.piwigo.remotesync.api.request.PwgGroupsSetInfoRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddSimpleRequest;
import org.piwigo.remotesync.api.request.PwgImagesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgPermissionsAddRequest;
import org.piwigo.remotesync.api.request.PwgPermissionsRemoveRequest;
import org.piwigo.remotesync.api.request.PwgPluginsGetListRequest;
import org.piwigo.remotesync.api.request.PwgPluginsPerformActionRequest;
import org.piwigo.remotesync.api.request.PwgRatesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgTagsAddRequest;
import org.piwigo.remotesync.api.request.PwgTagsGetImagesRequest;
import org.piwigo.remotesync.api.request.PwgUsersAddRequest;
import org.piwigo.remotesync.api.request.PwgUsersDeleteRequest;
import org.piwigo.remotesync.api.request.PwgUsersSetInfoRequest;
import org.piwigo.remotesync.api.request.ReflectionGetMethodDetailsRequest;
import org.piwigo.remotesync.api.request.ReflectionGetMethodListRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest.Status;
import org.piwigo.remotesync.api.request.PwgPluginsPerformActionRequest.Action;
import org.piwigo.remotesync.api.response.PwgCategoriesAddResponse;
import org.piwigo.remotesync.api.response.PwgGetInfosResponse;
import org.piwigo.remotesync.api.response.PwgGroupsAddResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddSimpleResponse;
import org.piwigo.remotesync.api.response.PwgPluginsGetListResponse;
import org.piwigo.remotesync.api.response.PwgUsersAddResponse;

public class RequestsTest extends AbstractTestCase {

	public void testReflection() throws ClientServerException {
		getClient().sendRequest(new ReflectionGetMethodListRequest());
		getClient().sendRequest(new ReflectionGetMethodDetailsRequest("pwg.caddie.add"));
	}

	public void testGetInfo() throws ClientServerException {
		PwgGetInfosResponse infos = getClient().sendRequest(new PwgGetInfosRequest());
		assertEquals("2.7.4", infos.version);
		int nbAlbums = infos.nb_categories;
		PwgCategoriesAddResponse album = getClient().sendRequest(new PwgCategoriesAddRequest("new"));
		infos = getClient().sendRequest(new PwgGetInfosRequest());
		assertEquals(nbAlbums + 1, infos.nb_categories.intValue());
		getClient().sendRequest(new PwgCategoriesDeleteRequest(album.id));
		
		getClient().sendRequest(new PwgGetMissingDerivativesRequest());
		assertEquals("2.7.4", getClient().sendRequest(new PwgGetVersionRequest()).version);
	}
	
	public void testCaddie() throws ClientServerException {
		assertEquals(0, getClient().sendRequest(new PwgCaddieAddRequest(123456)).added.intValue());
	}

	public void testCategories() throws ClientServerException, URISyntaxException {
		PwgCategoriesAddResponse categoryResponse = getClient().sendRequest(new PwgCategoriesAddRequest("test"));
		PwgCategoriesAddResponse categoryResponse2 = getClient().sendRequest(new PwgCategoriesAddRequest("test2"));
		PwgImagesAddSimpleResponse imageResponse = getClient().sendRequest(new PwgImagesAddSimpleRequest(getImageFile()).setCategory(categoryResponse.id));
		
		try {
			PwgCategoriesAddResponse category = getClient().sendRequest(new PwgCategoriesAddRequest("newcat"));
			getClient().sendRequest(new PwgCategoriesDeleteRequest(category.id));
			getClient().sendRequest(new PwgCategoriesGetAdminListRequest());
			getClient().sendRequest(new PwgCategoriesGetImagesRequest());
			getClient().sendRequest(new PwgCategoriesGetListRequest());
			getClient().sendRequest(new PwgCategoriesMoveRequest(categoryResponse.id, categoryResponse2.id));
			getClient().sendRequest(new PwgCategoriesSetInfoRequest(categoryResponse.id).setName("newname").setComment("newcomment"));			
			getClient().sendRequest(new PwgCategoriesSetRepresentativeRequest(categoryResponse.id, imageResponse.image_id));
		} finally {
			getClient().sendRequest(new PwgImagesDeleteRequest(imageResponse.image_id));
			getClient().sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
			getClient().sendRequest(new PwgCategoriesDeleteRequest(categoryResponse2.id));
		}
	}
	
	public void testExtenstion() throws ClientServerException {
		getClient().sendRequest(new PwgExtensionsCheckUpdatesRequest());
//		getClient().sendRequest(new PwgExtensionsIgnoreUpdateRequest());
//		getClient().sendRequest(new PwgExtensionsUpdateRequest());
	}
	
	public void testGroup() throws ClientServerException {
		PwgGroupsAddResponse a = getClient().sendRequest(new PwgGroupsAddRequest("a"));
		PwgGroupsAddResponse b = getClient().sendRequest(new PwgGroupsAddRequest("b"));
		getClient().sendRequest(new PwgGroupsAddUserRequest(a.id, 1));
		getClient().sendRequest(new PwgGroupsDeleteUserRequest(a.id, 1));
		getClient().sendRequest(new PwgGroupsSetInfoRequest(a.id).setName("c"));
		getClient().sendRequest(new PwgGroupsGetListRequest());		
		getClient().sendRequest(new PwgGroupsDeleteRequest(Arrays.asList(new Integer[] {a.id, b.id})));

	}
	
	public void testImages() {
		//TODO test all images request
	}

	public void testPermissions() throws ClientServerException {
		PwgCategoriesAddResponse categoryResponse = getClient().sendRequest(new PwgCategoriesAddRequest("test").setStatus(Status.PRIVATE));
	
		//TODO open piwigo bug
		
		try {
//			assertEquals(1, getClient().sendRequest(new PwgPermissionsGetListRequest().setCatId(categoryResponse.id)).permissions.size());
			getClient().sendRequest(new PwgPermissionsAddRequest(categoryResponse.id).setUserId(1));
//			assertEquals(2, getClient().sendRequest(new PwgPermissionsGetListRequest().setCatId(categoryResponse.id)).permissions.size());
			getClient().sendRequest(new PwgPermissionsRemoveRequest(categoryResponse.id).setUserId(1));
//			assertEquals(1, getClient().sendRequest(new PwgPermissionsGetListRequest().setCatId(categoryResponse.id)).permissions.size());
		} finally {
			getClient().sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
		}

	}

	public void testPlugins() throws ClientServerException {
		PwgPluginsGetListResponse sendRequest = getClient().sendRequest(new PwgPluginsGetListRequest());
		for (Plugin plugin : sendRequest.plugins) {
			if (plugin.state.equals("uninstalled")) {
				assertEquals(1, getClient().sendRequest(new PwgPluginsPerformActionRequest(Action.INSTALL, "Add_tags_mass")).nb.intValue());
				getClient().sendRequest(new PwgPluginsPerformActionRequest(Action.UNINSTALL, "Add_tags_mass"));
				return;
			}
		}
		
	}
	
	public void testRates() throws ClientServerException {
		assertEquals(0,  getClient().sendRequest(new PwgRatesDeleteRequest(1)).nb.intValue());
	}

	public void testSession() {
	}

	public void testTags() throws ClientServerException, URISyntaxException {
		//TODO not tag delete?
		API api = new API(getClient());
		
		String tagName = "imagetag" + new Date().toString();
		PwgImagesAddSimpleResponse imageResponse = getClient().sendRequest(new PwgImagesAddSimpleRequest(getImageFile()).setTags(tagName));
		
		try {
			api.tagsAdd(new PwgTagsAddRequest("alonetag" + new Date().toString()));
			api.tagsGetList(); //FIXME empty
			api.tagsGetAdminList();
			api.tagsGetImages(new PwgTagsGetImagesRequest().setTagName(tagName)); //FIXME no images?
		} finally {
			getClient().sendRequest(new PwgImagesDeleteRequest(imageResponse.image_id));
		}
	}
	
	public void testThemes() throws ClientServerException {
//		API api = new API(getClient());
//		api.themesPerformAction(new PwgThemesPerformActionRequest(org.piwigo.remotesync.api.request.PwgThemesPerformActionRequest.Action.ACTIVATE, "theme"));
	}
	
	public void testUsers() throws ClientServerException {
		API api = new API(getClient());
		PwgUsersAddResponse usersAdd = api.usersAdd(new PwgUsersAddRequest("matthieu"));
		assertEquals(1, usersAdd.users.size());
		Integer newUserId = usersAdd.users.get(0).id;
		api.usersGetList();
		api.usersSetInfo(new PwgUsersSetInfoRequest(newUserId).setNbImagePage(5));
		api.usersDelete(new PwgUsersDeleteRequest(newUserId));
		
	}
	
}
