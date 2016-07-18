package com.gv.midway.pojo.deviceHistory;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.pojo.verizon.DeviceId;

@Document(collection = "deviceConnection")
public class DeviceConnection {

    private DeviceId deviceId;
    private Integer netSuiteId;
    private String date;
    private String carrierName;
    private String transactionStatus;
    private String transactionErrorReason;
    private Boolean isValid;
    private DeviceEvent[] event;
    private String occurredAt;

    public String getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(String occurredAt) {
        this.occurredAt = occurredAt;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionErrorReason() {
        return transactionErrorReason;
    }

    public void setTransactionErrorReason(String transactionErrorReason) {
        this.transactionErrorReason = transactionErrorReason;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public DeviceEvent[] getEvent() {
        return event;
    }

    public void setEvent(DeviceEvent[] event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result
                + ((deviceId == null) ? 0 : deviceId.hashCode());
        result = prime * result + Arrays.hashCode(event);
        result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
        result = prime * result
                + ((occurredAt == null) ? 0 : occurredAt.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime
                * result
                + ((transactionErrorReason == null) ? 0
                        : transactionErrorReason.hashCode());
        result = prime
                * result
                + ((transactionStatus == null) ? 0 : transactionStatus
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
        DeviceConnection other = (DeviceConnection) obj;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (deviceId == null) {
            if (other.deviceId != null)
                return false;
        } else if (!deviceId.equals(other.deviceId))
            return false;
        if (!Arrays.equals(event, other.event))
            return false;
        if (isValid == null) {
            if (other.isValid != null)
                return false;
        } else if (!isValid.equals(other.isValid))
            return false;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        if (occurredAt == null) {
            if (other.occurredAt != null)
                return false;
        } else if (!occurredAt.equals(other.occurredAt))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (transactionErrorReason == null) {
            if (other.transactionErrorReason != null)
                return false;
        } else if (!transactionErrorReason.equals(other.transactionErrorReason))
            return false;
        if (transactionStatus == null) {
            if (other.transactionStatus != null)
                return false;
        } else if (!transactionStatus.equals(other.transactionStatus))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceConnection [deviceId=" + deviceId + ", netSuiteId="
                + netSuiteId + ", date=" + date + ", carrierName="
                + carrierName + ", transactionStatus=" + transactionStatus
                + ", transactionErrorReason=" + transactionErrorReason
                + ", isValid=" + isValid + ", event=" + Arrays.toString(event)
                + ", occurredAt=" + occurredAt + "]";
    }

}
