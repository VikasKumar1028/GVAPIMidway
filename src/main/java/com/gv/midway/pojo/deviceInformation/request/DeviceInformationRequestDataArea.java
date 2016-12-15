package com.gv.midway.pojo.deviceInformation.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class DeviceInformationRequestDataArea {

    @ApiModelProperty(value = "An identifier from NetSuite system")
    private Integer netSuiteId;

    @ApiModelProperty(value = "An identifier for device identification")
    private DeviceId deviceId;

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        DeviceInformationRequestDataArea other = (DeviceInformationRequestDataArea) obj;
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
        StringBuilder builder = new StringBuilder();
        builder.append("DeviceInformationRequestDataArea [netSuiteId=");
        builder.append(netSuiteId);
        builder.append(", deviceId=");
        builder.append(deviceId);
        builder.append("]");
        return builder.toString();
    }

}
