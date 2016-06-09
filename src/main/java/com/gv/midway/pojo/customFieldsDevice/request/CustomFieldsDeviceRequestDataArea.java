package com.gv.midway.pojo.customFieldsDevice.request;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFieldsDeviceRequestDataArea {
	
	@ApiModelProperty(value = "All identifiers for the device.")
	private MidWayDevices[] devices;

	@ApiModelProperty(value = "Name of the billing account.")
	private String accountName;

	public CustomFieldsDeviceRequestDataArea() {

	}

	@ApiModelProperty(value = "The device group that the requested device belongs to.")
	private String groupName;

	@ApiModelProperty(value = "Service Plan that that device belongs to.", required = true)
	private String servicePlan;

	@ApiModelProperty(value = "Custom field names and values, if you want to only include devices that have matching values.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "The names and new values of any custom fields that you want to change.")
	private CustomFieldsToUpdate[] customFieldsToUpdate;


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

	public CustomFieldsToUpdate[] getCustomFieldsToUpdate() {
		return customFieldsToUpdate;
	}

	public void setCustomFieldsToUpdate(
			CustomFieldsToUpdate[] customFieldsToUpdate) {
		this.customFieldsToUpdate = customFieldsToUpdate;
	}

	public MidWayDevices[] getDevices() {
		return devices;
	}

	public void setDevices(MidWayDevices[] devices) {
		this.devices = devices;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(customFieldsToUpdate);
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
		CustomFieldsDeviceRequestDataArea other = (CustomFieldsDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(customFieldsToUpdate, other.customFieldsToUpdate))
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
		builder.append("CustomFieldsDeviceRequestDataArea [devices=");
		builder.append(Arrays.toString(devices));
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", servicePlan=");
		builder.append(servicePlan);
		builder.append(", customFields=");
		builder.append(Arrays.toString(customFields));
		builder.append(", customFieldsToUpdate=");
		builder.append(Arrays.toString(customFieldsToUpdate));
		builder.append("]");
		return builder.toString();
	}

}
