package com.gv.midway.pojo.restoreDevice.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestoreDeviceResponseDataArea{
	
	@ApiModelProperty(value = "Request Id is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric" )
	private String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "RestoreDeviceResponseDataArea [requestId=" + requestId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
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
		RestoreDeviceResponseDataArea other = (RestoreDeviceResponseDataArea) obj;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		return true;
	}
	
	
	
}