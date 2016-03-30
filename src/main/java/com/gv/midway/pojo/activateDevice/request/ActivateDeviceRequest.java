package com.gv.midway.pojo.activateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceRequest extends BaseRequest {
	
	private ActivateDeviceRequestDataArea dataArea;

	public ActivateDeviceRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(ActivateDeviceRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}

	@Override
	public String toString() {
		return "ActivateDeviceRequest [dataArea=" + dataArea + "]";
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
		ActivateDeviceRequest other = (ActivateDeviceRequest) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}
}


