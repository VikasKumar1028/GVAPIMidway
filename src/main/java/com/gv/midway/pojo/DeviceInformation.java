package com.gv.midway.pojo;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformation {

	private String lastConnectionDate;

	private LstFeatures lstFeatures;

	private String futureSMSPlan;

	private String lastActivationBy;

	private CustomFields[] customFields;

	private String connected;

	private CarrierInformations carrierInformations;

	private String[] groupNames;

	private String monthlySMSThreshold;

	private String monthlyDataThreshold;

	private String accountName;

	private String dailyDataThreshold;

	private String billingCycleEndDate;

	private String createdAt;

	private String futureDataPlan;

	private DeviceIds deviceIds;

	private String currentSMSPlan;

	private String dailySMSThreshold;

	private String[] lstHistoryOverLastYear;

	private ExtendedAttributes extendedAttributes;

	private String ipAddress;

	private String staticIP;

	private String voiceDispatchNumber;

	private int mostRecentLocateId;

	private int previousLocateId;

	private String mostRecentLocateDate;

	private double mostRecentLatitude;

	private double mostRecentLongitude;

	private String mostRecentAddress;

	private String previousLocateDate;

	private double previousLatitude;

	private double previousLongitude;

	private String[] lstExtFeatures;

	public int getMostRecentLocateId() {
		return mostRecentLocateId;
	}

	public void setMostRecentLocateId(int mostRecentLocateId) {
		this.mostRecentLocateId = mostRecentLocateId;
	}

	public int getPreviousLocateId() {
		return previousLocateId;
	}

	public void setPreviousLocateId(int previousLocateId) {
		this.previousLocateId = previousLocateId;
	}

	public String getMostRecentLocateDate() {
		return mostRecentLocateDate;
	}

	public void setMostRecentLocateDate(String mostRecentLocateDate) {
		this.mostRecentLocateDate = mostRecentLocateDate;
	}

	public double getMostRecentLatitude() {
		return mostRecentLatitude;
	}

	public void setMostRecentLatitude(double mostRecentLatitude) {
		this.mostRecentLatitude = mostRecentLatitude;
	}

	public double getMostRecentLongitude() {
		return mostRecentLongitude;
	}

	public void setMostRecentLongitude(double mostRecentLongitude) {
		this.mostRecentLongitude = mostRecentLongitude;
	}

	public String getMostRecentAddress() {
		return mostRecentAddress;
	}

	public void setMostRecentAddress(String mostRecentAddress) {
		this.mostRecentAddress = mostRecentAddress;
	}

	public String getPreviousLocateDate() {
		return previousLocateDate;
	}

	public void setPreviousLocateDate(String previousLocateDate) {
		this.previousLocateDate = previousLocateDate;
	}

	public double getPreviousLatitude() {
		return previousLatitude;
	}

	public void setPreviousLatitude(double previousLatitude) {
		this.previousLatitude = previousLatitude;
	}

	public double getPreviousLongitude() {
		return previousLongitude;
	}

	public void setPreviousLongitude(double previousLongitude) {
		this.previousLongitude = previousLongitude;
	}

	public String[] getLstExtFeatures() {
		return lstExtFeatures;
	}

	public void setLstExtFeatures(String[] lstExtFeatures) {
		this.lstExtFeatures = lstExtFeatures;
	}

	public String getVoiceDispatchNumber() {
		return voiceDispatchNumber;
	}

	public void setVoiceDispatchNumber(String voiceDispatchNumber) {
		this.voiceDispatchNumber = voiceDispatchNumber;
	}

	public String getStaticIP() {
		return staticIP;
	}

	public void setStaticIP(String staticIP) {
		this.staticIP = staticIP;
	}

	private String lastActivationDate;

	public String getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(String lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public LstFeatures getLstFeatures() {
		return lstFeatures;
	}

	public void setLstFeatures(LstFeatures lstFeatures) {
		this.lstFeatures = lstFeatures;
	}

	public String getFutureSMSPlan() {
		return futureSMSPlan;
	}

	public void setFutureSMSPlan(String futureSMSPlan) {
		this.futureSMSPlan = futureSMSPlan;
	}

	public String getLastActivationBy() {
		return lastActivationBy;
	}

	public void setLastActivationBy(String lastActivationBy) {
		this.lastActivationBy = lastActivationBy;
	}

	public CustomFields[] getCustomFields() {
		return customFields;
	}

	public void setCustomFields(CustomFields[] customFields) {
		this.customFields = customFields;
	}

	public String getConnected() {
		return connected;
	}

	public void setConnected(String connected) {
		this.connected = connected;
	}

	public CarrierInformations getCarrierInformations() {
		return carrierInformations;
	}

	public void setCarrierInformations(CarrierInformations carrierInformations) {
		this.carrierInformations = carrierInformations;
	}

	public String[] getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String[] groupNames) {
		this.groupNames = groupNames;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDailyDataThreshold() {
		return dailyDataThreshold;
	}

	public void setDailyDataThreshold(String dailyDataThreshold) {
		this.dailyDataThreshold = dailyDataThreshold;
	}

	public String getBillingCycleEndDate() {
		return billingCycleEndDate;
	}

	public void setBillingCycleEndDate(String billingCycleEndDate) {
		this.billingCycleEndDate = billingCycleEndDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFutureDataPlan() {
		return futureDataPlan;
	}

	public void setFutureDataPlan(String futureDataPlan) {
		this.futureDataPlan = futureDataPlan;
	}

	public DeviceIds getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceIds deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getCurrentSMSPlan() {
		return currentSMSPlan;
	}

	public void setCurrentSMSPlan(String currentSMSPlan) {
		this.currentSMSPlan = currentSMSPlan;
	}

	public String getDailySMSThreshold() {
		return dailySMSThreshold;
	}

	public void setDailySMSThreshold(String dailySMSThreshold) {
		this.dailySMSThreshold = dailySMSThreshold;
	}

	public ExtendedAttributes getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(ExtendedAttributes extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastActivationDate() {
		return lastActivationDate;
	}

	public void setLastActivationDate(String lastActivationDate) {
		this.lastActivationDate = lastActivationDate;
	}

	public String[] getLstHistoryOverLastYear() {
		return lstHistoryOverLastYear;
	}

	public void setLstHistoryOverLastYear(String[] lstHistoryOverLastYear) {
		this.lstHistoryOverLastYear = lstHistoryOverLastYear;
	}

	@Override
	public String toString() {
		return "DeviceInformation [lastConnectionDate=" + lastConnectionDate
				+ ", lstFeatures=" + lstFeatures + ", futureSMSPlan="
				+ futureSMSPlan + ", lastActivationBy=" + lastActivationBy
				+ ", customFields=" + Arrays.toString(customFields)
				+ ", connected=" + connected + ", carrierInformations="
				+ carrierInformations + ", groupNames="
				+ Arrays.toString(groupNames) + ", monthlySMSThreshold="
				+ monthlySMSThreshold + ", monthlyDataThreshold="
				+ monthlyDataThreshold + ", accountName=" + accountName
				+ ", dailyDataThreshold=" + dailyDataThreshold
				+ ", billingCycleEndDate=" + billingCycleEndDate
				+ ", createdAt=" + createdAt + ", futureDataPlan="
				+ futureDataPlan + ", deviceIds=" + deviceIds
				+ ", currentSMSPlan=" + currentSMSPlan + ", dailySMSThreshold="
				+ dailySMSThreshold + ", lstHistoryOverLastYear="
				+ Arrays.toString(lstHistoryOverLastYear)
				+ ", extendedAttributes=" + extendedAttributes + ", ipAddress="
				+ ipAddress + ", staticIP=" + staticIP
				+ ", voiceDispatchNumber=" + voiceDispatchNumber
				+ ", mostRecentLocateId=" + mostRecentLocateId
				+ ", previousLocateId=" + previousLocateId
				+ ", mostRecentLocateDate=" + mostRecentLocateDate
				+ ", mostRecentLatitude=" + mostRecentLatitude
				+ ", mostRecentLongitude=" + mostRecentLongitude
				+ ", mostRecentAddress=" + mostRecentAddress
				+ ", previousLocateDate=" + previousLocateDate
				+ ", previousLatitude=" + previousLatitude
				+ ", previousLongitude=" + previousLongitude
				+ ", lstExtFeatures=" + Arrays.toString(lstExtFeatures)
				+ ", lastActivationDate=" + lastActivationDate
				+ ", getMostRecentLocateId()=" + getMostRecentLocateId()
				+ ", getPreviousLocateId()=" + getPreviousLocateId()
				+ ", getMostRecentLocateDate()=" + getMostRecentLocateDate()
				+ ", getMostRecentLatitude()=" + getMostRecentLatitude()
				+ ", getMostRecentLongitude()=" + getMostRecentLongitude()
				+ ", getMostRecentAddress()=" + getMostRecentAddress()
				+ ", getPreviousLocateDate()=" + getPreviousLocateDate()
				+ ", getPreviousLatitude()=" + getPreviousLatitude()
				+ ", getPreviousLongitude()=" + getPreviousLongitude()
				+ ", getLstExtFeatures()="
				+ Arrays.toString(getLstExtFeatures())
				+ ", getVoiceDispatchNumber()=" + getVoiceDispatchNumber()
				+ ", getStaticIP()=" + getStaticIP()
				+ ", getLastConnectionDate()=" + getLastConnectionDate()
				+ ", getLstFeatures()=" + getLstFeatures()
				+ ", getFutureSMSPlan()=" + getFutureSMSPlan()
				+ ", getLastActivationBy()=" + getLastActivationBy()
				+ ", getCustomFields()=" + Arrays.toString(getCustomFields())
				+ ", getConnected()=" + getConnected()
				+ ", getCarrierInformations()=" + getCarrierInformations()
				+ ", getGroupNames()=" + Arrays.toString(getGroupNames())
				+ ", getMonthlySMSThreshold()=" + getMonthlySMSThreshold()
				+ ", getMonthlyDataThreshold()=" + getMonthlyDataThreshold()
				+ ", getAccountName()=" + getAccountName()
				+ ", getDailyDataThreshold()=" + getDailyDataThreshold()
				+ ", getBillingCycleEndDate()=" + getBillingCycleEndDate()
				+ ", getCreatedAt()=" + getCreatedAt()
				+ ", getFutureDataPlan()=" + getFutureDataPlan()
				+ ", getDeviceIds()=" + getDeviceIds()
				+ ", getCurrentSMSPlan()=" + getCurrentSMSPlan()
				+ ", getDailySMSThreshold()=" + getDailySMSThreshold()
				+ ", getExtendedAttributes()=" + getExtendedAttributes()
				+ ", getIpAddress()=" + getIpAddress()
				+ ", getLastActivationDate()=" + getLastActivationDate()
				+ ", getLstHistoryOverLastYear()="
				+ Arrays.toString(getLstHistoryOverLastYear())
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
