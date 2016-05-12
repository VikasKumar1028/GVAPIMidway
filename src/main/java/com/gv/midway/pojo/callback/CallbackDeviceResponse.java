package com.gv.midway.pojo.callback;

import java.util.Arrays;

import com.gv.midway.pojo.callback.response.activate.CallbackActivateResponse;
import com.gv.midway.pojo.callback.response.deactivate.CallbackDeactivateResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class CallbackDeviceResponse {
	@ApiModelProperty(value = "Device Suspend Response from callback")
	private CallbackSuspendResponse suspendResponse;

	@ApiModelProperty(value = "Prl Information Response from Callback")
	private CallbackPrlInformationResponse prlInformationResponse;

	@ApiModelProperty(value = "Activate Device Response from Callback")
	private CallbackActivateResponse activateResponse;

	@ApiModelProperty(value = "Restore Device Response from Callback")
	private CallbackRestoreResponse restoreResponse;

	@ApiModelProperty(value = "SMS Response from Callback")
	private CallbackSmsDeliveryResponse smsDeliveryResponse;

	@ApiModelProperty(value = "Deactivate Response from Callback")
	private CallbackDeactivateResponse deactivateResponse;

	@ApiModelProperty(value = "usageResponse Response from Callback")
	private String[] usageResponse;


	public CallbackSuspendResponse getSuspendResponse() {
		return suspendResponse;
	}

	public void setSuspendResponse(CallbackSuspendResponse suspendResponse) {
		this.suspendResponse = suspendResponse;
	}

	public CallbackPrlInformationResponse getPrlInformationResponse() {
		return prlInformationResponse;
	}

	public void setPrlInformationResponse(
			CallbackPrlInformationResponse prlInformationResponse) {
		this.prlInformationResponse = prlInformationResponse;
	}

	public CallbackActivateResponse getActivateResponse() {
		return activateResponse;
	}

	public void setActivateResponse(CallbackActivateResponse activateResponse) {
		this.activateResponse = activateResponse;
	}

	public CallbackRestoreResponse getRestoreResponse() {
		return restoreResponse;
	}

	public void setRestoreResponse(CallbackRestoreResponse restoreResponse) {
		this.restoreResponse = restoreResponse;
	}

	public CallbackSmsDeliveryResponse getSmsDeliveryResponse() {
		return smsDeliveryResponse;
	}

	public void setSmsDeliveryResponse(
			CallbackSmsDeliveryResponse smsDeliveryResponse) {
		this.smsDeliveryResponse = smsDeliveryResponse;
	}

	public CallbackDeactivateResponse getDeactivateResponse() {
		return deactivateResponse;
	}

	public void setDeactivateResponse(
			CallbackDeactivateResponse deactivateResponse) {
		this.deactivateResponse = deactivateResponse;
	}

	@Override
	public String toString() {
		return "CallbackDeviceResponse [suspendResponse=" + suspendResponse
				+ ", prlInformationResponse=" + prlInformationResponse
				+ ", activateResponse=" + activateResponse
				+ ", restoreResponse=" + restoreResponse
				+ ", smsDeliveryResponse=" + smsDeliveryResponse
				+ ", deactivateResponse=" + deactivateResponse
				+ ", usageResponse=" + Arrays.toString(usageResponse) + "]";
	}

	public String[] getUsageResponse() {
		return usageResponse;
	}

	public void setUsageResponse(String[] usageResponse) {
		this.usageResponse = usageResponse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((activateResponse == null) ? 0 : activateResponse.hashCode());
		result = prime
				* result
				+ ((deactivateResponse == null) ? 0 : deactivateResponse
						.hashCode());
		result = prime
				* result
				+ ((prlInformationResponse == null) ? 0
						: prlInformationResponse.hashCode());
		result = prime * result
				+ ((restoreResponse == null) ? 0 : restoreResponse.hashCode());
		result = prime
				* result
				+ ((smsDeliveryResponse == null) ? 0 : smsDeliveryResponse
						.hashCode());
		result = prime * result
				+ ((suspendResponse == null) ? 0 : suspendResponse.hashCode());
		result = prime * result + Arrays.hashCode(usageResponse);
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
		CallbackDeviceResponse other = (CallbackDeviceResponse) obj;
		if (activateResponse == null) {
			if (other.activateResponse != null)
				return false;
		} else if (!activateResponse.equals(other.activateResponse))
			return false;
		if (deactivateResponse == null) {
			if (other.deactivateResponse != null)
				return false;
		} else if (!deactivateResponse.equals(other.deactivateResponse))
			return false;
		if (prlInformationResponse == null) {
			if (other.prlInformationResponse != null)
				return false;
		} else if (!prlInformationResponse.equals(other.prlInformationResponse))
			return false;
		if (restoreResponse == null) {
			if (other.restoreResponse != null)
				return false;
		} else if (!restoreResponse.equals(other.restoreResponse))
			return false;
		if (smsDeliveryResponse == null) {
			if (other.smsDeliveryResponse != null)
				return false;
		} else if (!smsDeliveryResponse.equals(other.smsDeliveryResponse))
			return false;
		if (suspendResponse == null) {
			if (other.suspendResponse != null)
				return false;
		} else if (!suspendResponse.equals(other.suspendResponse))
			return false;
		if (!Arrays.equals(usageResponse, other.usageResponse))
			return false;
		return true;
	}

	

}
