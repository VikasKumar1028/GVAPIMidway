package com.gv.midway.pojo.usageInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationRequestMidwayDataArea {

	@ApiModelProperty(value = "Device NetSuite Id")
	private Integer netSuiteId;

	@ApiModelProperty(value = "Start Date")
	private String startDate;

	@ApiModelProperty(value = "End Date")
	private String endDate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		UsageInformationRequestMidwayDataArea other = (UsageInformationRequestMidwayDataArea) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsageInformationRequestMidwayDataArea [netSuiteId="
				+ netSuiteId + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

	public Integer getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(Integer netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
