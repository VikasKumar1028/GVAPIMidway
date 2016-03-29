package com.gv.midway.service.callbacks.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.GVCallbacksDao;
import com.gv.midway.service.callbacks.GVCallbacksService;
@Service
public class GVCallbacksImpl implements GVCallbacksService{
	@Autowired
	private GVCallbacksDao gvCallbackDao;
	Logger log = Logger.getLogger(GVCallbacksImpl.class.getName());
	public void callbackRequestCall(Exchange exchange) {
		log.info("GVCallbacksImpl-callbackRequestCall");

		gvCallbackDao.callbackRequestCall(exchange);
	}

	public void callbackResponseCall(Exchange exchange) {
		log.info("GVCallbacksImpl-callbackResponseCall");
		gvCallbackDao.callbackResponseCall(exchange);
	}

	public void callbackExceptionResponseCall(Exchange exchange) {
		log.info("GVCallbacksImpl-callbackExceptionResponseCall");
		gvCallbackDao.callbackExceptionResponseCall(exchange);
	}

}
