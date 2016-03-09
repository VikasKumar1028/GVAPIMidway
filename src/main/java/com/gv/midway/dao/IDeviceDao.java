package com.gv.midway.dao;

import com.gv.midway.device.request.pojo.Device;
import com.gv.midway.device.request.pojo.Devices;


	

public interface IDeviceDao {
	public Object insertDeviceDetails(Device device);
	
	public Object updateDeviceDetails(String deviceId,Device device);
	
	public Object getDeviceDetails(String deviceId);
	
	public Object getDeviceDetailsBsId(String bsId);

	public Object insertDevicesDetailsInBatch(Devices devices);

	/*public String updateDevicesDetailsInBatch(Devices devices); */

	


}
