package com.gv.midway.pojo.deviceInformation.response;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.deviceInformation.verizon.CarrierInformations;
import com.gv.midway.pojo.deviceInformation.verizon.CustomFields;
import com.gv.midway.pojo.deviceInformation.verizon.DeviceIds;
import com.gv.midway.pojo.deviceInformation.verizon.ExtendedAttributes;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformation {
	@ApiModelProperty(value = "An identifier from NetSuite system")
	private String netSuiteId;

	@ApiModelProperty(value = "An unique identifier (Primary key) for device in Midway")
	private String midwayMasterDeviceId;

	@ApiModelProperty(value = "If the device is not connected, this indicates the last known connection date.")
	private String lastConnectionDate;

	@ApiModelProperty(value = "last Features of the device.")
	private String[] lstFeatures;

	public String getCurrentDataPlan() {
		return currentDataPlan;
	}

	public void setCurrentDataPlan(String currentDataPlan) {
		this.currentDataPlan = currentDataPlan;
	}

	public String getIMSIOrMIN() {
		return IMSIOrMIN;
	}

	public void setIMSIOrMIN(String iMSIOrMIN) {
		IMSIOrMIN = iMSIOrMIN;
	}

	public String getMSISDNOrMDN() {
		return MSISDNOrMDN;
	}

	public void setMSISDNOrMDN(String mSISDNOrMDN) {
		MSISDNOrMDN = mSISDNOrMDN;
	}

	@ApiModelProperty(value = "currentDataPlan for the device")
	private String currentDataPlan;
	
	@ApiModelProperty(value = "IMSIOrMIN of the device")
	private String IMSIOrMIN;
	
	@ApiModelProperty(value = "MSISDNOrMDN of the device")
	private String MSISDNOrMDN;

	public String[] getLstFeatures() {
		return lstFeatures;
	}

	public void setLstFeatures(String[] lstFeatures) {
		this.lstFeatures = lstFeatures;
	}

	public String getCustomField1() {
		return customField1;
	}

	public void setCustomField1(String customField1) {
		this.customField1 = customField1;
	}

	public String getCustomField2() {
		return customField2;
	}

	public void setCustomField2(String customField2) {
		this.customField2 = customField2;
	}

	public String getCustomField3() {
		return customField3;
	}

	public void setCustomField3(String customField3) {
		this.customField3 = customField3;
	}

	public String getCustomField4() {
		return customField4;
	}

	public void setCustomField4(String customField4) {
		this.customField4 = customField4;
	}

	public String getCustomField5() {
		return customField5;
	}

	public void setCustomField5(String customField5) {
		this.customField5 = customField5;
	}

	public String getCustomField6() {
		return customField6;
	}

	public void setCustomField6(String customField6) {
		this.customField6 = customField6;
	}

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField1;
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField2;
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField3;
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField4;
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField5;
	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private String customField6;

	@ApiModelProperty(value = "The custom fields and values that have been set for the device.")
	private CustomFields[] customFields;

	public String getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(String previousAddress) {
		this.previousAddress = previousAddress;
	}

	// ended by Sadhana
	@ApiModelProperty(value = "futureSMSPlan for the device")
	private String futureSMSPlan;

	@ApiModelProperty(value = "The user who last activated the device.")
	private String lastActivationBy;

	@ApiModelProperty(value = "True if the device is connected; false if it is not.")
	private String connected;

	@ApiModelProperty(value = "The carrier information associated with the device.")
	private CarrierInformations carrierInformations;

	@ApiModelProperty(value = "The device group that the device belongs to.")
	private String[] groupNames;

	@ApiModelProperty(value = "monthly SMS Threshold of the device")
	private String monthlySMSThreshold;

	@ApiModelProperty(value = "monthly Data Threshold of the device")
	private String monthlyDataThreshold;

	@ApiModelProperty(value = "The billing account for which a list of devices will be returned.")
	private String accountName;

	@ApiModelProperty(value = "Daily Data Threshold of the device.")
	private String dailyDataThreshold;

	@ApiModelProperty(value = "The date that the device's current billing cycle ends.")
	private String billingCycleEndDate;

	@ApiModelProperty(value = "The date and time that the device was added to the system.")
	private String createdAt;

	@ApiModelProperty(value = "FutureDataPlan of the device.")
	private String futureDataPlan;
	// added
	
	@ApiModelProperty(value = "All identifiers for the device.")
	private DeviceIds[] deviceIds;

	// private DeviceIds deviceIds;

	public DeviceIds[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceIds[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	@ApiModelProperty(value = "currentSMSPlan for the device.")
	private String currentSMSPlan;

	@ApiModelProperty(value = "dailySMSThreshold for the device.")
	private String dailySMSThreshold;

	@ApiModelProperty(value = "Last year history details for the device.")
	private String[] lstHistoryOverLastYear;

	@ApiModelProperty(value = "Any extended attributes for the device, as Key ")
	private ExtendedAttributes[] extendedAttributes;

	@ApiModelProperty(value = "The IP address of the device.")
	private String ipAddress;

	@ApiModelProperty(value = "The Static IP address of the device.")
	private String staticIP;

	@ApiModelProperty(value = "Voice dispatch number")
	private String voiceDispatchNumber;

	@ApiModelProperty(value = "mostRecentLocateId of the device.")
	private String mostRecentLocateId;

	@ApiModelProperty(value = "previousLocateId of the device.")
	private String previousLocateId;

	@ApiModelProperty(value = "mostRecentLocateDate of the device.")
	private String mostRecentLocateDate;
	
	@ApiModelProperty(value = "mostRecentLatitude of the device.")
	private String mostRecentLatitude;

	@ApiModelProperty(value = "mostRecentLongitude of the device.")
	private String mostRecentLongitude;

	@ApiModelProperty(value = "mostRecentAddress of the device.")
	private String mostRecentAddress;

	@ApiModelProperty(value = "previousLocateDate of the device.")
	private String previousLocateDate;

	@ApiModelProperty(value = "previousLatitude of the device.")
	private String previousLatitude;
	
	@ApiModelProperty(value = "previousLongitude of the device.")
	private String previousLongitude;

	@ApiModelProperty(value = "previousAddress of the device.")
	private String previousAddress;

	@ApiModelProperty(value = "Last Extended Features of the device.")
	private String[] lstExtFeatures;

	public String getMostRecentLocateId() {
		return mostRecentLocateId;
	}

	public void setMostRecentLocateId(String mostRecentLocateId) {
		this.mostRecentLocateId = mostRecentLocateId;
	}

	public String getPreviousLocateId() {
		return previousLocateId;
	}

	public void setPreviousLocateId(String previousLocateId) {
		this.previousLocateId = previousLocateId;
	}

	public String getMostRecentLocateDate() {
		return mostRecentLocateDate;
	}

	public void setMostRecentLocateDate(String mostRecentLocateDate) {
		this.mostRecentLocateDate = mostRecentLocateDate;
	}

	public String getMostRecentLatitude() {
		return mostRecentLatitude;
	}

	public void setMostRecentLatitude(String mostRecentLatitude) {
		this.mostRecentLatitude = mostRecentLatitude;
	}

	public String getMostRecentLongitude() {
		return mostRecentLongitude;
	}

	public void setMostRecentLongitude(String mostRecentLongitude) {
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

	public String getPreviousLatitude() {
		return previousLatitude;
	}

	public void setPreviousLatitude(String previousLatitude) {
		this.previousLatitude = previousLatitude;
	}

	public String getPreviousLongitude() {
		return previousLongitude;
	}

	public void setPreviousLongitude(String previousLongitude) {
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
	@ApiModelProperty(value = "The date and time that the device was last activated.")
	private String lastActivationDate;

	public String getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(String lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	/*
	 * public LstFeatures getLstFeatures() { return lstFeatures; }
	 * 
	 * public void setLstFeatures(LstFeatures lstFeatures) { this.lstFeatures =
	 * lstFeatures; }
	 */

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

	/*
	 * public DeviceIds getDeviceIds() { return deviceIds; }
	 * 
	 * public void setDeviceIds(DeviceIds deviceIds) { this.deviceIds =
	 * deviceIds; }
	 */
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

	public ExtendedAttributes[] getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(ExtendedAttributes[] extendedAttributes) {
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

	public String getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(String netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public String getMidwayMasterDeviceId() {
		return midwayMasterDeviceId;
	}

	public void setMidwayMasterDeviceId(String midwayMasterDeviceId) {
		this.midwayMasterDeviceId = midwayMasterDeviceId;
	}

	@Override
	public String toString() {
		return "DeviceInformation [netSuiteId=" + netSuiteId
				+ ", midwayMasterDeviceId=" + midwayMasterDeviceId
				+ ", lastConnectionDate=" + lastConnectionDate
				+ ", lstFeatures=" + Arrays.toString(lstFeatures)
				+ ", customField1=" + customField1 + ", customField2="
				+ customField2 + ", customField3=" + customField3
				+ ", customField4=" + customField4 + ", customField5="
				+ customField5 + ", customField6=" + customField6
				+ ", futureSMSPlan=" + futureSMSPlan + ", lastActivationBy="
				+ lastActivationBy + ", customFields="
				+ Arrays.toString(customFields) + ", connected=" + connected
				+ ", carrierInformations=" + carrierInformations
				+ ", groupNames=" + Arrays.toString(groupNames)
				+ ", monthlySMSThreshold=" + monthlySMSThreshold
				+ ", monthlyDataThreshold=" + monthlyDataThreshold
				+ ", accountName=" + accountName + ", dailyDataThreshold="
				+ dailyDataThreshold + ", billingCycleEndDate="
				+ billingCycleEndDate + ", createdAt=" + createdAt
				+ ", futureDataPlan=" + futureDataPlan + ", deviceIds="
				+ Arrays.toString(deviceIds) + ", currentSMSPlan="
				+ currentSMSPlan + ", dailySMSThreshold=" + dailySMSThreshold
				+ ", lstHistoryOverLastYear="
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
				+ ", previousAddress=" + previousAddress + ", lstExtFeatures="
				+ Arrays.toString(lstExtFeatures) + ", lastActivationDate="
				+ lastActivationDate + "]";
	}

}
