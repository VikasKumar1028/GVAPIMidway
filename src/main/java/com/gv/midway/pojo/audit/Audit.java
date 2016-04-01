package com.gv.midway.pojo.audit;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "audit")
public class Audit {

	private String api_OpreationName;
	private String from;
	private String to;
	private Date timeStamp;
	private String status;
	private String auditTransationID;
	private String Payload;
	private String errorProblem;
	private String errorCode;
	private String errorDetais;
	private String hostName;

	public String getApi_OpreationName() {
		return api_OpreationName;
	}

	public void setApi_OpreationName(String api_OpreationName) {
		this.api_OpreationName = api_OpreationName;
	}

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

	public String getAuditTransationID() {
		return auditTransationID;
	}

	public void setAuditTransationID(String auditTransationID) {
		this.auditTransationID = auditTransationID;
	}

	public String getPayload() {
		return Payload;
	}

	public void setPayload(String payload) {
		Payload = payload;
	}

	public String getErrorProblem() {
		return errorProblem;
	}

	public void setErrorProblem(String errorProblem) {
		this.errorProblem = errorProblem;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetais() {
		return errorDetais;
	}

	public void setErrorDetais(String errorDetais) {
		this.errorDetais = errorDetais;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Payload == null) ? 0 : Payload.hashCode());
		result = prime
				* result
				+ ((api_OpreationName == null) ? 0 : api_OpreationName
						.hashCode());
		result = prime
				* result
				+ ((auditTransationID == null) ? 0 : auditTransationID
						.hashCode());
		result = prime * result
				+ ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result
				+ ((errorDetais == null) ? 0 : errorDetais.hashCode());
		result = prime * result
				+ ((errorProblem == null) ? 0 : errorProblem.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
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
		if (api_OpreationName == null) {
			if (other.api_OpreationName != null)
				return false;
		} else if (!api_OpreationName.equals(other.api_OpreationName))
			return false;
		if (auditTransationID == null) {
			if (other.auditTransationID != null)
				return false;
		} else if (!auditTransationID.equals(other.auditTransationID))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (errorDetais == null) {
			if (other.errorDetais != null)
				return false;
		} else if (!errorDetais.equals(other.errorDetais))
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
		return "Audit [api_OpreationName=" + api_OpreationName + ", from="
				+ from + ", to=" + to + ", timeStamp=" + timeStamp
				+ ", status=" + status + ", auditTransationID="
				+ auditTransationID + ", Payload=" + Payload
				+ ", errorProblem=" + errorProblem + ", errorCode=" + errorCode
				+ ", errorDetais=" + errorDetais + ", hostName=" + hostName
				+ "]";
	}
}
