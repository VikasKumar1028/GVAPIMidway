package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface IAuditService {

	public void auditExternalRequestCall(Exchange exchange);

	public void auditExternalResponseCall(Exchange exchange);

}
