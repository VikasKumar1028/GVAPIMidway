package com.gv.midway.pojo.usageInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationMidwayRequest extends BaseRequest {

    private UsageInformationRequestMidwayDataArea dataArea;

    public UsageInformationRequestMidwayDataArea getUsageInformationRequestMidwayDataArea() {
        return dataArea;
    }

    public void setUsageInformationRequestMidwayDataArea(
            UsageInformationRequestMidwayDataArea dataArea) {
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
        UsageInformationMidwayRequest other = (UsageInformationMidwayRequest) obj;
        if (dataArea == null) {
            if (other.dataArea != null)
                return false;
        } else if (!dataArea.equals(other.dataArea))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UsageInformationMidwayRequest [dataArea=" + dataArea + "]";
    }
}
