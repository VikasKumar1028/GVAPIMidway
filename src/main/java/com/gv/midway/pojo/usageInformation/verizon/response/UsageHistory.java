package com.gv.midway.pojo.usageInformation.verizon.response;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageHistory {
	
	private long bytesUsed;
	private int smsUsed;
	private String servicePlan;
	private String source;
	private Date timestamp;
	private ExtendedAttributes[] extendAttributes;
	

	public long getBytesUsed() {
		return bytesUsed;
	}
	public void setBytesUsed(long bytesUsed) {
		this.bytesUsed = bytesUsed;
	}
	public int getSmsUsed() {
		return smsUsed;
	}
	public void setSmsUsed(int smsUsed) {
		this.smsUsed = smsUsed;
	}
	public String getServicePlan() {
		return servicePlan;
	}
	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public ExtendedAttributes[] getExtendAttributes() {
		return extendAttributes;
	}
	public void setExtendAttributes(ExtendedAttributes[] extendAttributes) {
		this.extendAttributes = extendAttributes;
	}
	
	@Override
	public String toString() {
		return "UsageHistory [bytesUsed=" + bytesUsed + ", smsUsed=" + smsUsed
				+ ", servicePlan=" + servicePlan + ", source=" + source
				+ ", timestamp=" + timestamp + ", extendAttributes="
				+ Arrays.toString(extendAttributes) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bytesUsed ^ (bytesUsed >>> 32));
		result = prime * result + Arrays.hashCode(extendAttributes);
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
		result = prime * result + smsUsed;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
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
		UsageHistory other = (UsageHistory) obj;
		if (bytesUsed != other.bytesUsed)
			return false;
		if (!Arrays.equals(extendAttributes, other.extendAttributes))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		if (smsUsed != other.smsUsed)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
}
