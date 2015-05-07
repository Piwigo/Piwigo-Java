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

import java.lang.reflect.Field;

import org.kohsuke.args4j.Option;
import org.piwigo.remotesync.api.conf.GalleryConfigValidator.Validator;
import org.piwigo.remotesync.api.conf.GalleryConfigValidator.ValidatorRequired;
import org.piwigo.remotesync.api.conf.GalleryConfigValidator.ValidatorType;
import org.piwigo.remotesync.api.xml.FakePasswordConverter;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;

public class GalleryConfig {
	private static final String DIRECTORY_DEFAULT = ConfigUtil.INSTANCE.getUserCurrentDirectory().getAbsolutePath();

	private static final String CHUNK_SIZE_DEFAULT = "500";

	@Element(required = false)
	@Option(name = "-url", usage = "remote gallery url")
	@Validator(type = ValidatorType.url, required = ValidatorRequired.yes)
	protected String url = "http://mygallery.piwigo.com";

	@Element(required = false)
	@Option(name = "-usr", usage = "remote gallery username")
	@Validator(type = ValidatorType.string, required = ValidatorRequired.yes)
	protected String username = "username";

	//TODO is it possible to send an encrypted password if it's already encrypted on the server side
	@Element(required = false)
	@Convert(FakePasswordConverter.class)
	@Option(name = "-pwd", usage = "remote gallery password")
	@Validator(type = ValidatorType.string, required = ValidatorRequired.yes)
	protected String password;

	@Element(required = false)
	@Option(name = "-dir", usage = "local directory path")
	@Validator(type = ValidatorType.dir)
	protected String directory = DIRECTORY_DEFAULT;

	@Element(required = false)
	@Option(name = "-proxy", usage = "use proxy")
	protected String usesProxy = Boolean.FALSE.toString();

	@Element(required = false)
	@Option(name = "-purl", usage = "proxy url")
	@Validator(type = ValidatorType.url, required = ValidatorRequired.proxy)
	protected String proxyUrl = "http://myproxy.server.com";

	@Element(required = false)
	@Option(name = "-pprt", usage = "proxy port")
	@Validator(type = ValidatorType.integer, required = ValidatorRequired.proxy)
	protected String proxyPort = "8000";

	@Element(required = false)
	@Option(name = "-pusr", usage = "proxy username")
	@Validator(type = ValidatorType.string)
	protected String proxyUsername;

	@Element(required = false)
	@Option(name = "-ppwd", usage = "proxy password")
	@Validator(type = ValidatorType.string)
	protected String proxyPassword;

	@Element(required = false)
	@Option(name = "-cs", usage = "chunk size (in Kbytes)")
	@Validator(type = ValidatorType.integer)
	protected String chunkSize = CHUNK_SIZE_DEFAULT;

	public String getValue(String fieldName) {
		try {
			Field field = GalleryConfig.class.getDeclaredField(fieldName);
			return (String) field.get(this);
		} catch (Exception e) {
			throw new IllegalStateException(fieldName + " not found", e);
		}
	}

	public void setValue(String fieldName, String value) {
		try {
			Field field = GalleryConfig.class.getDeclaredField(fieldName);
			field.set(this, value);
		} catch (Exception e) {
			throw new IllegalStateException(fieldName + " not found", e);
		}
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getUsesProxy() {
		return usesProxy;
	}

	public void setUsesProxy(String string) {
		this.usesProxy = string;
	}

	public String getProxyUrl() {
		return proxyUrl;
	}

	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}

	public String getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyUsername() {
		return proxyUsername;
	}

	public void setProxyUsername(String proxyUsername) {
		this.proxyUsername = proxyUsername;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public String getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(String chunkSize) {
		this.chunkSize = chunkSize;
	}
	
	public boolean getUsesProxyBoolean() {
		try {
			return Boolean.parseBoolean(usesProxy);
		} catch (NumberFormatException e) {
			return false;
		}
	}


	public Integer getChunkSizeInteger() {
		try {
			return Integer.parseInt(chunkSize);
		} catch (NumberFormatException e) {
			return Integer.parseInt(CHUNK_SIZE_DEFAULT);
		}
	}

	public boolean isEmpty() {
		return this.equals(new GalleryConfig());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chunkSize == null) ? 0 : chunkSize.hashCode());
		result = prime * result + ((directory == null) ? 0 : directory.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((proxyPassword == null) ? 0 : proxyPassword.hashCode());
		result = prime * result + ((proxyPort == null) ? 0 : proxyPort.hashCode());
		result = prime * result + ((proxyUrl == null) ? 0 : proxyUrl.hashCode());
		result = prime * result + ((proxyUsername == null) ? 0 : proxyUsername.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((usesProxy == null) ? 0 : usesProxy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GalleryConfig other = (GalleryConfig) obj;
		if (chunkSize == null) {
			if (other.chunkSize != null)
				return false;
		} else if (!chunkSize.equals(other.chunkSize))
			return false;
		if (directory == null) {
			if (other.directory != null)
				return false;
		} else if (!directory.equals(other.directory))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (proxyPassword == null) {
			if (other.proxyPassword != null)
				return false;
		} else if (!proxyPassword.equals(other.proxyPassword))
			return false;
		if (proxyPort == null) {
			if (other.proxyPort != null)
				return false;
		} else if (!proxyPort.equals(other.proxyPort))
			return false;
		if (proxyUrl == null) {
			if (other.proxyUrl != null)
				return false;
		} else if (!proxyUrl.equals(other.proxyUrl))
			return false;
		if (proxyUsername == null) {
			if (other.proxyUsername != null)
				return false;
		} else if (!proxyUsername.equals(other.proxyUsername))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usesProxy == null) {
			if (other.usesProxy != null)
				return false;
		} else if (!usesProxy.equals(other.usesProxy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GalleryConfig [url=" + url + 
				", username=" + username + 
				", password=" + password + 
				", directory=" + directory + 
				", usesProxy=" + usesProxy + 
				", proxyUrl=" + proxyUrl + 
				", proxyPort=" + proxyPort + 
				", proxyUsername=" + proxyUsername + 
				", proxyPassword=" + proxyPassword + 
				", chunkSize=" + chunkSize + "]";
	}

	
	
}
