package com.gv.midway.pojo.usageInformation.verizon.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationResponseDataArea  extends BaseResponse{

	@ApiModelProperty(value = "Total Device Usages")
	@JsonProperty("totalUsages")
	private String totalUsages;

	

	public String getTotalUsages() {
		return totalUsages;
	}

	public void setTotalUsages(String totalUsages) {
		this.totalUsages = totalUsages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((totalUsages == null) ? 0 : totalUsages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsageInformationResponseDataArea other = (UsageInformationResponseDataArea) obj;
		if (totalUsages == null) {
			if (other.totalUsages != null)
				return false;
		} else if (!totalUsages.equals(other.totalUsages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsageInformationResponseDataArea [totalUsages=" + totalUsages
				+ "]";
	}

}
