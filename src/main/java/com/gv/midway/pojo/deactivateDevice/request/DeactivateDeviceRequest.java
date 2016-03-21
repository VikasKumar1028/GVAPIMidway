package com.gv.midway.pojo.deactivateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequest extends BaseRequest {

	private DeactivateDeviceRequestDataArea dataArea;

	public DeactivateDeviceRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeactivateDeviceRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}
}
