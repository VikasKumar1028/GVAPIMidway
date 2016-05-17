package com.gv.midway.dao;

import org.apache.camel.Exchange;

public interface ISchedulerDao {

	
	public void saveDeviceConnectionHistory(Exchange exchange);
	
	public void saveDeviceUsageHistory(Exchange exchange);
}
