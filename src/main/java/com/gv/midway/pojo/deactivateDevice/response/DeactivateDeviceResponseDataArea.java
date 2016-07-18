package com.gv.midway.pojo.deactivateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceResponseDataArea {

    @ApiModelProperty(value = "Order number is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric")
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((orderNumber == null) ? 0 : orderNumber.hashCode());
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
        DeactivateDeviceResponseDataArea other = (DeactivateDeviceResponseDataArea) obj;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeactivateDeviceResponseDataArea [orderNumber=");
        builder.append(orderNumber);
        builder.append("]");
        return builder.toString();
    }

}
