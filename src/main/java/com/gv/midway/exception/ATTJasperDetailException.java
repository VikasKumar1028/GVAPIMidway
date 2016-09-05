package com.gv.midway.exception;

public class ATTJasperDetailException {

	@Override
	public String toString() {
		return "DetailException [requestId=" + requestId + ", error=" + error
				+ ", exception=" + exception + ", message=" + message + "]";
	}
	public boolean isOverageLimitReached() {
		return overageLimitReached;
	}

	public void setOverageLimitReached(boolean overageLimitReached) {
		this.overageLimitReached = overageLimitReached;
	}
	private boolean overageLimitReached;
	private String requestId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String error;
	private String exception;
	private String message;
}
