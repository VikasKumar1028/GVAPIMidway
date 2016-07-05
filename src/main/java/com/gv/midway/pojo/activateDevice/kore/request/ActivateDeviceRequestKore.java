package com.gv.midway.pojo.activateDevice.kore.request;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wordnik.swagger.annotations.ApiModelProperty;

@SuppressWarnings("unused")
@JsonAutoDetect()
public class ActivateDeviceRequestKore {

	@ApiModelProperty(value = "The number of Device that has to be activated.")
	private String deviceNumber;
	
		@ApiModelProperty(value = "KORE , The EAP code is the Express Activation Profile to use for the activation.", required = true)
		@JsonProperty("EAPCode")
		private String EAPCode;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@SuppressWarnings("unchecked")
	@JsonSerialize
	@JsonProperty("EAPCode")
	public String getEAPCode() {
		return EAPCode;
	}

	@SuppressWarnings("unchecked")
	@JsonDeserialize
	@JsonProperty("EAPCode")
	public void setEAPCode(String EAPCode) {
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
		builder.append(", EAPCode=");
		builder.append(EAPCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
