package com.gv.midway.pojo.customFieldsUpdateDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CustomFieldsUpdateDevices {

	@ApiModelProperty(value = "Having type and value of device identifier")
	private CustomFieldsUpdateDeviceId[] deviceIds;

	public CustomFieldsUpdateDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(CustomFieldsUpdateDeviceId[] deviceIds) {
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
		CustomFieldsUpdateDevices other = (CustomFieldsUpdateDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomFieldsUpdateDevices [deviceIds="
				+ Arrays.toString(deviceIds) + "]";
	}
	
	

}
