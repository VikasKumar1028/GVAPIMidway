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

	@Override
	public void populateActivateDBPayload(Exchange exchange) {

		transactionalDao.populateActivateDBPayload(exchange);
	}

	@Override
	public void populateDeactivateDBPayload(Exchange exchange) {

		transactionalDao.populateDeactivateDBPayload(exchange);
	}

	@Override
	public void populateSuspendDBPayload(Exchange exchange) {

		transactionalDao.populateSuspendDBPayload(exchange);
	}

	@Override
	public void populateVerizonTransactionalResponse(Exchange exchange) {
		transactionalDao.populateVerizonTransactionalResponse(exchange);

	}

	@Override
	public void populateVerizonTransactionalErrorResponse(Exchange exchange) {
		transactionalDao.populateVerizonTransactionalErrorResponse(exchange);

	}

	@Override
	public void populateKoreTransactionalErrorResponse(Exchange exchange) {
		transactionalDao.populateKoreTransactionalErrorResponse(exchange);

	}

	@Override
	public void populateKoreTransactionalResponse(Exchange exchange) {
		transactionalDao.populateKoreTransactionalResponse(exchange);

	}

	@Override
	public void populatePendingKoreCheckStatus(Exchange exchange) {
		transactionalDao.populatePendingKoreCheckStatus(exchange);
	}

	@Override
	public void populateConnectionErrorResponse(Exchange exchange,
			String errorType) {
		transactionalDao.populateConnectionErrorResponse(exchange, errorType);
	}

	@Override
	public void populateCallbackDBPayload(Exchange exchange) {
		transactionalDao.populateCallbackDBPayload(exchange);

	}

	@Override
	public void findMidwayTransactionId(Exchange exchange) {
		transactionalDao.findMidwayTransactionId(exchange);

	}

	@Override
	public void populateReactivateDBPayload(Exchange exchange) {
		transactionalDao.populateReactivateDBPayload(exchange);

	}

	@Override
	public void populateRestoreDBPayload(Exchange exchange) {
		transactionalDao.populateRestoreDBPayload(exchange);
	}

	@Override
	public void populateCustomeFieldsDBPayload(Exchange exchange) {
		transactionalDao.populateCustomeFieldsDBPayload(exchange);
	}

	@Override
	public void populateChangeDeviceServicePlansDBPayload(Exchange exchange) {
		transactionalDao.populateChangeDeviceServicePlansDBPayload(exchange);
	}

	@Override
	public void populateKoreCheckStatusResponse(Exchange exchange) {
		transactionalDao.populateKoreCheckStatusResponse(exchange);
	}

	@Override
	public void populateKoreCheckStatusConnectionResponse(Exchange exchange) {
		transactionalDao.populateKoreCheckStatusConnectionResponse(exchange);
	}

	@Override
	public void populateKoreCheckStatusErrorResponse(Exchange exchange) {
		transactionalDao.populateKoreCheckStatusErrorResponse(exchange);
	}

	@Override
	public void updateNetSuiteCallBackResponse(Exchange exchange) {
		transactionalDao.updateNetSuiteCallBackResponse(exchange);
	}

	@Override
	public void updateNetSuiteCallBackError(Exchange exchange) {
		transactionalDao.updateNetSuiteCallBackError(exchange);
	}

	@Override
	public void updateNetSuiteCallBackRequest(Exchange exchange) {
		transactionalDao.updateNetSuiteCallBackRequest(exchange);
	}

	@Override
	public void populateKoreCustomChangeResponse(Exchange exchange) {
		transactionalDao.populateKoreCustomChangeResponse(exchange);
	}

	@Override
	public void populateATTJasperTransactionalResponse(Exchange exchange) {
		transactionalDao.populateATTJasperTransactionalResponse(exchange);
	}

	@Override
	public void populateATTJasperTransactionalErrorResponse(Exchange exchange) {
		transactionalDao.populateATTJasperTransactionalErrorResponse(exchange);
	}
	
	@Override
	public void populateATTCustomeFieldsDBPayload(Exchange exchange) {
            transactionalDao.populateATTCustomeFieldsDBPayload(exchange);
    }

	@Override
	public void updateKoreActivationCustomeFieldsDBPayloadError(
			Exchange exchange) {
		transactionalDao.updateKoreActivationCustomeFieldsDBPayloadError(exchange);
	}

	@Override
	public void updateKoreActivationCustomeFieldsDBPayload(Exchange exchange) {
		// TODO Auto-generated method stub
		transactionalDao.updateKoreActivationCustomeFieldsDBPayload(exchange);
	}
}
