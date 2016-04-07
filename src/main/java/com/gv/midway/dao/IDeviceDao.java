package com.gv.midway.dao;

import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.InsertDeviceResponse;


	

public interface IDeviceDao {
	
	public InsertDeviceResponse insertDeviceDetails(SingleDevice device);
	
	public Object updateDeviceDetails(SingleDevice device);
	
	public Object getDeviceDetails(String deviceId);
	
	public Object getDeviceDetailsBsId(String bsId);

	public Object insertDevicesDetailsInBatch(BulkDevices devices);

	/*public String updateDevicesDetailsInBatch(Devices devices); */

	


}
