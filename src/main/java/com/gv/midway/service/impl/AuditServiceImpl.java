package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IAuditDao;
import com.gv.midway.service.IAuditService;

@Service
public class AuditServiceImpl implements IAuditService {

	@Autowired
	private IAuditDao iAuditDao;

	public void auditExternalRequestCall(Exchange exchange) {
		iAuditDao.auditExternalRequestCall(exchange);

	}

	public void auditExternalResponseCall(Exchange exchange) {
		iAuditDao.auditExternalResponseCall(exchange);
	}

}
