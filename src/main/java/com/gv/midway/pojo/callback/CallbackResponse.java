package com.gv.midway.pojo.callback;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "callbacks")
public class CallbackResponse {
    @ApiModelProperty(value = "Response code for the triggered request.")
    String responseCode;

    Object payload;

    @ApiModelProperty(value = "Callback Status for the request.")
    String callbackStatus;

    @ApiModelProperty(value = "Request Type.")
    String requestType;

    @ApiModelProperty(value = "Carrier Transaction Id.")
    String carrierTransactionID;

    @ApiModelProperty(value = "Carrier Status.")
    String carrierStatus;

    @ApiModelProperty(value = "Stamp Updated Last Time.")
    String lastTimeStampUpdated;

    @ApiModelProperty(value = "Carrier Error Description.")
    String carrierErrorDescription;

    Object callBackPayload;
    @ApiModelProperty(value = "Delivered Callback.")
    String callBackDelivered;

    @ApiModelProperty(value = "Received Callback.")
    String callBackReceived;

    @ApiModelProperty(value = "Callback Failure Reson to NetSuite.")
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
     * @param payload
     *            the payload to set
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
     * @param callbackStatus
     *            the callbackStatus to set
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
     * @param requestType
     *            the requestType to set
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * @return the carrierTransactionID
     */
    public String getCarrierTransactionID() {
        return carrierTransactionID;
    }

    /**
     * @param carrierTransationID
     *            the carrierTransationID to set
     */
    public void setCarrierTransactionID(String carrierTransactionID) {
        this.carrierTransactionID = carrierTransactionID;
    }

    /**
     * @return the carrierStatus
     */
    public String getCarrierStatus() {
        return carrierStatus;
    }

    /**
     * @param carrierStatus
     *            the carrierStatus to set
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
     * @param lastTimeStampUpdated
     *            the lastTimeStampUpdated to set
     */
    public void setLastTimeStampUpdated(String lastTimeStampUpdated) {
        this.lastTimeStampUpdated = lastTimeStampUpdated;
    }

    /**
     * @return the carrierErrorDecription
     */
    public String getCarrierErrorDescription() {
        return carrierErrorDescription;
    }

    /**
     * @param carrierErrorDecription
     *            the carrierErrorDecription to set
     */
    public void setCarrierErrorDescription(String carrierErrorDescription) {
        this.carrierErrorDescription = carrierErrorDescription;
    }

    /**
     * @return the callBackPayload
     */
    public Object getCallBackPayload() {
        return callBackPayload;
    }

    /**
     * @param callBackPayload
     *            the callBackPayload to set
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
     * @param callBackDelivered
     *            the callBackDelivered to set
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
     * @param callBackReceived
     *            the callBackReceived to set
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
     * @param callBackFailureToNetSuitReason
     *            the callBackFailureToNetSuitReason to set
     */
    public void setCallBackFailureToNetSuitReason(
            String callBackFailureToNetSuitReason) {
        this.callBackFailureToNetSuitReason = callBackFailureToNetSuitReason;
    }

    @Override
    public String toString() {
        return "CallbackResponse [responseCode=" + responseCode + ", payload="
                + payload + ", callbackStatus=" + callbackStatus
                + ", requestType=" + requestType + ", carrierTransactionID="
                + carrierTransactionID + ", carrierStatus=" + carrierStatus
                + ", lastTimeStampUpdated=" + lastTimeStampUpdated
                + ", carrierErrorDescription=" + carrierErrorDescription
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
                + ((carrierErrorDescription == null) ? 0
                        : carrierErrorDescription.hashCode());
        result = prime * result
                + ((carrierStatus == null) ? 0 : carrierStatus.hashCode());
        result = prime
                * result
                + ((carrierTransactionID == null) ? 0 : carrierTransactionID
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
        if (carrierErrorDescription == null) {
            if (other.carrierErrorDescription != null)
                return false;
        } else if (!carrierErrorDescription
                .equals(other.carrierErrorDescription))
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
