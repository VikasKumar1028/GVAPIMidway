package com.gv.midway.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MidWayDeviceId {
    // @JsonProperty("id")
    @ApiModelProperty(value = "Value of device identifier.", required = true)
    private String id;

    // @JsonProperty("kind")
    @ApiModelProperty(value = "Type of device identifier.", required = true)
    private String kind;

    // activation KORE field

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
        StringBuilder builder = new StringBuilder();
        builder.append("MidWayDeviceId [id=");
        builder.append(id);
        builder.append(", kind=");
        builder.append(kind);
        builder.append("]");
        return builder.toString();
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
        MidWayDeviceId other = (MidWayDeviceId) obj;
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
