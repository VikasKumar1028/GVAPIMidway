package com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SessionBeginEndResponseDataArea {

	@ApiModelProperty(value = "Array of begin and end information of a device session" )
	private DeviceSession[] deviceSession;


	public DeviceSession[] getDeviceSession() {
		return deviceSession;
	}

	public void setDeviceSession(DeviceSession[] deviceSession) {
		this.deviceSession = deviceSession;
	}
	
	@Override
	public String toString() {
		return "SessionBeginEndResponseDataArea [deviceSession="
				+ Arrays.toString(deviceSession) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(deviceSession);
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
		SessionBeginEndResponseDataArea other = (SessionBeginEndResponseDataArea) obj;
		if (!Arrays.equals(deviceSession, other.deviceSession))
			return false;
		return true;
	}

}
