package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IAuditDao;
import com.gv.midway.service.IAuditService;

@Service
public class AuditServiceImpl implements IAuditService {

	@Autowired
	private IAuditDao iAuditDao;
	Logger log = Logger.getLogger(AuditServiceImpl.class.getName());
	@Override
	public void auditExternalRequestCall(Exchange exchange) {

		log.info("AuditServiceImpl-auditExternalRequestCall");

		iAuditDao.auditExternalRequestCall(exchange);

	}
	@Override
	public void auditExternalResponseCall(Exchange exchange) {

		log.info("AuditServiceImpl-auditExternalResponseCall");
		iAuditDao.auditExternalResponseCall(exchange);
	}

	@Override
	public void auditExternalExceptionResponseCall(Exchange exchange){
		
		log.info("AuditServiceImpl-auditExternalExceptionResponseCall");
		iAuditDao.auditExternalExceptionResponseCall(exchange);
	}
	@Override
	public void auditExternalConnectionExceptionResponseCall(Exchange exchange){
		log.info("AuditServiceImpl-auditExternalConnectionExceptionResponseCall");
		iAuditDao.auditExternalConnectionExceptionResponseCall(exchange);
	}
	
}
