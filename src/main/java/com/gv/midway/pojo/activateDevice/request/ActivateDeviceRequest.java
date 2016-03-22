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
}


