package com.gv.midway.pojo.activateDevice.kore.request;

public class ActivateDeviceRequestKore {

	private String deviceNumber;
	
	private String eapCode;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getEapCode() {
		return eapCode;
	}

	public void setEapCode(String eapCode) {
		this.eapCode = eapCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
		result = prime * result + ((eapCode == null) ? 0 : eapCode.hashCode());
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
		if (eapCode == null) {
			if (other.eapCode != null)
				return false;
		} else if (!eapCode.equals(other.eapCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateDeviceRequestKore [deviceNumber=");
		builder.append(deviceNumber);
		builder.append(", eapCode=");
		builder.append(eapCode);
		builder.append("]");
		return builder.toString();
	}
	
	
}
