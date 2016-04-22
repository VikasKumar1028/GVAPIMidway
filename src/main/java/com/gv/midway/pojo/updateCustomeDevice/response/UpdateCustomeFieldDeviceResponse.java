package com.gv.midway.pojo.updateCustomeDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class UpdateCustomeFieldDeviceResponse {


	@ApiModelProperty(value = "Data area for Activate device response")
	private UpdateCustomeFieldDeviceResponseDataArea dataArea;
}
