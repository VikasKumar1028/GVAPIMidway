package com.gv.midway.pojo.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetsuitDeviceResponseDataArea {
	private String midwayTransactionId;

	public String getMidwayTransactionId() {
		return midwayTransactionId;
	}

	public void setMidwayTransactionId(String midwayTransactionId) {
		this.midwayTransactionId = midwayTransactionId;
	}

	
	
}
