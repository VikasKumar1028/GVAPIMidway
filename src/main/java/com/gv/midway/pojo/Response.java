package com.gv.midway.pojo;

public class Response {

	private String responseCode;

	private String responseStatus;
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
