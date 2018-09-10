package org.piwigo.remotesync.api.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class PwgSessionGetStatusResponseAvailableSizes {
	@Element(required=false)
	public String item;
}
