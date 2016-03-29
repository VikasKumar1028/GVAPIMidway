package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface GVCallbackTransactionalDao {
	public void populateCallbackDBPayload(Exchange exchange);
}
