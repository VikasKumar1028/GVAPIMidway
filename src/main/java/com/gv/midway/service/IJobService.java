package com.gv.midway.service;

import java.util.List;

import org.apache.camel.Exchange;

public interface IJobService {

	public List fetchDevices(Exchange exchange);

	public List insertJobDetails(Exchange exchange);

	public List updateJobDetails(Exchange exchange);

}
	

	

