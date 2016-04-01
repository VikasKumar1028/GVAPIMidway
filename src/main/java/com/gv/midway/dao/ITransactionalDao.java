package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface ITransactionalDao {
	//santosh:declared new methods
	public void populateActivateDBPayload(Exchange exchange);
	public void populateDeactivateDBPayload(Exchange exchange);
	public void populateVerizonTransactionalResponse(Exchange exchange);
	public void populateVerizonTransactionalErrorResponse(Exchange exchange);
	public void populateKoreTransactionalResponse(Exchange exchange);
	public void populateKoreTransactionalErrorResponse(Exchange exchange);
	public void populateConnectionErrorResponse(Exchange exchange,String errorType);
	public void populatePendingKoreCheckStatus(Exchange exchange);
}
