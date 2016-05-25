package com.gv.midway.pojo.usageInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationResponse {

	
	@ApiModelProperty(value = "Device connection evnents.")
	private UsageHistory[] usageHistory;

	@ApiModelProperty(value = "Indicates that there is more data to be retrieved.")
	private String hasMoreData;

	public UsageHistory[] getUsageHistory() {
		return usageHistory;
	}

	public void setUsageHistory(UsageHistory[] usageHistory) {
		this.usageHistory = usageHistory;
	}

	public String getHasMoreData() {
		return hasMoreData;
	}

	public void setHasMoreData(String hasMoreData) {
		this.hasMoreData = hasMoreData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hasMoreData == null) ? 0 : hasMoreData.hashCode());
		result = prime * result + Arrays.hashCode(usageHistory);
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
		UsageInformationResponse other = (UsageInformationResponse) obj;
		if (hasMoreData == null) {
			if (other.hasMoreData != null)
				return false;
		} else if (!hasMoreData.equals(other.hasMoreData))
			return false;
		if (!Arrays.equals(usageHistory, other.usageHistory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsageInformationResponse [usageHistory="
				+ Arrays.toString(usageHistory) + ", hasMoreData="
				+ hasMoreData + "]";
	}


	
	


	


	
	
	
}
