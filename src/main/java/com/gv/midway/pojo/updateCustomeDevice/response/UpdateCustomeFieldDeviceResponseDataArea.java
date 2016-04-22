package com.gv.midway.pojo.updateCustomeDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class UpdateCustomeFieldDeviceResponseDataArea {
	@ApiModelProperty(value = "Order number is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric")
	private String orderNumber;

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
		UpdateCustomeFieldDeviceResponseDataArea other = (UpdateCustomeFieldDeviceResponseDataArea) obj;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateCustomeFieldDeviceResponseDataArea [orderNumber="
				+ orderNumber + "]";
	}

}
