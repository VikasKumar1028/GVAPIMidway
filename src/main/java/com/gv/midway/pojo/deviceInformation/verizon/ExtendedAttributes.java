package com.gv.midway.pojo.deviceInformation.verizon;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedAttributes {
	
	public ExtendedAttributes(String key, String value) {
		//super();
		this.key = key;
		this.value = value;
	}

	
	public ExtendedAttributes() {

	}

	@ApiModelProperty(value = "Key for the device as custom field")
	private String key;
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ApiModelProperty(value = "Value for the device as custom field")
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
