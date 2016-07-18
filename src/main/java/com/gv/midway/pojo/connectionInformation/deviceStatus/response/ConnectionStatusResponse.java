package com.gv.midway.pojo.connectionInformation.deviceStatus.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ConnectionStatusResponse extends BaseResponse {

    @ApiModelProperty(value = "Data area for device's connection status response")
    private ConnectionStatusResponseDataArea dataArea;

    public ConnectionStatusResponseDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(ConnectionStatusResponseDataArea dataArea) {
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
        ConnectionStatusResponse other = (ConnectionStatusResponse) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConnectionStatusResponse [dataArea=" + dataArea + "]";
    }

}
