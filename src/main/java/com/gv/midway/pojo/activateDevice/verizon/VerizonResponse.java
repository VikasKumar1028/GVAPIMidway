package com.gv.midway.pojo.activateDevice.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VerizonResponse {

//	private String requestId;

	private Devices[] devices;

	
	public Devices[] getDevices() {
		return devices;
	}

	public void setDevices(Devices[] devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "ClassPojo [ devices = "
				+ devices + "]";
	}
}
