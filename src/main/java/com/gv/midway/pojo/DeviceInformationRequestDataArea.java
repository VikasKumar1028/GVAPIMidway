package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.GetDeviceRequest.CustomFields;
import com.gv.midway.pojo.GetDeviceRequest.DeviceId;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationRequestDataArea {
	private String groupName;

	private String accountName;

	private String earliest;

	private CustomFields[] customFields;

	private String currentState;

	private String servicePlan;

	private String latest;

	private String deviceNumber;

	private DeviceId deviceId;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEarliest() {
		return earliest;
	}

	public void setEarliest(String earliest) {
		this.earliest = earliest;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public DeviceId getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "DeviceInformationRequest [groupName = " + groupName
				+ ", accountName = " + accountName + ", earliest = " + earliest
				+ ", customFields = " + customFields + ", currentState = "
				+ currentState + ", servicePlan = " + servicePlan
				+ ", latest = " + latest + ", deviceNumber = " + deviceNumber
				+ ", deviceId = " + deviceId + "]";
	}
}
