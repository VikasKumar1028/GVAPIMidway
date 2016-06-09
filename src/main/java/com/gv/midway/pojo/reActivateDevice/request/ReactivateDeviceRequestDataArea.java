package com.gv.midway.pojo.reActivateDevice.request;

import java.util.Arrays;

import com.gv.midway.pojo.MidWayDevices;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ReactivateDeviceRequestDataArea {
	@ApiModelProperty(value = "All identifiers for the device.")
	private MidWayDevices[] devices;

	/**
	 * @return the devices
	 */
	public MidWayDevices[] getDevices() {
		return devices;
	}

	/**
	 * @param devices the devices to set
	 */
	public void setDevices(MidWayDevices[] devices) {
		this.devices = devices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(devices);
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
		ReactivateDeviceRequestDataArea other = (ReactivateDeviceRequestDataArea) obj;
		if (!Arrays.equals(devices, other.devices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReactivateDeviceRequestDataArea [devices=");
		builder.append(Arrays.toString(devices));
		builder.append("]");
		return builder.toString();
	}

	

	
}
