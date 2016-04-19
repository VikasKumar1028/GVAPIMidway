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
	//santosh:new method
	public void populateActivateDBPayload(Exchange exchange) {

		transactionalDao.populateActivateDBPayload(exchange);
	}
	//santosh:new method
	public void populateDeactivateDBPayload(Exchange exchange) {

		transactionalDao.populateDeactivateDBPayload(exchange);
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
	
	public void populateConnectionErrorResponse(Exchange exchange,String errorType){
		transactionalDao.populateConnectionErrorResponse(exchange,errorType);
	}
	public void populateCallbackDBPayload(Exchange exchange) {
		transactionalDao.populateCallbackDBPayload(exchange);
		
	}
	public void findMidwayTransactionId(Exchange exchange) {
		 transactionalDao.findMidwayTransactionId(exchange);
		
	}

}
