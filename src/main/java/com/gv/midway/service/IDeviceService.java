package com.gv.midway.service;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public interface IDeviceService {

	public UpdateDeviceResponse updateDeviceDetails(Exchange exchange);

	public DeviceInformationResponse getDeviceInformationDB(Exchange exchange);

	public void updateDevicesDetailsBulk(Exchange exchange);

	public void setDeviceInformationDB(Exchange exchange);

	public void updateDeviceInformationDB(Exchange exchange);

	public void bulkOperationDeviceSyncInDB(Exchange exchange);

}
