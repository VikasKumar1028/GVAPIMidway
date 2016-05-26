package com.gv.midway.job;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;

/**
 * @author SG00421138
 *
 */
@Document(collection = "jobDetail")
public class JobDetail {

	private JobName name;
	private JobType type;
	private String date;
	private String startTime;
	private String endTime;
	private String status;
	private String transationFailed;
	private String transationPassed;
	private String carrierName;
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
	public String getTransationFailed() {
		return transationFailed;
	}
	public void setTransationFailed(String transationFailed) {
		this.transationFailed = transationFailed;
	}
	public String getTransationPassed() {
		return transationPassed;
	}
	public void setTransationPassed(String transationPassed) {
		this.transationPassed = transationPassed;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((transationFailed == null) ? 0 : transationFailed.hashCode());
		result = prime
				* result
				+ ((transationPassed == null) ? 0 : transationPassed.hashCode());
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
		if (transationFailed == null) {
			if (other.transationFailed != null)
				return false;
		} else if (!transationFailed.equals(other.transationFailed))
			return false;
		if (transationPassed == null) {
			if (other.transationPassed != null)
				return false;
		} else if (!transationPassed.equals(other.transationPassed))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "JobDetail [name=" + name + ", type=" + type + ", date=" + date
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", transationFailed="
				+ transationFailed + ", transationPassed=" + transationPassed
				+ ", carrierName=" + carrierName + "]";
	}

	
}
