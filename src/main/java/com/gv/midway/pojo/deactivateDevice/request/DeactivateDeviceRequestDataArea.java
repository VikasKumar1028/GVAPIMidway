package com.gv.midway.pojo.deactivateDevice.request;
import com.gv.midway.pojo.DeviceId;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestDataArea {
	private String groupName;

	private String accountName;

	private CustomFields[] customFields;

	private String servicePlan;

	private String reasonCode;

	private DeviceId[] deviceId;

	private String etfWaiver;

	private Boolean flagScrap;

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


	public Boolean getFlagScrap() {
		return flagScrap;
	}

	public void setFlagScrap(Boolean flagScrap) {
		this.flagScrap = flagScrap;
	}

	/**
	 * @return the deviceId
	 */
	public DeviceId[] getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(DeviceId[] deviceId) {
		this.deviceId = deviceId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeactivateDeviceRequestDataArea [groupName=" + groupName + ", accountName=" + accountName + ", customFields=" + Arrays.toString(customFields) + ", servicePlan=" + servicePlan + ", reasonCode=" + reasonCode + ", deviceId=" + Arrays.toString(deviceId) + ", etfWaiver=" + etfWaiver + ", flagScrap=" + flagScrap + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(deviceId);
		result = prime * result + ((etfWaiver == null) ? 0 : etfWaiver.hashCode());
		result = prime * result + ((flagScrap == null) ? 0 : flagScrap.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((reasonCode == null) ? 0 : reasonCode.hashCode());
		result = prime * result + ((servicePlan == null) ? 0 : servicePlan.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DeactivateDeviceRequestDataArea))
			return false;
		DeactivateDeviceRequestDataArea other = (DeactivateDeviceRequestDataArea) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(deviceId, other.deviceId))
			return false;
		if (etfWaiver == null) {
			if (other.etfWaiver != null)
				return false;
		} else if (!etfWaiver.equals(other.etfWaiver))
			return false;
		if (flagScrap == null) {
			if (other.flagScrap != null)
				return false;
		} else if (!flagScrap.equals(other.flagScrap))
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
	
}
