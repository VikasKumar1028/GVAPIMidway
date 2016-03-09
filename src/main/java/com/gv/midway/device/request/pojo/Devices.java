package com.gv.midway.device.request.pojo;

import java.util.Arrays;

public class Devices {

    private Device[] devices;
	
	public Device[] getDevices() {
		return devices;
	}

	public void setDevices(Device[] devices) {
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
		Devices other = (Devices) obj;
		if (!Arrays.equals(devices, other.devices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Devices [devices=" + Arrays.toString(devices) + "]";
	}
	
	
}
