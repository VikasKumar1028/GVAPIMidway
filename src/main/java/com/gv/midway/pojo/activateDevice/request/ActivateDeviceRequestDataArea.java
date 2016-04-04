package com.gv.midway.pojo.activateDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.ActivateDeviceId;
import com.gv.midway.pojo.DeviceId;
import com.gv.midway.pojo.activateDevice.verizon.CustomFields;
import com.gv.midway.pojo.activateDevice.verizon.PrimaryPlaceOfUse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceRequestDataArea {

	private String groupName;

	private String accountName;

	private String skuNumber;

	private CustomFields[] customFields;

	private String costCenterCode;

	private String carrierIpPoolName;

	private String servicePlan;

	private PrimaryPlaceOfUse primaryPlaceOfUse;

	private String leadId;

	private String carrierName;

	private String publicIpRestriction;

	private String mdnZipCode;

	private ActivateDeviceId[] deviceId;

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

	public ActivateDeviceId[] getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(ActivateDeviceId[] deviceId) {
		this.deviceId = deviceId;
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
				+ ", mdnZipCode=" + mdnZipCode + ", deviceId="
				+ Arrays.toString(deviceId) + "]";
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
		result = prime * result + Arrays.hashCode(deviceId);
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
		if (!Arrays.equals(deviceId, other.deviceId))
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

}
