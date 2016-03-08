package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class ExtendedAttributes {
	private String key4;

	private String key3;

	private String key2;

	private String key1;

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	@Override
	public String toString() {
		return "ExtendedAttributes [key4 = " + key4 + ", key3 = " + key3
				+ ", key2 = " + key2 + ", key1 = " + key1 + "]";
	}
}
