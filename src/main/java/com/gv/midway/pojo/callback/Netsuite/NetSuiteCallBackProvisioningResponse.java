package com.gv.midway.pojo.callback.Netsuite;

import java.util.Arrays;

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.verizon.DeviceId;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class NetSuiteCallBackProvisioningResponse {
	
	@ApiModelProperty(value = "Having type and value of device identifier.")
	private DeviceId[] deviceIds;
	
	
	@ApiModelProperty(value = "Midway Transaction Id.")
	private String carrierOrderNumber;
	
	@ApiModelProperty(value = "Type of Request - Activation, Deactivation etc.")
	private RequestType requestType;
	
	@ApiModelProperty(value = "NetSuite Id of the device.")
	private String netSuiteID;
	
	@ApiModelProperty(value = "Device CallBack status.")
	private String status;
	
	@ApiModelProperty(value = "Device CallBack status message.")
	private String response;

	public DeviceId[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(DeviceId[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String getCarrierOrderNumber() {
		return carrierOrderNumber;
	}

	public void setCarrierOrderNumber(String carrierOrderNumber) {
		this.carrierOrderNumber = carrierOrderNumber;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String getNetSuiteID() {
		return netSuiteID;
	}

	public void setNetSuiteID(String netSuiteID) {
		this.netSuiteID = netSuiteID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((carrierOrderNumber == null) ? 0 : carrierOrderNumber
						.hashCode());
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result
				+ ((netSuiteID == null) ? 0 : netSuiteID.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		NetSuiteCallBackProvisioningResponse other = (NetSuiteCallBackProvisioningResponse) obj;
		if (carrierOrderNumber == null) {
			if (other.carrierOrderNumber != null)
				return false;
		} else if (!carrierOrderNumber.equals(other.carrierOrderNumber))
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (netSuiteID == null) {
			if (other.netSuiteID != null)
				return false;
		} else if (!netSuiteID.equals(other.netSuiteID))
			return false;
		if (requestType != other.requestType)
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NetSuiteCallBackProvisioningResponse [deviceIds=");
		builder.append(Arrays.toString(deviceIds));
		builder.append(", carrierOrderNumber=");
		builder.append(carrierOrderNumber);
		builder.append(", requestType=");
		builder.append(requestType);
		builder.append(", netSuiteID=");
		builder.append(netSuiteID);
		builder.append(", status=");
		builder.append(status);
		builder.append(", response=");
		builder.append(response);
		builder.append("]");
		return builder.toString();
	}
	
	

}
