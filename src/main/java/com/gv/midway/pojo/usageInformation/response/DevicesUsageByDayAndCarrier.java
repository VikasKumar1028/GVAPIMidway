package com.gv.midway.pojo.usageInformation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DevicesUsageByDayAndCarrier {
	
	@ApiModelProperty(value = "netSuiteId of the device")
	@JsonProperty("netSuiteId")
	private Integer netSuiteId;

	@ApiModelProperty(value = "dataUsed by the device")
	@JsonProperty("dataUsed")
	private float dataUsed;

	public Integer getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(Integer netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public float getDataUsed() {
		return dataUsed;
	}

	public void setDataUsed(float dataUsed) {
		this.dataUsed = dataUsed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(dataUsed);
		result = prime * result
				+ ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
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
		DevicesUsageByDayAndCarrier other = (DevicesUsageByDayAndCarrier) obj;
		if (Float.floatToIntBits(dataUsed) != Float
				.floatToIntBits(other.dataUsed))
			return false;
		if (netSuiteId == null) {
			if (other.netSuiteId != null)
				return false;
		} else if (!netSuiteId.equals(other.netSuiteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevicesUsageByDay [netSuiteId=");
		builder.append(netSuiteId);
		builder.append(", dataUsed=");
		builder.append(dataUsed);
		builder.append("]");
		return builder.toString();
	}
}
