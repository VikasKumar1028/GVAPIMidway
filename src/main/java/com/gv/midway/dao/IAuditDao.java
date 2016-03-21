package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface IAuditDao {

	public void auditExternalResponseCall(Exchange exchange);

	public void auditExternalExceptionResponseCall(Exchange exchange);
	
	public void auditExternalRequestCall(Exchange exchange);
	
	

}
