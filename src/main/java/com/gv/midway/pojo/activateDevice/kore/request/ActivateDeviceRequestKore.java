package com.gv.midway.pojo.activateDevice.kore.request;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class ActivateDeviceRequestKore {

	@ApiModelProperty(value = "The number of Device that has to be activated.")
	private String deviceNumber;
	
		@ApiModelProperty(value = "The EAP code is the Express Activation Profile to use for the activation.")
		private String EAPCode;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getEapCode() {
		return EAPCode;
	}

	public void setEapCode(String EAPCode) {
		this.EAPCode = EAPCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
		result = prime * result + ((EAPCode == null) ? 0 : EAPCode.hashCode());
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
		ActivateDeviceRequestKore other = (ActivateDeviceRequestKore) obj;
		if (deviceNumber == null) {
			if (other.deviceNumber != null)
				return false;
		} else if (!deviceNumber.equals(other.deviceNumber))
			return false;
		if (EAPCode == null) {
			if (other.EAPCode != null)
				return false;
		} else if (!EAPCode.equals(other.EAPCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateDeviceRequestKore [deviceNumber=");
		builder.append(deviceNumber);
		builder.append(", eapCode=");
		builder.append(EAPCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
