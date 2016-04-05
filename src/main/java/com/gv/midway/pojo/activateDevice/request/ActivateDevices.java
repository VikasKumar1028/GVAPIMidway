package com.gv.midway.pojo.activateDevice.request;

import java.util.Arrays;

public class ActivateDevices {
	
	private ActivateDeviceId[] activateDeviceIds;

	public ActivateDeviceId[] getActivateDeviceIds() {
		return activateDeviceIds;
	}

	public void setActivateDeviceIds(ActivateDeviceId[] activateDeviceIds) {
		this.activateDeviceIds = activateDeviceIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateDeviceIds [activateDeviceIds=");
		builder.append(Arrays.toString(activateDeviceIds));
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(activateDeviceIds);
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
		if (!Arrays.equals(activateDeviceIds, other.activateDeviceIds))
			return false;
		return true;
	}
	
 
}
