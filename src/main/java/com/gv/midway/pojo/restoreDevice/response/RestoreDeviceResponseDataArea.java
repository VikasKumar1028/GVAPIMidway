package com.gv.midway.pojo.restoreDevice.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RestoreDeviceResponseDataArea {

    @ApiModelProperty(value = "Order number is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric")
    private String orderNumber;

    @Override
    public String toString() {
        return "RestoreDeviceResponseDataArea [orderNumber=" + orderNumber
                + "]";
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
        RestoreDeviceResponseDataArea other = (RestoreDeviceResponseDataArea) obj;
        if (orderNumber == null) {
            if (other.orderNumber != null)
                return false;
        } else if (!orderNumber.equals(other.orderNumber))
            return false;
        return true;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

}