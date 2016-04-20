package com.gv.midway.pojo.audit;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "audit")
public class Audit {

	private String apiOperationName;
	private String from;
	private String to;
	private Date timeStamp;
	//status ??
	private String status;
	private String auditTransactionId;
	private Object Payload;
	private String errorProblem;
	private Integer errorCode;
	private String errorDetails;
	//Which server it is running on
	private String hostName; 
	//GrantVitor Transaction Id Send
	private String gvTransactionId;



	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Object getPayload() {
		return Payload;
	}

	public void setPayload(Object payload) {
		Payload = payload;
	}

	public String getErrorProblem() {
		return errorProblem;
	}

	public void setErrorProblem(String errorProblem) {
		this.errorProblem = errorProblem;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}



	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}





	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getAuditTransactionId() {
		return auditTransactionId;
	}

	public void setAuditTransactionId(String auditTransactionId) {
		this.auditTransactionId = auditTransactionId;
	}

	public String getGvTransactionId() {
		return gvTransactionId;
	}

	public void setGvTransactionId(String gvTransactionId) {
		this.gvTransactionId = gvTransactionId;
	}

	public String getApiOperationName() {
		return apiOperationName;
	}

	public void setApiOperationName(String apiOperationName) {
		this.apiOperationName = apiOperationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Payload == null) ? 0 : Payload.hashCode());
		result = prime
				* result
				+ ((apiOperationName == null) ? 0 : apiOperationName.hashCode());
		result = prime
				* result
				+ ((auditTransactionId == null) ? 0 : auditTransactionId
						.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result
				+ ((errorDetails == null) ? 0 : errorDetails.hashCode());
		result = prime * result
				+ ((errorProblem == null) ? 0 : errorProblem.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result
				+ ((gvTransactionId == null) ? 0 : gvTransactionId.hashCode());
		result = prime * result
				+ ((hostName == null) ? 0 : hostName.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Audit other = (Audit) obj;
		if (Payload == null) {
			if (other.Payload != null)
				return false;
		} else if (!Payload.equals(other.Payload))
			return false;
		if (apiOperationName == null) {
			if (other.apiOperationName != null)
				return false;
		} else if (!apiOperationName.equals(other.apiOperationName))
			return false;
		if (auditTransactionId == null) {
			if (other.auditTransactionId != null)
				return false;
		} else if (!auditTransactionId.equals(other.auditTransactionId))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (errorDetails == null) {
			if (other.errorDetails != null)
				return false;
		} else if (!errorDetails.equals(other.errorDetails))
			return false;
		if (errorProblem == null) {
			if (other.errorProblem != null)
				return false;
		} else if (!errorProblem.equals(other.errorProblem))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (gvTransactionId == null) {
			if (other.gvTransactionId != null)
				return false;
		} else if (!gvTransactionId.equals(other.gvTransactionId))
			return false;
		if (hostName == null) {
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Audit [apiOperationName=");
		builder.append(apiOperationName);
		builder.append(", from=");
		builder.append(from);
		builder.append(", to=");
		builder.append(to);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", status=");
		builder.append(status);
		builder.append(", auditTransactionId=");
		builder.append(auditTransactionId);
		builder.append(", Payload=");
		builder.append(Payload);
		builder.append(", errorProblem=");
		builder.append(errorProblem);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorDetails=");
		builder.append(errorDetails);
		builder.append(", hostName=");
		builder.append(hostName);
		builder.append(", gvTransactionId=");
		builder.append(gvTransactionId);
		builder.append("]");
		return builder.toString();
	}



}
