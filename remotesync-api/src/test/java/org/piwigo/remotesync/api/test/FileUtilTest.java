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

public class FileUtilTest extends AbstractTestCase {
	public void testMD5Sum() throws IOException, URISyntaxException {
		assertEquals("6c73caff0ed3241365058f0a7f82d7d2", FileUtil.getMD5Sum(getPictureFile()));
	}
	
	public void testChunkNumber() throws URISyntaxException {
		assertEquals(1, FileUtil.getChunkNumber(getPictureFile(), Integer.MAX_VALUE));
		assertEquals(3, FileUtil.getChunkNumber(getPictureFile(), (int) (getPictureFile().length() / 2 - 1)));
	}
	
	public void testChunkSize() throws URISyntaxException {
		assertEquals((int) getPictureFile().length(), FileUtil.getChunkSize(getPictureFile(), (int) getPictureFile().length(), 0));
		assertEquals(1, FileUtil.getChunkSize(getPictureFile(), 1, 0));
		assertEquals(1, FileUtil.getChunkSize(getPictureFile(), (int) getPictureFile().length() - 1, 1));
	}

	public void testChunkSizeAndNumber() throws URISyntaxException {
		int chunkSize = 123;
		int chunkNumber = FileUtil.getChunkNumber(getPictureFile(), chunkSize);
		long total = 0;
		for  (int i = 0 ; i < chunkNumber ; i++) {
			int actualChunkSize = FileUtil.getChunkSize(getPictureFile(), chunkSize, i);
			assertTrue(actualChunkSize > 0);
			total += actualChunkSize;
		}
		assertEquals(getPictureFile().length(), total);
	}
	
	public void testFilePart() throws IOException, URISyntaxException {
		int chunkSize = 456;
		int chunkNumber = FileUtil.getChunkNumber(getPictureFile(), chunkSize);
		byte[] result = new byte[0];
		for  (int i = 0 ; i < chunkNumber ; i++)
			result = ArrayUtils.addAll(result, FileUtil.getFilePart(getPictureFile(), chunkSize, i));
		assertTrue(Arrays.equals(IOUtils.toByteArray(new FileInputStream(getPictureFile())), result));
	}
	
	public void testIsImage() throws URISyntaxException  {
		assertTrue(FileUtil.isImage(getPictureFile()));
		assertFalse(FileUtil.isImage(getPiwigoImportTreeFile()));
	}
}
