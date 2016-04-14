package com.gv.midway.pojo.verizon;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class CustomFields {
	@ApiModelProperty(value = "The value of the custom field. The value is not case-sensitive.Wildcards and partial matches are not supported.", required = true)
	private String value;

	@ApiModelProperty(value = "The name of the custom field. Valid names are CustomField1, CustomField2, CustomField3, CustomField4, and CustomField5.", required = true)
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
		CustomFields other = (CustomFields) obj;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomFields [value=");
		builder.append(value);
		builder.append(", key=");
		builder.append(key);
		builder.append("]");
		return builder.toString();
	}

	
}
