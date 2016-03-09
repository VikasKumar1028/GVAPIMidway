package com.gv.midway.device.response.pojo;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "key", "value" })
public class ExtendedAttribute {

	

	@JsonProperty("key")
	private String key;
	@JsonProperty("value")
	private String value;
	

	/**
	 * 
	 * @return The key
	 */
	
	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            The key
	 */
	
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 
	 * @return The value
	 */
	
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 *            The value
	 */
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "ExtendedAttribute [key=" + key + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtendedAttribute other = (ExtendedAttribute) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
