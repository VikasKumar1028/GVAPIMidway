package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface ITransactionalDao {
	
	public void populateDBPayload(Exchange exchange);
	public void callbackSaveDB(Exchange exchange);
}
