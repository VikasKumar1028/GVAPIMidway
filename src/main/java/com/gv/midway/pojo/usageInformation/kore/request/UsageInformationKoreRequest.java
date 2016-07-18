package com.gv.midway.pojo.usageInformation.kore.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationKoreRequest {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((SimNumber == null) ? 0 : SimNumber.hashCode());
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
        UsageInformationKoreRequest other = (UsageInformationKoreRequest) obj;
        if (SimNumber == null) {
            if (other.SimNumber != null)
                return false;
        } else if (!SimNumber.equals(other.SimNumber))
            return false;
        return true;
    }

    private String SimNumber;

    @Override
    public String toString() {
        return "UsageInformationKoreRequest [SimNumber=" + SimNumber + "]";
    }

    public String getSimNumber() {
        return SimNumber;
    }

    public void setSimNumber(String simNumber) {
        SimNumber = simNumber;
    }
}
