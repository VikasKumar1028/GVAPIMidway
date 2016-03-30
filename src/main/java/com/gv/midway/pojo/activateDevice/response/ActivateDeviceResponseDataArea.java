package com.gv.midway.pojo.activateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceResponseDataArea {

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
		ActivateDeviceResponseDataArea other = (ActivateDeviceResponseDataArea) obj;
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

	@Override
	public String toString() {
		return "ActivateDeviceResponseDataArea [orderNumber=" + orderNumber
				+ "]";
	}

}