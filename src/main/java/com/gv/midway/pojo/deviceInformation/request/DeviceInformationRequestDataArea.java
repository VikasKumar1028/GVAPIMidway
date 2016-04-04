package com.gv.midway.pojo.deviceInformation.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.deviceInformation.verizon.CustomFields;
import com.gv.midway.pojo.deviceInformation.verizon.DeviceIds;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationRequestDataArea {
	@ApiModelProperty(value = "The device group that the device belongs to.")
	private String groupName;
	
	@ApiModelProperty(value = "The billing account that the device is associated with.")	
	private String accountName;

	@ApiModelProperty(value = "The earliest date and time for which you want connection events.")	
	private String earliest;

	@ApiModelProperty(value = "Custom field names and values, if you want to only include devices that have matching values.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "The name of a device state, to only include devices in that state.")
	private String currentState;

	@ApiModelProperty(value = "The service plan that the device is assigned to.")
	private String servicePlan;

	@ApiModelProperty(value = "The last date and time for which you want connection events.")
	private String latest;

	@ApiModelProperty(value = "The device number of the device to query.")
	private String deviceNumber;

	@ApiModelProperty(value = "All identifiers for the device.")
	private DeviceIds[] deviceId;
	
	@ApiModelProperty(value = "An identifier from NetSuite system")
	private String netSuiteId;
	@ApiModelProperty(value = "An unique identifier (Primary key) for device in Midway")
	private String midwayMasterDeviceId;

	public String getGroupName() {
		return groupName;
	}

	public String getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(String netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public String getMidwayMasterDeviceId() {
		return midwayMasterDeviceId;
	}

	public void setMidwayMasterDeviceId(String midwayMasterDeviceId) {
		this.midwayMasterDeviceId = midwayMasterDeviceId;
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

	public DeviceIds[] getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceIds[] deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "DeviceInformationRequest [groupName = " + groupName
				+ ", accountName = " + accountName + ", earliest = " + earliest
				+ ", customFields = " + customFields + ", currentState = "
				+ currentState + ", servicePlan = " + servicePlan
				+ ", netSuiteId=" + netSuiteId
				+ ", midwayMasterDeviceId=" + midwayMasterDeviceId
				+ ", latest = " + latest + ", deviceNumber = " + deviceNumber
				+ ", deviceId = " + deviceId + "]";
	}
}
