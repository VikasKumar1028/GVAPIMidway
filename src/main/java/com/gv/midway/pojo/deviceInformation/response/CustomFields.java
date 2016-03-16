package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class CustomFields {
	

	private String key;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "CustomFields [value = " + value + ", key = " + key + "]";
	}
}
