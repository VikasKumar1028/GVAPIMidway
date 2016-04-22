package com.gv.midway.pojo.connectionInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.Devices;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationRequestDataArea {
	

	@ApiModelProperty(value = "All identifiers for the device.")
	private Devices devices;
	
	@ApiModelProperty(value = "Start Date")
	private String earliest;
	
	@ApiModelProperty(value = "End Date")
	private String latest;
	
	

	public Devices getDevices() {
		return devices;
	}

	public void setDevices(Devices devices) {
		this.devices = devices;
	}

	public String getEarliest() {
		return earliest;
	}

	public void setEarliest(String earliest) {
		this.earliest = earliest;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((devices == null) ? 0 : devices.hashCode());
		result = prime * result
				+ ((earliest == null) ? 0 : earliest.hashCode());
		result = prime * result + ((latest == null) ? 0 : latest.hashCode());
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
		ConnectionInformationRequestDataArea other = (ConnectionInformationRequestDataArea) obj;
		if (devices == null) {
			if (other.devices != null)
				return false;
		} else if (!devices.equals(other.devices))
			return false;
		if (earliest == null) {
			if (other.earliest != null)
				return false;
		} else if (!earliest.equals(other.earliest))
			return false;
		if (latest == null) {
			if (other.latest != null)
				return false;
		} else if (!latest.equals(other.latest))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConnectionInformationRequestDataArea [devices=" + devices
				+ ", earliest=" + earliest + ", latest=" + latest + "]";
	}
	
	
	
}

