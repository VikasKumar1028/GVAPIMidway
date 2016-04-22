package com.gv.midway.pojo.connectionInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationResponseDataArea {

	
	@ApiModelProperty(value = "Device connection evnents.")
	private ConnectionHistory connectionHistory;

	@ApiModelProperty(value = "Indicates that there is more data to be retrieved.")
	private String hasMoreData;
	
	
	
	public ConnectionHistory getConnectionHistory() {
		return connectionHistory;
	}

	public void setConnectionHistory(ConnectionHistory connectionHistory) {
		this.connectionHistory = connectionHistory;
	}

	public String getHasMoreData() {
		return hasMoreData;
	}

	public void setHasMoreData(String hasMoreData) {
		this.hasMoreData = hasMoreData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((connectionHistory == null) ? 0 : connectionHistory
						.hashCode());
		result = prime * result
				+ ((hasMoreData == null) ? 0 : hasMoreData.hashCode());
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
		ConnectionInformationResponseDataArea other = (ConnectionInformationResponseDataArea) obj;
		if (connectionHistory == null) {
			if (other.connectionHistory != null)
				return false;
		} else if (!connectionHistory.equals(other.connectionHistory))
			return false;
		if (hasMoreData == null) {
			if (other.hasMoreData != null)
				return false;
		} else if (!hasMoreData.equals(other.hasMoreData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConnectionInformationResponseDataArea [connectionHistory="
				+ connectionHistory + ", hasMoreData=" + hasMoreData + "]";
	}


	
	
	
}
