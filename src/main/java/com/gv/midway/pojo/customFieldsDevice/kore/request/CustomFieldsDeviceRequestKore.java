package com.gv.midway.pojo.customFieldsDevice.kore.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CustomFieldsDeviceRequestKore {

    @ApiModelProperty(value = "The number of Device that has to be activated.")
    private String deviceNumber;

    @ApiModelProperty(value = "Update the Custom field 1 (up to 20 characters) for a single device. ")
    @JsonProperty("customField1")
    private String customField1;

    @ApiModelProperty(value = "Update the Custom field 2 (up to 20 characters) for a single device. ")
    @JsonProperty("customField2")
    private String customField2;

    @ApiModelProperty(value = "Update the Custom field 3 (up to 20 characters) for a single device. ")
    @JsonProperty("customField3")
    private String customField3;

    @ApiModelProperty(value = "Update the Custom field 4 (up to 20 characters) for a single device. ")
    @JsonProperty("customField4")
    private String customField4;

    @ApiModelProperty(value = "Update the Custom field 5 (up to 20 characters) for a single device. ")
    @JsonProperty("customField5")
    private String customField5;

    @ApiModelProperty(value = "Update the Custom field 6 (up to 20 characters) for a single device. ")
    @JsonProperty("customField6")
    private String customField6;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((customField1 == null) ? 0 : customField1.hashCode());
        result = prime * result
                + ((customField2 == null) ? 0 : customField2.hashCode());
        result = prime * result
                + ((customField3 == null) ? 0 : customField3.hashCode());
        result = prime * result
                + ((customField4 == null) ? 0 : customField4.hashCode());
        result = prime * result
                + ((customField5 == null) ? 0 : customField5.hashCode());
        result = prime * result
                + ((customField6 == null) ? 0 : customField6.hashCode());
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
        CustomFieldsDeviceRequestKore other = (CustomFieldsDeviceRequestKore) obj;
        if (customField1 == null) {
            if (other.customField1 != null)
                return false;
        } else if (!customField1.equals(other.customField1))
            return false;
        if (customField2 == null) {
            if (other.customField2 != null)
                return false;
        } else if (!customField2.equals(other.customField2))
            return false;
        if (customField3 == null) {
            if (other.customField3 != null)
                return false;
        } else if (!customField3.equals(other.customField3))
            return false;
        if (customField4 == null) {
            if (other.customField4 != null)
                return false;
        } else if (!customField4.equals(other.customField4))
            return false;
        if (customField5 == null) {
            if (other.customField5 != null)
                return false;
        } else if (!customField5.equals(other.customField5))
            return false;
        if (customField6 == null) {
            if (other.customField6 != null)
                return false;
        } else if (!customField6.equals(other.customField6))
            return false;
        if (deviceNumber == null) {
            if (other.deviceNumber != null)
                return false;
        } else if (!deviceNumber.equals(other.deviceNumber))
            return false;
        return true;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    @Override
    public String toString() {
        return "CustomFieldsDeviceRequestKore [deviceNumber=" + deviceNumber
                + ", customField1=" + customField1 + ", customField2="
                + customField2 + ", customField3=" + customField3
                + ", customField4=" + customField4 + ", customField5="
                + customField5 + ", customField6=" + customField6 + "]";
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public String getCustomField2() {
        return customField2;
    }

    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    public String getCustomField3() {
        return customField3;
    }

    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    public String getCustomField4() {
        return customField4;
    }

    public void setCustomField4(String customField4) {
        this.customField4 = customField4;
    }

    public String getCustomField5() {
        return customField5;
    }

    public void setCustomField5(String customField5) {
        this.customField5 = customField5;
    }

    public String getCustomField6() {
        return customField6;
    }

    public void setCustomField6(String customField6) {
        this.customField6 = customField6;
    }

}
