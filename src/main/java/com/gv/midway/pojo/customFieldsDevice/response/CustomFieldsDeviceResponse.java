package com.gv.midway.pojo.customFieldsDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class CustomFieldsDeviceResponse extends BaseResponse {


	@ApiModelProperty(value = "Data area for CustomFields device response")
	private CustomFieldsDeviceResponseDataArea dataArea;

	public CustomFieldsDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(CustomFieldsDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

	@Override
	public String toString() {
		return "CustomFieldsUpdateDeviceResponse [dataArea=" + dataArea + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataArea == null) ? 0 : dataArea.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomFieldsDeviceResponse other = (CustomFieldsDeviceResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}
}
