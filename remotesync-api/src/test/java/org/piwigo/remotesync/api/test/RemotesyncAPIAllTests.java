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

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RemotesyncAPIAllTests extends TestCase {

	public static Test suite() {
		((ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ROOT")).setLevel(Level.DEBUG);
		
		TestSuite suite = new TestSuite(RemotesyncAPIAllTests.class.getName());
		// $JUnit-BEGIN$
		suite.addTestSuite(FileUtilTest.class);
		suite.addTestSuite(APITest.class);
		suite.addTestSuite(ImagesTest.class);
		suite.addTestSuite(RequestsTest.class);
		// $JUnit-END$
		return suite;
	}

}
