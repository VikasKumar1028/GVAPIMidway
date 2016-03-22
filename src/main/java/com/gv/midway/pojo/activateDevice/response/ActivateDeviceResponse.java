package com.gv.midway.pojo.activateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceResponse extends BaseResponse {

	private ActivateDeviceResponseDataArea dataArea;

	public ActivateDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	//Change dataArea
	public void setDataArea(ActivateDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

	
}
