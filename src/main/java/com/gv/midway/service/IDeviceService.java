package com.gv.midway.service;

import org.apache.camel.Exchange;

public interface IDeviceService {
	
	public Object insertDeviceDetails(Exchange exchange);

	public Object updateDeviceDetails(Exchange exchange);
	
	public Object getDeviceDetails(Exchange exchange);
	
	public Object getDeviceDetailsBsId(Exchange exchange);

	public Object insertDevicesDetailsInBatch(Exchange exchange);
	
	/**public String updateDevicesDetailsInBatch(Exchange exchange);*/


}
