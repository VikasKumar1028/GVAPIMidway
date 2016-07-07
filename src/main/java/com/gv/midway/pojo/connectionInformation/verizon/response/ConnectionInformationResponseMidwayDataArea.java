package com.gv.midway.pojo.connectionInformation.verizon.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationResponseMidwayDataArea {

	private ConnectionEventMidway connectionEventAttributes;

	public ConnectionEventMidway getConnectionEventAttributes() {
		return connectionEventAttributes;
	}

	public void setConnectionEventAttributes(
			ConnectionEventMidway connectionEventAttributes) {
		this.connectionEventAttributes = connectionEventAttributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((connectionEventAttributes == null) ? 0
						: connectionEventAttributes.hashCode());
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
		ConnectionInformationResponseMidwayDataArea other = (ConnectionInformationResponseMidwayDataArea) obj;
		if (connectionEventAttributes == null) {
			if (other.connectionEventAttributes != null)
				return false;
		} else if (!connectionEventAttributes
				.equals(other.connectionEventAttributes))
			return false;
		return true;
	}

}
