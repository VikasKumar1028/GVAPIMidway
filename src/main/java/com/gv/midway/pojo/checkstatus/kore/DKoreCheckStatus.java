package com.gv.midway.pojo.checkstatus.kore;

public class DKoreCheckStatus {

    private String __type;

    private String requestStatus;

    private String provisioningRequestStatus;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getProvisioningRequestStatus() {
        return provisioningRequestStatus;
    }

    public void setProvisioningRequestStatus(String provisioningRequestStatus) {
        this.provisioningRequestStatus = provisioningRequestStatus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DKoreCheckStatus [__type=");
        builder.append(__type);
        builder.append(", requestStatus=");
        builder.append(requestStatus);
        builder.append(", provisioningRequestStatus=");
        builder.append(provisioningRequestStatus);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((__type == null) ? 0 : __type.hashCode());
        result = prime
                * result
                + ((provisioningRequestStatus == null) ? 0
                        : provisioningRequestStatus.hashCode());
        result = prime * result
                + ((requestStatus == null) ? 0 : requestStatus.hashCode());
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
        DKoreCheckStatus other = (DKoreCheckStatus) obj;
        if (__type == null) {
            if (other.__type != null)
                return false;
        } else if (!__type.equals(other.__type))
            return false;
        if (provisioningRequestStatus == null) {
            if (other.provisioningRequestStatus != null)
                return false;
        } else if (!provisioningRequestStatus
                .equals(other.provisioningRequestStatus))
            return false;
        if (requestStatus == null) {
            if (other.requestStatus != null)
                return false;
        } else if (!requestStatus.equals(other.requestStatus))
            return false;
        return true;
    }

}
