package com.gv.midway.dao;

import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;
import com.gv.midway.device.response.pojo.InsertDeviceResponse;


	

public interface IDeviceDao {
	
	public InsertDeviceResponse insertDeviceDetails(Device device);
	
	public Object updateDeviceDetails(Device device);
	
	public Object getDeviceDetails(String deviceId);
	
	public Object getDeviceDetailsBsId(String bsId);

	public Object insertDevicesDetailsInBatch(Devices devices);

	/*public String updateDevicesDetailsInBatch(Devices devices); */

	


}
