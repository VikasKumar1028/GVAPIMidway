package com.gv.midway.pojo.callback.common.response;

import java.util.Arrays;

import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CallbackCommonResponseDataArea {

	@ApiModelProperty(value = "Having type and value of device identifier.")
	private DeviceId[] deviceIds;
	
	@ApiModelProperty(value = "Transaction Id.")
	private String transactionId;
	
	@ApiModelProperty(value = "Midway Transaction Id.")
	private String midwayTransactionId;
	
	@ApiModelProperty(value = "Type of Request - Activation, Deactivation etc.")
	private String RequestType;
	
	@ApiModelProperty(value = "Explains whether transaction is processed successfully or not.")
	private String RequestStatus;
	
	
	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMidwayTransactionId() {
		return midwayTransactionId;
	}

	public void setMidwayTransactionId(String midwayTransactionId) {
		this.midwayTransactionId = midwayTransactionId;
	}

	public String getRequestType() {
		return RequestType;
	}

	public void setRequestType(String requestType) {
		RequestType = requestType;
	}

	public String getRequestStatus() {
		return RequestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		RequestStatus = requestStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((RequestStatus == null) ? 0 : RequestStatus.hashCode());
		result = prime * result
				+ ((RequestType == null) ? 0 : RequestType.hashCode());
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime
				* result
				+ ((midwayTransactionId == null) ? 0 : midwayTransactionId
						.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
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
		if (RequestType == null) {
			if (other.RequestType != null)
				return false;
		} else if (!RequestType.equals(other.RequestType))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (midwayTransactionId == null) {
			if (other.midwayTransactionId != null)
				return false;
		} else if (!midwayTransactionId.equals(other.midwayTransactionId))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CallbackCommonResponseDataArea [deviceIds="
				+ Arrays.toString(deviceIds) + ", transactionId="
				+ transactionId + ", midwayTransactionId="
				+ midwayTransactionId + ", RequestType=" + RequestType
				+ ", RequestStatus=" + RequestStatus + "]";
	}

	
}
