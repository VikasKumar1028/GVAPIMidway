package com.gv.midway.pojo.updateCustomeDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class UpdateCustomeFieldDeviceResponseDataArea {
	@ApiModelProperty(value = "Order number is an unique Id of the request submitted from the source. Can be Numeric or Alphanumeric")
	private String orderNumber;

}
