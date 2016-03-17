package com.gv.midway.exception;

public class VerizonSessionTokenExpirationException extends Exception {
	
	private String code;
	private String reason;
	
	public VerizonSessionTokenExpirationException(){
		
	}
	
	public VerizonSessionTokenExpirationException(String code, String reason){
		this.code = code;
		this.reason = reason;
	}

}
