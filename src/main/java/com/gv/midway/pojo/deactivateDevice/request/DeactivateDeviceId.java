package com.gv.midway.pojo.deactivateDevice.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceId {
	// @JsonProperty("id")
	@ApiModelProperty(value = "The value of device indentifier.", required = true)
	private String id;
	// @JsonProperty("kind")
	@ApiModelProperty(value = "The type of device indentifier.", required = true)
	private String kind;
	
	@ApiModelProperty(value = "KORE : A flag indicating whether to scrap the device or just deactivate it to stock.")
	private Boolean flagScrap;

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

	public Boolean getFlagScrap() {
		return flagScrap;
	}

	public void setFlagScrap(Boolean flagScrap) {
		this.flagScrap = flagScrap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flagScrap == null) ? 0 : flagScrap.hashCode());
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
		DeactivateDeviceId other = (DeactivateDeviceId) obj;
		if (flagScrap == null) {
			if (other.flagScrap != null)
				return false;
		} else if (!flagScrap.equals(other.flagScrap))
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeactivateDeviceId [id=");
		builder.append(id);
		builder.append(", kind=");
		builder.append(kind);
		builder.append(", flagScrap=");
		builder.append(flagScrap);
		builder.append("]");
		return builder.toString();
	}


}
