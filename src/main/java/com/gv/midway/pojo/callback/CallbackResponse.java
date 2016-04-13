package com.gv.midway.pojo.callback;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "callbacks")
public class CallbackResponse {
	String responseCode;
	Object payload;
	String callbackStatus;
	String requestType;

	String carrierTransationID;
	String carrierStatus;
	String lastTimeStampUpdated;
	String carrierErrorDecription;
	Object callBackPayload;
	String callBackDelivered;
	String callBackReceived;
	String callBackFailureToNetSuitReason;
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the payload
	 */
	public Object getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	/**
	 * @return the callbackStatus
	 */
	public String getCallbackStatus() {
		return callbackStatus;
	}
	/**
	 * @param callbackStatus the callbackStatus to set
	 */
	public void setCallbackStatus(String callbackStatus) {
		this.callbackStatus = callbackStatus;
	}
	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	/**
	 * @return the carrierTransationID
	 */
	public String getCarrierTransationID() {
		return carrierTransationID;
	}
	/**
	 * @param carrierTransationID the carrierTransationID to set
	 */
	public void setCarrierTransationID(String carrierTransationID) {
		this.carrierTransationID = carrierTransationID;
	}
	/**
	 * @return the carrierStatus
	 */
	public String getCarrierStatus() {
		return carrierStatus;
	}
	/**
	 * @param carrierStatus the carrierStatus to set
	 */
	public void setCarrierStatus(String carrierStatus) {
		this.carrierStatus = carrierStatus;
	}
	/**
	 * @return the lastTimeStampUpdated
	 */
	public String getLastTimeStampUpdated() {
		return lastTimeStampUpdated;
	}
	/**
	 * @param lastTimeStampUpdated the lastTimeStampUpdated to set
	 */
	public void setLastTimeStampUpdated(String lastTimeStampUpdated) {
		this.lastTimeStampUpdated = lastTimeStampUpdated;
	}
	/**
	 * @return the carrierErrorDecription
	 */
	public String getCarrierErrorDecription() {
		return carrierErrorDecription;
	}
	/**
	 * @param carrierErrorDecription the carrierErrorDecription to set
	 */
	public void setCarrierErrorDecription(String carrierErrorDecription) {
		this.carrierErrorDecription = carrierErrorDecription;
	}
	/**
	 * @return the callBackPayload
	 */
	public Object getCallBackPayload() {
		return callBackPayload;
	}
	/**
	 * @param callBackPayload the callBackPayload to set
	 */
	public void setCallBackPayload(Object callBackPayload) {
		this.callBackPayload = callBackPayload;
	}
	/**
	 * @return the callBackDelivered
	 */
	public String getCallBackDelivered() {
		return callBackDelivered;
	}
	/**
	 * @param callBackDelivered the callBackDelivered to set
	 */
	public void setCallBackDelivered(String callBackDelivered) {
		this.callBackDelivered = callBackDelivered;
	}
	/**
	 * @return the callBackReceived
	 */
	public String getCallBackReceived() {
		return callBackReceived;
	}
	/**
	 * @param callBackReceived the callBackReceived to set
	 */
	public void setCallBackReceived(String callBackReceived) {
		this.callBackReceived = callBackReceived;
	}
	/**
	 * @return the callBackFailureToNetSuitReason
	 */
	public String getCallBackFailureToNetSuitReason() {
		return callBackFailureToNetSuitReason;
	}
	/**
	 * @param callBackFailureToNetSuitReason the callBackFailureToNetSuitReason to set
	 */
	public void setCallBackFailureToNetSuitReason(String callBackFailureToNetSuitReason) {
		this.callBackFailureToNetSuitReason = callBackFailureToNetSuitReason;
	}
	@Override
	public String toString() {
		return "CallbackResponse [responseCode=" + responseCode + ", payload="
				+ payload + ", callbackStatus=" + callbackStatus
				+ ", requestType=" + requestType + ", carrierTransationID="
				+ carrierTransationID + ", carrierStatus=" + carrierStatus
				+ ", lastTimeStampUpdated=" + lastTimeStampUpdated
				+ ", carrierErrorDecription=" + carrierErrorDecription
				+ ", callBackPayload=" + callBackPayload
				+ ", callBackDelivered=" + callBackDelivered
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
		result = prime * result
				+ ((callbackStatus == null) ? 0 : callbackStatus.hashCode());
		result = prime
				* result
				+ ((carrierErrorDecription == null) ? 0
						: carrierErrorDecription.hashCode());
		result = prime * result
				+ ((carrierStatus == null) ? 0 : carrierStatus.hashCode());
		result = prime
				* result
				+ ((carrierTransationID == null) ? 0 : carrierTransationID
						.hashCode());
		result = prime
				* result
				+ ((lastTimeStampUpdated == null) ? 0 : lastTimeStampUpdated
						.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result
				+ ((responseCode == null) ? 0 : responseCode.hashCode());
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
		CallbackResponse other = (CallbackResponse) obj;
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
		if (callbackStatus == null) {
			if (other.callbackStatus != null)
				return false;
		} else if (!callbackStatus.equals(other.callbackStatus))
			return false;
		if (carrierErrorDecription == null) {
			if (other.carrierErrorDecription != null)
				return false;
		} else if (!carrierErrorDecription.equals(other.carrierErrorDecription))
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
		if (lastTimeStampUpdated == null) {
			if (other.lastTimeStampUpdated != null)
				return false;
		} else if (!lastTimeStampUpdated.equals(other.lastTimeStampUpdated))
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (requestType == null) {
			if (other.requestType != null)
				return false;
		} else if (!requestType.equals(other.requestType))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		return true;
	}
	
}
