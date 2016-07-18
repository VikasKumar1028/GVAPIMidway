package com.gv.midway.pojo.kore;

public class DKoreProvisoning {

    private String __type;

    private String trackingNumber;

    private String requestStatus;

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((__type == null) ? 0 : __type.hashCode());
        result = prime * result
                + ((requestStatus == null) ? 0 : requestStatus.hashCode());
        result = prime * result
                + ((trackingNumber == null) ? 0 : trackingNumber.hashCode());
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
        DKoreProvisoning other = (DKoreProvisoning) obj;
        if (__type == null) {
            if (other.__type != null)
                return false;
        } else if (!__type.equals(other.__type))
            return false;
        if (requestStatus == null) {
            if (other.requestStatus != null)
                return false;
        } else if (!requestStatus.equals(other.requestStatus))
            return false;
        if (trackingNumber == null) {
            if (other.trackingNumber != null)
                return false;
        } else if (!trackingNumber.equals(other.trackingNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DKoreProvisoning [__type=");
        builder.append(__type);
        builder.append(", trackingNumber=");
        builder.append(trackingNumber);
        builder.append(", requestStatus=");
        builder.append(requestStatus);
        builder.append("]");
        return builder.toString();
    }

}
