package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationRequest extends BaseRequest {

	private DeviceInformationRequestDataArea dataArea;

	public DeviceInformationRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeviceInformationRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}

}
