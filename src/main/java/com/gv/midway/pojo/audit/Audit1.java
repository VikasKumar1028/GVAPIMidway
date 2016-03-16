package com.gv.midway.pojo.audit;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "audit1")
public class Audit1 {

	String audit_id;
	String carrier;
	String inboundURL;
	String outboundURL;
	String payload;
	String apiAction;
	String createdTimestamp;
	String transactionId;
	String auditType;
	String source;
	String status;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getAudit_id() {
		return audit_id;
	}

	public void setAudit_id(String audit_id) {
		this.audit_id = audit_id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getInboundURL() {
		return inboundURL;
	}

	public void setInboundURL(String inboundURL) {
		this.inboundURL = inboundURL;
	}

	public String getOutboundURL() {
		return outboundURL;
	}

	public void setOutboundURL(String outboundURL) {
		this.outboundURL = outboundURL;
	}

	public String getApiAction() {
		return apiAction;
	}

	public void setApiAction(String apiAction) {
		this.apiAction = apiAction;
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
