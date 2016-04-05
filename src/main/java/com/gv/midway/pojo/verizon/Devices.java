package com.gv.midway.pojo.verizon;

import java.util.Arrays;

public class Devices {
	
	private DeviceId[] deviceIds;

	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Devices [deviceIds=");
		builder.append(Arrays.toString(deviceIds));
		builder.append("]");
		return builder.toString();
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
		Devices other = (Devices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

}
