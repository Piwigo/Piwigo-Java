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
package org.piwigo.remotesync.api.conf;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class Config {

	@ElementList
	private List<GalleryConfig> galleryConfigs = new ArrayList<GalleryConfig>();

	@Attribute(required = false)
	private Integer currentGalleryConfigId;

	public GalleryConfig getCurrentGalleryConfig() {
		if (currentGalleryConfigId == null) {
			currentGalleryConfigId = 0;
			galleryConfigs.add(new GalleryConfig());
		}
		return galleryConfigs.get(currentGalleryConfigId);
	}

	public void setCurrentGalleryConfig(GalleryConfig currentGalleryConfig) {
		if (!galleryConfigs.contains(currentGalleryConfig))
			galleryConfigs.add(currentGalleryConfig);
		currentGalleryConfigId = galleryConfigs.indexOf(currentGalleryConfig);
	}
	
	public List<GalleryConfig> getGalleryConfigs() {
		return galleryConfigs;
	}

}
