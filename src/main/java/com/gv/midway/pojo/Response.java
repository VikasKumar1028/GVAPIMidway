package com.gv.midway.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Response {

	@ApiModelProperty(value = "Response code for triggered request.")
	private Integer responseCode;

	@ApiModelProperty(value = "Reesponse status : SUCCESS or FAILURE")
	private String responseStatus;

	@ApiModelProperty(value = "Details of the response status received.")
	private String responseDescription;

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime
				* result
				+ ((responseDescription == null) ? 0 : responseDescription
						.hashCode());
		result = prime * result
				+ ((responseStatus == null) ? 0 : responseStatus.hashCode());
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
		Response other = (Response) obj;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (responseDescription == null) {
			if (other.responseDescription != null)
				return false;
		} else if (!responseDescription.equals(other.responseDescription))
			return false;
		if (responseStatus == null) {
			if (other.responseStatus != null)
				return false;
		} else if (!responseStatus.equals(other.responseStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", responseStatus="
				+ responseStatus + ", responseDescription="
				+ responseDescription + "]";
	}


}
