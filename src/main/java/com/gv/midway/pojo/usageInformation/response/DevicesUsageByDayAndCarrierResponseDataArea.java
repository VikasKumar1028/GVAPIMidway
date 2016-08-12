package com.gv.midway.pojo.usageInformation.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class DevicesUsageByDayAndCarrierResponseDataArea {

	@ApiModelProperty(value = "date for the data usages resposne.")
	@JsonProperty("date")
	private String date;
	
	@ApiModelProperty(value = "devices with usage for the day.")
	@JsonProperty("devices")
	private List<DevicesUsageByDayAndCarrier> devices;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<DevicesUsageByDayAndCarrier> getDevices() {
		return devices;
	}

	public void setDevices(List<DevicesUsageByDayAndCarrier> devices) {
		this.devices = devices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((devices == null) ? 0 : devices.hashCode());
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
		DevicesUsageByDayAndCarrierResponseDataArea other = (DevicesUsageByDayAndCarrierResponseDataArea) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (devices == null) {
			if (other.devices != null)
				return false;
		} else if (!devices.equals(other.devices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevicesUsageByDayResponseDataArea [date=");
		builder.append(date);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
