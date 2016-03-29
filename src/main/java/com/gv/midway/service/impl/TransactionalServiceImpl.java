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

	public void populateDBPayload(Exchange exchange) {

		transactionalDao.populateDBPayload(exchange);
	}

	public void callbackSaveDB(Exchange exchange) {
		
		transactionalDao.callbackSaveDB(exchange);
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

	public void populateKoreTransactionalSuccessResponse(Exchange exchange) {
		transactionalDao.populateKoreTransactionalSuccessResponse(exchange);
		
	}

}
