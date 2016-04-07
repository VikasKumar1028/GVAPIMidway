package com.gv.midway.pojo.device.request;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
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
