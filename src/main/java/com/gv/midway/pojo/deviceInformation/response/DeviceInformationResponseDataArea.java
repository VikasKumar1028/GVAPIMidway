package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationResponseDataArea {

	private DeviceInformation[] devices;

	public DeviceInformation[] getDevices() {
		return devices;
	}

	public void setDevices(DeviceInformation[] devices) {
		this.devices = devices;
	}
}
