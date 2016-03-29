package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface GVCallbacksDao {
	public void callbackResponseCall(Exchange exchange);

	public void callbackExceptionResponseCall(Exchange exchange);
	
	public void callbackRequestCall(Exchange exchange);
}
