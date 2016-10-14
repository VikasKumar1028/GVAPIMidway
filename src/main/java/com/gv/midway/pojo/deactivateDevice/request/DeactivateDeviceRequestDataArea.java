package com.gv.midway.pojo.deactivateDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestDataArea {
	@ApiModelProperty(value = "VERIZON: The device group that the requested device belongs to. This parameter can serve either of two purposes:If you specify devices by ID in the devices paramters, this is the name of a device group that the devices should be added to. They will be in the default device group if you don't specify one.If you don't specify individual devices with the devices parameter,you can provide the name of a device group to activate all devices in that group")
	private String groupName;

	@ApiModelProperty(value = "Name of the billing account.This parameter is only required if the UWS account used for the current API session has access to multiple billing accounts")
	private String accountName;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "Service Plan that that device belongs to.Verizon Wireless provides service plan codes at the time of on-boarding and subsequently whenever there are any changes to the service plan. NOTE:  Any devices in the request that are not supported by the service plan will not activate. For example, if the service plan is only for 4G devices, any 3G devices included in the activation request will fail.", required = true)
	private String servicePlan;

	@ApiModelProperty(value = "VERIZON : Code identifying the reason for the deactivation.Currently the only valid reason code is FF, which corresponds to General Admin/Maintenance.", required = true)
	private String reasonCode;

	@ApiModelProperty(value = "All identifiers for the device.", required = true)
	private DeactivateDevices[] devices;

	public DeactivateDevices[] getDevices() {
		return devices;
	}

	public void setDevices(DeactivateDevices[] devices) {
		this.devices = devices;
	}

	@ApiModelProperty(value = "VERIZON : The etfWaiver parameter waives the Early Termination Fee (ETF), if applicable. Fees may be assessed for deactivating Verizon Wireless devices, depending on the account contract. The etfWaiver parameter waives the Early Termination Fee (ETF), if applicable. When etfWaiver is set to true, the fee will be waived, assuming that waivers are allowed in the contract and they have not all been used. When etfWaiver is set to false, the fee will not be waived via this mechanism. If you use a business process where you deactivate devices and apply ETF waivers through some other mechanism, you may continue to follow that process,and simply set etfWaiver to false.If your account does not have ETF waivers at all, set etfWaiver to False. The Deactivate request will fail if you set etfWaiter to true and there are no waivers available based on the contract.")
	private String etfWaiver;

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

	public String getEtfWaiver() {
		return etfWaiver;
	}

	public void setEtfWaiver(String etfWaiver) {
		this.etfWaiver = etfWaiver;
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
				+ ((etfWaiver == null) ? 0 : etfWaiver.hashCode());
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
		DeactivateDeviceRequestDataArea other = (DeactivateDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(devices, other.devices))
			return false;
		if (etfWaiver == null) {
			if (other.etfWaiver != null)
				return false;
		} else if (!etfWaiver.equals(other.etfWaiver))
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
		return "DeactivateDeviceRequestDataArea [groupName=" + groupName
				+ ", accountName=" + accountName + ", customFields="
				+ Arrays.toString(customFields) + ", servicePlan="
				+ servicePlan + ", reasonCode=" + reasonCode + ", devices="
				+ Arrays.toString(devices)
				+ ", etfWaiver=" + etfWaiver + "]";
	}

}
