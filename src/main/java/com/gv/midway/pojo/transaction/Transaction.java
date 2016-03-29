package com.gv.midway.pojo.transaction;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactionalDetail")
public class Transaction {
//	String audit_id;
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
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
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
