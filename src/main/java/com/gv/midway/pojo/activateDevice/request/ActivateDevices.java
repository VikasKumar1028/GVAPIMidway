package com.gv.midway.pojo.activateDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ActivateDevices {
	
	@ApiModelProperty(value = "Having type and value of device identifier" , required = true)
	private ActivateDeviceId[] deviceIds;
	
	@ApiModelProperty(value = "Device NetSuite Id" , required = true)
	private String netSuiteId;

	public ActivateDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(ActivateDeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(String netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateDevices [deviceIds=");
		builder.append(Arrays.toString(deviceIds));
		builder.append(", netSuiteId=");
		builder.append(netSuiteId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
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
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		return true;
	}

	
	
}
