package com.gv.midway.pojo.updateCustomeDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class UpdateCustomeFieldDeviceResponse {


	@ApiModelProperty(value = "Data area for Activate device response")
	private UpdateCustomeFieldDeviceResponseDataArea dataArea;

	public UpdateCustomeFieldDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(UpdateCustomeFieldDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

	@Override
	public String toString() {
		return "UpdateCustomeFieldDeviceResponse [dataArea=" + dataArea + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataArea == null) ? 0 : dataArea.hashCode());
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
		UpdateCustomeFieldDeviceResponse other = (UpdateCustomeFieldDeviceResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}
}
