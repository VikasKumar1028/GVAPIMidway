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
    private String transactionCount;
    private String transactionFailed;
    private String transactionPassed;
    private String carrierName;
    private String ipAddress;
    private String jobId;
    private String period;

    public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

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
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((transactionCount == null) ? 0 : transactionCount.hashCode());
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
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (name != other.name)
			return false;
		if (period != other.period)
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
		if (transactionCount == null) {
			if (other.transactionCount != null)
				return false;
		} else if (!transactionCount.equals(other.transactionCount))
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
		StringBuilder builder = new StringBuilder();
		builder.append("JobDetail [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", date=");
		builder.append(date);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", transactionCount=");
		builder.append(transactionCount);
		builder.append(", transactionFailed=");
		builder.append(transactionFailed);
		builder.append(", transactionPassed=");
		builder.append(transactionPassed);
		builder.append(", carrierName=");
		builder.append(carrierName);
		builder.append(", ipAddress=");
		builder.append(ipAddress);
		builder.append(", jobId=");
		builder.append(jobId);
		builder.append(", period=");
		builder.append(period);
		builder.append("]");
		return builder.toString();
	}

	public String getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(String transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
