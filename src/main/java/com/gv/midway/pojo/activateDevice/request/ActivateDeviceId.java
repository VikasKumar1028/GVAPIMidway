package com.gv.midway.pojo.activateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceId {
	// @JsonProperty("id")
	@ApiModelProperty(value = "Value of device identifier.", required = true)
	private String id;
	
	// @JsonProperty("kind")
	@ApiModelProperty(value = "Type of device identifier.", required = true)
	private String kind;
	
	@ApiModelProperty(value = "Extensible Authentication Protocol Code.")
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

	/**
	 * @return the eAPCode
	 */
	public String geteAPCode() {
		return eAPCode;
	}

	/**
	 * @param eAPCode the eAPCode to set
	 */
	public void seteAPCode(String eAPCode) {
		this.eAPCode = eAPCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceId [id=" + id + ", kind=" + kind + ", eAPCode=" + eAPCode + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eAPCode == null) ? 0 : eAPCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ActivateDeviceId))
			return false;
		ActivateDeviceId other = (ActivateDeviceId) obj;
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
