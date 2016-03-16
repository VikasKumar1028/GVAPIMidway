package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.pojo.BaseResponse;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationResponse extends BaseResponse {

	private DeviceInformationResponseDataArea dataArea;

	public DeviceInformationResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeviceInformationResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

}
