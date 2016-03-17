package com.gv.midway.pojo.deviceInformation.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {

	private String accountName;

	private String lastActivationBy;

	private String createdAt;

	private String billingCycleEndDate;

	private DeviceIds[] deviceIds;

	private String connected;

	private String[] groupNames;

	private CarrierInformations[] carrierInformations;

	private ExtendedAttributes[] extendedAttributes;

	private String lastActivationDate;

	private String ipAddress;
	
	private CustomFields[] customFields;

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	private String lastConnectionDate;

	public String getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(String lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLastActivationBy() {
		return lastActivationBy;
	}

	public void setLastActivationBy(String lastActivationBy) {
		this.lastActivationBy = lastActivationBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getBillingCycleEndDate() {
		return billingCycleEndDate;
	}

	public void setBillingCycleEndDate(String billingCycleEndDate) {
		this.billingCycleEndDate = billingCycleEndDate;
	}

	public DeviceIds[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceIds[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getConnected() {
		return connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	public String[] getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String[] groupNames) {
		this.groupNames = groupNames;
	}

	public CarrierInformations[] getCarrierInformations() {
		return carrierInformations;
	}

	public void setCarrierInformations(CarrierInformations[] carrierInformations) {
		this.carrierInformations = carrierInformations;
	}

	public ExtendedAttributes[] getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(ExtendedAttributes[] extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

	public String getLastActivationDate() {
		return lastActivationDate;
	}

	public void setLastActivationDate(String lastActivationDate) {
		this.lastActivationDate = lastActivationDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "ClassPojo [accountName = " + accountName
				+ ", lastActivationBy = " + lastActivationBy + ", createdAt = "
				+ createdAt + ", billingCycleEndDate = " + billingCycleEndDate
				+ ", deviceIds = " + deviceIds + ", connected = " + connected
				+ ", groupNames = " + groupNames + ", carrierInformations = "
				+ carrierInformations + ", extendedAttributes = "
				+ extendedAttributes + ", lastActivationDate = "
				+ lastActivationDate + ", ipAddress = " + ipAddress + "]";
	}

}
