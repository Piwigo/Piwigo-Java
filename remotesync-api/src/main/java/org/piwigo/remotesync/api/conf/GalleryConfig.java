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
	protected String url;

	@Element(required = false)
	@Option(name = "-usr", usage = "remote gallery username")
	@Validator(type = ValidatorType.string, required = ValidatorRequired.yes)
	protected String username;

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
	protected String proxyUrl;

	@Element(required = false)
	@Option(name = "-pprt", usage = "proxy port")
	@Validator(type = ValidatorType.integer, required = ValidatorRequired.proxy)
	protected String proxyPort;

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

	public Boolean getUsesProxy() {
		return Boolean.parseBoolean(usesProxy);
	}

	public void setUsesProxy(Boolean usesProxy) {
		this.usesProxy = usesProxy.toString();
	}

	public String getProxyUrl() {
		return proxyUrl;
	}

	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}

	public Integer getProxyPort() {
		return Integer.parseInt(proxyPort);
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort.toString();
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

	public int getChunkSize() {
		return Integer.parseInt(chunkSize);
	}

	public void setChunkSize(Integer chunkSize) {
		this.chunkSize = chunkSize.toString();
	}
}
