package com.gv.midway.pojo.job;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;


@Document(collection = "jobDetail")
public class JobDetail {

	private JobName name;
	private JobType type;
	private String date;
	private String startTime;
	private String endTime;
	private String status;
	private String transactionFailed;
	private String transactionPassed;
	private String carrierName;
	private String ipAddress;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public JobName getName() {
		return name;
	}
	public void setName(JobName name) {
		this.name = name;
	}
	public JobType getType() {
		return type;
	}
	public void setType(JobType type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getTransactionFailed() {
		return transactionFailed;
	}
	public void setTransactionFailed(String transactionFailed) {
		this.transactionFailed = transactionFailed;
	}
	public String getTransactionPassed() {
		return transactionPassed;
	}
	public void setTransactionPassed(String transactionPassed) {
		this.transactionPassed = transactionPassed;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((transactionFailed == null) ? 0 : transactionFailed
						.hashCode());
		result = prime
				* result
				+ ((transactionPassed == null) ? 0 : transactionPassed
						.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		JobDetail other = (JobDetail) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (name != other.name)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transactionFailed == null) {
			if (other.transactionFailed != null)
				return false;
		} else if (!transactionFailed.equals(other.transactionFailed))
			return false;
		if (transactionPassed == null) {
			if (other.transactionPassed != null)
				return false;
		} else if (!transactionPassed.equals(other.transactionPassed))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "JobDetail [name=" + name + ", type=" + type + ", date=" + date
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", transactionFailed="
				+ transactionFailed + ", transactionPassed="
				+ transactionPassed + ", carrierName=" + carrierName
				+ ", ipAddress=" + ipAddress + "]";
	}

}
