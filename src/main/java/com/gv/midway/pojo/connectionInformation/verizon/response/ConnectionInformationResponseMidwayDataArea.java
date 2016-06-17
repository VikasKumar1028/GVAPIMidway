package com.gv.midway.pojo.connectionInformation.verizon.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationResponseMidwayDataArea {

	@ApiModelProperty(value = "Total Device Usages")
	@JsonProperty("totalUsages")
	private Long totalUsages;

	public Long getTotalUsages() {
		return totalUsages;
	}

	public void setTotalUsages(Long totalUsages) {
		this.totalUsages = totalUsages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((totalUsages == null) ? 0 : totalUsages.hashCode());
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
		ConnectionInformationResponseMidwayDataArea other = (ConnectionInformationResponseMidwayDataArea) obj;
		if (totalUsages == null) {
			if (other.totalUsages != null)
				return false;
		} else if (!totalUsages.equals(other.totalUsages))
			return false;
		return true;
	}

}
