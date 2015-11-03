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

public class UserConfiguration {

	@ElementList
	private List<SyncConfiguration> syncConfigurations = new ArrayList<SyncConfiguration>();

	@Attribute(required = false)
	private Integer currentSyncConfigurationId;

	public SyncConfiguration getCurrentSyncConfiguration() {
		if (currentSyncConfigurationId == null) {
			currentSyncConfigurationId = 0;
			syncConfigurations.add(new SyncConfiguration());
		}
		return syncConfigurations.get(currentSyncConfigurationId);
	}

	public void setCurrentSyncConfiguration(SyncConfiguration currentSyncConfiguration) {
		if (!syncConfigurations.contains(currentSyncConfiguration))
			syncConfigurations.add(currentSyncConfiguration);
		currentSyncConfigurationId = syncConfigurations.indexOf(currentSyncConfiguration);
	}
	
	public List<SyncConfiguration> getSyncConfigurations() {
		return syncConfigurations;
	}

}
