package com.gv.midway.pojo.callback.request;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.callback.CallbackDeviceResponse;
import com.gv.midway.pojo.callback.CallbackFaultResponse;
import com.gv.midway.pojo.callback.CallbackSummary;
import com.gv.midway.pojo.verizon.DeviceId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CallBackVerizonRequest {
    @ApiModelProperty(value = "Summary of requested device callback")
    private CallbackSummary summary;

    @ApiModelProperty(value = "The User Name returned in callback message.Use it to authenticate callback messages ,")
    private String username;

    @ApiModelProperty(value = "Request Id for Callback")
    private String requestId;

    @ApiModelProperty(value = "Callback Fault Response Details")
    private CallbackFaultResponse faultResponse;

    @ApiModelProperty(value = "All Identifier for the device")
    private DeviceId[] deviceIds;

    @ApiModelProperty(value = "Response of Callback for Requested Device")
    private CallbackDeviceResponse deviceResponse;

    @ApiModelProperty(value = "Callback Comment")
    private String comment;

    @ApiModelProperty(value = "Callback SMS Message")
    private String smsMessage;

    @ApiModelProperty(value = "Password for authentication  of Callback message.")
    private String password;

    public CallbackSummary getSummary() {
        return summary;
    }

    public void setSummary(CallbackSummary summary) {
        this.summary = summary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public CallbackFaultResponse getFaultResponse() {
        return faultResponse;
    }

    public void setFaultResponse(CallbackFaultResponse faultResponse) {
        this.faultResponse = faultResponse;
    }

    public DeviceId[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(DeviceId[] deviceIds) {
        this.deviceIds = deviceIds;
    }

    public CallbackDeviceResponse getDeviceResponse() {
        return deviceResponse;
    }

    public void setDeviceResponse(CallbackDeviceResponse deviceResponse) {
        this.deviceResponse = deviceResponse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClassPojo [summary = " + summary + ", username = " + username
                + ", requestId = " + requestId + ", faultResponse = "
                + faultResponse + ", deviceIds = " + deviceIds
                + ", deviceResponse = " + deviceResponse + ", comment = "
                + comment + ", smsMessage = " + smsMessage + ", password = "
                + password + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + Arrays.hashCode(deviceIds);
        result = prime * result
                + ((deviceResponse == null) ? 0 : deviceResponse.hashCode());
        result = prime * result
                + ((faultResponse == null) ? 0 : faultResponse.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((requestId == null) ? 0 : requestId.hashCode());
        result = prime * result
                + ((smsMessage == null) ? 0 : smsMessage.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CallBackVerizonRequest))
            return false;
        CallBackVerizonRequest other = (CallBackVerizonRequest) obj;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (!Arrays.equals(deviceIds, other.deviceIds))
            return false;
        if (deviceResponse == null) {
            if (other.deviceResponse != null)
                return false;
        } else if (!deviceResponse.equals(other.deviceResponse))
            return false;
        if (faultResponse == null) {
            if (other.faultResponse != null)
                return false;
        } else if (!faultResponse.equals(other.faultResponse))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (requestId == null) {
            if (other.requestId != null)
                return false;
        } else if (!requestId.equals(other.requestId))
            return false;
        if (smsMessage == null) {
            if (other.smsMessage != null)
                return false;
        } else if (!smsMessage.equals(other.smsMessage))
            return false;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
