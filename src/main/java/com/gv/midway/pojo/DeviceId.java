package com.gv.midway.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceId {

	private String id;

	private String kind;

	// activation KORE field
	private String eAPCode;

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
		return "DeviceId [id=" + id + ", kind=" + kind + ", eAPCode=" + eAPCode
				+ "]";
	}

	public String geteAPCode() {
		return eAPCode;
	}

	public void seteAPCode(String eAPCode) {
		this.eAPCode = eAPCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eAPCode == null) ? 0 : eAPCode.hashCode());
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
		if (eAPCode == null) {
			if (other.eAPCode != null)
				return false;
		} else if (!eAPCode.equals(other.eAPCode))
			return false;
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
