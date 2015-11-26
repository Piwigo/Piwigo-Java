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

public interface IClientConfiguration {
	public String getUrl();
	
	public String getUsername();
	public String getPassword();
	
	public boolean getUsesProxy();
	public String getProxyUrl();
	public int getProxyPort();
	public String getProxyUsername();
	public String getProxyPassword();
	
	public boolean getTrustSelfSignedSSLCertificate();

	public int getChunkSize();
}
