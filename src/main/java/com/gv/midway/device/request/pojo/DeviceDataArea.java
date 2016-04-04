package com.gv.midway.device.request.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeviceDataArea {
	@ApiModelProperty(value = "Information of the Device(s)")
	private DeviceInformation device;

	public DeviceInformation getDevice() {
		return device;
	}

	public void setDevices(DeviceInformation device) {
		this.device = device;
	}
}
