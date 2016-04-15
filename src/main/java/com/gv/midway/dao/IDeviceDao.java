package com.gv.midway.dao;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;


	

public interface IDeviceDao {
	
	/*public InsertDeviceResponse insertDeviceDetails(SingleDevice device);*/
	
	public UpdateDeviceResponse updateDeviceDetails(SingleDevice device);
	
	public DeviceInformationResponse getDeviceInformationDB(DeviceInformationRequest deviceInformationRequest);
	
	/*public Object getDeviceDetailsBsId(String bsId);

	public Object insertDevicesDetailsInBatch(BulkDevices devices);*/
	
	public void setDeviceInformationDB(Exchange e);
	
	public void updateDeviceInformationDB(Exchange e);
	
	/*public void bulkOperationDeviceInsert(Exchange exchange);*/

	public void bulkOperationDeviceUpload(Exchange exchange);

	


}
