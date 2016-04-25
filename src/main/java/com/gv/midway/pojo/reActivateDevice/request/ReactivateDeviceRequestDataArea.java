package com.gv.midway.pojo.reActivateDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ReactivateDeviceRequestDataArea {
	@ApiModelProperty(value = "All identifiers for the device.")
	private ReactivateDevices[] devices;

	/**
	 * @return the devices
	 */
	public ReactivateDevices[] getDevices() {
		return devices;
	}

	/**
	 * @param devices the devices to set
	 */
	public void setDevices(ReactivateDevices[] devices) {
		this.devices = devices;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(devices);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReactivateDeviceRequestDataArea)) {
			return false;
		}
		ReactivateDeviceRequestDataArea other = (ReactivateDeviceRequestDataArea) obj;
		if (!Arrays.equals(devices, other.devices)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReactivateDeviceRequestDataArea [devices=" + Arrays.toString(devices) + "]";
	}

	
}
