package com.gv.midway.pojo;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class Response {

	@ApiModelProperty(value = "Response code for triggered request.")
	private String responseCode;

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

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", responseStatus="
				+ responseStatus + ", responseDescription="
				+ responseDescription + "]";
	}
}
