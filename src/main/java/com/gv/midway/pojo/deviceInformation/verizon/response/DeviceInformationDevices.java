package com.gv.midway.pojo.deviceInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationDevices {

	private String accountName;
	
	private String billingCycleEndDate;
	
	private CarrierInformations[] carrierInformations;
	
	private Boolean connected;
	
	private String createdAt;
	
	private CustomFields[] customFields;
	
	private DeviceId[] deviceIds;

	private ExtendedAttributes[] extendedAttributes;

	private String[] groupNames;

	private String ipAddress;
	
	private String lastActivationBy;
	
	private String lastConnectionDate;

	private String lastActivationDate;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBillingCycleEndDate() {
		return billingCycleEndDate;
	}

	public void setBillingCycleEndDate(String billingCycleEndDate) {
		this.billingCycleEndDate = billingCycleEndDate;
	}

	public CarrierInformations[] getCarrierInformations() {
		return carrierInformations;
	}

	public void setCarrierInformations(CarrierInformations[] carrierInformations) {
		this.carrierInformations = carrierInformations;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public ExtendedAttributes[] getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(ExtendedAttributes[] extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

	public String[] getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String[] groupNames) {
		this.groupNames = groupNames;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastActivationBy() {
		return lastActivationBy;
	}

	public void setLastActivationBy(String lastActivationBy) {
		this.lastActivationBy = lastActivationBy;
	}

	public String getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(String lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public String getLastActivationDate() {
		return lastActivationDate;
	}

	public void setLastActivationDate(String lastActivationDate) {
		this.lastActivationDate = lastActivationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime
				* result
				+ ((billingCycleEndDate == null) ? 0 : billingCycleEndDate
						.hashCode());
		result = prime * result + Arrays.hashCode(carrierInformations);
		result = prime * result
				+ ((connected == null) ? 0 : connected.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result + Arrays.hashCode(extendedAttributes);
		result = prime * result + Arrays.hashCode(groupNames);
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime
				* result
				+ ((lastActivationBy == null) ? 0 : lastActivationBy.hashCode());
		result = prime
				* result
				+ ((lastActivationDate == null) ? 0 : lastActivationDate
						.hashCode());
		result = prime
				* result
				+ ((lastConnectionDate == null) ? 0 : lastConnectionDate
						.hashCode());
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
		DeviceInformationDevices other = (DeviceInformationDevices) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (billingCycleEndDate == null) {
			if (other.billingCycleEndDate != null)
				return false;
		} else if (!billingCycleEndDate.equals(other.billingCycleEndDate))
			return false;
		if (!Arrays.equals(carrierInformations, other.carrierInformations))
			return false;
		if (connected == null) {
			if (other.connected != null)
				return false;
		} else if (!connected.equals(other.connected))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (!Arrays.equals(extendedAttributes, other.extendedAttributes))
			return false;
		if (!Arrays.equals(groupNames, other.groupNames))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (lastActivationBy == null) {
			if (other.lastActivationBy != null)
				return false;
		} else if (!lastActivationBy.equals(other.lastActivationBy))
			return false;
		if (lastActivationDate == null) {
			if (other.lastActivationDate != null)
				return false;
		} else if (!lastActivationDate.equals(other.lastActivationDate))
			return false;
		if (lastConnectionDate == null) {
			if (other.lastConnectionDate != null)
				return false;
		} else if (!lastConnectionDate.equals(other.lastConnectionDate))
			return false;
		return true;
	}

	

}
