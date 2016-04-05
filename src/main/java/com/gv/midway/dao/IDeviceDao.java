package com.gv.midway.dao;

import com.gv.midway.device.request.pojo.SingleDevice;
import com.gv.midway.device.request.pojo.BulkDevices;
import com.gv.midway.device.response.pojo.InsertDeviceResponse;


	

public interface IDeviceDao {
	
	public InsertDeviceResponse insertDeviceDetails(SingleDevice device);
	
	public Object updateDeviceDetails(SingleDevice device);
	
	public Object getDeviceDetails(String deviceId);
	
	public Object getDeviceDetailsBsId(String bsId);

	public Object insertDevicesDetailsInBatch(BulkDevices devices);

	/*public String updateDevicesDetailsInBatch(Devices devices); */

	


}
