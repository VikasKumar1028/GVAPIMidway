package com.gv.midway.pojo.deviceInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationRequest extends BaseRequest {

    @ApiModelProperty(value = "DataArea for the Device Information Request")
    private DeviceInformationRequestDataArea dataArea;

    public DeviceInformationRequestDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(DeviceInformationRequestDataArea dataArea) {
        this.dataArea = dataArea;
    }

}
