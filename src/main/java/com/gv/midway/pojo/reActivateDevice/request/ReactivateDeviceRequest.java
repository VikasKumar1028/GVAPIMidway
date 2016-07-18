package com.gv.midway.pojo.reActivateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReactivateDeviceRequest extends BaseRequest {
    private ReactivateDeviceRequestDataArea dataArea;

    public ReactivateDeviceRequestDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(ReactivateDeviceRequestDataArea dataArea) {
        this.dataArea = dataArea;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReactivateDeviceRequest [dataArea=" + dataArea + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((dataArea == null) ? 0 : dataArea.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ReactivateDeviceRequest)) {
            return false;
        }
        ReactivateDeviceRequest other = (ReactivateDeviceRequest) obj;
        if (dataArea == null) {
            if (other.dataArea != null) {
                return false;
            }
        } else if (!dataArea.equals(other.dataArea)) {
            return false;
        }
        return true;
    }

}
