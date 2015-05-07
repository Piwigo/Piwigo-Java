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

import java.net.URISyntaxException;

import org.piwigo.remotesync.api.sync.SyncCache.AlbumCache;
import org.piwigo.remotesync.api.sync.SyncCache.FileCache;
import org.piwigo.remotesync.api.sync.SyncCache.MemoryCache;
import org.piwigo.remotesync.api.sync.SyncCache.PictureCache;

public class SyncTest extends AbstractTestCase {

	public void testMemoryCache() {
		MemoryCache memoryCache = new MemoryCache("test");

		AlbumCache a = new AlbumCache();
		a.id = 3;
		a.url = "url";
		memoryCache.parseContent(a.toString());
		assertEquals(a.toString(), memoryCache.albumCache.toString());

		PictureCache p = new PictureCache();
		p.id = 3;
		p.url = "url";
		p.md5 = "md5";
		memoryCache.parseContent(p.toString());
		assertEquals(p.toString(), memoryCache.picturesCache.get(0).toString());
	}

	public void testFileCache() throws URISyntaxException {
		assertTrue(getPiwigoImportTreeFile().exists());
		FileCache fileCache = new FileCache(null, getPiwigoImportTreeFile());
		fileCache.parseFile();
		assertEquals(116, fileCache.albumCache.id.intValue());
		assertEquals(20, fileCache.picturesCache.size());
		assertEquals(502, fileCache.picturesCache.get(0).id.intValue());
		assertEquals(521, fileCache.picturesCache.get(19).id.intValue());
		assertEquals("d2d97dd5727972522adfd82f43daddcd", fileCache.picturesCache.get(1).md5);
	}
}
