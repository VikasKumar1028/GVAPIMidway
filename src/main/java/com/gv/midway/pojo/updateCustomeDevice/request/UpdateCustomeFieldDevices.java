package com.gv.midway.pojo.updateCustomeDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateCustomeFieldDevices {

	@ApiModelProperty(value = "Having type and value of device identifier")
	private UpdateCustomeFieldDeviceId[] deviceIds;

	public UpdateCustomeFieldDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(UpdateCustomeFieldDeviceId[] deviceIds) {
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
		UpdateCustomeFieldDevices other = (UpdateCustomeFieldDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateCustomeFieldDevices [deviceIds="
				+ Arrays.toString(deviceIds) + "]";
	}
	
	

}
