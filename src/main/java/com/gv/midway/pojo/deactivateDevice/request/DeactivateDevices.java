package com.gv.midway.pojo.deactivateDevice.request;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeactivateDevices {
    @ApiModelProperty(value = "All identifiers for the device", required = true)
    private DeactivateDeviceId[] deviceIds;

    @ApiModelProperty(value = "Device NetSuite Id", required = true)
    private Integer netSuiteId;

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public DeactivateDeviceId[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(DeactivateDeviceId[] deviceIds) {
        this.deviceIds = deviceIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(deviceIds);
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
        DeactivateDevices other = (DeactivateDevices) obj;
        if (!Arrays.equals(deviceIds, other.deviceIds))
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
        builder.append("DeactivateDevices [deviceIds=");
        builder.append(Arrays.toString(deviceIds));
        builder.append(", netSuiteId=");
        builder.append(netSuiteId);
        builder.append("]");
        return builder.toString();
    }

}
