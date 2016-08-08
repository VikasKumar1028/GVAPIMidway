package com.gv.midway.pojo.usageInformation.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UsageInformationResponseMidwayDataArea {

	@Override
	public String toString() {
		return "UsageInformationResponseMidwayDataArea [deviceUsages="
				+ deviceUsages + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceUsages == null) ? 0 : deviceUsages.hashCode());
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
		UsageInformationResponseMidwayDataArea other = (UsageInformationResponseMidwayDataArea) obj;
		if (deviceUsages == null) {
			if (other.deviceUsages != null)
				return false;
		} else if (!deviceUsages.equals(other.deviceUsages))
			return false;
		return true;
	}

	public List<DeviceUsages> getDeviceUsages() {
		return deviceUsages;
	}

	public void setDeviceUsages(List<DeviceUsages> deviceUsages) {
		this.deviceUsages = deviceUsages;
	}

	private List<DeviceUsages> deviceUsages;

}
