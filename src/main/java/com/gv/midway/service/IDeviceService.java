package com.gv.midway.service;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public interface IDeviceService {
	
	public InsertDeviceResponse insertDeviceDetails(Exchange exchange);

	public UpdateDeviceResponse updateDeviceDetails(Exchange exchange);
	
	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange);
	
	public Object getDeviceDetailsBsId(Exchange exchange);

	public void insertDevicesDetailsInBatch(Exchange exchange);
	
	public void setDeviceInformationDB(Exchange exchange);
	
	public void updateDeviceInformationDB(Exchange exchange);
	
	public void bulkOperationDeviceSyncInDB(Exchange exchange);
	
	/**public String updateDevicesDetailsInBatch(Exchange exchange);*/


}
