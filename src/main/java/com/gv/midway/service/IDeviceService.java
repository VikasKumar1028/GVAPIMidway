package com.gv.midway.service;

import org.apache.camel.Exchange;

import com.gv.midway.device.response.pojo.InsertDeviceResponse;

public interface IDeviceService {
	
	public InsertDeviceResponse insertDeviceDetails(Exchange exchange);

	public Object updateDeviceDetails(Exchange exchange);
	
	public Object getDeviceDetails(Exchange exchange);
	
	public Object getDeviceDetailsBsId(Exchange exchange);

	public Object insertDevicesDetailsInBatch(Exchange exchange);
	
	/**public String updateDevicesDetailsInBatch(Exchange exchange);*/


}
