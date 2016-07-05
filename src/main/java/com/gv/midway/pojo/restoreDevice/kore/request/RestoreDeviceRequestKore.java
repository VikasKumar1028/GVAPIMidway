package com.gv.midway.pojo.restoreDevice.kore.request;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestoreDeviceRequestKore{
	
	@ApiModelProperty(value = "Device number to be restored.", required = true)
	private String deviceNumber;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Override
	public String toString() {
		return "RestoreDeviceRequestKore [deviceNumber=" + deviceNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
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
		RestoreDeviceRequestKore other = (RestoreDeviceRequestKore) obj;
		if (deviceNumber == null) {
			if (other.deviceNumber != null)
				return false;
		} else if (!deviceNumber.equals(other.deviceNumber))
			return false;
		return true;
	}
	
	
}