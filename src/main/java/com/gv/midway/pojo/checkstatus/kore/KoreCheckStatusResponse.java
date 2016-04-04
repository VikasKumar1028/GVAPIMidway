package com.gv.midway.pojo.checkstatus.kore;

public class KoreCheckStatusResponse {
	
	private ResponseCode responseCode;
	
	private String provisioningRequestStatus;

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public String getProvisioningRequestStatus() {
		return provisioningRequestStatus;
	}

	public void setProvisioningRequestStatus(String provisioningRequestStatus) {
		this.provisioningRequestStatus = provisioningRequestStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((provisioningRequestStatus == null) ? 0
						: provisioningRequestStatus.hashCode());
		result = prime * result
				+ ((responseCode == null) ? 0 : responseCode.hashCode());
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
		KoreCheckStatusResponse other = (KoreCheckStatusResponse) obj;
		if (provisioningRequestStatus == null) {
			if (other.provisioningRequestStatus != null)
				return false;
		} else if (!provisioningRequestStatus
				.equals(other.provisioningRequestStatus))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KoreCheckStatusResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", provisioningRequestStatus=");
		builder.append(provisioningRequestStatus);
		builder.append("]");
		return builder.toString();
	}

	
}
