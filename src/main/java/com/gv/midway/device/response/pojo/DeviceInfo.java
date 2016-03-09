package com.gv.midway.device.response.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"accountName",
"billingCycleEndDate",
"carrierInformations",
"connected",
"createdAt",
"customFields",
"deviceIds",
"groupName",
"ipAddress",
"lastActivationBy",
"lastActivationDate",
"lastConnectionDate",
"extendedAttributes"
})
public class DeviceInfo {

   
	
	@JsonProperty("accountName")
	private String accountName;
	
	
	@JsonProperty("billingCycleEndDate")
	private String billingCycleEndDate;
	
	@JsonProperty("carrierInformations")
	private List<CarrierInformation> carrierInformations = new ArrayList<CarrierInformation>();
	
	@JsonProperty("connected")
	private Boolean connected;
	
	@JsonProperty("createdAt")
	private String createdAt;
	
	@JsonProperty("customFields")
	private List<CustomField> customFields = new ArrayList<CustomField>();
	
	@JsonProperty("deviceIds")
	private List<DeviceId> deviceIds = new ArrayList<DeviceId>();
	
	@JsonProperty("groupName")
	private String groupName;
	
	@JsonProperty("ipAddress")
	private String ipAddress;
	
	@JsonProperty("lastActivationBy")
	private String lastActivationBy;
	
	@JsonProperty("lastActivationDate")
	private String lastActivationDate;
	
	@JsonProperty("lastConnectionDate")
	private String lastConnectionDate;
	
	@JsonProperty("extendedAttributes")
	private List<ExtendedAttribute> extendedAttributes = new ArrayList<ExtendedAttribute>();
	
	public String getVoiceDispatchNumber() {
		return voiceDispatchNumber;
	}

	public void setVoiceDispatchNumber(String voiceDispatchNumber) {
		this.voiceDispatchNumber = voiceDispatchNumber;
	}

	public String getCurrentSMSPlan() {
		return currentSMSPlan;
	}

	public void setCurrentSMSPlan(String currentSMSPlan) {
		this.currentSMSPlan = currentSMSPlan;
	}

	public String getFutureSMSPlan() {
		return futureSMSPlan;
	}

	public void setFutureSMSPlan(String futureSMSPlan) {
		this.futureSMSPlan = futureSMSPlan;
	}

	public String getFutureDataPlan() {
		return futureDataPlan;
	}

	public void setFutureDataPlan(String futureDataPlan) {
		this.futureDataPlan = futureDataPlan;
	}

	public String getDailySMSThreshold() {
		return dailySMSThreshold;
	}

	public void setDailySMSThreshold(String dailySMSThreshold) {
		this.dailySMSThreshold = dailySMSThreshold;
	}

	public String getDailyDataThreshold() {
		return dailyDataThreshold;
	}

	public void setDailyDataThreshold(String dailyDataThreshold) {
		this.dailyDataThreshold = dailyDataThreshold;
	}

	public String getMonthlySMSThreshold() {
		return monthlySMSThreshold;
	}

	public void setMonthlySMSThreshold(String monthlySMSThreshold) {
		this.monthlySMSThreshold = monthlySMSThreshold;
	}

	public String getMonthlyDataThreshold() {
		return monthlyDataThreshold;
	}

	public void setMonthlyDataThreshold(String monthlyDataThreshold) {
		this.monthlyDataThreshold = monthlyDataThreshold;
	}

	public List<String> getLstHistoryOverLastYear() {
		return lstHistoryOverLastYear;
	}

	public void setLstHistoryOverLastYear(List<String> lstHistoryOverLastYear) {
		this.lstHistoryOverLastYear = lstHistoryOverLastYear;
	}

	public List<String> getLstFeatures() {
		return lstFeatures;
	}

	public void setLstFeatures(List<String> lstFeatures) {
		this.lstFeatures = lstFeatures;
	}

	@JsonProperty("voiceDispatchNumber")
	private String voiceDispatchNumber;
	
	@JsonProperty("currentSMSPlan")
	private String currentSMSPlan;
	
	@JsonProperty("futureSMSPlan")
	private String futureSMSPlan;
	
	@JsonProperty("futureDataPlan")
	private String futureDataPlan;
	
	@JsonProperty("dailySMSThreshold")
	private String dailySMSThreshold;
	
	@JsonProperty("dailyDataThreshold")
	private String dailyDataThreshold;
	
	@JsonProperty("monthlySMSThreshold")
	private String monthlySMSThreshold;
	
	@JsonProperty("monthlyDataThreshold")
	private String monthlyDataThreshold;
	
	@JsonProperty("lstHistoryOverLastYear")
	private List<String> lstHistoryOverLastYear = new ArrayList<String>();
	
	@JsonProperty("lstFeatures")
	private List<String> lstFeatures = new ArrayList<String>();

    
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

	public List<CarrierInformation> getCarrierInformations() {
		return carrierInformations;
	}

	public void setCarrierInformations(List<CarrierInformation> carrierInformations) {
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

	public List<CustomField> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}

	public List<DeviceId> getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(List<DeviceId> deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getLastActivationDate() {
		return lastActivationDate;
	}

	public void setLastActivationDate(String lastActivationDate) {
		this.lastActivationDate = lastActivationDate;
	}

	public String getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(String lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public List<ExtendedAttribute> getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(List<ExtendedAttribute> extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

    

	@Override
	public String toString() {
		return "DeviceInfo [accountName=" + accountName
				+ ", billingCycleEndDate=" + billingCycleEndDate
				+ ", carrierInformations=" + carrierInformations
				+ ", connected=" + connected + ", createdAt=" + createdAt
				+ ", customFields=" + customFields + ", deviceIds=" + deviceIds
				+ ", groupName=" + groupName + ", ipAddress=" + ipAddress
				+ ", lastActivationBy=" + lastActivationBy
				+ ", lastActivationDate=" + lastActivationDate
				+ ", lastConnectionDate=" + lastConnectionDate
				+ ", extendedAttributes=" + extendedAttributes
				+ ", voiceDispatchNumber=" + voiceDispatchNumber
				+ ", currentSMSPlan=" + currentSMSPlan + ", futureSMSPlan="
				+ futureSMSPlan + ", futureDataPlan=" + futureDataPlan
				+ ", dailySMSThreshold=" + dailySMSThreshold
				+ ", dailyDataThreshold=" + dailyDataThreshold
				+ ", monthlySMSThreshold=" + monthlySMSThreshold
				+ ", monthlyDataThreshold=" + monthlyDataThreshold
				+ ", lstHistoryOverLastYear=" + lstHistoryOverLastYear
				+ ", lstFeatures=" + lstFeatures + "]";
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
		result = prime
				* result
				+ ((carrierInformations == null) ? 0 : carrierInformations
						.hashCode());
		result = prime * result
				+ ((connected == null) ? 0 : connected.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result
				+ ((currentSMSPlan == null) ? 0 : currentSMSPlan.hashCode());
		result = prime * result
				+ ((customFields == null) ? 0 : customFields.hashCode());
		result = prime
				* result
				+ ((dailyDataThreshold == null) ? 0 : dailyDataThreshold
						.hashCode());
		result = prime
				* result
				+ ((dailySMSThreshold == null) ? 0 : dailySMSThreshold
						.hashCode());
		result = prime * result
				+ ((deviceIds == null) ? 0 : deviceIds.hashCode());
		result = prime
				* result
				+ ((extendedAttributes == null) ? 0 : extendedAttributes
						.hashCode());
		result = prime * result
				+ ((futureDataPlan == null) ? 0 : futureDataPlan.hashCode());
		result = prime * result
				+ ((futureSMSPlan == null) ? 0 : futureSMSPlan.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
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
		result = prime * result
				+ ((lstFeatures == null) ? 0 : lstFeatures.hashCode());
		result = prime
				* result
				+ ((lstHistoryOverLastYear == null) ? 0
						: lstHistoryOverLastYear.hashCode());
		result = prime
				* result
				+ ((monthlyDataThreshold == null) ? 0 : monthlyDataThreshold
						.hashCode());
		result = prime
				* result
				+ ((monthlySMSThreshold == null) ? 0 : monthlySMSThreshold
						.hashCode());
		result = prime
				* result
				+ ((voiceDispatchNumber == null) ? 0 : voiceDispatchNumber
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
		DeviceInfo other = (DeviceInfo) obj;
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
		if (carrierInformations == null) {
			if (other.carrierInformations != null)
				return false;
		} else if (!carrierInformations.equals(other.carrierInformations))
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
		if (currentSMSPlan == null) {
			if (other.currentSMSPlan != null)
				return false;
		} else if (!currentSMSPlan.equals(other.currentSMSPlan))
			return false;
		if (customFields == null) {
			if (other.customFields != null)
				return false;
		} else if (!customFields.equals(other.customFields))
			return false;
		if (dailyDataThreshold == null) {
			if (other.dailyDataThreshold != null)
				return false;
		} else if (!dailyDataThreshold.equals(other.dailyDataThreshold))
			return false;
		if (dailySMSThreshold == null) {
			if (other.dailySMSThreshold != null)
				return false;
		} else if (!dailySMSThreshold.equals(other.dailySMSThreshold))
			return false;
		if (deviceIds == null) {
			if (other.deviceIds != null)
				return false;
		} else if (!deviceIds.equals(other.deviceIds))
			return false;
		if (extendedAttributes == null) {
			if (other.extendedAttributes != null)
				return false;
		} else if (!extendedAttributes.equals(other.extendedAttributes))
			return false;
		if (futureDataPlan == null) {
			if (other.futureDataPlan != null)
				return false;
		} else if (!futureDataPlan.equals(other.futureDataPlan))
			return false;
		if (futureSMSPlan == null) {
			if (other.futureSMSPlan != null)
				return false;
		} else if (!futureSMSPlan.equals(other.futureSMSPlan))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
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
		if (lstFeatures == null) {
			if (other.lstFeatures != null)
				return false;
		} else if (!lstFeatures.equals(other.lstFeatures))
			return false;
		if (lstHistoryOverLastYear == null) {
			if (other.lstHistoryOverLastYear != null)
				return false;
		} else if (!lstHistoryOverLastYear.equals(other.lstHistoryOverLastYear))
			return false;
		if (monthlyDataThreshold == null) {
			if (other.monthlyDataThreshold != null)
				return false;
		} else if (!monthlyDataThreshold.equals(other.monthlyDataThreshold))
			return false;
		if (monthlySMSThreshold == null) {
			if (other.monthlySMSThreshold != null)
				return false;
		} else if (!monthlySMSThreshold.equals(other.monthlySMSThreshold))
			return false;
		if (voiceDispatchNumber == null) {
			if (other.voiceDispatchNumber != null)
				return false;
		} else if (!voiceDispatchNumber.equals(other.voiceDispatchNumber))
			return false;
		return true;
	}


}
