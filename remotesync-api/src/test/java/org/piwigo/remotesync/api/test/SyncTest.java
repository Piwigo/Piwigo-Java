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

import org.piwigo.remotesync.api.cache.AlbumCacheElement;
import org.piwigo.remotesync.api.cache.ImageCacheElement;
import org.piwigo.remotesync.api.cache.LegacyCache;

public class SyncTest extends AbstractTestCase {

	public void testLegacyCache() throws URISyntaxException {
		LegacyCache legacyCache = new LegacyCache("http://a", null);

		assertTrue(legacyCache.isSameUrl("http://a"));
		assertTrue(legacyCache.isSameUrl("https://a"));
		assertFalse(legacyCache.isSameUrl("http://b"));
		assertFalse(legacyCache.isSameUrl("https://b"));
	}

	public void testLegacyCache1() throws URISyntaxException {
		LegacyCache legacyCache = new LegacyCache("url", null);

		AlbumCacheElement a = new AlbumCacheElement("url", 3);
		legacyCache.parseContent(a.writeToString());
		assertEquals(a.writeToString(), legacyCache.getAlbumCacheElement().writeToString());

		ImageCacheElement p = new ImageCacheElement("url", 3, "filePathMD5");
		legacyCache.parseContent(p.writeToString());
		assertEquals(p.writeToString(), legacyCache.getImagesCache().get(0).writeToString());
	}

	public void testLegacyCache2() throws URISyntaxException {
		assertTrue(getPiwigoImportTreeFile().exists());

		LegacyCache legacyCache = new LegacyCache("http://localhost/piwigo/dev/branches/2.6/", getPiwigoImportTreeFile());
		legacyCache.parseFile();
		assertEquals(116, legacyCache.getAlbumCacheElement().getId().intValue());
		assertEquals(20, legacyCache.getImagesCache().size());
		assertEquals(502, legacyCache.getImagesCache().get(0).getId().intValue());
		assertEquals(521, legacyCache.getImagesCache().get(19).getId().intValue());
		assertEquals("d2d97dd5727972522adfd82f43daddcd", legacyCache.getImagesCache().get(1).getFilePathMD5());
		
		legacyCache = new LegacyCache("http://other", getPiwigoImportTreeFile());
		legacyCache.parseFile();
		assertNull(legacyCache.getAlbumCacheElement());
		assertEquals(0, legacyCache.getImagesCache().size());
	}
}
