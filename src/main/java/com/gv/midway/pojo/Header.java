package com.gv.midway.pojo;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class Header {
	@ApiModelProperty(value = "Region for the request.")
	private String region;
	
	@ApiModelProperty(value = "Date and time of the request.")
	private String timestamp;
	
	@ApiModelProperty(value = "Organization name of the request.")
	private String organization;
	
	@ApiModelProperty(value = "Unique id of the entire flow for the request.")
	private String transactionId;
	
	@ApiModelProperty(value = "Name of the source from where the request is triggered.")
	private String sourceName;
	
	@ApiModelProperty(value = "Mode of the request triggered.")
	private String applicationName;
	
	@ApiModelProperty(value = "Target System of the request.")
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result
				+ ((bsCarrier == null) ? 0 : bsCarrier.hashCode());
		result = prime * result
				+ ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result
				+ ((sourceName == null) ? 0 : sourceName.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
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
		Header other = (Header) obj;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (bsCarrier == null) {
			if (other.bsCarrier != null)
				return false;
		} else if (!bsCarrier.equals(other.bsCarrier))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Header [region=");
		builder.append(region);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append(", organization=");
		builder.append(organization);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", sourceName=");
		builder.append(sourceName);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", bsCarrier=");
		builder.append(bsCarrier);
		builder.append("]");
		return builder.toString();
	}

	

}
