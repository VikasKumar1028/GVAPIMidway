package com.gv.midway.pojo.usageInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gv.midway.pojo.BaseResponse;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationResponse extends BaseResponse {

    private UsageInformationResponseDataArea dataArea;

    public UsageInformationResponseDataArea getDataArea() {
        return dataArea;
    }

    public void setDataArea(UsageInformationResponseDataArea dataArea) {
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
        UsageInformationResponse other = (UsageInformationResponse) obj;
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
        builder.append("UsageInformationResponse [dataArea=");
        builder.append(dataArea);
        builder.append("]");
        return builder.toString();
    }

}
