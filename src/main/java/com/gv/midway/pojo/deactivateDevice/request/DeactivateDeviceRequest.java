package com.gv.midway.pojo.deactivateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequest extends BaseRequest {

	@ApiModelProperty(value = "Data area for the deactivate device request")
	private DeactivateDeviceRequestDataArea dataArea;

	public DeactivateDeviceRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeactivateDeviceRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}
}
