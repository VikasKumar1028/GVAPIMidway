package com.gv.midway.pojo.activateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceResponse extends BaseResponse {

	private ActivateDeviceResponseDataArea dataArea;

	public ActivateDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(ActivateDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

}
