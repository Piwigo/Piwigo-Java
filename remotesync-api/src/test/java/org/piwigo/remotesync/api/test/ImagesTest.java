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

import java.io.File;

import org.piwigo.remotesync.api.IClient;
import org.piwigo.remotesync.api.request.PwgCategoriesAddRequest;
import org.piwigo.remotesync.api.request.PwgCategoriesDeleteRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddSimpleRequest;
import org.piwigo.remotesync.api.request.PwgImagesAddWithChunkRequest;
import org.piwigo.remotesync.api.request.PwgImagesDeleteRequest;
import org.piwigo.remotesync.api.response.ComposedResponse;
import org.piwigo.remotesync.api.response.PwgCategoriesAddResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddResponse;
import org.piwigo.remotesync.api.response.PwgImagesAddSimpleResponse;

public class ImagesTest extends AbstractTestCase {
	public void testAddSimple() throws Exception {
		IClient client = getClient();
		File image = getImageFile();
		PwgCategoriesAddResponse categoryResponse = client.sendRequest(new PwgCategoriesAddRequest("test"));
		PwgImagesAddSimpleResponse imageResponse = client.sendRequest(new PwgImagesAddSimpleRequest(image).setCategory(categoryResponse.id));
		client.sendRequest(new PwgImagesDeleteRequest(imageResponse.image_id));
		client.sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
	}

	public void testAddWithChunk() throws Exception {
		IClient client = getClient();
		File image = getImageFile();
		PwgCategoriesAddResponse categoryResponse = client.sendRequest(new PwgCategoriesAddRequest("test"));
		ComposedResponse<PwgImagesAddResponse> composedResponse = client.sendRequest(new PwgImagesAddWithChunkRequest(image).setCategories(categoryResponse.id + ""));
		PwgImagesAddResponse imagesAddResponse = composedResponse.getResponse();
		client.sendRequest(new PwgImagesDeleteRequest(imagesAddResponse.image_id));
		client.sendRequest(new PwgCategoriesDeleteRequest(categoryResponse.id));
	}
}
