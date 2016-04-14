package com.gv.midway.pojo.callback;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetsuitDeviceResponseDataArea {
	@ApiModelProperty(value = "Midway Transaction Id.")
	private String midwayTransactionId;

	public String getMidwayTransactionId() {
		return midwayTransactionId;
	}

	public void setMidwayTransactionId(String midwayTransactionId) {
		this.midwayTransactionId = midwayTransactionId;
	}

	
	
}
