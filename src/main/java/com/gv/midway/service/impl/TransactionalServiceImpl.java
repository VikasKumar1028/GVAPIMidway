package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.ITransactionalDao;
import com.gv.midway.service.ITransactionalService;

@Service
public class TransactionalServiceImpl implements ITransactionalService {
	@Autowired
	private ITransactionalDao transactionalDao;

	// santosh:new method
	public void populateActivateDBPayload(Exchange exchange) {

		transactionalDao.populateActivateDBPayload(exchange);
	}

	// santosh:new method
	public void populateDeactivateDBPayload(Exchange exchange) {

		transactionalDao.populateDeactivateDBPayload(exchange);
	}

	public void populateSuspendDBPayload(Exchange exchange) {

		transactionalDao.populateSuspendDBPayload(exchange);
	}

	public void populateVerizonTransactionalResponse(Exchange exchange) {
		transactionalDao.populateVerizonTransactionalResponse(exchange);

	}

	public void populateVerizonTransactionalErrorResponse(Exchange exchange) {
		transactionalDao.populateVerizonTransactionalErrorResponse(exchange);

	}

	public void populateKoreTransactionalErrorResponse(Exchange exchange) {
		transactionalDao.populateKoreTransactionalErrorResponse(exchange);

	}

	public void populateKoreTransactionalResponse(Exchange exchange) {
		transactionalDao.populateKoreTransactionalResponse(exchange);

	}

	public void populatePendingKoreCheckStatus(Exchange exchange) {
		// TODO Auto-generated method stub
		transactionalDao.populatePendingKoreCheckStatus(exchange);
	}

	public void populateConnectionErrorResponse(Exchange exchange,
			String errorType) {
		transactionalDao.populateConnectionErrorResponse(exchange, errorType);
	}

	public void populateCallbackDBPayload(Exchange exchange) {
		transactionalDao.populateCallbackDBPayload(exchange);

	}

	public void findMidwayTransactionId(Exchange exchange) {
		transactionalDao.findMidwayTransactionId(exchange);

	}

	public void populateReactivateDBPayload(Exchange exchange) {
		// TODO Auto-generated method stub
		transactionalDao.populateReactivateDBPayload(exchange);

	}

	public void populateRestoreDBPayload(Exchange exchange) {
		transactionalDao.populateRestoreDBPayload(exchange);
	}

	public void populateCustomeFieldsDBPayload(Exchange exchange) {
		transactionalDao.populateCustomeFieldsDBPayload(exchange);
	}

	public void populateChangeDeviceServicePlansDBPayload(Exchange exchange) {
		transactionalDao.populateChangeDeviceServicePlansDBPayload(exchange);
	}

	@Override
	public void populateKoreCheckStatusResponse(Exchange exchange) {
		// TODO Auto-generated method stub
		transactionalDao.populateKoreCheckStatusResponse(exchange);
	}
	
	@Override
	public void populateKoreCheckStatusConnectionResponse(Exchange exchange){
		transactionalDao.populateKoreCheckStatusConnectionResponse(exchange);
	}
	
	@Override
	public void populateKoreCheckStatusErrorResponse(Exchange exchange){
		transactionalDao.populateKoreCheckStatusErrorResponse(exchange);
	}
	
	
	@Override
	public void updateNetSuiteCallBack(Exchange exchange) {
		// TODO Auto-generated method stub
		transactionalDao.updateNetSuiteCallBack(exchange);
	}
	
	@Override
	public void updateNetSuiteCallBackError(Exchange exchange){
		transactionalDao.updateNetSuiteCallBackError(exchange);
	}
	
	@Override
	public void populateKoreCustomChangeResponse(Exchange exchange){
		transactionalDao.populateKoreCustomChangeResponse(exchange);
	}

	@Override
	public void populateRetrieveDeviceUsageHistoryDBPayload(Exchange exchange) {
		transactionalDao.populateRetrieveDeviceUsageHistoryDBPayload(exchange);
		
	}

}
