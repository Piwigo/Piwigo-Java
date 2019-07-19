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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.piwigo.remotesync.api.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyCache {

	public static final String LEGACY_CACHE_FILE_NAME = ".piwigo_import_tree";

	private static final Pattern ALBUM_PATTERN = Pattern.compile("(.*) album_id = (\\d*)");

	private static final Pattern IMAGE_PATTERN = Pattern.compile("(.*) (.*) \\[id=(\\d*)\\]");

	static final Logger logger = LoggerFactory.getLogger(LegacyCache.class);

	protected File cacheFile;
	protected String url;
	protected AlbumCacheElement albumCacheElement;
	protected List<ImageCacheElement> imagesCache = new ArrayList<ImageCacheElement>();

	public LegacyCache(String url, File cacheFile) {
		this.url = url;
		this.cacheFile = cacheFile;
	}
	
	public AlbumCacheElement getAlbumCacheElement() {
		return albumCacheElement;
	}
	
	public List<ImageCacheElement> getImagesCache() {
		return imagesCache;
	}

	public AlbumCacheElement addAlbum(Integer id) {
		if (id == null)
			return null;

		AlbumCacheElement addAlbum = doAddAlbum(id);
		writeToFile(addAlbum);
		return addAlbum;
	}

	public ImageCacheElement addImage(File file, Integer id) {
		if (id == null)
			return null;

		ImageCacheElement addImage = doAddImage(file, id);
		writeToFile(addImage);
		return addImage;
	}

	protected AlbumCacheElement doAddAlbum(Integer id) {
		albumCacheElement = new AlbumCacheElement(url, id);
		return albumCacheElement;
	}

	protected ImageCacheElement doAddImage(File file, Integer id) {
		ImageCacheElement imageCacheElement = new ImageCacheElement(url, id, FileUtil.getFileNameMD5Sum(file));
		imagesCache.add(imageCacheElement);
		return imageCacheElement;
	}

	public boolean containsImage(File file) {
		String md5 = FileUtil.getFileNameMD5Sum(file);
		for (ImageCacheElement imageCacheElement : imagesCache) {
			if (imageCacheElement.filePathMD5.equals(md5))
				return true;
		}
		return false;
	}

	public LegacyCache parseFile() {
		if (!cacheFile.exists()) {
			logger.debug(cacheFile + " doesn't exist");
			return this;
		}

		try {
			String content = FileUtils.readFileToString(cacheFile);
			parseContent(content);
		} catch (Exception e) {
			logger.error("Cannot read file " + cacheFile, e);
		}

		return this;
	}

	public LegacyCache parseContent(String content) {
		Matcher matcher = ALBUM_PATTERN.matcher(content);
		while (matcher.find()) {
			if (isSameUrl(matcher.group(1))) {
				albumCacheElement = new AlbumCacheElement();
				albumCacheElement.url = matcher.group(1);
				albumCacheElement.id = Integer.parseInt(matcher.group(2));
			}
		}

		matcher = IMAGE_PATTERN.matcher(content);
		while (matcher.find()) {
			if (isSameUrl(matcher.group(1))) {
				ImageCacheElement imageCacheElement = new ImageCacheElement();
				imageCacheElement.url = matcher.group(1);
				imageCacheElement.filePathMD5 = matcher.group(2);
				imageCacheElement.id = Integer.parseInt(matcher.group(3));
				imagesCache.add(imageCacheElement);
			}
		}

		return this;
	}

	public boolean isSameUrl(String otherUrl) {
		return url.replaceAll("https?", "").equals(otherUrl.replaceAll("https?", ""));
	}

	protected void writeToFile(ILegacyCacheElement abstractCacheElement) {
		try {
			FileUtils.writeStringToFile(cacheFile, abstractCacheElement.writeToString() + "\n", true);
		} catch (IOException e) {
			logger.error("Cannot write " + LEGACY_CACHE_FILE_NAME + " in directory " + cacheFile.getParent(), e);
		}
	}

	public static File getLegacyCacheFile(File directory) {
		return new File(directory, LEGACY_CACHE_FILE_NAME);
	}

}
