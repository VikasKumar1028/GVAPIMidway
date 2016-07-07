package com.gv.midway.pojo.connectionInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionEventMidway {

	private DeviceEvents[] event;

	public DeviceEvents[] getEvent() {
		return event;
	}

	public void setEvent(DeviceEvents[] event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "ConnectionEventMidway [event=" + Arrays.toString(event) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(event);
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
		ConnectionEventMidway other = (ConnectionEventMidway) obj;
		if (!Arrays.equals(event, other.event))
			return false;
		return true;
	}

}
