package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ITransactionalService {

	public void populateActivateDBPayload(Exchange exchange);
	public void populateDeactivateDBPayload(Exchange exchange);
	public void populateVerizonTransactionalResponse(Exchange exchange);
	public void populateVerizonTransactionalErrorResponse(Exchange exchange);
	public void populateKoreTransactionalErrorResponse(Exchange exchange);
	public void populateKoreTransactionalSuccessResponse(Exchange exchange);
	public void callbackSaveDB(Exchange exchange);
	
}
