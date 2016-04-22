package com.gv.midway.pojo.suspendDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspendDeviceRequestDataArea {

	@ApiModelProperty(value = "All identifiers for the device.")
	private SuspendDevices[] devices;
	
	@ApiModelProperty(value = "Name of the billing account.")
	private String accountName;
	
	@ApiModelProperty(value = "The device group that the requested device belongs to.")
	private String groupName;
	
	@ApiModelProperty(value = "Service Plan that that device belongs to.", required=true)
	private String servicePlan;
	
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;
	
/*	@ApiModelProperty(value = "The number of Device that has to be activated.")
	private String deviceNumber;*/


	public SuspendDevices[] getDevices() {
		return devices;
	}

	public void setDevices(SuspendDevices[] devices) {
		this.devices = devices;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

/*	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		/*result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());*/
		result = prime * result + Arrays.hashCode(devices);
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
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
		SuspendDeviceRequestDataArea other = (SuspendDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		/*if (deviceNumber == null) {
			if (other.deviceNumber != null)
				return false;
		} else if (!deviceNumber.equals(other.deviceNumber))
			return false;*/
		if (!Arrays.equals(devices, other.devices))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SuspendDeviceRequestDataArea [devices="
				+ Arrays.toString(devices) + ", accountName=" + accountName
				+ ", groupName=" + groupName + ", servicePlan=" + servicePlan
				+ ", customFields=" + Arrays.toString(customFields)
				/*+ ", deviceNumber=" + deviceNumber */+ "]";
	}
	
	
}
