package com.gv.midway.device.request.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices extends BaseRequest{

	@ApiModelProperty(value = "Device Information DataArea")
	private DevicesDataArea dataArea;

	public DevicesDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DevicesDataArea dataArea) {
		this.dataArea = dataArea;
	}
}
