package com.gv.midway.pojo.deviceInformation.response;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gv.midway.pojo.KeyValuePair;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.deviceInformation.verizon.response.ExtendedAttributes;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Document(collection = "deviceInfo")
public class DeviceInformation {

    @ApiModelProperty(value = "An unique identifier (Primary key) for device in Midway.")
    @JsonIgnore
    @Id
    private String midwayMasterDeviceId;

    @ApiModelProperty(value = "An identifier from NetSuite system.")
    private Integer netSuiteId;

    @ApiModelProperty(value = "Value of the device identifier. Cell information.")
    private Cell cell;

    @ApiModelProperty(value = "Bs Id of the device.")
    private String bs_id;

    @ApiModelProperty(value = "Serial number of the device.")
    private String serial_num;

    @ApiModelProperty(value = "Mac address of the device.")
    private String mac;

    @ApiModelProperty(value = "Business carrier reseller information of the device.")
    private String bs_carrier;

    @ApiModelProperty(value = "Business service plan of the device for end customer.")
    private Bs_plan bs_plan;

    @ApiModelProperty(value = "TimeStamp for latest device Information updated.")
    private Date lastUpdated;

    @ApiModelProperty(value = "State of the device. i.e. Active, Suspended, Deactivate, Restore")
    private String state;

    @ApiModelProperty(value = "The current service plan of the device.")
    private String currentServicePlan;

    @ApiModelProperty(value = "The IP address of the device.")
    private String ipAddress;

    @ApiModelProperty(value = "The custom fields and values that have been set for the device.")
    private KeyValuePair[] customFields;

    @ApiModelProperty(value = "All identifiers for the device.")
    private DeviceId[] deviceIds;

    @ApiModelProperty(value = "Any extended attributes for the device, as Key. It will only in case of Verizon.")
    private ExtendedAttributes[] extendedAttributes;

    @ApiModelProperty(value = "The billing account for which a list of devices will be returned. It will only in case of Verizon.")
    private String accountName;

    @ApiModelProperty(value = "The date that the device's current billing cycle ends. It will only in case of Verizon.")
    private String billingCycleEndDate;

    @ApiModelProperty(value = "The device group that the device belongs to. It will only in case of Verizon.")
    private String groupName;

    @ApiModelProperty(value = "True if the device is connected; false if it is not. It will only in case of Verizon.")
    private Boolean isConnected;

	@ApiModelProperty(value = "The date and time that the device was added to the system. It will only in case of Verizon.")
    private String createdAt;

    @ApiModelProperty(value = "The user who last activated the device. It will only in case of Verizon.")
    private String lastActivationBy;

    @ApiModelProperty(value = "The date and time that the device was last activated. It will only in case of Verizon.")
    private String lastActivationDate;

    @ApiModelProperty(value = "If the device is not connected, this indicates the last known connection date. It will only in case of Verizon.")
    private String lastConnectionDate;

    @ApiModelProperty(value = "Voice dispatch number. It will be only in case of Kore.")
    private String voiceDispatchNumber;

    @ApiModelProperty(value = "CurrentSMSPlan for the device. It will be only in case of Kore.")
    private String currentSMSPlan;

    @ApiModelProperty(value = "futureSMSPlan for the device. It will be only in case of Kore.")
    private String futureSMSPlan;

    @ApiModelProperty(value = "FutureDataPlan of the device. It will be only in case of Kore.")
    private String futureDataPlan;

    @ApiModelProperty(value = "The dailySMSThreshold for the device. It will be only in case of Kore.")
    private Integer dailySMSThreshold;

    @ApiModelProperty(value = "Daily Data Threshold of the device. It will be only in case of Kore.")
    private Integer dailyDataThreshold;

    @ApiModelProperty(value = "Monthly SMS Threshold of the device. It will be only in case of Kore.")
    private Integer monthlySMSThreshold;

    @ApiModelProperty(value = "Monthly Data Threshold of the device. It will be only in case of Kore.")
    private Integer monthlyDataThreshold;

    @ApiModelProperty(value = "Last year history details for the device. It will be only in case of Kore.")
    private String[] lstHistoryOverLastYear;

    @ApiModelProperty(value = "Last Features of the device. It will be only in case of Kore.")
    private String[] lstFeatures;

    @ApiModelProperty(value = "Last Extended Features of the device.")
    private String[] lstExtFeatures;
    
    @ApiModelProperty(value = "Month to date Data usage. Only for the ATT jasper devices.")
    private String monthToDateDataUsage; 
   
	@ApiModelProperty(value = "Month to date Voice usage. Only for the ATT jasper devices.")
    private String monthToDateVoiceUsage;
	
	@ApiModelProperty(value = "The operator custom fields and values.Only for the ATT jasper devices.")
	private KeyValuePair[] operatorCustomFields;
	
	@ApiModelProperty(value = "The customer custom fields and values.Only for the ATT jasper devices.")
	private KeyValuePair[] customerCustomFields;
	
	@ApiModelProperty(value = "Date when device was activated.Only for the ATT jasper devices.")
	private String dateActivated;

	@ApiModelProperty(value = "Date when device is modified.Only for the ATT jasper devices.")
	private String dateModified;
	
	public String getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(String dateActivated) {
		this.dateActivated = dateActivated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
    
	public KeyValuePair[] getOperatorCustomFields() {
		return operatorCustomFields;
	}

	public void setOperatorCustomFields(KeyValuePair[] operatorCustomFields) {
		this.operatorCustomFields = operatorCustomFields;
	}

	public KeyValuePair[] getCustomerCustomFields() {
		return customerCustomFields;
	}

	public void setCustomerCustomFields(KeyValuePair[] customerCustomFields) {
		this.customerCustomFields = customerCustomFields;
	}
  
	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	public String getMonthToDateDataUsage() {
		return monthToDateDataUsage;
	}

	public void setMonthToDateDataUsage(String monthToDateDataUsage) {
		this.monthToDateDataUsage = monthToDateDataUsage;
	}

	public String getMonthToDateVoiceUsage() {
		return monthToDateVoiceUsage;
	}

	public void setMonthToDateVoiceUsage(String monthToDateVoiceUsage) {
		this.monthToDateVoiceUsage = monthToDateVoiceUsage;
	}
   

	public String getMidwayMasterDeviceId() {
        return midwayMasterDeviceId;
    }

    public void setMidwayMasterDeviceId(String midwayMasterDeviceId) {
        this.midwayMasterDeviceId = midwayMasterDeviceId;
    }

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getBs_id() {
        return bs_id;
    }

    public void setBs_id(String bs_id) {
        this.bs_id = bs_id;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBs_carrier() {
        return bs_carrier;
    }

    public void setBs_carrier(String bs_carrier) {
        this.bs_carrier = bs_carrier;
    }

    public Bs_plan getBs_plan() {
        return bs_plan;
    }

    public void setBs_plan(Bs_plan bs_plan) {
        this.bs_plan = bs_plan;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentServicePlan() {
        return currentServicePlan;
    }

    public void setCurrentServicePlan(String currentServicePlan) {
        this.currentServicePlan = currentServicePlan;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public KeyValuePair[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(KeyValuePair[] customFields) {
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    public Boolean isConnected() {
//        return isConnected;
//    }

//    public void setConnected(Boolean isConnected) {
//        this.isConnected = isConnected;
//    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Integer getDailySMSThreshold() {
        return dailySMSThreshold;
    }

    public void setDailySMSThreshold(Integer dailySMSThreshold) {
        this.dailySMSThreshold = dailySMSThreshold;
    }

    public Integer getDailyDataThreshold() {
        return dailyDataThreshold;
    }

    public void setDailyDataThreshold(Integer dailyDataThreshold) {
        this.dailyDataThreshold = dailyDataThreshold;
    }

    public Integer getMonthlySMSThreshold() {
        return monthlySMSThreshold;
    }

    public void setMonthlySMSThreshold(Integer monthlySMSThreshold) {
        this.monthlySMSThreshold = monthlySMSThreshold;
    }

    public Integer getMonthlyDataThreshold() {
        return monthlyDataThreshold;
    }

    public void setMonthlyDataThreshold(Integer monthlyDataThreshold) {
        this.monthlyDataThreshold = monthlyDataThreshold;
    }

    public String[] getLstHistoryOverLastYear() {
        return lstHistoryOverLastYear;
    }

    public void setLstHistoryOverLastYear(String[] lstHistoryOverLastYear) {
        this.lstHistoryOverLastYear = lstHistoryOverLastYear;
    }

    public String[] getLstFeatures() {
        return lstFeatures;
    }

    public void setLstFeatures(String[] lstFeatures) {
        this.lstFeatures = lstFeatures;
    }

    public String[] getLstExtFeatures() {
        return lstExtFeatures;
    }

    public void setLstExtFeatures(String[] lstExtFeatures) {
        this.lstExtFeatures = lstExtFeatures;
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
		result = prime * result
				+ ((bs_carrier == null) ? 0 : bs_carrier.hashCode());
		result = prime * result + ((bs_id == null) ? 0 : bs_id.hashCode());
		result = prime * result + ((bs_plan == null) ? 0 : bs_plan.hashCode());
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result
				+ ((currentSMSPlan == null) ? 0 : currentSMSPlan.hashCode());
		result = prime
				* result
				+ ((currentServicePlan == null) ? 0 : currentServicePlan
						.hashCode());
		result = prime * result + Arrays.hashCode(customFields);
		result = prime * result + Arrays.hashCode(customerCustomFields);
		result = prime
				* result
				+ ((dailyDataThreshold == null) ? 0 : dailyDataThreshold
						.hashCode());
		result = prime
				* result
				+ ((dailySMSThreshold == null) ? 0 : dailySMSThreshold
						.hashCode());
		result = prime * result
				+ ((dateActivated == null) ? 0 : dateActivated.hashCode());
		result = prime * result
				+ ((dateModified == null) ? 0 : dateModified.hashCode());
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result + Arrays.hashCode(extendedAttributes);
		result = prime * result
				+ ((futureDataPlan == null) ? 0 : futureDataPlan.hashCode());
		result = prime * result
				+ ((futureSMSPlan == null) ? 0 : futureSMSPlan.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((isConnected == null) ? 0 : isConnected.hashCode());
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
				+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + Arrays.hashCode(lstExtFeatures);
		result = prime * result + Arrays.hashCode(lstFeatures);
		result = prime * result + Arrays.hashCode(lstHistoryOverLastYear);
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime
				* result
				+ ((midwayMasterDeviceId == null) ? 0 : midwayMasterDeviceId
						.hashCode());
		result = prime
				* result
				+ ((monthToDateDataUsage == null) ? 0 : monthToDateDataUsage
						.hashCode());
		result = prime
				* result
				+ ((monthToDateVoiceUsage == null) ? 0 : monthToDateVoiceUsage
						.hashCode());
		result = prime
				* result
				+ ((monthlyDataThreshold == null) ? 0 : monthlyDataThreshold
						.hashCode());
		result = prime
				* result
				+ ((monthlySMSThreshold == null) ? 0 : monthlySMSThreshold
						.hashCode());
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
		result = prime * result + Arrays.hashCode(operatorCustomFields);
		result = prime * result
				+ ((serial_num == null) ? 0 : serial_num.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		DeviceInformation other = (DeviceInformation) obj;
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
		if (bs_carrier == null) {
			if (other.bs_carrier != null)
				return false;
		} else if (!bs_carrier.equals(other.bs_carrier))
			return false;
		if (bs_id == null) {
			if (other.bs_id != null)
				return false;
		} else if (!bs_id.equals(other.bs_id))
			return false;
		if (bs_plan == null) {
			if (other.bs_plan != null)
				return false;
		} else if (!bs_plan.equals(other.bs_plan))
			return false;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
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
		if (currentServicePlan == null) {
			if (other.currentServicePlan != null)
				return false;
		} else if (!currentServicePlan.equals(other.currentServicePlan))
			return false;
		if (!Arrays.equals(customFields, other.customFields))
			return false;
		if (!Arrays.equals(customerCustomFields, other.customerCustomFields))
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
		if (dateActivated == null) {
			if (other.dateActivated != null)
				return false;
		} else if (!dateActivated.equals(other.dateActivated))
			return false;
		if (dateModified == null) {
			if (other.dateModified != null)
				return false;
		} else if (!dateModified.equals(other.dateModified))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (!Arrays.equals(extendedAttributes, other.extendedAttributes))
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
		if (isConnected == null) {
			if (other.isConnected != null)
				return false;
		} else if (!isConnected.equals(other.isConnected))
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
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (!Arrays.equals(lstExtFeatures, other.lstExtFeatures))
			return false;
		if (!Arrays.equals(lstFeatures, other.lstFeatures))
			return false;
		if (!Arrays
				.equals(lstHistoryOverLastYear, other.lstHistoryOverLastYear))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (midwayMasterDeviceId == null) {
			if (other.midwayMasterDeviceId != null)
				return false;
		} else if (!midwayMasterDeviceId.equals(other.midwayMasterDeviceId))
			return false;
		if (monthToDateDataUsage == null) {
			if (other.monthToDateDataUsage != null)
				return false;
		} else if (!monthToDateDataUsage.equals(other.monthToDateDataUsage))
			return false;
		if (monthToDateVoiceUsage == null) {
			if (other.monthToDateVoiceUsage != null)
				return false;
		} else if (!monthToDateVoiceUsage.equals(other.monthToDateVoiceUsage))
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
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		if (!Arrays.equals(operatorCustomFields, other.operatorCustomFields))
			return false;
		if (serial_num == null) {
			if (other.serial_num != null)
				return false;
		} else if (!serial_num.equals(other.serial_num))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (voiceDispatchNumber == null) {
			if (other.voiceDispatchNumber != null)
				return false;
		} else if (!voiceDispatchNumber.equals(other.voiceDispatchNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceInformation [midwayMasterDeviceId=");
		builder.append(midwayMasterDeviceId);
		builder.append(", netSuiteId=");
		builder.append(netSuiteId);
		builder.append(", cell=");
		builder.append(cell);
		builder.append(", bs_id=");
		builder.append(bs_id);
		builder.append(", serial_num=");
		builder.append(serial_num);
		builder.append(", mac=");
		builder.append(mac);
		builder.append(", bs_carrier=");
		builder.append(bs_carrier);
		builder.append(", bs_plan=");
		builder.append(bs_plan);
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append(", state=");
		builder.append(state);
		builder.append(", currentServicePlan=");
		builder.append(currentServicePlan);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", customFields=");
		builder.append(Arrays.toString(customFields));
		builder.append(", deviceIds=");
		builder.append(Arrays.toString(deviceIds));
		builder.append(", extendedAttributes=");
		builder.append(Arrays.toString(extendedAttributes));
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", billingCycleEndDate=");
		builder.append(billingCycleEndDate);
		builder.append(", groupName=");
		builder.append(groupName);
		builder.append(", isConnected=");
		builder.append(isConnected);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", lastActivationBy=");
		builder.append(lastActivationBy);
		builder.append(", lastActivationDate=");
		builder.append(lastActivationDate);
		builder.append(", lastConnectionDate=");
		builder.append(lastConnectionDate);
		builder.append(", voiceDispatchNumber=");
		builder.append(voiceDispatchNumber);
		builder.append(", currentSMSPlan=");
		builder.append(currentSMSPlan);
		builder.append(", futureSMSPlan=");
		builder.append(futureSMSPlan);
		builder.append(", futureDataPlan=");
		builder.append(futureDataPlan);
		builder.append(", dailySMSThreshold=");
		builder.append(dailySMSThreshold);
		builder.append(", dailyDataThreshold=");
		builder.append(dailyDataThreshold);
		builder.append(", monthlySMSThreshold=");
		builder.append(monthlySMSThreshold);
		builder.append(", monthlyDataThreshold=");
		builder.append(monthlyDataThreshold);
		builder.append(", lstHistoryOverLastYear=");
		builder.append(Arrays.toString(lstHistoryOverLastYear));
		builder.append(", lstFeatures=");
		builder.append(Arrays.toString(lstFeatures));
		builder.append(", lstExtFeatures=");
		builder.append(Arrays.toString(lstExtFeatures));
		builder.append(", monthToDateDataUsage=");
		builder.append(monthToDateDataUsage);
		builder.append(", monthToDateVoiceUsage=");
		builder.append(monthToDateVoiceUsage);
		builder.append(", operatorCustomFields=");
		builder.append(Arrays.toString(operatorCustomFields));
		builder.append(", customerCustomFields=");
		builder.append(Arrays.toString(customerCustomFields));
		builder.append(", dateActivated=");
		builder.append(dateActivated);
		builder.append(", dateModified=");
		builder.append(dateModified);
		builder.append("]");
		return builder.toString();
	}



}
