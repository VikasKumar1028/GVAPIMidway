package com.gv.midway.pojo.connectionInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ConnectionStatusResponseDataArea {

	@ApiModelProperty(value = "Device in session or not" )
	private String connectionStatus;



	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String status) {
		this.connectionStatus = status;
	}
	
	@Override
	public String toString() {
		return "ConnectionStatusResponseDataArea [status=" + connectionStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionStatus == null) ? 0 : connectionStatus.hashCode());
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
		ConnectionStatusResponseDataArea other = (ConnectionStatusResponseDataArea) obj;
		if (connectionStatus == null) {
			if (other.connectionStatus != null)
				return false;
		} else if (!connectionStatus.equals(other.connectionStatus))
			return false;
		return true;
	}
}
