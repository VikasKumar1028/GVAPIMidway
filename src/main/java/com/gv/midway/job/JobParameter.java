package com.gv.midway.job;

import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;

public class JobParameter {

	private String date;
	private JobName jobName;
	private JobType jobType;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public JobName getJobName() {
		return jobName;
	}
	public void setJobName(JobName jobName) {
		this.jobName = jobName;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
		result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
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
		JobParameter other = (JobParameter) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (jobName != other.jobName)
			return false;
		if (jobType != other.jobType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "JobParameter [date=" + date + ", jobName=" + jobName
				+ ", jobType=" + jobType + "]";
	}
	

}
