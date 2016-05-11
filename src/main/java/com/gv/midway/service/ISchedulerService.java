package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface ISchedulerService {
	
	public void retrieveDeviceConnectionHistory(Exchange exchange);
	
	public void retrieveDeviceUsageHistory(Exchange exchange);

}
