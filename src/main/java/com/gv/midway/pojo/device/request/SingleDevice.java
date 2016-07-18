package com.gv.midway.pojo.device.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDevice extends BaseRequest {

    @ApiModelProperty(value = "Device Information DataArea")
    private DeviceDataArea dataArea;

    public DeviceDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(DeviceDataArea dataArea) {
        this.dataArea = dataArea;
    }

}
