package com.gv.midway.pojo.job;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobinitializedResponseDataArea {

	@Override
	public String toString() {
		return "JobinitializedResponseDataArea [jobStatus=" + jobStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jobStatus == null) ? 0 : jobStatus.hashCode());
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
		JobinitializedResponseDataArea other = (JobinitializedResponseDataArea) obj;
		if (jobStatus == null) {
			if (other.jobStatus != null)
				return false;
		} else if (!jobStatus.equals(other.jobStatus))
			return false;
		return true;
	}

	@ApiModelProperty(value = "JobInitialized Status" )
	private String jobStatus;

	public String getJobInitializedStaus() {
		return jobStatus;
	}

	public void setJobInitializedStaus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

}
