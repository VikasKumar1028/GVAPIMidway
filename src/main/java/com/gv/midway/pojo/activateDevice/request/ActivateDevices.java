package com.gv.midway.pojo.activateDevice.request;

import java.util.Arrays;
import com.gv.midway.pojo.verizon.Address;
import com.gv.midway.pojo.verizon.CustomFields;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ActivateDevices {

	@ApiModelProperty(value = "Having type and value of device identifier.", required = true)
    private ActivateDeviceId[] deviceIds;

    @ApiModelProperty(value = "Device NetSuite Id.", required = true)
    private Integer netSuiteId;

    @ApiModelProperty(value = "Service Plan that that device belongs to in Case of Verizon or EAP code for Kore.", required = true)
    private String servicePlan;

    @ApiModelProperty(value = "The custom fields and values that have been set for the device.")
    private CustomFields[] customFields;

    @ApiModelProperty(value = "The residential street address or the primary business street address of the Customer and Customer Name . Leave these fields empty to use the account profile address as the primary place of use. These values will be applied to all devices in the activation request.If the account is enabled for non-geographic MDNs and the device supports it, the primaryPlaceOfUse address will also be used to derive the MDN for the device.The Primary Place of Use location may affect taxation or have other legal implications. You may want to speak with legal and/or financial advisers before entering values for these fields.primaryPlaceOfUse cannot be used with leadId. VPP partners should enter a leadId value for a customer lead, and the AddressZipCode in the lead record will be used for taxation. VPP partners can use primaryPlaceOfUse fields without a leadId to associate customer-specific data with devices.")
    private Address address;

    @ApiModelProperty(value = "Mapped to first name to Verizon Devices.")
    private String serialNumber;

    @ApiModelProperty(value = "Mapped to Last name to Verizon Devices.")
    private String macAddress;

    @ApiModelProperty(value = "Middle name of the Customer for Verizon Devices")
    private String middleName;

    @ApiModelProperty(value = "Title for the Customer. Eg: Mr , Mrs , etc")
    private String title;

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public CustomFields[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields[] customFields) {
        this.customFields = customFields;
    }

    public ActivateDeviceId[] getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(ActivateDeviceId[] deviceIds) {
        this.deviceIds = deviceIds;
    }

    public Integer getNetSuiteId() {
        return netSuiteId;
    }

    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + Arrays.hashCode(customFields);
        result = prime * result + Arrays.hashCode(deviceIds);
        result = prime * result
                + ((macAddress == null) ? 0 : macAddress.hashCode());
        result = prime * result
                + ((middleName == null) ? 0 : middleName.hashCode());
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
        result = prime * result
                + ((serialNumber == null) ? 0 : serialNumber.hashCode());
        result = prime * result
                + ((servicePlan == null) ? 0 : servicePlan.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        ActivateDevices other = (ActivateDevices) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (!Arrays.equals(customFields, other.customFields))
            return false;
        if (!Arrays.equals(deviceIds, other.deviceIds))
            return false;
        if (macAddress == null) {
            if (other.macAddress != null)
                return false;
        } else if (!macAddress.equals(other.macAddress))
            return false;
        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        if (serialNumber == null) {
            if (other.serialNumber != null)
                return false;
        } else if (!serialNumber.equals(other.serialNumber))
            return false;
        if (servicePlan == null) {
            if (other.servicePlan != null)
                return false;
        } else if (!servicePlan.equals(other.servicePlan))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ActivateDevices [deviceIds=");
        builder.append(Arrays.toString(deviceIds));
        builder.append(", netSuiteId=");
        builder.append(netSuiteId);
        builder.append(", servicePlan=");
        builder.append(servicePlan);
        builder.append(", customFields=");
        builder.append(Arrays.toString(customFields));
        builder.append(", address=");
        builder.append(address);
        builder.append(", serialNumber=");
        builder.append(serialNumber);
        builder.append(", macAddress=");
        builder.append(macAddress);
        builder.append(", middleName=");
        builder.append(middleName);
        builder.append(", title=");
        builder.append(title);
        builder.append("]");
        return builder.toString();
    }

    /*
     * @Override public String toString() { StringBuilder builder = new
     * StringBuilder(); builder.append("ActivateDevices [deviceIds=");
     * builder.append(Arrays.toString(deviceIds));
     * builder.append(", netSuiteId="); builder.append(netSuiteId);
     * builder.append("]"); return builder.toString(); }
     * 
     * @Override public int hashCode() { final int prime = 31; int result = 1;
     * result = prime * result + Arrays.hashCode(deviceIds); result = prime *
     * result + ((netSuiteId == null) ? 0 : netSuiteId.hashCode()); return
     * result; }
     * 
     * @Override public boolean equals(Object obj) { if (this == obj) return
     * true; if (obj == null) return false; if (getClass() != obj.getClass())
     * return false; ActivateDevices other = (ActivateDevices) obj; if
     * (!Arrays.equals(deviceIds, other.deviceIds)) return false; if (netSuiteId
     * == null) { if (other.netSuiteId != null) return false; } else if
     * (!netSuiteId.equals(other.netSuiteId)) return false; return true; }
     */

}
