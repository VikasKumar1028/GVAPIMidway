package com.gv.midway.pojo.deactivateDevice.kore.request;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestKore {

   @ApiModelProperty(value = "The number of Device that has to be activated.", required = true)
   private String deviceNumber;
	@ApiModelProperty(value = "KORE : A flag indicating whether to scrap the device or just deactivate it to stock." , required = true)
	private Boolean flagScrap;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Boolean getFlagScrap() {
		return flagScrap;
	}

	public void setFlagScrap(Boolean flagScrap) {
		this.flagScrap = flagScrap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeactivateDeviceRequestKore [deviceNumber=");
		builder.append(deviceNumber);
		builder.append(", flagScrap=");
		builder.append(flagScrap);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
		result = prime * result
				+ ((flagScrap == null) ? 0 : flagScrap.hashCode());
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
		DeactivateDeviceRequestKore other = (DeactivateDeviceRequestKore) obj;
		if (deviceNumber == null) {
			if (other.deviceNumber != null)
				return false;
		} else if (!deviceNumber.equals(other.deviceNumber))
			return false;
		if (flagScrap == null) {
			if (other.flagScrap != null)
				return false;
		} else if (!flagScrap.equals(other.flagScrap))
			return false;
		return true;
	}
	

	

}
