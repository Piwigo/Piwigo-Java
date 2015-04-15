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
package org.piwigo.remotesync.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.piwigo.remotesync.api.exception.IORuntimeException;
import org.piwigo.remotesync.api.type.Type;
import org.piwigo.remotesync.api.type.ValueValidator;

public class FileUtil {
	public static String getMD5Sum(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			return DigestUtils.md5Hex(fileInputStream);
		} catch (IOException e) {
			throw new IORuntimeException("Cannot compute MD5Sum", e);
		} finally {
			IOUtils.closeQuietly(fileInputStream);
		}
	}

	public static boolean isImage(File file) {
		try {
			ValueValidator.checkValue(Type.FILE, null, file);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getChunkNumber(File file, int chunkSize) {
		// TODO implement for directory 
		return getChunkNumber(file.length(), chunkSize);
	}

	public static int getChunkNumber(long fileLength, int chunkSize) {
		return (int) (fileLength / (long) chunkSize) + 1;
	}

	public static int getChunkSize(File file, int chunkSize, int chunkNumber) {
		int bytesSize = chunkSize;
		if ((chunkNumber + 1) * chunkSize > file.length())
			bytesSize = (int) (file.length() - ((long) chunkNumber * chunkSize));
		return bytesSize;
	}

	/**
	 * chunkNumber start at 0
	 */
	public static byte[] getFilePart(File file, int chunkSize, int chunkNumber) {
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			int bytesSize = getChunkSize(file, chunkSize, chunkNumber);
			byte[] bytes = new byte[bytesSize];
			randomAccessFile.seek(chunkSize * chunkNumber);
			randomAccessFile.read(bytes, 0, bytesSize);
			return bytes;
		} catch (IOException e) {
			throw new IORuntimeException("Cannot get file part", e);
		} finally {
			IOUtils.closeQuietly(randomAccessFile);
		}
	}

	/**
	 * chunkNumber start at 0
	 */
	public static String getBase64String(File file, int chunkSize, int chunkNumber) {
		return Base64.encodeBase64String(getFilePart(file, chunkSize, chunkNumber));
	}

}
