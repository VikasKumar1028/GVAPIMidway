package com.gv.midway.pojo.activateDevice.request;

import java.util.Arrays;

public class ActivateDevices {
	
	private ActivateDeviceId[] deviceIds;

	public ActivateDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(ActivateDeviceId[] deviceIds) {
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
		ActivateDevices other = (ActivateDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivateDevices [deviceIds=" + Arrays.toString(deviceIds) + "]";
	}

	
}
