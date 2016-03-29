package com.gv.midway.service.callbacks;

import org.apache.camel.Exchange;

public interface GVCallbackTransactionalService {
	public void populateCallbackDBPayload(Exchange exchange);
}
