package com.gv.midway.pojo.changeDeviceServicePlans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ChangeDeviceServicePlansResponse extends BaseResponse {

    @ApiModelProperty(value = "Data area for Change Device Service Plans response")
    private ChangeDeviceServicePlansResponseDataArea dataArea;

    public ChangeDeviceServicePlansResponseDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(ChangeDeviceServicePlansResponseDataArea dataArea) {
        this.dataArea = dataArea;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((dataArea == null) ? 0 : dataArea.hashCode());
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
        ChangeDeviceServicePlansResponse other = (ChangeDeviceServicePlansResponse) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChangeDeviceServicePlansResponse [dataArea=" + dataArea + "]";
    }
}
