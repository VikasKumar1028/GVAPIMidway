package com.gv.midway.pojo.deactivateDevice.verizon.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.Devices;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestVerizon {

	@ApiModelProperty(value = "The device group that the device belongs to.")
	private String groupName;

	@ApiModelProperty(value = "The billing account for which a list of devices will be returned.")
	private String accountName;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "Service Plan that that device belongs to.")
	private String servicePlan;

	@ApiModelProperty(value = "Code identifying the reason for the deactivation.")
	private String reasonCode;

	@ApiModelProperty(value = "All identifiers for the device.")
	private Devices[] devices;

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

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Devices[] getDevices() {
		return devices;
	}

	public void setDevices(Devices[] devices) {
		this.devices = devices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(devices);
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((reasonCode == null) ? 0 : reasonCode.hashCode());
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
		DeactivateDeviceRequestVerizon other = (DeactivateDeviceRequestVerizon) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
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
		if (reasonCode == null) {
			if (other.reasonCode != null)
				return false;
		} else if (!reasonCode.equals(other.reasonCode))
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
		builder.append("DeactivateDeviceRequestVerizon [groupName=");
		builder.append(groupName);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", customFields=");
		builder.append(Arrays.toString(customFields));
		builder.append(", servicePlan=");
		builder.append(servicePlan);
		builder.append(", reasonCode=");
		builder.append(reasonCode);
		builder.append(", devices=");
		builder.append(Arrays.toString(devices));
		builder.append("]");
		return builder.toString();
	}
	

	

}
