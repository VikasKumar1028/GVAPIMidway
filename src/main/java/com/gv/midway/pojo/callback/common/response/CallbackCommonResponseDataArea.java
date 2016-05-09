package com.gv.midway.pojo.callback.common.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.gv.midway.constant.RequestType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackCommonResponseDataArea {


	@ApiModelProperty(value = "Having type and value of device identifier.")
	private DeviceId[] deviceIds;
	
/*	@ApiModelProperty(value = "Transaction Id.")
	private String transactionId;*/
	
	@ApiModelProperty(value = "Midway Transaction Id.")
	private String requestId;
	
	@ApiModelProperty(value = "Type of Request - Activation, Deactivation etc.")
	private RequestType requestType;
	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	@ApiModelProperty(value = "Explains whether transaction is processed successfully or not.")
	private Boolean RequestStatus;
	
	
	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

/*	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}*/

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public Boolean getRequestStatus() {
		return RequestStatus;
	}

	public void setRequestStatus(Boolean requestStatus) {
		RequestStatus = requestStatus;
	}

	@Override
	public String toString() {
		return "CallbackCommonResponseDataArea [deviceIds="
				+ Arrays.toString(deviceIds) /*+ ", transactionId="
				+ transactionId*/ + ", requestId=" + requestId + ", requestType="
				+ requestType + ", RequestStatus=" + RequestStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((RequestStatus == null) ? 0 : RequestStatus.hashCode());
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
		/*result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());*/
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
		CallbackCommonResponseDataArea other = (CallbackCommonResponseDataArea) obj;
		if (RequestStatus == null) {
			if (other.RequestStatus != null)
				return false;
		} else if (!RequestStatus.equals(other.RequestStatus))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (requestType != other.requestType)
			return false;
		/*if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;*/
		return true;
	}
	
}
