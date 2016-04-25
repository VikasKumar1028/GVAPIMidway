package com.gv.midway.pojo.reActivateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ReactivateDeviceResponseDataArea {
	@ApiModelProperty(value = "Order number is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric" )
	private String orderNumber;

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ReactivateDeviceResponseDataArea)) {
			return false;
		}
		ReactivateDeviceResponseDataArea other = (ReactivateDeviceResponseDataArea) obj;
		if (orderNumber == null) {
			if (other.orderNumber != null) {
				return false;
			}
		} else if (!orderNumber.equals(other.orderNumber)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReactivateDeviceResponseDataArea [orderNumber=" + orderNumber + "]";
	}
	
}
