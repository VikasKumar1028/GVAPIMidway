package com.gv.midway.pojo.deviceHistory;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.pojo.verizon.DeviceId;

@Document(collection = "deviceUsage")
public class DeviceUsage {

	private DeviceId deviceId;
	private String netSuiteId;
	private String timestamp;
	private float dataUsed;
	private String transactionStatus;
	private String transactionErrorReason;
	private Boolean isValid;


	public DeviceId getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}
	public String getNetSuiteId() {
		return netSuiteId;
	}
	public void setNetSuiteId(String netSuiteId) {
		this.netSuiteId = netSuiteId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public float getDataUsed() {
		return dataUsed;
	}
	public void setDataUsed(float dataUsed) {
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
	
	@Override
	public String toString() {
		return "DeviceUsage [deviceId=" + deviceId + ", netSuiteId="
				+ netSuiteId + ", timestamp=" + timestamp + ", dataUsed="
				+ dataUsed + ", transactionStatus=" + transactionStatus
				+ ", transactionErrorReason=" + transactionErrorReason
				+ ", isValid=" + isValid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(dataUsed);
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
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
		if (Float.floatToIntBits(dataUsed) != Float
				.floatToIntBits(other.dataUsed))
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
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
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
}
