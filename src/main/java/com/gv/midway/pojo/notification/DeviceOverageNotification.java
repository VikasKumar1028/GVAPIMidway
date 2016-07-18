package com.gv.midway.pojo.notification;

import org.springframework.data.mongodb.core.mapping.Document;

import com.gv.midway.pojo.verizon.DeviceId;

@Document(collection = "notification")
public class DeviceOverageNotification {

    private DeviceId deviceId;
    private Integer netSuiteId;
    private String date;
    private float dataUsed;
    private String carrierName;

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

    public float getDataUsed() {
        return dataUsed;
    }

    public void setDataUsed(float dataUsed) {
        this.dataUsed = dataUsed;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result + Float.floatToIntBits(dataUsed);
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result
                + ((deviceId == null) ? 0 : deviceId.hashCode());
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
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
        DeviceOverageNotification other = (DeviceOverageNotification) obj;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (Float.floatToIntBits(dataUsed) != Float
                .floatToIntBits(other.dataUsed))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (deviceId == null) {
            if (other.deviceId != null)
                return false;
        } else if (!deviceId.equals(other.deviceId))
            return false;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeviceOverageNotification [deviceId=" + deviceId
                + ", netSuiteId=" + netSuiteId + ", date=" + date
                + ", dataUsed=" + dataUsed + ", carrierName=" + carrierName
                + "]";
    }

}
