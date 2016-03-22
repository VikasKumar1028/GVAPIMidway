package com.gv.midway.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
public class ResponseHeader {
	
	@ApiModelProperty(value = "Region for the request")
	private String region;

	@ApiModelProperty(value = "Timestamp for the triggered request.")
	private String timestamp;

	@ApiModelProperty(value = "Organisation name for the requests")
	private String organization;

	@ApiModelProperty(value = "Unique Identifier of the complete flow for the requests.")
	private String transactionId;

	@ApiModelProperty(value = "Name of the source from where the request is trigger.")
	private String sourceName;

	@ApiModelProperty(value = "Mode of the request")
	private String applicationName;

	@ApiModelProperty(value = "Target System of request")
	private String bsCarrier;

	public String getBsCarrier() {
		return bsCarrier;
	}

	public void setBsCarrier(String bsCarrier) {
		this.bsCarrier = bsCarrier;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		return "Header [region=" + region + ", timestamp=" + timestamp
				+ ", organization=" + organization + ", transactionId="
				+ transactionId + ", sourceName=" + sourceName
				+ ", applicationName=" + applicationName + ", bsCarrier="
				+ bsCarrier + "]";
	}

}
