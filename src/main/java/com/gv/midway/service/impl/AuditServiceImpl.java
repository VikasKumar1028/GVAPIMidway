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
    private static final Logger LOGGER = Logger.getLogger(AuditServiceImpl.class.getName());

    @Override
    public void auditExternalRequestCall(Exchange exchange) {

        LOGGER.info("AuditServiceImpl-auditExternalRequestCall");

        iAuditDao.auditExternalRequestCall(exchange);

    }

    @Override
    public void auditExternalResponseCall(Exchange exchange) {

        LOGGER.info("AuditServiceImpl-auditExternalResponseCall");
        iAuditDao.auditExternalResponseCall(exchange);
    }
    
    @Override
    public void auditExternalSOAPResponseCall(Exchange exchange) {

        LOGGER.info("AuditServiceImpl-auditExternalSOAPResponseCall");
        
        iAuditDao.auditExternalSOAPResponseCall(exchange);
    }

    @Override
    public void auditExternalExceptionResponseCall(Exchange exchange) {

        LOGGER.info("AuditServiceImpl-auditExternalExceptionResponseCall");
        iAuditDao.auditExternalExceptionResponseCall(exchange);
    }
    
    @Override
    public void auditExternalSOAPExceptionResponseCall(Exchange exchange) {

        LOGGER.info("AuditServiceImpl-auditExternalSOAPExceptionResponseCall");
        iAuditDao.auditExternalSOAPExceptionResponseCall(exchange);
    }

    @Override
    public void auditExternalConnectionExceptionResponseCall(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalConnectionExceptionResponseCall");
        iAuditDao.auditExternalConnectionExceptionResponseCall(exchange);
    }

}
