package com.gv.midway.pojo.suspendDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuspendDeviceRequest extends BaseRequest {

    private SuspendDeviceRequestDataArea dataArea;

    public SuspendDeviceRequestDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(SuspendDeviceRequestDataArea dataArea) {
        this.dataArea = dataArea;
    }

    @Override
    public String toString() {
        return "ActivateDeviceRequest [dataArea=" + dataArea + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((dataArea == null) ? 0 : dataArea.hashCode());
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
        SuspendDeviceRequest other = (SuspendDeviceRequest) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }
}
