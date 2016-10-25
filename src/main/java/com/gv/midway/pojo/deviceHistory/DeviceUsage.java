package com.gv.midway.pojo.deviceHistory;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.pojo.verizon.DeviceId;

@Document(collection = "deviceUsage")
public class DeviceUsage {

	private DeviceId deviceId;
	private Integer netSuiteId;
	private String date;
	private long dataUsed;
	private String transactionStatus;
	private String transactionErrorReason;
	private Boolean isValid;
	private String carrierName;
	private String jobId;
	private long monthToDateUsage;

	public DeviceId getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(Integer netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public long getDataUsed() {
		return dataUsed;
	}

	public void setDataUsed(long dataUsed) {
		this.dataUsed = dataUsed;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionErrorReason() {
		return transactionErrorReason;
	}

	public void setTransactionErrorReason(String transactionErrorReason) {
		this.transactionErrorReason = transactionErrorReason;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public long getMonthToDateUsage() {
		return monthToDateUsage;
	}

	public void setMonthToDateUsage(long monthToDateUsage) {
		this.monthToDateUsage = monthToDateUsage;
	}

	@Override
	public String toString() {
		return "DeviceUsage [deviceId=" + deviceId + ", netSuiteId="
				+ netSuiteId + ", date=" + date + ", dataUsed=" + dataUsed
				+ ", transactionStatus=" + transactionStatus
				+ ", transactionErrorReason=" + transactionErrorReason
				+ ", isValid=" + isValid + ", carrierName=" + carrierName
				+ ", jobId=" + jobId + ", monthToDateUsage=" + monthToDateUsage
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + (int) (dataUsed ^ (dataUsed >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result
				+ (int) (monthToDateUsage ^ (monthToDateUsage >>> 32));
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
		result = prime
				* result
				+ ((transactionErrorReason == null) ? 0
						: transactionErrorReason.hashCode());
		result = prime
				* result
				+ ((transactionStatus == null) ? 0 : transactionStatus
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
		DeviceUsage other = (DeviceUsage) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (dataUsed != other.dataUsed)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (isValid == null) {
			if (other.isValid != null)
				return false;
		} else if (!isValid.equals(other.isValid))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (monthToDateUsage != other.monthToDateUsage)
			return false;
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		if (transactionErrorReason == null) {
			if (other.transactionErrorReason != null)
				return false;
		} else if (!transactionErrorReason.equals(other.transactionErrorReason))
			return false;
		if (transactionStatus == null) {
			if (other.transactionStatus != null)
				return false;
		} else if (!transactionStatus.equals(other.transactionStatus))
			return false;
		return true;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
