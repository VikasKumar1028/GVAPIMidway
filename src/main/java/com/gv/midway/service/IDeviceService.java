package com.gv.midway.service;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public interface IDeviceService {
	
	public InsertDeviceResponse insertDeviceDetails(Exchange exchange);

	public Object updateDeviceDetails(Exchange exchange);
	
	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange);
	
	public Object getDeviceDetailsBsId(Exchange exchange);

	public Object insertDevicesDetailsInBatch(Exchange exchange);
	
	/**public String updateDevicesDetailsInBatch(Exchange exchange);*/


}
