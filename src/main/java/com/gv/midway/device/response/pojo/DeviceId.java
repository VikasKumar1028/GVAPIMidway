package com.gv.midway.device.response.pojo;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "kind" })
public class DeviceId {

	

	@JsonProperty("id")
	private String id;
	@JsonProperty("kind")
	private String kind;

	/**
	 * 
	 * @return The id
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The kind
	 */
	
	public String getKind() {
		return kind;
	}

	/**
	 * 
	 * @param kind
	 *            The kind
	 */
	
	public void setKind(String kind) {
		this.kind = kind;
	}

	
	@Override
	public String toString() {
		return "DeviceId [id=" + id + ", kind=" + kind + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
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
		DeviceId other = (DeviceId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		return true;
	}
}
