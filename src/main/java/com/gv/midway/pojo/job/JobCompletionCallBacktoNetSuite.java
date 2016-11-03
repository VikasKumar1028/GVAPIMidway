package com.gv.midway.pojo.job;



public class JobCompletionCallBacktoNetSuite {

	private String jobDate;
	
	private String jobType;
	
	private String bsCarrier;
	
	private String period;
	
	private String successCount;
	
	private String errorCount;

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getBsCarrier() {
		return bsCarrier;
	}

	public void setBsCarrier(String bsCarrier) {
		this.bsCarrier = bsCarrier;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}

	public String getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bsCarrier == null) ? 0 : bsCarrier.hashCode());
		result = prime * result
				+ ((errorCount == null) ? 0 : errorCount.hashCode());
		result = prime * result + ((jobDate == null) ? 0 : jobDate.hashCode());
		result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result
				+ ((successCount == null) ? 0 : successCount.hashCode());
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
		JobCompletionCallBacktoNetSuite other = (JobCompletionCallBacktoNetSuite) obj;
		if (bsCarrier == null) {
			if (other.bsCarrier != null)
				return false;
		} else if (!bsCarrier.equals(other.bsCarrier))
			return false;
		if (errorCount == null) {
			if (other.errorCount != null)
				return false;
		} else if (!errorCount.equals(other.errorCount))
			return false;
		if (jobDate == null) {
			if (other.jobDate != null)
				return false;
		} else if (!jobDate.equals(other.jobDate))
			return false;
		if (jobType == null) {
			if (other.jobType != null)
				return false;
		} else if (!jobType.equals(other.jobType))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (successCount == null) {
			if (other.successCount != null)
				return false;
		} else if (!successCount.equals(other.successCount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobCompletionCallBacktoNetSuite [jobDate=");
		builder.append(jobDate);
		builder.append(", jobType=");
		builder.append(jobType);
		builder.append(", bsCarrier=");
		builder.append(bsCarrier);
		builder.append(", period=");
		builder.append(period);
		builder.append(", successCount=");
		builder.append(successCount);
		builder.append(", errorCount=");
		builder.append(errorCount);
		builder.append("]");
		return builder.toString();
	}
}
