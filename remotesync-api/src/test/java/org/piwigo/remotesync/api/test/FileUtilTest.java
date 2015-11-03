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
package org.piwigo.remotesync.api.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.piwigo.remotesync.api.util.FileUtil;
import org.piwigo.remotesync.generator.Generated;

public class FileUtilTest extends AbstractTestCase {
	public void testMD5Sum() throws IOException, URISyntaxException {
		assertEquals("6c73caff0ed3241365058f0a7f82d7d2", FileUtil.getFileContentMD5Sum(getImageFile()));
		assertEquals("0d5b1c4c7f720f698946c7f6ab08f687", FileUtil.getFileNameMD5Sum(getImageFile()));
	}
	
	public void testChunkNumber() throws URISyntaxException {
		assertEquals(1, FileUtil.getChunkNumber(getImageFile(), Integer.MAX_VALUE));
		assertEquals(3, FileUtil.getChunkNumber(getImageFile(), (int) (getImageFile().length() / 2 - 1)));
	}
	
	public void testChunkSize() throws URISyntaxException {
		assertEquals((int) getImageFile().length(), FileUtil.getChunkSize(getImageFile(), (int) getImageFile().length(), 0));
		assertEquals(1, FileUtil.getChunkSize(getImageFile(), 1, 0));
		assertEquals(1, FileUtil.getChunkSize(getImageFile(), (int) getImageFile().length() - 1, 1));
	}

	public void testChunkSizeAndNumber() throws URISyntaxException {
		int chunkSize = 123;
		int chunkNumber = FileUtil.getChunkNumber(getImageFile(), chunkSize);
		long total = 0;
		for  (int i = 0 ; i < chunkNumber ; i++) {
			int actualChunkSize = FileUtil.getChunkSize(getImageFile(), chunkSize, i);
			assertTrue(actualChunkSize > 0);
			total += actualChunkSize;
		}
		assertEquals(getImageFile().length(), total);
	}
	
	public void testFilePart() throws IOException, URISyntaxException {
		int chunkSize = 456;
		int chunkNumber = FileUtil.getChunkNumber(getImageFile(), chunkSize);
		byte[] result = new byte[0];
		for  (int i = 0 ; i < chunkNumber ; i++)
			result = ArrayUtils.addAll(result, FileUtil.getFilePart(getImageFile(), chunkSize, i));
		assertTrue(Arrays.equals(IOUtils.toByteArray(new FileInputStream(getImageFile())), result));
	}
	
	public void testIsImage() throws URISyntaxException  {
		assertTrue(FileUtil.isImage(getImageFile()));
		assertFalse(FileUtil.isImage(getPiwigoImportTreeFile()));
	}
	
	public void testResources() {
		assertNull(FileUtil.getFile(this.getClass(), "apiTemplate.jmte"));
		assertNotNull(FileUtil.getFile(Generated.class, "apiTemplate.jmte"));
	}
}
