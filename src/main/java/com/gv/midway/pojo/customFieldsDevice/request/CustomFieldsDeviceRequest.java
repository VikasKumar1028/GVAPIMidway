package com.gv.midway.pojo.customFieldsDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFieldsDeviceRequest extends BaseRequest {

	private CustomFieldsDeviceRequestDataArea dataArea;

	public CustomFieldsDeviceRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(CustomFieldsDeviceRequestDataArea dataArea) {
		this.dataArea = dataArea;
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
		CustomFieldsDeviceRequest other = (CustomFieldsDeviceRequest) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomFieldsUpdateDeviceRequest [dataArea=" + dataArea + "]";
	}
}
