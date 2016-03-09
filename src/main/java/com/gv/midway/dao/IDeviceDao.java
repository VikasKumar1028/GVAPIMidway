package com.gv.midway.dao;

import com.gv.midway.pojo.request.Device;
import com.gv.midway.pojo.request.Devices;

public interface IDeviceDao {
	public String insertDeviceDetails(Device device);

	public String insertDevicesDetailsInBatch(Devices devices);

	public String updateDevicesDetailsInBatch(Devices devices);

	public String updateDeviceDetails(Device device);

}
