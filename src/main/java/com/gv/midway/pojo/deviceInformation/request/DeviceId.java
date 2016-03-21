package com.gv.midway.pojo.deviceInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceId {
	@JsonProperty("id")
	private String id;
	@JsonProperty("kind")
	private String kind;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "DeviceId [id=" + id + ", kind=" + kind + "]";
	}
}
