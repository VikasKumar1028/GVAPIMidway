package com.gv.midway.pojo.activateDevice.request;
import com.wordnik.swagger.annotations.ApiModelProperty;




import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.PrimaryPlaceOfUse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceRequestDataArea {

	@ApiModelProperty(value = "VERIZON: The device group that the requested device belongs to. This parameter can serve either of two purposes:If you specify devices by ID in the devices paramters, this is the name of a device group that the devices should be added to. They will be in the default device group if you don't specify one.If you don't specify individual devices with the devices parameter,you can provide the name of a device group to activate all devices in that group")
	private String groupName;

	@ApiModelProperty(value = "Name of the billing account.This parameter is only required if the UWS account used for the current API session has access to multiple billing accounts")
	private String accountName;

	@ApiModelProperty(value = "VERIZON : Stock Keeping Unit(SKU) number of a 4G device. The Stock Keeping Unit (SKU) number of a 4G device type with an embedded SIM. Can be used with ICCID device identifiers in lieu of an IMEI when activating 4G devices. The SkuNumber will be used with all devices in the request, so all devices must be of the same type.Only 4G devices with embedded SIMs can be activated by SKU at this time")
	private String skuNumber;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	@ApiModelProperty(value = "VERIZON : Name of cost Center Code.")
	private String costCenterCode;

	@ApiModelProperty(value = "VERIZON : Name of the carrier Ip Pool.")
	private String carrierIpPoolName;

	@ApiModelProperty(value = "Service Plan that that device belongs to.Verizon Wireless provides service plan codes at the time of on-boarding and subsequently whenever there are any changes to the service plan. NOTE:  Any devices in the request that are not supported by the service plan will not activate. For example, if the service plan is only for 4G devices, any 3G devices included in the activation request will fail.", required=true)
	private String servicePlan;

	@ApiModelProperty(value = "The residential street address or the primary business street address of the Customer and Customer Name . Leave these fields empty to use the account profile address as the primary place of use. These values will be applied to all devices in the activation request.If the account is enabled for non-geographic MDNs and the device supports it, the primaryPlaceOfUse address will also be used to derive the MDN for the device.The Primary Place of Use location may affect taxation or have other legal implications. You may want to speak with legal and/or financial advisers before entering values for these fields.primaryPlaceOfUse cannot be used with leadId. VPP partners should enter a leadId value for a customer lead, and the AddressZipCode in the lead record will be used for taxation. VPP partners can use primaryPlaceOfUse fields without a leadId to associate customer-specific data with devices.")
	private PrimaryPlaceOfUse primaryPlaceOfUse;

	@ApiModelProperty(value = "VERIZON : The device lead id that the device belongs to.The ID of a Qualified or Closed - Won VPP customer lead, which is used with other values to determine MDN assignment, taxation, and compensation.This parameter is required when activating devices for Verizon Partner Program accounts, and it is not allowed for other activations.If you include leadId in an activation request, you should set the mdnZipCode value to match the zip value in the address returned for the lead.")
	private String leadId;

	@ApiModelProperty(value = "The name of the Carrier.",required = true)
	private String carrierName;
	
	@ApiModelProperty(value = "VERIZON : Name of Public IP Restriction.It may be restricted/unrestricted.If left blank, the device will get the default value set for the account. Public network devices with dynamic IP addresses are always unrestricted.")
	private String publicIpRestriction;

	@ApiModelProperty(value = "MDN zip code number" , required=true)
	private String mdnZipCode;
	
	@ApiModelProperty(value = "KORE : The EAP code is the Express Activation Profile to use for the activation.", required=true)
	private String eAPCode;


	@ApiModelProperty(value = "All identifiers for the device." , required=true)
	private ActivateDevices[] devices;

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

	public String getSkuNumber() {
		return skuNumber;
	}

	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getCarrierIpPoolName() {
		return carrierIpPoolName;
	}

	public void setCarrierIpPoolName(String carrierIpPoolName) {
		this.carrierIpPoolName = carrierIpPoolName;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public PrimaryPlaceOfUse getPrimaryPlaceOfUse() {
		return primaryPlaceOfUse;
	}

	public void setPrimaryPlaceOfUse(PrimaryPlaceOfUse primaryPlaceOfUse) {
		this.primaryPlaceOfUse = primaryPlaceOfUse;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getPublicIpRestriction() {
		return publicIpRestriction;
	}

	public void setPublicIpRestriction(String publicIpRestriction) {
		this.publicIpRestriction = publicIpRestriction;
	}

	public String getMdnZipCode() {
		return mdnZipCode;
	}

	public void setMdnZipCode(String mdnZipCode) {
		this.mdnZipCode = mdnZipCode;
	}

	
	public ActivateDevices[] getDevices() {
		return devices;
	}

	public void setDevices(ActivateDevices[] devices) {
		this.devices = devices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime
				* result
				+ ((carrierIpPoolName == null) ? 0 : carrierIpPoolName
						.hashCode());
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result
				+ ((costCenterCode == null) ? 0 : costCenterCode.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(devices);
		result = prime * result + ((eAPCode == null) ? 0 : eAPCode.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((leadId == null) ? 0 : leadId.hashCode());
		result = prime * result
				+ ((mdnZipCode == null) ? 0 : mdnZipCode.hashCode());
		result = prime
				* result
				+ ((primaryPlaceOfUse == null) ? 0 : primaryPlaceOfUse
						.hashCode());
		result = prime
				* result
				+ ((publicIpRestriction == null) ? 0 : publicIpRestriction
						.hashCode());
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
		result = prime * result
				+ ((skuNumber == null) ? 0 : skuNumber.hashCode());
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
		ActivateDeviceRequestDataArea other = (ActivateDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (carrierIpPoolName == null) {
			if (other.carrierIpPoolName != null)
				return false;
		} else if (!carrierIpPoolName.equals(other.carrierIpPoolName))
			return false;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (costCenterCode == null) {
			if (other.costCenterCode != null)
				return false;
		} else if (!costCenterCode.equals(other.costCenterCode))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(devices, other.devices))
			return false;
		if (eAPCode == null) {
			if (other.eAPCode != null)
				return false;
		} else if (!eAPCode.equals(other.eAPCode))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (leadId == null) {
			if (other.leadId != null)
				return false;
		} else if (!leadId.equals(other.leadId))
			return false;
		if (mdnZipCode == null) {
			if (other.mdnZipCode != null)
				return false;
		} else if (!mdnZipCode.equals(other.mdnZipCode))
			return false;
		if (primaryPlaceOfUse == null) {
			if (other.primaryPlaceOfUse != null)
				return false;
		} else if (!primaryPlaceOfUse.equals(other.primaryPlaceOfUse))
			return false;
		if (publicIpRestriction == null) {
			if (other.publicIpRestriction != null)
				return false;
		} else if (!publicIpRestriction.equals(other.publicIpRestriction))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		if (skuNumber == null) {
			if (other.skuNumber != null)
				return false;
		} else if (!skuNumber.equals(other.skuNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActivateDeviceRequestDataArea [groupName=" + groupName
				+ ", accountName=" + accountName + ", skuNumber=" + skuNumber
				+ ", customFields=" + Arrays.toString(customFields)
				+ ", costCenterCode=" + costCenterCode + ", carrierIpPoolName="
				+ carrierIpPoolName + ", servicePlan=" + servicePlan
				+ ", primaryPlaceOfUse=" + primaryPlaceOfUse + ", leadId="
				+ leadId + ", carrierName=" + carrierName
				+ ", publicIpRestriction=" + publicIpRestriction
				+ ", mdnZipCode=" + mdnZipCode + ", eAPCode=" + eAPCode
				+ ", devices=" + Arrays.toString(devices) + "]";
	}

	public String geteAPCode() {
		return eAPCode;
	}

	public void seteAPCode(String eAPCode) {
		this.eAPCode = eAPCode;
	}

	

}
