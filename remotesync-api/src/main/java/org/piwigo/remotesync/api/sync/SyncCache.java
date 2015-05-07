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
package org.piwigo.remotesync.api.sync;

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

public class SyncCache {

	static final Logger logger = LoggerFactory.getLogger(SyncCache.class);

	public static final String LEGACY_CACHE_FILE_NAME = ".piwigo_import_tree.txt";

	private static final Pattern ALBUM_PATTERN = Pattern.compile("(.*) album_id = (\\d*)");

	private static final Pattern PICTURE_PATTERN = Pattern.compile("(.*) (.*) \\[id=(\\d*)\\]");

	public static abstract class CacheElement {
		public String url;
		public Integer id;
	}

	public static class AlbumCache extends CacheElement {

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(url);
			stringBuilder.append(" album_id = ");
			stringBuilder.append(id);
			return stringBuilder.toString();
		}
	}

	public static class PictureCache extends CacheElement {
		public String md5;

		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(url);
			stringBuilder.append(" ");
			stringBuilder.append(md5);
			stringBuilder.append(" [id=");
			stringBuilder.append(id);
			stringBuilder.append("]");
			return stringBuilder.toString();
		}
	}

	public static class MemoryCache {
		public String url;
		public AlbumCache albumCache;
		public List<PictureCache> picturesCache = new ArrayList<PictureCache>();

		public MemoryCache(String url) {
			this.url = url;
		}

		public MemoryCache parseContent(String content) {
			Matcher matcher = ALBUM_PATTERN.matcher(content);
			if (matcher.find()) {
				albumCache = new AlbumCache();
				albumCache.url = matcher.group(1);
				albumCache.id = Integer.parseInt(matcher.group(2));
			}

			matcher = PICTURE_PATTERN.matcher(content);
			while (matcher.find()) {
				PictureCache pictureCache = new PictureCache();
				pictureCache.url = matcher.group(1);
				pictureCache.md5 = matcher.group(2);
				pictureCache.id = Integer.parseInt(matcher.group(3));
				picturesCache.add(pictureCache);
			}

			return this;
		}

		protected AlbumCache addAlbum(Integer id) {
			AlbumCache albumCache = new AlbumCache();
			albumCache.id = id;
			albumCache.url = url;
			this.albumCache = albumCache;
			return albumCache;
		}

		protected PictureCache addPicture(File file, Integer id) {
			PictureCache pictureCache = new PictureCache();
			pictureCache.id = id;
			pictureCache.url = url;
			pictureCache.md5 = FileUtil.getMD5Sum(file);
			picturesCache.add(pictureCache);
			return pictureCache;
		}
		
		public boolean containsPicture(File file) {
			String md5 = FileUtil.getMD5Sum(file);
			for (PictureCache pictureCache : picturesCache) {
				if (pictureCache.md5.equals(md5))
					return true;
			}
			return false;
		}
	}

	public static class FileCache extends MemoryCache {
		private File cacheFile;

		public FileCache(String url, File cacheFile) {
			super(url);
			this.cacheFile = cacheFile;
		}

		public FileCache parseFile() {
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

		@Override
		protected AlbumCache addAlbum(Integer id) {
			if (id == null)
				return null;

			AlbumCache addAlbum = super.addAlbum(id);
			writeToFile(addAlbum);
			return addAlbum;
		}

		@Override
		protected PictureCache addPicture(File file, Integer id) {
			if (id == null)
				return null;

			PictureCache addPicture = super.addPicture(file, id);
			writeToFile(addPicture);
			return addPicture;
		}

		protected void writeToFile(CacheElement cacheElement) {
			try {
				FileUtils.writeStringToFile(cacheFile, "\n" + cacheElement.toString(), true);
			} catch (IOException e) {
				logger.error("Cannot write " + LEGACY_CACHE_FILE_NAME + " in directory " + cacheFile.getParent(), e);
			}
		}
	}

	public static File getLegacyCacheFile(File directory) {
		return new File(directory, LEGACY_CACHE_FILE_NAME);
	}
}
