package com.gv.midway.pojo.callback;

import java.util.Arrays;

public class DeviceResponse {

	private String[] usageResponse;

	public String[] getUsageResponse() {
		return usageResponse;
	}

	public void setUsageResponse(String[] usageResponse) {
		this.usageResponse = usageResponse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(usageResponse);
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
		DeviceResponse other = (DeviceResponse) obj;
		if (!Arrays.equals(usageResponse, other.usageResponse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceResponse [usageResponse="
				+ Arrays.toString(usageResponse) + "]";
	}
}
