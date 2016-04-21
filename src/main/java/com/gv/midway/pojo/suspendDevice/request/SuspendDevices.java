package com.gv.midway.pojo.suspendDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SuspendDevices {

	@ApiModelProperty(value = "Having type and value of device identifier")
	private SuspendDeviceId[] deviceIds;

	public SuspendDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(SuspendDeviceId[] deviceIds) {
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
		SuspendDevices other = (SuspendDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuspendDevices [deviceIds=" + Arrays.toString(deviceIds) + "]";
	}
	
}

