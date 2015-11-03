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
package org.piwigo.remotesync.api;

import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.*;
import org.piwigo.remotesync.api.response.*;

@org.piwigo.remotesync.generator.Generated
public abstract class AbstractAPI {

	protected abstract IClient getClient();

	public PwgCaddieAddResponse caddieAdd(PwgCaddieAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesAddResponse categoriesAdd(PwgCategoriesAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesDeleteResponse categoriesDelete(PwgCategoriesDeleteRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesGetAdminListResponse categoriesGetAdminList() throws ClientServerException {
		return getClient().sendRequest(new PwgCategoriesGetAdminListRequest());
	}

	public PwgCategoriesGetImagesResponse categoriesGetImages() throws ClientServerException {
		return getClient().sendRequest(new PwgCategoriesGetImagesRequest());
	}

	public PwgCategoriesGetImagesResponse categoriesGetImages(PwgCategoriesGetImagesRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesGetListResponse categoriesGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgCategoriesGetListRequest());
	}

	public PwgCategoriesGetListResponse categoriesGetList(PwgCategoriesGetListRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesMoveResponse categoriesMove(PwgCategoriesMoveRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesSetInfoResponse categoriesSetInfo(PwgCategoriesSetInfoRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgCategoriesSetRepresentativeResponse categoriesSetRepresentative(PwgCategoriesSetRepresentativeRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgExtensionsCheckUpdatesResponse extensionsCheckUpdates() throws ClientServerException {
		return getClient().sendRequest(new PwgExtensionsCheckUpdatesRequest());
	}

	public PwgExtensionsIgnoreUpdateResponse extensionsIgnoreUpdate() throws ClientServerException {
		return getClient().sendRequest(new PwgExtensionsIgnoreUpdateRequest());
	}

	public PwgExtensionsIgnoreUpdateResponse extensionsIgnoreUpdate(PwgExtensionsIgnoreUpdateRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgExtensionsUpdateResponse extensionsUpdate(PwgExtensionsUpdateRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGetInfosResponse galleryGetInfos() throws ClientServerException {
		return getClient().sendRequest(new PwgGetInfosRequest());
	}

	public PwgGetMissingDerivativesResponse galleryGetMissingDerivatives() throws ClientServerException {
		return getClient().sendRequest(new PwgGetMissingDerivativesRequest());
	}

	public PwgGetMissingDerivativesResponse galleryGetMissingDerivatives(PwgGetMissingDerivativesRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGetVersionResponse galleryGetVersion() throws ClientServerException {
		return getClient().sendRequest(new PwgGetVersionRequest());
	}

	public PwgGroupsAddResponse groupsAdd(PwgGroupsAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGroupsAddUserResponse groupsAddUser(PwgGroupsAddUserRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGroupsDeleteResponse groupsDelete(PwgGroupsDeleteRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGroupsDeleteUserResponse groupsDeleteUser(PwgGroupsDeleteUserRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGroupsGetListResponse groupsGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgGroupsGetListRequest());
	}

	public PwgGroupsGetListResponse groupsGetList(PwgGroupsGetListRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgGroupsSetInfoResponse groupsSetInfo(PwgGroupsSetInfoRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesAddResponse imagesAdd(PwgImagesAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesAddChunkResponse imagesAddChunk(PwgImagesAddChunkRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesAddCommentResponse imagesAddComment(PwgImagesAddCommentRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesAddFileResponse imagesAddFile(PwgImagesAddFileRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesAddSimpleResponse imagesAddSimple(PwgImagesAddSimpleRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesCheckFilesResponse imagesCheckFiles(PwgImagesCheckFilesRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesCheckUploadResponse imagesCheckUpload() throws ClientServerException {
		return getClient().sendRequest(new PwgImagesCheckUploadRequest());
	}

	public PwgImagesDeleteResponse imagesDelete(PwgImagesDeleteRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesExistResponse imagesExist() throws ClientServerException {
		return getClient().sendRequest(new PwgImagesExistRequest());
	}

	public PwgImagesExistResponse imagesExist(PwgImagesExistRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesGetInfoResponse imagesGetInfo(PwgImagesGetInfoRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesRateResponse imagesRate(PwgImagesRateRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesSearchResponse imagesSearch(PwgImagesSearchRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesSetInfoResponse imagesSetInfo(PwgImagesSetInfoRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesSetPrivacyLevelResponse imagesSetPrivacyLevel(PwgImagesSetPrivacyLevelRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgImagesSetRankResponse imagesSetRank(PwgImagesSetRankRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgPermissionsAddResponse permissionsAdd(PwgPermissionsAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgPermissionsGetListResponse permissionsGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgPermissionsGetListRequest());
	}

	public PwgPermissionsGetListResponse permissionsGetList(PwgPermissionsGetListRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgPermissionsRemoveResponse permissionsRemove(PwgPermissionsRemoveRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgPluginsGetListResponse pluginsGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgPluginsGetListRequest());
	}

	public PwgPluginsPerformActionResponse pluginsPerformAction(PwgPluginsPerformActionRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgRatesDeleteResponse ratesDelete(PwgRatesDeleteRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgSessionGetStatusResponse sessionGetStatus() throws ClientServerException {
		return getClient().sendRequest(new PwgSessionGetStatusRequest());
	}

	public PwgSessionLoginResponse sessionLogin(PwgSessionLoginRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgSessionLogoutResponse sessionLogout() throws ClientServerException {
		return getClient().sendRequest(new PwgSessionLogoutRequest());
	}

	public PwgTagsAddResponse tagsAdd(PwgTagsAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgTagsGetAdminListResponse tagsGetAdminList() throws ClientServerException {
		return getClient().sendRequest(new PwgTagsGetAdminListRequest());
	}

	public PwgTagsGetImagesResponse tagsGetImages() throws ClientServerException {
		return getClient().sendRequest(new PwgTagsGetImagesRequest());
	}

	public PwgTagsGetImagesResponse tagsGetImages(PwgTagsGetImagesRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgTagsGetListResponse tagsGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgTagsGetListRequest());
	}

	public PwgTagsGetListResponse tagsGetList(PwgTagsGetListRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgThemesPerformActionResponse themesPerformAction(PwgThemesPerformActionRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgUsersAddResponse usersAdd(PwgUsersAddRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgUsersDeleteResponse usersDelete(PwgUsersDeleteRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgUsersGetListResponse usersGetList() throws ClientServerException {
		return getClient().sendRequest(new PwgUsersGetListRequest());
	}

	public PwgUsersGetListResponse usersGetList(PwgUsersGetListRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public PwgUsersSetInfoResponse usersSetInfo(PwgUsersSetInfoRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public ReflectionGetMethodDetailsResponse reflectionGetMethodDetails(ReflectionGetMethodDetailsRequest request) throws ClientServerException {
		return getClient().sendRequest(request);
	}

	public ReflectionGetMethodListResponse reflectionGetMethodList() throws ClientServerException {
		return getClient().sendRequest(new ReflectionGetMethodListRequest());
	}

}
