package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ITransactionalService {
	//santosh:new method
	public void populateActivateDBPayload(Exchange exchange);
	//santosh:new method
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
	public void populateChangeDeviceServicePlansDBPayload(Exchange exchange);
	public void populateKoreCheckStatusResponse(Exchange exchange);
	public void populateKoreCheckStatusConnectionResponse(Exchange exchange);
	public void populateKoreCheckStatusErrorResponse(Exchange exchange);
	public void updateNetSuiteCallBack(Exchange exchange);
	public void updateNetSuiteCallBackError(Exchange exchange);
	public void populateKoreCustomChangeResponse(Exchange exchange);
}
