package com.gv.midway.pojo.deviceInformation.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedAttributes {
	private String key;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "ExtendedAttributes [key=" + key + ", value=" + value + "]";
	}

}
