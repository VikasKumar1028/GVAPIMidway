package com.gv.midway.service.callbacks.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.GVCallbackTransactionalDao;
import com.gv.midway.service.callbacks.GVCallbackTransactionalService;
@Service
public class GVCallbackTransactionalServiceImpl implements GVCallbackTransactionalService{
	@Autowired
	GVCallbackTransactionalDao gvCallbackTransactionalDao;
	Logger log = Logger.getLogger(GVCallbacksImpl.class.getName());
	
	public void populateCallbackDBPayload(Exchange exchange) {
		log.info("GVCallbackTransactionalServiceImpl-populateCallbackDBPayload");

		gvCallbackTransactionalDao.populateCallbackDBPayload(exchange);
	}
	public void getCallbackMidwayTransactionID(Exchange exchange) {
		log.info("GVCallbackTransactionalServiceImpl-getCallbackMidwayTransactionID");

		gvCallbackTransactionalDao.getCallbackMidwayTransactionID(exchange);
	}
}
