package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class DeviceInformationResponseDataArea {

    @ApiModelProperty(value = "Information of the Device(s)")
    private DeviceInformation device;

    public DeviceInformation getDevices() {
        return device;
    }

    public void setDevices(DeviceInformation device) {
        this.device = device;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((device == null) ? 0 : device.hashCode());
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
        DeviceInformationResponseDataArea other = (DeviceInformationResponseDataArea) obj;
        if (device == null) {
            if (other.device != null)
                return false;
        } else if (!device.equals(other.device))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeviceInformationResponseDataArea [device=");
        builder.append(device);
        builder.append("]");
        return builder.toString();
    }

}
