package com.gv.midway.pojo.suspendDevice.verizon.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.KeyValuePair;
import com.gv.midway.pojo.verizon.Devices;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class SuspendDeviceRequestVerizon {

    @ApiModelProperty(value = "All identifiers for the device.", required = true)
    private Devices[] devices;

    @ApiModelProperty(value = "Name of the billing account.This parameter is only required if the UWS account used for the current API session has access to multiple billing accounts")
    private String accountName;

    @ApiModelProperty(value = "VERIZON: The device group that the requested device belongs to. This parameter can serve either of two purposes:If you specify devices by ID in the devices paramters, this is the name of a device group that the devices should be added to. They will be in the default device group if you don't specify one.If you don't specify individual devices with the devices parameter,you can provide the name of a device group to activate all devices in that group")
    private String groupName;

    @ApiModelProperty(value = "Service Plan that that device belongs to.Verizon Wireless provides service plan codes at the time of on-boarding and subsequently whenever there are any changes to the service plan. NOTE:  Any devices in the request that are not supported by the service plan will not activate. For example, if the service plan is only for 4G devices, any 3G devices included in the activation request will fail.", required = true)
    private String servicePlan;

    @ApiModelProperty(value = "The custom fields and values that have been set for the device.")
    private KeyValuePair[] customFields;

    public Devices[] getDevices() {
        return devices;
    }

    public void setDevices(Devices[] devices) {
        this.devices = devices;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public KeyValuePair[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(KeyValuePair[] customFields) {
        this.customFields = customFields;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountName == null) ? 0 : accountName.hashCode());
        result = prime * result + Arrays.hashCode(customFields);
        result = prime * result + Arrays.hashCode(devices);
        result = prime * result
                + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result
                + ((servicePlan == null) ? 0 : servicePlan.hashCode());
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
        SuspendDeviceRequestVerizon other = (SuspendDeviceRequestVerizon) obj;
        if (accountName == null) {
            if (other.accountName != null)
                return false;
        } else if (!accountName.equals(other.accountName))
            return false;
        if (!Arrays.equals(customFields, other.customFields))
            return false;
        if (!Arrays.equals(devices, other.devices))
            return false;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        if (servicePlan == null) {
            if (other.servicePlan != null)
                return false;
        } else if (!servicePlan.equals(other.servicePlan))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SuspendDeviceResponseVerizon [devices="
                + Arrays.toString(devices) + ", accountName=" + accountName
                + ", groupName=" + groupName + ", servicePlan=" + servicePlan
                + ", customFields=" + Arrays.toString(customFields) + "]";
    }
}
