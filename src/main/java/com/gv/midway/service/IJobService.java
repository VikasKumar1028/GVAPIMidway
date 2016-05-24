package com.gv.midway.service;

import java.util.List;

import org.apache.camel.Exchange;

public interface IJobService {

	public List fetchDevices(Exchange exchange);

	public void insertJobDetails(Exchange exchange);

	public void updateJobDetails(Exchange exchange);

}
	

	

