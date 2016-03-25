package com.gv.midway.pojo.deactivateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceResponse extends BaseResponse {

	private DeactivateDeviceResponseDataArea dataArea;

	public DeactivateDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeactivateDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
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
		DeactivateDeviceResponse other = (DeactivateDeviceResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeactivateDeviceResponse [dataArea=" + dataArea + "]";
	}

}
