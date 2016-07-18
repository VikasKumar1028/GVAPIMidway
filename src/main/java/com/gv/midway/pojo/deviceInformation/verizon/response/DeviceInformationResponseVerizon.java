package com.gv.midway.pojo.deviceInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationResponseVerizon {

    private String hasMoreData;

    private DeviceInformationDevices[] devices;

    public String getHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(String hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

    public DeviceInformationDevices[] getDevices() {
        return devices;
    }

    public void setDevices(DeviceInformationDevices[] devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeviceInformationResponseVerizon [hasMoreData=");
        builder.append(hasMoreData);
        builder.append(", devices=");
        builder.append(Arrays.toString(devices));
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(devices);
        result = prime * result
                + ((hasMoreData == null) ? 0 : hasMoreData.hashCode());
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
        DeviceInformationResponseVerizon other = (DeviceInformationResponseVerizon) obj;
        if (!Arrays.equals(devices, other.devices))
            return false;
        if (hasMoreData == null) {
            if (other.hasMoreData != null)
                return false;
        } else if (!hasMoreData.equals(other.hasMoreData))
            return false;
        return true;
    }

}
