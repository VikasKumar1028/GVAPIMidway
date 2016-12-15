package com.gv.midway.pojo.changeDeviceServicePlans.verizon.request;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.KeyValuePair;
import com.gv.midway.pojo.verizon.Devices;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ChangeDeviceServicePlansRequestVerizon {

    @ApiModelProperty(value = "Name of the billing account.")
    private String accountName;

    @ApiModelProperty(value = "The custom fields and values that have been set for the device.")
    private KeyValuePair[] customFields;

    @ApiModelProperty(value = "All identifiers for the device.")
    private Devices[] devices;

    @ApiModelProperty(value = "Service Plan that that device belongs to.", required = true)
    private String servicePlan;

    @ApiModelProperty(value = "The device group that the requested device belongs to.")
    private String groupName;

    @ApiModelProperty(value = "The device group that the requested device belongs to.")
    private String currentServicePlan;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public KeyValuePair[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(KeyValuePair[] customFields) {
        this.customFields = customFields;
    }

    public Devices[] getDevices() {
        return devices;
    }

    public void setDevices(Devices[] devices) {
        this.devices = devices;
    }

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCurrentServicePlan() {
        return currentServicePlan;
    }

    public void setCurrentServicePlan(String currentServicePlan) {
        this.currentServicePlan = currentServicePlan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountName == null) ? 0 : accountName.hashCode());
        result = prime
                * result
                + ((currentServicePlan == null) ? 0 : currentServicePlan
                        .hashCode());
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
        ChangeDeviceServicePlansRequestVerizon other = (ChangeDeviceServicePlansRequestVerizon) obj;
        if (accountName == null) {
            if (other.accountName != null)
                return false;
        } else if (!accountName.equals(other.accountName))
            return false;
        if (currentServicePlan == null) {
            if (other.currentServicePlan != null)
                return false;
        } else if (!currentServicePlan.equals(other.currentServicePlan))
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
        return "ChangeDeviceServicePlansRequestVerizon [accountName="
                + accountName + ", customFields="
                + Arrays.toString(customFields) + ", devices="
                + Arrays.toString(devices) + ", servicePlan=" + servicePlan
                + ", groupName=" + groupName + ", currentServicePlan="
                + currentServicePlan + "]";
    }
}
