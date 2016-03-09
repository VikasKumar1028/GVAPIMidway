package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface IAuditDao {
	public void auditExternalRequestCall(Exchange exchange);

	public void auditExternalResponseCall(Exchange exchange);

}
