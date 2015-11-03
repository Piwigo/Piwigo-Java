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
package org.piwigo.remotesync.api.request;

import java.io.File;

import org.piwigo.remotesync.api.exception.ClientException;
import org.piwigo.remotesync.api.request.PwgImagesAddRequest;
import org.piwigo.remotesync.api.response.PwgImagesAddResponse;
import org.piwigo.remotesync.api.util.FileUtil;

public class PwgImagesAddWithChunkRequest extends ComposedRequest<PwgImagesAddResponse> {

	private PwgImagesAddRequest request;

	public PwgImagesAddWithChunkRequest(File file) throws ClientException {
		addRequest(new PwgImagesAddAllChunksRequest(file));
		request = new PwgImagesAddRequest(FileUtil.getFileContentMD5Sum(file));
		addRequest(request);
	}
	
 	public PwgImagesAddWithChunkRequest setThumbnailSum(String thumbnail_sum) {
		request.setThumbnailSum(thumbnail_sum);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setHighSum(String high_sum) {
 		request.setHighSum(high_sum);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setOriginalSum(String original_sum) {
 		request.setOriginalSum(original_sum);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setOriginalFilename(String original_filename) {
 		request.setOriginalFilename(original_filename);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setName(String name) {
 		request.setName(name);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setAuthor(String author) {
 		request.setAuthor(author);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setDateCreation(String date_creation) {
 		request.setDateCreation(date_creation);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setComment(String comment) {
 		request.setComment(comment);
		return this;
	}	

	//info : String list "category_id[,rank];category_id[,rank]".<br>The rank is optional and is equivalent to "auto" if not given.
 	public PwgImagesAddWithChunkRequest setCategories(String categories) {
 		request.setCategories(categories);
		return this;
	}	

	//info : Comma separated ids
 	public PwgImagesAddWithChunkRequest setTagIds(Integer tag_ids) {
 		request.setTagIds(tag_ids);
		return this;
	}	

	//defaultValue : 0
	//maxValue : 8
 	public PwgImagesAddWithChunkRequest setLevel(Integer level) {
 		request.setLevel(level);
		return this;
	}	

	//defaultValue : 1
 	public PwgImagesAddWithChunkRequest setCheckUniqueness(Boolean check_uniqueness) {
 		request.setCheckUniqueness(check_uniqueness);
		return this;
	}	

 	public PwgImagesAddWithChunkRequest setImageId(Integer image_id) {
 		request.setImageId(image_id);
		return this;
	}	
}
