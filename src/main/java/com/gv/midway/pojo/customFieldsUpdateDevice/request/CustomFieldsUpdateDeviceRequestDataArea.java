package com.gv.midway.pojo.customFieldsUpdateDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFieldsUpdateDeviceRequestDataArea {
	@ApiModelProperty(value = "All identifiers for the device.")
	private CustomFieldsUpdateDevices[] devices;

	@ApiModelProperty(value = "Name of the billing account.")
	private String accountName;

	public CustomFieldsUpdateDeviceRequestDataArea()
	{
		
	}
	public CustomFieldsUpdateDeviceRequestDataArea(
			CustomFieldsUpdateDevices[] devices, String accountName,
			String groupName, String servicePlan, CustomFields[] customFields,
			CustomFieldsToUpdate[] customFieldsToUpdate) {
		super();
		this.devices = devices;
		this.accountName = accountName;
		this.groupName = groupName;
		this.servicePlan = servicePlan;
		this.customFields = customFields;
		this.customFieldsToUpdate = customFieldsToUpdate;
	}

	@ApiModelProperty(value = "The device group that the requested device belongs to.")
	private String groupName;

	@ApiModelProperty(value = "Service Plan that that device belongs to.", required = true)
	private String servicePlan;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "Update custom fields and values that have been set for the device.")
	private CustomFieldsToUpdate[] customFieldsToUpdate;

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
		CustomFieldsUpdateDeviceRequestDataArea other = (CustomFieldsUpdateDeviceRequestDataArea) obj;
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

	public CustomFieldsUpdateDevices[] getDevices() {
		return devices;
	}

	public void setDevices(CustomFieldsUpdateDevices[] devices) {
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

	public CustomFieldsToUpdate[] getCustomFieldsToUpdate() {
		return customFieldsToUpdate;
	}

	public void setCustomFieldsToUpdate(
			CustomFieldsToUpdate[] customFieldsToUpdate) {
		this.customFieldsToUpdate = customFieldsToUpdate;
	}
	@Override
	public String toString() {
		return "CustomFieldsUpdateDeviceRequestDataArea [devices="
				+ Arrays.toString(devices) + ", accountName=" + accountName
				+ ", groupName=" + groupName + ", servicePlan=" + servicePlan
				+ ", customFields=" + Arrays.toString(customFields)
				+ ", customFieldsToUpdate="
				+ Arrays.toString(customFieldsToUpdate) + "]";
	}

}
