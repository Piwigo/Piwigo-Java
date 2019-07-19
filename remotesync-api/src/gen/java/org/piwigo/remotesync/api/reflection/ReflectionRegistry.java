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

import java.util.List;
import java.util.ArrayList;
import org.piwigo.remotesync.api.reflection.MethodReflection;
import org.piwigo.remotesync.api.request.*;
import org.piwigo.remotesync.api.response.*;

@org.piwigo.remotesync.generator.Generated
public class ReflectionRegistry {

	public static List<MethodReflection> methodDetails;

	static {
		methodDetails = new ArrayList<MethodReflection>();
		methodDetails.add(new MethodReflection("pwg.caddie.add", "pwg.caddie.add.xml", PwgCaddieAddRequest.class, PwgCaddieAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.add", "pwg.categories.add.xml", PwgCategoriesAddRequest.class, PwgCategoriesAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.delete", "pwg.categories.delete.xml", PwgCategoriesDeleteRequest.class, PwgCategoriesDeleteResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.getAdminList", "pwg.categories.getAdminList.xml", PwgCategoriesGetAdminListRequest.class, PwgCategoriesGetAdminListResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.getImages", "pwg.categories.getImages.xml", PwgCategoriesGetImagesRequest.class, PwgCategoriesGetImagesResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.getList", "pwg.categories.getList.xml", PwgCategoriesGetListRequest.class, PwgCategoriesGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.move", "pwg.categories.move.xml", PwgCategoriesMoveRequest.class, PwgCategoriesMoveResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.setInfo", "pwg.categories.setInfo.xml", PwgCategoriesSetInfoRequest.class, PwgCategoriesSetInfoResponse.class));
		methodDetails.add(new MethodReflection("pwg.categories.setRepresentative", "pwg.categories.setRepresentative.xml", PwgCategoriesSetRepresentativeRequest.class, PwgCategoriesSetRepresentativeResponse.class));
		methodDetails.add(new MethodReflection("pwg.extensions.checkUpdates", "pwg.extensions.checkUpdates.xml", PwgExtensionsCheckUpdatesRequest.class, PwgExtensionsCheckUpdatesResponse.class));
		methodDetails.add(new MethodReflection("pwg.extensions.ignoreUpdate", "pwg.extensions.ignoreUpdate.xml", PwgExtensionsIgnoreUpdateRequest.class, PwgExtensionsIgnoreUpdateResponse.class));
		methodDetails.add(new MethodReflection("pwg.extensions.update", "pwg.extensions.update.xml", PwgExtensionsUpdateRequest.class, PwgExtensionsUpdateResponse.class));
		methodDetails.add(new MethodReflection("pwg.getInfos", "pwg.getInfos.xml", PwgGetInfosRequest.class, PwgGetInfosResponse.class));
		methodDetails.add(new MethodReflection("pwg.getMissingDerivatives", "pwg.getMissingDerivatives.xml", PwgGetMissingDerivativesRequest.class, PwgGetMissingDerivativesResponse.class));
		methodDetails.add(new MethodReflection("pwg.getVersion", "pwg.getVersion.xml", PwgGetVersionRequest.class, PwgGetVersionResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.add", "pwg.groups.add.xml", PwgGroupsAddRequest.class, PwgGroupsAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.addUser", "pwg.groups.addUser.xml", PwgGroupsAddUserRequest.class, PwgGroupsAddUserResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.delete", "pwg.groups.delete.xml", PwgGroupsDeleteRequest.class, PwgGroupsDeleteResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.deleteUser", "pwg.groups.deleteUser.xml", PwgGroupsDeleteUserRequest.class, PwgGroupsDeleteUserResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.getList", "pwg.groups.getList.xml", PwgGroupsGetListRequest.class, PwgGroupsGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.groups.setInfo", "pwg.groups.setInfo.xml", PwgGroupsSetInfoRequest.class, PwgGroupsSetInfoResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.add", "pwg.images.add.xml", PwgImagesAddRequest.class, PwgImagesAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.addChunk", "pwg.images.addChunk.xml", PwgImagesAddChunkRequest.class, PwgImagesAddChunkResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.addComment", "pwg.images.addComment.xml", PwgImagesAddCommentRequest.class, PwgImagesAddCommentResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.addFile", "pwg.images.addFile.xml", PwgImagesAddFileRequest.class, PwgImagesAddFileResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.addSimple", "pwg.images.addSimple.xml", PwgImagesAddSimpleRequest.class, PwgImagesAddSimpleResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.upload", "pwg.images.upload.xml", PwgImagesUploadRequest.class, PwgImagesUploadResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.checkFiles", "pwg.images.checkFiles.xml", PwgImagesCheckFilesRequest.class, PwgImagesCheckFilesResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.checkUpload", "pwg.images.checkUpload.xml", PwgImagesCheckUploadRequest.class, PwgImagesCheckUploadResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.delete", "pwg.images.delete.xml", PwgImagesDeleteRequest.class, PwgImagesDeleteResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.exist", "pwg.images.exist.xml", PwgImagesExistRequest.class, PwgImagesExistResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.getInfo", "pwg.images.getInfo.xml", PwgImagesGetInfoRequest.class, PwgImagesGetInfoResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.rate", "pwg.images.rate.xml", PwgImagesRateRequest.class, PwgImagesRateResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.search", "pwg.images.search.xml", PwgImagesSearchRequest.class, PwgImagesSearchResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.setInfo", "pwg.images.setInfo.xml", PwgImagesSetInfoRequest.class, PwgImagesSetInfoResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.setPrivacyLevel", "pwg.images.setPrivacyLevel.xml", PwgImagesSetPrivacyLevelRequest.class, PwgImagesSetPrivacyLevelResponse.class));
		methodDetails.add(new MethodReflection("pwg.images.setRank", "pwg.images.setRank.xml", PwgImagesSetRankRequest.class, PwgImagesSetRankResponse.class));
		methodDetails.add(new MethodReflection("pwg.permissions.add", "pwg.permissions.add.xml", PwgPermissionsAddRequest.class, PwgPermissionsAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.permissions.getList", "pwg.permissions.getList.xml", PwgPermissionsGetListRequest.class, PwgPermissionsGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.permissions.remove", "pwg.permissions.remove.xml", PwgPermissionsRemoveRequest.class, PwgPermissionsRemoveResponse.class));
		methodDetails.add(new MethodReflection("pwg.plugins.getList", "pwg.plugins.getList.xml", PwgPluginsGetListRequest.class, PwgPluginsGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.plugins.performAction", "pwg.plugins.performAction.xml", PwgPluginsPerformActionRequest.class, PwgPluginsPerformActionResponse.class));
		methodDetails.add(new MethodReflection("pwg.rates.delete", "pwg.rates.delete.xml", PwgRatesDeleteRequest.class, PwgRatesDeleteResponse.class));
		methodDetails.add(new MethodReflection("pwg.session.getStatus", "pwg.session.getStatus.xml", PwgSessionGetStatusRequest.class, PwgSessionGetStatusResponse.class));
		methodDetails.add(new MethodReflection("pwg.session.login", "pwg.session.login.xml", PwgSessionLoginRequest.class, PwgSessionLoginResponse.class));
		methodDetails.add(new MethodReflection("pwg.session.logout", "pwg.session.logout.xml", PwgSessionLogoutRequest.class, PwgSessionLogoutResponse.class));
		methodDetails.add(new MethodReflection("pwg.tags.add", "pwg.tags.add.xml", PwgTagsAddRequest.class, PwgTagsAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.tags.getAdminList", "pwg.tags.getAdminList.xml", PwgTagsGetAdminListRequest.class, PwgTagsGetAdminListResponse.class));
		methodDetails.add(new MethodReflection("pwg.tags.getImages", "pwg.tags.getImages.xml", PwgTagsGetImagesRequest.class, PwgTagsGetImagesResponse.class));
		methodDetails.add(new MethodReflection("pwg.tags.getList", "pwg.tags.getList.xml", PwgTagsGetListRequest.class, PwgTagsGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.themes.performAction", "pwg.themes.performAction.xml", PwgThemesPerformActionRequest.class, PwgThemesPerformActionResponse.class));
		methodDetails.add(new MethodReflection("pwg.users.add", "pwg.users.add.xml", PwgUsersAddRequest.class, PwgUsersAddResponse.class));
		methodDetails.add(new MethodReflection("pwg.users.delete", "pwg.users.delete.xml", PwgUsersDeleteRequest.class, PwgUsersDeleteResponse.class));
		methodDetails.add(new MethodReflection("pwg.users.getList", "pwg.users.getList.xml", PwgUsersGetListRequest.class, PwgUsersGetListResponse.class));
		methodDetails.add(new MethodReflection("pwg.users.setInfo", "pwg.users.setInfo.xml", PwgUsersSetInfoRequest.class, PwgUsersSetInfoResponse.class));
		methodDetails.add(new MethodReflection("reflection.getMethodDetails", "reflection.getMethodDetails.xml", ReflectionGetMethodDetailsRequest.class, ReflectionGetMethodDetailsResponse.class));
		methodDetails.add(new MethodReflection("reflection.getMethodList", "reflection.getMethodList.xml", ReflectionGetMethodListRequest.class, ReflectionGetMethodListResponse.class));
	}
}
