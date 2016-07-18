package com.gv.midway.pojo.usageInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationMidwayResponse extends BaseResponse {
    private UsageInformationResponseMidwayDataArea dataArea;

    public UsageInformationResponseMidwayDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(UsageInformationResponseMidwayDataArea dataArea) {
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
    public String toString() {
        return "UsageInformationMidwayResponse [dataArea=" + dataArea + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsageInformationMidwayResponse other = (UsageInformationMidwayResponse) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }
}
