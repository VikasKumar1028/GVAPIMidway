package com.gv.midway.pojo.transaction;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactionalDetail")
public class Transaction {

	private String midwayTransactionID; 
	private String carrierTransactionID;
	private Object deviceNumber;
	private Object devicePayload;
	private String midwayStatus;
	private String carrierStatus;
	private String carrierName;
	private String timeStampReceived;
	private String lastTimeStampUpdated;
	private String carrierErrorDescription;
	private String requestType;
	private Object callBackPayload;
	private Boolean callBackDelivered;
	private String auditTransationID;
	private Boolean callBackReceived;
	private String callBackFailureToNetSuitReason;

	public Transaction() {
		super();
	}

	public String getMidwayTransactionID() {
		return midwayTransactionID;
	}

	public void setMidwayTransactionID(String midwayTransactionID) {
		this.midwayTransactionID = midwayTransactionID;
	}

	public String getCarrierTransactionID() {
		return carrierTransactionID;
	}

	public void setCarrierTransactionID(String carrierTransactionID) {
		this.carrierTransactionID = carrierTransactionID;
	}

	public Object getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(Object deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Object getDevicePayload() {
		return devicePayload;
	}

	public void setDevicePayload(Object devicePayload) {
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

	public String getCarrierErrorDescription() {
		return carrierErrorDescription;
	}

	public void setCarrierErrorDescription(String carrierErrorDescription) {
		this.carrierErrorDescription = carrierErrorDescription;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Object getCallBackPayload() {
		return callBackPayload;
	}

	public void setCallBackPayload(Object callBackPayload) {
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
		return "Transaction [midwayTransactionID=" + midwayTransactionID
				+ ", carrierTransactionID=" + carrierTransactionID
				+ ", deviceNumber=" + deviceNumber + ", devicePayload="
				+ devicePayload + ", midwayStatus=" + midwayStatus
				+ ", carrierStatus=" + carrierStatus + ", carrierName="
				+ carrierName + ", timeStampReceived=" + timeStampReceived
				+ ", lastTimeStampUpdated=" + lastTimeStampUpdated
				+ ", carrierErrorDescription=" + carrierErrorDescription
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
				+ ((carrierErrorDescription == null) ? 0
						: carrierErrorDescription.hashCode());
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result
				+ ((carrierStatus == null) ? 0 : carrierStatus.hashCode());
		result = prime
				* result
				+ ((carrierTransactionID == null) ? 0 : carrierTransactionID
						.hashCode());
		result = prime * result
				+ ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
		result = prime * result
				+ ((devicePayload == null) ? 0 : devicePayload.hashCode());
		result = prime
				* result
				+ ((lastTimeStampUpdated == null) ? 0 : lastTimeStampUpdated
						.hashCode());
		result = prime * result
				+ ((midwayStatus == null) ? 0 : midwayStatus.hashCode());
		result = prime
				* result
				+ ((midwayTransactionID == null) ? 0 : midwayTransactionID
						.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
		result = prime
				* result
				+ ((timeStampReceived == null) ? 0 : timeStampReceived
						.hashCode());
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
		if (carrierErrorDescription == null) {
			if (other.carrierErrorDescription != null)
				return false;
		} else if (!carrierErrorDescription.equals(other.carrierErrorDescription))
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
		if (carrierTransactionID == null) {
			if (other.carrierTransactionID != null)
				return false;
		} else if (!carrierTransactionID.equals(other.carrierTransactionID))
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
		if (lastTimeStampUpdated == null) {
			if (other.lastTimeStampUpdated != null)
				return false;
		} else if (!lastTimeStampUpdated.equals(other.lastTimeStampUpdated))
			return false;
		if (midwayStatus == null) {
			if (other.midwayStatus != null)
				return false;
		} else if (!midwayStatus.equals(other.midwayStatus))
			return false;
		if (midwayTransactionID == null) {
			if (other.midwayTransactionID != null)
				return false;
		} else if (!midwayTransactionID.equals(other.midwayTransactionID))
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		if (timeStampReceived == null) {
			if (other.timeStampReceived != null)
				return false;
		} else if (!timeStampReceived.equals(other.timeStampReceived))
			return false;
		return true;
	}

	public String getTimeStampReceived() {
		return timeStampReceived;
	}

	public void setTimeStampReceived(String timeStampReceived) {
		this.timeStampReceived = timeStampReceived;
	}

	public String getLastTimeStampUpdated() {
		return lastTimeStampUpdated;
	}

	public void setLastTimeStampUpdated(String lastTimeStampUpdated) {
		this.lastTimeStampUpdated = lastTimeStampUpdated;
	}

}
