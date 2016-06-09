package com.gv.midway.pojo.changeDeviceServicePlans.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.verizon.CustomFields;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeDeviceServicePlansRequestDataArea {

	@ApiModelProperty(value = "Name of the billing account.")
	private String accountName;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "All identifiers for the device.")
	private MidWayDevices[] devices;

	@ApiModelProperty(value = "New Service Plan.", required = true)
	private String servicePlan;

	@ApiModelProperty(value = "The device group that the requested device belongs to.")
	private String groupName;

	@ApiModelProperty(value = "The device group that the requested device belongs to.")
	private String currentServicePlan;


	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public MidWayDevices[] getDevices() {
		return devices;
	}

	public void setDevices(MidWayDevices[] devices) {
		this.devices = devices;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCurrentServicePlan() {
		return currentServicePlan;
	}

	public void setCurrentServicePlan(String currentServicePlan) {
		this.currentServicePlan = currentServicePlan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime
				* result
				+ ((currentServicePlan == null) ? 0 : currentServicePlan
						.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
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
		ChangeDeviceServicePlansRequestDataArea other = (ChangeDeviceServicePlansRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (currentServicePlan == null) {
			if (other.currentServicePlan != null)
				return false;
		} else if (!currentServicePlan.equals(other.currentServicePlan))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeDeviceServicePlansRequestDataArea [accountName=");
		builder.append(accountName);
		builder.append(", customFields=");
		builder.append(Arrays.toString(customFields));
		builder.append(", devices=");
		builder.append(Arrays.toString(devices));
		builder.append(", servicePlan=");
		builder.append(servicePlan);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", currentServicePlan=");
		builder.append(currentServicePlan);
		builder.append("]");
		return builder.toString();
	}

	


	
}
