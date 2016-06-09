package com.gv.midway.pojo.usageInformation.verizon.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationResponseDataArea  extends BaseResponse{

	@ApiModelProperty(value = "Total Device Usages")
	private long totalUsages;

	public long getTotalUsages() {
		return totalUsages;
	}

	public void setTotalUsages(long totalUsages) {
		this.totalUsages = totalUsages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (totalUsages ^ (totalUsages >>> 32));
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
		UsageInformationResponseDataArea other = (UsageInformationResponseDataArea) obj;
		if (totalUsages != other.totalUsages)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsageInformationResponseDataArea [totalUsages=" + totalUsages
				+ "]";
	}

}
