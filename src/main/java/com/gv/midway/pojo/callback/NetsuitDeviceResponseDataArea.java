package com.gv.midway.pojo.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetsuitDeviceResponseDataArea {
	private String midwaytransactionId;

	public String getMidwaytransactionId() {
		return midwaytransactionId;
	}

	public void setMidwaytransactionId(String midwaytransactionId) {
		this.midwaytransactionId = midwaytransactionId;
	}

	
	
}
