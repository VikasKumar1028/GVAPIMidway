package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface IAuditService {

    void auditExternalRequestCall(Exchange exchange);

    void auditExternalResponseCall(Exchange exchange);
    
    void auditExternalSOAPResponseCall(Exchange exchange);

    void auditExternalExceptionResponseCall(Exchange exchange);
    
    void auditExternalSOAPExceptionResponseCall(Exchange exchange);

    void auditExternalConnectionExceptionResponseCall(Exchange exchange);

    void auditExternalRequestCallMock(Exchange exchange);

    void auditExternalResponseCallMock(Exchange exchange);

    void auditExternalSOAPResponseCallMock(Exchange exchange);

    void auditExternalExceptionResponseCallMock(Exchange exchange);

    void auditExternalSOAPExceptionResponseCallMock(Exchange exchange);

    void auditExternalConnectionExceptionResponseCallMock(Exchange exchange);

}
