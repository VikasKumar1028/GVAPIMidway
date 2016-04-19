package com.gv.midway.pojo.callback;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.DeviceId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetDeviceResponseDataArea {
	@ApiModelProperty(value = "Midway Transaction Id.")
	private String midwayTransactionId;
	private String requestId;
	private DeviceId [] deviceIds;
	private CallbackDeviceResponse callbackDeviceResponse;
	
	

	public CallbackDeviceResponse getCallbackDeviceResponse() {
		return callbackDeviceResponse;
	}

	public void setCallbackDeviceResponse(CallbackDeviceResponse callbackDeviceResponse) {
		this.callbackDeviceResponse = callbackDeviceResponse;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getMidwayTransactionId() {
		return midwayTransactionId;
	}

	public void setMidwayTransactionId(String midwayTransactionId) {
		this.midwayTransactionId = midwayTransactionId;
	}

	
	
}
