package com.gv.midway.device.request.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DevicesDataArea {
	@ApiModelProperty(value = "Information of the Device(s)")
	private DeviceInformation[] devices;

	public DeviceInformation[] getDevices() {
		return devices;
	}

	public void setDevices(DeviceInformation[] devices) {
		this.devices = devices;
	}
}

