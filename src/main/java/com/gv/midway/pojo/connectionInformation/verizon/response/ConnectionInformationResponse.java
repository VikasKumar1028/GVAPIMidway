package com.gv.midway.pojo.connectionInformation.verizon.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionInformationResponse {

    @ApiModelProperty(value = "Device connection evnents.")
    private ConnectionHistory[] connectionHistory;

    @ApiModelProperty(value = "Indicates that there is more data to be retrieved.")
    private Boolean hasMoreData;

    public ConnectionHistory[] getConnectionHistory() {
        return connectionHistory;
    }

    public void setConnectionHistory(ConnectionHistory[] connectionHistory) {
        this.connectionHistory = connectionHistory;
    }

    public Boolean getHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(Boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(connectionHistory);
        result = prime * result
                + ((hasMoreData == null) ? 0 : hasMoreData.hashCode());
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
        ConnectionInformationResponse other = (ConnectionInformationResponse) obj;
        if (!Arrays.equals(connectionHistory, other.connectionHistory))
            return false;
        if (hasMoreData == null) {
            if (other.hasMoreData != null)
                return false;
        } else if (!hasMoreData.equals(other.hasMoreData))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ConnectionInformationResponse [connectionHistory="
                + Arrays.toString(connectionHistory) + ", hasMoreData="
                + hasMoreData + "]";
    }

}
