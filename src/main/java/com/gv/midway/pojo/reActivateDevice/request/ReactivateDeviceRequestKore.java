package com.gv.midway.pojo.reActivateDevice.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.wordnik.swagger.annotations.ApiModelProperty;

@SuppressWarnings("unused")
@JsonAutoDetect()
public class ReactivateDeviceRequestKore {
	@ApiModelProperty(value = "The number of Device that has to be activated.")
	private String deviceNumber;

	/**
	 * @return the deviceNumber
	 */
	public String getDeviceNumber() {
		return deviceNumber;
	}

	/**
	 * @param deviceNumber the deviceNumber to set
	 */
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
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
		if (!(obj instanceof ReactivateDeviceRequestKore)) {
			return false;
		}
		ReactivateDeviceRequestKore other = (ReactivateDeviceRequestKore) obj;
		if (deviceNumber == null) {
			if (other.deviceNumber != null) {
				return false;
			}
		} else if (!deviceNumber.equals(other.deviceNumber)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReactivateDeviceRequestKore [deviceNumber=" + deviceNumber + "]";
	}
	
	
}
