package com.gv.midway.dao;

import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;

public interface ISchedulerDao {

	
	public void saveDeviceConnectionHistory(DeviceConnection deviceConnection);
	
	public void saveDeviceUsageHistory(DeviceUsage deviceUsage);
}
