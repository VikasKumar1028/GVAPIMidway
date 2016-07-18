package com.gv.midway.pojo.suspendDevice.kore.request;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SuspendDeviceRequestKore {

    @ApiModelProperty(value = "The number of Device that has to be activated.", required = true)
    private String deviceNumber;

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
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
        SuspendDeviceRequestKore other = (SuspendDeviceRequestKore) obj;
        if (deviceNumber == null) {
            if (other.deviceNumber != null)
                return false;
        } else if (!deviceNumber.equals(other.deviceNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SuspendDeviceRequestKore [deviceNumber=");
        builder.append(deviceNumber);
        builder.append("]");
        return builder.toString();
    }
}
