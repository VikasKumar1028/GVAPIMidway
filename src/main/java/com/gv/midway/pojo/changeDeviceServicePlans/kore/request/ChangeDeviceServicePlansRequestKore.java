package com.gv.midway.pojo.changeDeviceServicePlans.kore.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ChangeDeviceServicePlansRequestKore {

    @ApiModelProperty(value = "The number of Device that has to be activated.")
    private String deviceNumber;

    @ApiModelProperty(value = "The Change a plan for a single active device for the next period.")
    @JsonProperty("planCode")
    private String planCode;

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("planCode")
    public String getPlanCode() {
        return planCode;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("planCode")
    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((deviceNumber == null) ? 0 : deviceNumber.hashCode());
        result = prime * result
                + ((planCode == null) ? 0 : planCode.hashCode());
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
        ChangeDeviceServicePlansRequestKore other = (ChangeDeviceServicePlansRequestKore) obj;
        if (deviceNumber == null) {
            if (other.deviceNumber != null)
                return false;
        } else if (!deviceNumber.equals(other.deviceNumber))
            return false;
        if (planCode == null) {
            if (other.planCode != null)
                return false;
        } else if (!planCode.equals(other.planCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChangeDeviceServicePlansRequestKore [deviceNumber="
                + deviceNumber + ", planCode=" + planCode + "]";
    }
}
