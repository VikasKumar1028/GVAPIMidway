package com.gv.midway.pojo.activateDevice.request;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.PrimaryPlaceOfUse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivateDeviceRequestDataArea {

    @ApiModelProperty(value = "VERIZON: The device group that the requested device belongs to. This parameter can serve either of two purposes:If you specify devices by ID in the devices paramters, this is the name of a device group that the devices should be added to. They will be in the default device group if you don't specify one.If you don't specify individual devices with the devices parameter,you can provide the name of a device group to activate all devices in that group")
    private String groupName;

    @ApiModelProperty(value = "Name of the billing account.This parameter is only required if the UWS account used for the current API session has access to multiple billing accounts")
    private String accountName;

    @ApiModelProperty(value = "VERIZON : Stock Keeping Unit(SKU) number of a 4G device. The Stock Keeping Unit (SKU) number of a 4G device type with an embedded SIM. Can be used with ICCID device identifiers in lieu of an IMEI when activating 4G devices. The SkuNumber will be used with all devices in the request, so all devices must be of the same type.Only 4G devices with embedded SIMs can be activated by SKU at this time")
    private String skuNumber;

    @ApiModelProperty(value = "VERIZON : Name of cost Center Code.")
    private String costCenterCode;

    @ApiModelProperty(value = "VERIZON : Name of the carrier Ip Pool.")
    private String carrierIpPoolName;

    @ApiModelProperty(value = "VERIZON : The device lead id that the device belongs to.The ID of a Qualified or Closed - Won VPP customer lead, which is used with other values to determine MDN assignment, taxation, and compensation.This parameter is required when activating devices for Verizon Partner Program accounts, and it is not allowed for other activations.If you include leadId in an activation request, you should set the mdnZipCode value to match the zip value in the address returned for the lead.")
    private String leadId;

    @ApiModelProperty(value = "The name of the Carrier.", required = true)
    private String carrierName;

    @ApiModelProperty(value = "VERIZON : Name of Public IP Restriction.It may be restricted/unrestricted.If left blank, the device will get the default value set for the account. Public network devices with dynamic IP addresses are always unrestricted.")
    private String publicIpRestriction;

    @ApiModelProperty(value = "MDN zip code number", required = true)
    private String mdnZipCode;

    @ApiModelProperty(value = "All identifiers for the device.", required = true)
    private ActivateDevices devices;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getCostCenterCode() {
        return costCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        this.costCenterCode = costCenterCode;
    }

    public String getCarrierIpPoolName() {
        return carrierIpPoolName;
    }

    public void setCarrierIpPoolName(String carrierIpPoolName) {
        this.carrierIpPoolName = carrierIpPoolName;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getPublicIpRestriction() {
        return publicIpRestriction;
    }

    public void setPublicIpRestriction(String publicIpRestriction) {
        this.publicIpRestriction = publicIpRestriction;
    }

    public String getMdnZipCode() {
        return mdnZipCode;
    }

    public void setMdnZipCode(String mdnZipCode) {
        this.mdnZipCode = mdnZipCode;
    }

    public ActivateDevices getDevices() {
        return devices;
    }

    public void setDevices(ActivateDevices devices) {
        this.devices = devices;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountName == null) ? 0 : accountName.hashCode());
        result = prime
                * result
                + ((carrierIpPoolName == null) ? 0 : carrierIpPoolName
                        .hashCode());
        result = prime * result
                + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result
                + ((costCenterCode == null) ? 0 : costCenterCode.hashCode());
        result = prime * result + devices.hashCode();
        result = prime * result
                + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result + ((leadId == null) ? 0 : leadId.hashCode());
        result = prime * result
                + ((mdnZipCode == null) ? 0 : mdnZipCode.hashCode());

        result = prime
                * result
                + ((publicIpRestriction == null) ? 0 : publicIpRestriction
                        .hashCode());

        result = prime * result
                + ((skuNumber == null) ? 0 : skuNumber.hashCode());
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
        ActivateDeviceRequestDataArea other = (ActivateDeviceRequestDataArea) obj;
        if (accountName == null) {
            if (other.accountName != null)
                return false;
        } else if (!accountName.equals(other.accountName))
            return false;
        if (carrierIpPoolName == null) {
            if (other.carrierIpPoolName != null)
                return false;
        } else if (!carrierIpPoolName.equals(other.carrierIpPoolName))
            return false;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (costCenterCode == null) {
            if (other.costCenterCode != null)
                return false;
        } else if (!costCenterCode.equals(other.costCenterCode))
            return false;

        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        if (leadId == null) {
            if (other.leadId != null)
                return false;
        } else if (!leadId.equals(other.leadId))
            return false;
        if (mdnZipCode == null) {
            if (other.mdnZipCode != null)
                return false;
        } else if (!mdnZipCode.equals(other.mdnZipCode))
            return false;

        if (publicIpRestriction == null) {
            if (other.publicIpRestriction != null)
                return false;
        } else if (!publicIpRestriction.equals(other.publicIpRestriction))
            return false;

        if (skuNumber == null) {
            if (other.skuNumber != null)
                return false;
        } else if (!skuNumber.equals(other.skuNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ActivateDeviceRequestDataArea [groupName=" + groupName
                + ", accountName=" + accountName + ", skuNumber=" + skuNumber
                + ", costCenterCode=" + costCenterCode + ", carrierIpPoolName="
                + carrierIpPoolName + ", leadId=" + leadId + ", carrierName="
                + carrierName + ", publicIpRestriction=" + publicIpRestriction
                + ", mdnZipCode=" + mdnZipCode + "]";
    }

}
