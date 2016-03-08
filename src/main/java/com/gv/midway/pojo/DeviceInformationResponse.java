package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationResponse extends BaseResponse {

	private DeviceInformationResponseDataArea dataArea;

	public DeviceInformationResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeviceInformationResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

}
