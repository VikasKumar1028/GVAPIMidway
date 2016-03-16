package com.gv.midway.pojo.deviceInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInformationRequest extends BaseRequest {

	private DeviceInformationRequestDataArea dataArea;

	public DeviceInformationRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeviceInformationRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}


}
