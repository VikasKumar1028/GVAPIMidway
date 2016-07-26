package com.gv.midway.exception;

public class KoreSimMissingException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String code;
    private String reason;

    KoreSimMissingException() {
    }

    public KoreSimMissingException(String code, String reason) {
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
