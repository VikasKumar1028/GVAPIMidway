package com.gv.midway.pojo.deviceInformation.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomFields {

	@ApiModelProperty(value = "The value of the custom field. The value is not case-sensitive, but other than that it must match exactly with the value set for a device. Wildcards and partial matches are not supported.")
	private String value;
	
	@ApiModelProperty(value = "The name of the custom field. Valid names are CustomField1, CustomField2, CustomField3, CustomField4, and CustomField5.")
	private String key;

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
		return "ClassPojo [value = " + value + ", key = " + key + "]";
	}

}
