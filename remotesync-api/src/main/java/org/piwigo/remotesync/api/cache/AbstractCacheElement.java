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
package org.piwigo.remotesync.api.cache;

public abstract class AbstractCacheElement implements ILegacyCacheElement {
	protected String url;
	protected Integer id;
	
	public AbstractCacheElement() {
	}

	public AbstractCacheElement(String url, Integer id) {
		this.url = url;
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
}
