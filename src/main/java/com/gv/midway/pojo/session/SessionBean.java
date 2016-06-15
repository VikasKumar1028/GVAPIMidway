package com.gv.midway.pojo.session;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vzSessionDetail")
public class SessionBean {
	
	@Id
	private String id;
	String vzSessionToken;
	String vzAuthorizationToken;
	String isValid;
	String ipAddress;
	public String getVzSessionToken() {
		return vzSessionToken;
	}
	public void setVzSessionToken(String vzSessionToken) {
		this.vzSessionToken = vzSessionToken;
	}
	public String getVzAuthorizationToken() {
		return vzAuthorizationToken;
	}
	public void setVzAuthorizationToken(String vzAuthorizationToken) {
		this.vzAuthorizationToken = vzAuthorizationToken;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	
}
