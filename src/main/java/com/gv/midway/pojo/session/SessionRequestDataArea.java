package com.gv.midway.pojo.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionRequestDataArea {

    @ApiModelProperty(value = "Identifier for the device.")
    private Integer netSuiteId;

    @ApiModelProperty(value = "Start Date")
    private String earliest;

    @ApiModelProperty(value = "End Date")
    private String latest;

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public String getEarliest() {
        return earliest;
    }

    public void setEarliest(String earliest) {
        this.earliest = earliest;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
        result = prime * result
                + ((earliest == null) ? 0 : earliest.hashCode());
        result = prime * result + ((latest == null) ? 0 : latest.hashCode());
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
        SessionRequestDataArea other = (SessionRequestDataArea) obj;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        if (earliest == null) {
            if (other.earliest != null)
                return false;
        } else if (!earliest.equals(other.earliest))
            return false;
        if (latest == null) {
            if (other.latest != null)
                return false;
        } else if (!latest.equals(other.latest))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SessionRequestDataArea [netSuiteId=" + netSuiteId
                + ", earliest=" + earliest + ", latest=" + latest + "]";
    }

}
