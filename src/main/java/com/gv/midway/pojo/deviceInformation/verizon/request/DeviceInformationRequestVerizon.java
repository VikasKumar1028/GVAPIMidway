package com.gv.midway.pojo.deviceInformation.verizon.request;

import com.gv.midway.pojo.verizon.DeviceId;

public class DeviceInformationRequestVerizon {

	private DeviceId deviceId;

	public DeviceId getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
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
		DeviceInformationRequestVerizon other = (DeviceInformationRequestVerizon) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceInformationRequestVerizon [deviceId=");
		builder.append(deviceId);
		builder.append("]");
		return builder.toString();
	}
	
	
}


