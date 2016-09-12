package com.gv.midway.pojo.transaction;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.constant.RecordType;
import com.gv.midway.constant.RequestType;

@Document(collection = "transactionalDetail")
public class Transaction {

    private String midwayTransactionId;
    private String carrierTransactionId;
    private Object deviceNumber;
    private Object devicePayload;
    private String midwayStatus;
    private String carrierStatus;
    private String carrierName;
    private Date timeStampReceived;
    private Date lastTimeStampUpdated;
    private String carrierErrorDescription;
    private RequestType requestType;
    private Object callBackPayload;
    private Boolean callBackDelivered;
    private String auditTransactionId;
    private Boolean callBackReceived;
    private String callBackFailureToNetSuiteReason;
    private Integer netSuiteId;
    private Object netSuiteRequest;
    private String netSuiteResponse;
    private Date   effectiveDate;
    private RecordType recordType;

   

	public Transaction() {
        super();
    }

    public Object getNetSuiteRequest() {
        return netSuiteRequest;
    }

    public void setNetSuiteRequest(Object netSuiteRequest) {
        this.netSuiteRequest = netSuiteRequest;
    }

    public String getNetSuiteResponse() {
        return netSuiteResponse;
    }

    public void setNetSuiteResponse(String netSuiteResponse) {
        this.netSuiteResponse = netSuiteResponse;
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

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Object getCallBackPayload() {
        return callBackPayload;
    }

    public void setCallBackPayload(Object callBackPayload) {
        this.callBackPayload = callBackPayload;
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

    public Date getTimeStampReceived() {
        return timeStampReceived;
    }

    public void setTimeStampReceived(Date timeStampReceived) {
        this.timeStampReceived = timeStampReceived;
    }

    public Date getLastTimeStampUpdated() {
        return lastTimeStampUpdated;
    }

    public void setLastTimeStampUpdated(Date lastTimeStampUpdated) {
        this.lastTimeStampUpdated = lastTimeStampUpdated;
    }

    public String getMidwayTransactionId() {
        return midwayTransactionId;
    }

    public void setMidwayTransactionId(String midwayTransactionId) {
        this.midwayTransactionId = midwayTransactionId;
    }

    public String getCarrierTransactionId() {
        return carrierTransactionId;
    }

    public void setCarrierTransactionId(String carrierTransactionId) {
        this.carrierTransactionId = carrierTransactionId;
    }

    public String getAuditTransactionId() {
        return auditTransactionId;
    }

    public void setAuditTransactionId(String auditTransactionId) {
        this.auditTransactionId = auditTransactionId;
    }

    public String getCallBackFailureToNetSuiteReason() {
        return callBackFailureToNetSuiteReason;
    }

    public void setCallBackFailureToNetSuiteReason(
            String callBackFailureToNetSuiteReason) {
        this.callBackFailureToNetSuiteReason = callBackFailureToNetSuiteReason;
    }

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }
    
    public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((auditTransactionId == null) ? 0 : auditTransactionId
                        .hashCode());
        result = prime
                * result
                + ((callBackDelivered == null) ? 0 : callBackDelivered
                        .hashCode());
        result = prime
                * result
                + ((callBackFailureToNetSuiteReason == null) ? 0
                        : callBackFailureToNetSuiteReason.hashCode());
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
                + ((carrierTransactionId == null) ? 0 : carrierTransactionId
                        .hashCode());
        result = prime * result
                + ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
        result = prime * result
                + ((devicePayload == null) ? 0 : devicePayload.hashCode());
        result = prime * result
                + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
        result = prime
                * result
                + ((lastTimeStampUpdated == null) ? 0 : lastTimeStampUpdated
                        .hashCode());
        result = prime * result
                + ((midwayStatus == null) ? 0 : midwayStatus.hashCode());
        result = prime
                * result
                + ((midwayTransactionId == null) ? 0 : midwayTransactionId
                        .hashCode());
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
        result = prime * result
                + ((netSuiteRequest == null) ? 0 : netSuiteRequest.hashCode());
        result = prime
                * result
                + ((netSuiteResponse == null) ? 0 : netSuiteResponse.hashCode());
        result = prime * result
                + ((recordType == null) ? 0 : recordType.hashCode());
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
        if (auditTransactionId == null) {
            if (other.auditTransactionId != null)
                return false;
        } else if (!auditTransactionId.equals(other.auditTransactionId))
            return false;
        if (callBackDelivered == null) {
            if (other.callBackDelivered != null)
                return false;
        } else if (!callBackDelivered.equals(other.callBackDelivered))
            return false;
        if (callBackFailureToNetSuiteReason == null) {
            if (other.callBackFailureToNetSuiteReason != null)
                return false;
        } else if (!callBackFailureToNetSuiteReason
                .equals(other.callBackFailureToNetSuiteReason))
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
        } else if (!carrierErrorDescription
                .equals(other.carrierErrorDescription))
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
        if (carrierTransactionId == null) {
            if (other.carrierTransactionId != null)
                return false;
        } else if (!carrierTransactionId.equals(other.carrierTransactionId))
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
        if (effectiveDate == null) {
            if (other.effectiveDate != null)
                return false;
        } else if (!effectiveDate.equals(other.effectiveDate))
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
        if (midwayTransactionId == null) {
            if (other.midwayTransactionId != null)
                return false;
        } else if (!midwayTransactionId.equals(other.midwayTransactionId))
            return false;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        if (netSuiteRequest == null) {
            if (other.netSuiteRequest != null)
                return false;
        } else if (!netSuiteRequest.equals(other.netSuiteRequest))
            return false;
        if (netSuiteResponse == null) {
            if (other.netSuiteResponse != null)
                return false;
        } else if (!netSuiteResponse.equals(other.netSuiteResponse))
            return false;
        if (recordType != other.recordType)
            return false;
        if (requestType != other.requestType)
            return false;
        if (timeStampReceived == null) {
            if (other.timeStampReceived != null)
                return false;
        } else if (!timeStampReceived.equals(other.timeStampReceived))
            return false;
        return true;
    }

	@Override
    public String toString() {
        return "Transaction [midwayTransactionId=" + midwayTransactionId
                + ", carrierTransactionId=" + carrierTransactionId
                + ", deviceNumber=" + deviceNumber + ", devicePayload="
                + devicePayload + ", midwayStatus=" + midwayStatus
                + ", carrierStatus=" + carrierStatus + ", carrierName="
                + carrierName + ", timeStampReceived=" + timeStampReceived
                + ", lastTimeStampUpdated=" + lastTimeStampUpdated
                + ", carrierErrorDescription=" + carrierErrorDescription
                + ", requestType=" + requestType + ", callBackPayload="
                + callBackPayload + ", callBackDelivered=" + callBackDelivered
                + ", auditTransactionId=" + auditTransactionId
                + ", callBackReceived=" + callBackReceived
                + ", callBackFailureToNetSuiteReason="
                + callBackFailureToNetSuiteReason + ", netSuiteId="
                + netSuiteId + ", netSuiteRequest=" + netSuiteRequest
                + ", netSuiteResponse=" + netSuiteResponse + ", effectiveDate="
                + effectiveDate + ", recordType=" + recordType + "]";
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }


    

}
