package com.gv.midway.pojo.deactivateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceResponse extends BaseResponse {
	
	private String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "DeactivateDeviceResponse [requestId=" + requestId + "]";
	}
	
	
}
