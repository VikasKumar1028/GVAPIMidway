package com.gv.midway.pojo.restoreDevice.request;

import java.util.Arrays;

import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestoreDevices{
	
	@ApiModelProperty(value = "Having type and value of device identifier")
	private DeviceId[] deviceIds;

	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(deviceIds);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestoreDevices other = (RestoreDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestoreDevices [deviceIds=" + Arrays.toString(deviceIds) + "]";
	}

	
}