package com.gv.midway.pojo.transaction;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;

@Document(collection = "transactionalDetail")
public class Transaction {

	String midwayTransationID;
	String carrierTransationID;
	String deviceNumber;
	String devicePayload;
	String midwayStatus;
	String carrierStatus;
	String carrierName;
	String TimeStampReceived;
	String LastTimeStampUpdated;
	String carrierErrorDecription;
	String requestType;
	String callBackPayload;
	Boolean callBackDelivered;
	String auditTransationID;
	Boolean callBackReceived;
	String callBackFailureToNetSuitReason;
	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMidwayTransationID() {
		return midwayTransationID;
	}

	public void setMidwayTransationID(String midwayTransationID) {
		this.midwayTransationID = midwayTransationID;
	}

	public String getCarrierTransationID() {
		return carrierTransationID;
	}

	public void setCarrierTransationID(String carrierTransationID) {
		this.carrierTransationID = carrierTransationID;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDevicePayload() {
		return devicePayload;
	}

	public void setDevicePayload(String devicePayload) {
		this.devicePayload = devicePayload;
	}

	public String getMidwayStatus() {
		return midwayStatus;
	}

	public void setMidwayStatus(String midwayStatus) {
		this.midwayStatus = midwayStatus;
	}

	public String getCarrierStatus() {
		return carrierStatus;
	}

	public void setCarrierStatus(String carrierStatus) {
		this.carrierStatus = carrierStatus;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getTimeStampReceived() {
		return TimeStampReceived;
	}

	public void setTimeStampReceived(String timeStampReceived) {
		TimeStampReceived = timeStampReceived;
	}

	public String getLastTimeStampUpdated() {
		return LastTimeStampUpdated;
	}

	public void setLastTimeStampUpdated(String lastTimeStampUpdated) {
		LastTimeStampUpdated = lastTimeStampUpdated;
	}

	public String getCarrierErrorDecription() {
		return carrierErrorDecription;
	}

	public void setCarrierErrorDecription(String carrierErrorDecription) {
		this.carrierErrorDecription = carrierErrorDecription;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getCallBackPayload() {
		return callBackPayload;
	}

	public void setCallBackPayload(String callBackPayload) {
		this.callBackPayload = callBackPayload;
	}

	public String getAuditTransationID() {
		return auditTransationID;
	}

	public void setAuditTransationID(String auditTransationID) {
		this.auditTransationID = auditTransationID;
	}

	public String getCallBackFailureToNetSuitReason() {
		return callBackFailureToNetSuitReason;
	}

	public void setCallBackFailureToNetSuitReason(
			String callBackFailureToNetSuitReason) {
		this.callBackFailureToNetSuitReason = callBackFailureToNetSuitReason;
	}

	public Boolean getCallBackDelivered() {
		return callBackDelivered;
	}

	public void setCallBackDelivered(Boolean callBackDelivered) {
		this.callBackDelivered = callBackDelivered;
	}

	public Boolean getCallBackReceived() {
		return callBackReceived;
	}

	public void setCallBackReceived(Boolean callBackReceived) {
		this.callBackReceived = callBackReceived;
	}

	@Override
	public String toString() {
		return "Transaction [midwayTransationID=" + midwayTransationID
				+ ", carrierTransationID=" + carrierTransationID
				+ ", deviceNumber=" + deviceNumber + ", devicePayload="
				+ devicePayload + ", midwayStatus=" + midwayStatus
				+ ", carrierStatus=" + carrierStatus + ", carrierName="
				+ carrierName + ", TimeStampReceived=" + TimeStampReceived
				+ ", LastTimeStampUpdated=" + LastTimeStampUpdated
				+ ", carrierErrorDecription=" + carrierErrorDecription
				+ ", requestType=" + requestType + ", callBackPayload="
				+ callBackPayload + ", callBackDelivered=" + callBackDelivered
				+ ", auditTransationID=" + auditTransationID
				+ ", callBackReceived=" + callBackReceived
				+ ", callBackFailureToNetSuitReason="
				+ callBackFailureToNetSuitReason + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((LastTimeStampUpdated == null) ? 0 : LastTimeStampUpdated
						.hashCode());
		result = prime
				* result
				+ ((TimeStampReceived == null) ? 0 : TimeStampReceived
						.hashCode());
		result = prime
				* result
				+ ((auditTransationID == null) ? 0 : auditTransationID
						.hashCode());
		result = prime
				* result
				+ ((callBackDelivered == null) ? 0 : callBackDelivered
						.hashCode());
		result = prime
				* result
				+ ((callBackFailureToNetSuitReason == null) ? 0
						: callBackFailureToNetSuitReason.hashCode());
		result = prime * result
				+ ((callBackPayload == null) ? 0 : callBackPayload.hashCode());
		result = prime
				* result
				+ ((callBackReceived == null) ? 0 : callBackReceived.hashCode());
		result = prime
				* result
				+ ((carrierErrorDecription == null) ? 0
						: carrierErrorDecription.hashCode());
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result
				+ ((carrierStatus == null) ? 0 : carrierStatus.hashCode());
		result = prime
				* result
				+ ((carrierTransationID == null) ? 0 : carrierTransationID
						.hashCode());
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
		result = prime * result
				+ ((devicePayload == null) ? 0 : devicePayload.hashCode());
		result = prime * result
				+ ((midwayStatus == null) ? 0 : midwayStatus.hashCode());
		result = prime
				* result
				+ ((midwayTransationID == null) ? 0 : midwayTransationID
						.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (LastTimeStampUpdated == null) {
			if (other.LastTimeStampUpdated != null)
				return false;
		} else if (!LastTimeStampUpdated.equals(other.LastTimeStampUpdated))
			return false;
		if (TimeStampReceived == null) {
			if (other.TimeStampReceived != null)
				return false;
		} else if (!TimeStampReceived.equals(other.TimeStampReceived))
			return false;
		if (auditTransationID == null) {
			if (other.auditTransationID != null)
				return false;
		} else if (!auditTransationID.equals(other.auditTransationID))
			return false;
		if (callBackDelivered == null) {
			if (other.callBackDelivered != null)
				return false;
		} else if (!callBackDelivered.equals(other.callBackDelivered))
			return false;
		if (callBackFailureToNetSuitReason == null) {
			if (other.callBackFailureToNetSuitReason != null)
				return false;
		} else if (!callBackFailureToNetSuitReason
				.equals(other.callBackFailureToNetSuitReason))
			return false;
		if (callBackPayload == null) {
			if (other.callBackPayload != null)
				return false;
		} else if (!callBackPayload.equals(other.callBackPayload))
			return false;
		if (callBackReceived == null) {
			if (other.callBackReceived != null)
				return false;
		} else if (!callBackReceived.equals(other.callBackReceived))
			return false;
		if (carrierErrorDecription == null) {
			if (other.carrierErrorDecription != null)
				return false;
		} else if (!carrierErrorDecription.equals(other.carrierErrorDecription))
			return false;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (carrierStatus == null) {
			if (other.carrierStatus != null)
				return false;
		} else if (!carrierStatus.equals(other.carrierStatus))
			return false;
		if (carrierTransationID == null) {
			if (other.carrierTransationID != null)
				return false;
		} else if (!carrierTransationID.equals(other.carrierTransationID))
			return false;
		if (deviceNumber == null) {
			if (other.deviceNumber != null)
				return false;
		} else if (!deviceNumber.equals(other.deviceNumber))
			return false;
		if (devicePayload == null) {
			if (other.devicePayload != null)
				return false;
		} else if (!devicePayload.equals(other.devicePayload))
			return false;
		if (midwayStatus == null) {
			if (other.midwayStatus != null)
				return false;
		} else if (!midwayStatus.equals(other.midwayStatus))
			return false;
		if (midwayTransationID == null) {
			if (other.midwayTransationID != null)
				return false;
		} else if (!midwayTransationID.equals(other.midwayTransationID))
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		return true;
	}

}
