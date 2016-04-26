package com.gv.midway.pojo.connectionInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionHistory {

	@ApiModelProperty(value = "All the attributes that describe the connection event.")
	private ConnectionEvent[] connectionEventAttributes;
	
	@ApiModelProperty(value = "Currently not used.")
	private String extendedAttributes;
	
	@ApiModelProperty(value = "The date and time when the connection event occured.")
	private String occuredAt;



	public ConnectionEvent[] getConnectionEventAttributes() {
		return connectionEventAttributes;
	}

	public void setConnectionEventAttributes(
			ConnectionEvent[] connectionEventAttributes) {
		this.connectionEventAttributes = connectionEventAttributes;
	}

	public String getExtendedAttributes() {
		return extendedAttributes;
	}

	public void setExtendedAttributes(String extendedAttributes) {
		this.extendedAttributes = extendedAttributes;
	}

	public String getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(String occuredAt) {
		this.occuredAt = occuredAt;
	}
	
	@Override
	public String toString() {
		return "ConnectionHistory [connectionEventAttributes="
				+ Arrays.toString(connectionEventAttributes)
				+ ", extendedAttributes=" + extendedAttributes + ", occuredAt="
				+ occuredAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(connectionEventAttributes);
		result = prime
				* result
				+ ((extendedAttributes == null) ? 0 : extendedAttributes
						.hashCode());
		result = prime * result
				+ ((occuredAt == null) ? 0 : occuredAt.hashCode());
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
		ConnectionHistory other = (ConnectionHistory) obj;
		if (!Arrays.equals(connectionEventAttributes,
				other.connectionEventAttributes))
			return false;
		if (extendedAttributes == null) {
			if (other.extendedAttributes != null)
				return false;
		} else if (!extendedAttributes.equals(other.extendedAttributes))
			return false;
		if (occuredAt == null) {
			if (other.occuredAt != null)
				return false;
		} else if (!occuredAt.equals(other.occuredAt))
			return false;
		return true;
	}
}
