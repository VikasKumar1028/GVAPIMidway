package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface ITransactionalDao {
	public void populateActivateDBPayload(Exchange exchange);
	public void populateDeactivateDBPayload(Exchange exchange);
	public void populateSuspendDBPayload(Exchange exchange);
	public void populateVerizonTransactionalResponse(Exchange exchange);
	public void populateVerizonTransactionalErrorResponse(Exchange exchange);
	public void populateKoreTransactionalResponse(Exchange exchange);
	public void populateKoreTransactionalErrorResponse(Exchange exchange);
	public void populateConnectionErrorResponse(Exchange exchange,String errorType);
	public void populatePendingKoreCheckStatus(Exchange exchange);
	public void populateCallbackDBPayload(Exchange exchange);
	public void findMidwayTransactionId(Exchange exchange);
	public void populateReactivateDBPayload(Exchange exchange);
	public void populateRestoreDBPayload(Exchange exchange);
	public void populateCustomeFieldsDBPayload(Exchange exchange);
}
