package com.gv.midway.pojo.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetsuitDeviceResponseDataArea {
	private String midwayTransactionId;

	public String getMidwaytransactionId() {
		return midwayTransactionId;
	}

	public void setMidwaytransactionId(String midwayTransactionId) {
		this.midwayTransactionId = midwayTransactionId;
	}

	
	
}
