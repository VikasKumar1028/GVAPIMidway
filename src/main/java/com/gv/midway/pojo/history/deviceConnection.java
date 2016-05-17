package com.gv.midway.pojo.history;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deviceConnection")
public class DeviceConnection {
	

	private Object devicePayload;
	private String carrierName;
	private String timestamp;
	
	
	public Object getDevicePayload() {
		return devicePayload;
	}
	public void setDevicePayload(Object devicePayload) {
		this.devicePayload = devicePayload;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "deviceConnection [devicePayload=" + devicePayload
				+ ", carrierName=" + carrierName + ", timestamp=" + timestamp
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result
				+ ((devicePayload == null) ? 0 : devicePayload.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
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
		DeviceConnection other = (DeviceConnection) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (devicePayload == null) {
			if (other.devicePayload != null)
				return false;
		} else if (!devicePayload.equals(other.devicePayload))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
