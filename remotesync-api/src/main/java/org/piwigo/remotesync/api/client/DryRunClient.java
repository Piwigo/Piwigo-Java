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
package org.piwigo.remotesync.api.client;

import org.piwigo.remotesync.api.exception.ClientServerException;
import org.piwigo.remotesync.api.request.AbstractRequest;
import org.piwigo.remotesync.api.response.BasicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DryRunClient extends AbstractClient {

	private static Logger logger = LoggerFactory.getLogger(DryRunClient.class);

	@Override
	public <T extends BasicResponse> T doSendRequest(AbstractRequest<T> request) throws ClientServerException {
		try {
			logger.info("DryRun call method " + request.getWSMethodName());
			Thread.sleep(200);
			T newInstance = request.getReturnType().newInstance();
			newInstance.status = "ok";
			return newInstance;
		} catch (Exception e) {
			logger.error("Unable to create dryrun response", e);
			return null;
		}
	}

	@Override
	public Client login(String username, String password) {
		logger.info("DryRun login");
		return this;
	}

	@Override
	public Client logout() {
		logger.info("DryRun logout");
		return this;
	}
}
