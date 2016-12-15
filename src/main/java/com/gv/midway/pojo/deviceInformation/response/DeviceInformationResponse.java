package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationResponse extends BaseResponse {

    public DeviceInformationResponse() { }

    public DeviceInformationResponse(Header header, Response response, DeviceInformationResponseDataArea dataArea) {
        super(header, response);
        this.dataArea = dataArea;
    }

    @ApiModelProperty(value = "Device Information Response DataArea")
    private DeviceInformationResponseDataArea dataArea;

    public DeviceInformationResponseDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(DeviceInformationResponseDataArea dataArea) {
        this.dataArea = dataArea;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dataArea == null) ? 0 : dataArea.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeviceInformationResponse other = (DeviceInformationResponse) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeviceInformationResponse [dataArea=");
        builder.append(dataArea);
        builder.append("]");
        return builder.toString();
    }
}