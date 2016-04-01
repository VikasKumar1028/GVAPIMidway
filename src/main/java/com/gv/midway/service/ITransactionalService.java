package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ITransactionalService {

	public void populateDBPayload(Exchange exchange);
	public void populateVerizonTransactionalResponse(Exchange exchange);
	public void populateVerizonTransactionalErrorResponse(Exchange exchange);
	public void populateKoreTransactionalResponse(Exchange exchange);
	public void populateKoreTransactionalErrorResponse(Exchange exchange);
	public void populateConnectionErrorResponse(Exchange exchange,String errorType);
	public void populatePendingKoreCheckStatus(Exchange exchange);
	
}
