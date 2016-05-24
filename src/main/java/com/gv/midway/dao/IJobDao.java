package com.gv.midway.dao;

import java.util.List;

import org.apache.camel.Exchange;

public interface IJobDao {


	public List fetchDevices(Exchange exchange) ;


	public List insertJobDetails(Exchange exchange) ;
	
	public List updateJobDetails(Exchange exchange) ;
		
}
