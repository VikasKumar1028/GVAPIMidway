package com.gv.midway.pojo.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerizonResponse {

	private String hasMoreData;

	private Devices[] devices;

	public String getHasMoreData() {
		return hasMoreData;
	}

	public void setHasMoreData(String hasMoreData) {
		this.hasMoreData = hasMoreData;
	}

	public Devices[] getDevices() {
		return devices;
	}

	public void setDevices(Devices[] devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "ClassPojo [hasMoreData = " + hasMoreData + ", devices = "
				+ devices + "]";
	}
}
