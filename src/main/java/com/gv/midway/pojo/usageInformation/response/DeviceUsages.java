package com.gv.midway.pojo.usageInformation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeviceUsages {

	@ApiModelProperty(value = "Date")
	@JsonProperty("date")
	private String date;

	@ApiModelProperty(value = "dataUsed")
	@JsonProperty("dataUsed")
	private long dataUsed;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(dataUsed);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		DeviceUsages other = (DeviceUsages) obj;
		if (Float.floatToIntBits(dataUsed) != Float
				.floatToIntBits(other.dataUsed))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getDataUsed() {
		return dataUsed;
	}

	public void setDataUsed(long dataUsed) {
		this.dataUsed = dataUsed;
	}

	@Override
	public String toString() {
		return "DevicesDateBasedUsage [date=" + date + ", dataUsed=" + dataUsed
				+ "]";
	}

}
