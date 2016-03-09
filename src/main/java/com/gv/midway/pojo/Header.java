package com.gv.midway.pojo;

public class Header {
	private String region;

	private String timestamp;

	private String organization;

	private String transactionId;

	private String sourceName;

	private String applicationName;

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
