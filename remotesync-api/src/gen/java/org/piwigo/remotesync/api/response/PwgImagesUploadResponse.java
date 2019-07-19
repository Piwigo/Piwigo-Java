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

import org.piwigo.remotesync.api.model.UploadCategory;
import org.simpleframework.xml.Element;

@org.piwigo.remotesync.generator.Generated
public class PwgImagesUploadResponse extends BasicResponse {

    @Element(required = false)
	public Integer image_id;

	@Element(required = false)
    public String src;

    @Element(required = false)
    public String square_src;

    @Element(required = false)
    public String name;

    @Element(required = false)
    public UploadCategory category;
}
