package com.gv.midway.service.callbacks;

import org.apache.camel.Exchange;

public interface GVCallbacksService {
	
	public void callbackRequestCall(Exchange exchange);

	public void callbackResponseCall(Exchange exchange);

	public void callbackExceptionResponseCall(Exchange exchange);
}
