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

    @SuppressWarnings("unused")  //Needed for framework
    public AuditServiceImpl() {

    }

    //Added this constructor so that this class could be tested.
    public AuditServiceImpl(IAuditDao iAuditDao) {
        this.iAuditDao = iAuditDao;
    }

    @Override
    public void auditExternalRequestCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalRequestCall");
        iAuditDao.auditExternalRequestCall(exchange);
    }

    @Override
    public void auditExternalResponseCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalResponseCall");
        iAuditDao.auditExternalResponseCall(exchange);
    }

    @Override
    public void auditExternalSOAPResponseCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalSOAPResponseCall");
        iAuditDao.auditExternalSOAPResponseCall(exchange);
    }

    @Override
    public void auditExternalExceptionResponseCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalExceptionResponseCall");
        iAuditDao.auditExternalExceptionResponseCall(exchange);
    }

    @Override
    public void auditExternalSOAPExceptionResponseCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalSOAPExceptionResponseCall");
        iAuditDao.auditExternalSOAPExceptionResponseCall(exchange);
    }

    @Override
    public void auditExternalConnectionExceptionResponseCall(Exchange exchange) {
        LOGGER.debug("AuditServiceImpl-auditExternalConnectionExceptionResponseCall");
        iAuditDao.auditExternalConnectionExceptionResponseCall(exchange);
    }

    @Override
    public void auditExternalRequestCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalRequestCallMock");
    }

    @Override
    public void auditExternalResponseCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalResponseCallMock");
    }

    @Override
    public void auditExternalSOAPResponseCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalSOAPResponseCallMock");
    }

    @Override
    public void auditExternalExceptionResponseCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalExceptionResponseCallMock");
    }

    @Override
    public void auditExternalSOAPExceptionResponseCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalSOAPExceptionResponseCallMock");
    }

    @Override
    public void auditExternalConnectionExceptionResponseCallMock(Exchange exchange) {
        LOGGER.info("AuditServiceImpl-auditExternalConnectionExceptionResponseCallMock");
    }
}