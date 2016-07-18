package com.gv.midway.pojo.usageInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationRequestDataArea {

    @ApiModelProperty(value = "All identifiers for the device.")
    private DeviceId deviceId;

    @ApiModelProperty(value = "Start Date")
    private String earliest;

    @ApiModelProperty(value = "End Date")
    private String latest;

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public String getEarliest() {
        return earliest;
    }

    public void setEarliest(String earliest) {
        this.earliest = earliest;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((deviceId == null) ? 0 : deviceId.hashCode());
        result = prime * result
                + ((earliest == null) ? 0 : earliest.hashCode());
        result = prime * result + ((latest == null) ? 0 : latest.hashCode());
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
        UsageInformationRequestDataArea other = (UsageInformationRequestDataArea) obj;
        if (deviceId == null) {
            if (other.deviceId != null)
                return false;
        } else if (!deviceId.equals(other.deviceId))
            return false;
        if (earliest == null) {
            if (other.earliest != null)
                return false;
        } else if (!earliest.equals(other.earliest))
            return false;
        if (latest == null) {
            if (other.latest != null)
                return false;
        } else if (!latest.equals(other.latest))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UsageInformationRequestDataArea [deviceId=" + deviceId
                + ", earliest=" + earliest + ", latest=" + latest + "]";
    }

}
