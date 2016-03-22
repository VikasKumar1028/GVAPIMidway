package com.gv.midway.pojo.activateDevice.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {

	private String accountName;
	
	private DeviceIds[] deviceIds;

	private String requestId;

	
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public DeviceIds[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceIds[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	
	@Override
	public String toString() {
		return "ClassPojo [accountName = " + accountName
				+ ", requestId = " + requestId 
				+ ", deviceIds = " + deviceIds + "]";
	}

}
