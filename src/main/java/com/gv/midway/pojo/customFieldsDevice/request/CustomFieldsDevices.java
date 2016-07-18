package com.gv.midway.pojo.customFieldsDevice.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFieldsDevices {

    @ApiModelProperty(value = "Having type and value of device identifier")
    private CustomFieldsDeviceId[] deviceIds;

    public CustomFieldsDeviceId[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(CustomFieldsDeviceId[] deviceIds) {
        this.deviceIds = deviceIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(deviceIds);
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
        CustomFieldsDevices other = (CustomFieldsDevices) obj;
        if (!Arrays.equals(deviceIds, other.deviceIds))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomFieldsUpdateDevices [deviceIds="
                + Arrays.toString(deviceIds) + "]";
    }

}
