package com.gv.midway.pojo.activateDevice.response;

public class ActivateDeviceResponseDataArea{
	
	    public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

		private String requestId;

		@Override
		public String toString() {
			return "ActivateDeviceResponseDataArea [requestId=" + requestId
					+ "]";
		}
	   /* private String midwayMasterDeviceId;

	    private String netSuiteId;*/

	  
	
}