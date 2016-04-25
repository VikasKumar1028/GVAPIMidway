package com.gv.midway.pojo.reActivateDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReactivateDevices {
	@ApiModelProperty(value = "Having type and value of device identifier")
	private ReactivateDeviceId[] deviceIds;

	/**
	 * @return the deviceIds
	 */
	public ReactivateDeviceId[] getDeviceIds() {
		return deviceIds;
	}

	/**
	 * @param deviceIds
	 *            the deviceIds to set
	 */
	public void setDeviceIds(ReactivateDeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(deviceIds);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReactivateDevices)) {
			return false;
		}
		ReactivateDevices other = (ReactivateDevices) obj;
		if (!Arrays.equals(deviceIds, other.deviceIds)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReactivateDevices [deviceIds=" + Arrays.toString(deviceIds) + "]";
	}
	
}
