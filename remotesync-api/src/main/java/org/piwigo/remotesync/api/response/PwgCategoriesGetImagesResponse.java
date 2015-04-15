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
package org.piwigo.remotesync.api.response;

import java.util.List;

import org.piwigo.remotesync.api.model.Image;
import org.piwigo.remotesync.api.model.Paging;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class PwgCategoriesGetImagesResponse extends BasicResponse {
	
	@Element
	public Paging paging;

	@ElementList
	public List<Image> images;
}
