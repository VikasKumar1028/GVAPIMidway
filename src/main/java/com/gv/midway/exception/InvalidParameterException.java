package com.gv.midway.exception;


public class InvalidParameterException extends Exception{
	
	private String code;
	private String reason;
	
	InvalidParameterException(){
	}
	
	public InvalidParameterException(String code, String reason){
		this.code = code;
		this.reason = reason;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
