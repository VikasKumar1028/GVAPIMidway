package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ITransactionalService {

	public void populateDBPayload(Exchange exchange);
	
	public void populateVerizonTransactionalResponse(Exchange exchange);
	
	public void callbackSaveDB(Exchange exchange);
	
}
