package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface IDeviceService {
	
	public String insertDeviceDetails(Exchange exchange);

	public String updateDeviceDetails(Exchange exchange);

	public String insertDevicesDetailsInBatch(Exchange exchange);
	
	public String updateDevicesDetailsInBatch(Exchange exchange);


}
