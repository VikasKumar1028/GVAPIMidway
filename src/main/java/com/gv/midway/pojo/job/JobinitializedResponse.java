package com.gv.midway.pojo.job;


import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobinitializedResponse  {

	@ApiModelProperty(value = "message Job initialized status response")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		JobinitializedResponse other = (JobinitializedResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobinitializedResponse [message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

	

}
