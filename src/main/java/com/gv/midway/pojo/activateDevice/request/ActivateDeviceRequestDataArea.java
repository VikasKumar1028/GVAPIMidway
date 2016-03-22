package com.gv.midway.pojo.activateDevice.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gv.midway.device.response.pojo.DeviceId;
import com.gv.midway.pojo.activateDevice.verizon.Address;
import com.gv.midway.pojo.activateDevice.verizon.CustomFields;
import com.gv.midway.pojo.activateDevice.verizon.CustomerName;
import com.gv.midway.pojo.activateDevice.verizon.DeviceIds;
import com.gv.midway.pojo.activateDevice.verizon.PrimaryPlaceOfUse;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ActivateDeviceRequestDataArea
{
	private String netSuiteId;

	private String midwayMasterDeviceId;
	
	private String groupName;

    public String getNetSuiteId() {
		return netSuiteId;
	}

	public void setNetSuiteId(String netSuiteId) {
		this.netSuiteId = netSuiteId;
	}

	public String getMidwayMasterDeviceId() {
		return midwayMasterDeviceId;
	}

	public void setMidwayMasterDeviceId(String midwayMasterDeviceId) {
		this.midwayMasterDeviceId = midwayMasterDeviceId;
	}

	private String accountName;

    private String skuNumber;

    private CustomFields[] customFields;

    private String costCenterCode;

    private String carrierIpPoolName;

    private String servicePlan;

    private PrimaryPlaceOfUse primaryPlaceOfUse;

    private String leadId;

    private String carrierName;

    private String publicIpRestriction;

    private String mdnZipCode;

    //private DeviceIds[] deviceId;
    
    private DeviceId deviceId;

    private CustomerName customerName;
    
    private Address address;
    
    public String getGroupName ()
    {
        return groupName;
    }

    public void setGroupName (String groupName)
    {
        this.groupName = groupName;
    }

    public String getAccountName ()
    {
        return accountName;
    }

    public void setAccountName (String accountName)
    {
        this.accountName = accountName;
    }

    public String getSkuNumber ()
    {
        return skuNumber;
    }

    public void setSkuNumber (String skuNumber)
    {
        this.skuNumber = skuNumber;
    }

    public CustomFields[] getCustomFields ()
    {
        return customFields;
    }

    public void setCustomFields (CustomFields[] customFields)
    {
        this.customFields = customFields;
    }

    public String getCostCenterCode ()
    {
        return costCenterCode;
    }

    public void setCostCenterCode (String costCenterCode)
    {
        this.costCenterCode = costCenterCode;
    }

    public String getCarrierIpPoolName ()
    {
        return carrierIpPoolName;
    }

    public void setCarrierIpPoolName (String carrierIpPoolName)
    {
        this.carrierIpPoolName = carrierIpPoolName;
    }

    public String getServicePlan ()
    {
        return servicePlan;
    }

    public void setServicePlan (String servicePlan)
    {
        this.servicePlan = servicePlan;
    }

    public PrimaryPlaceOfUse getPrimaryPlaceOfUse ()
    {
        return primaryPlaceOfUse;
    }

    public void setPrimaryPlaceOfUse (PrimaryPlaceOfUse primaryPlaceOfUse)
    {
        this.primaryPlaceOfUse = primaryPlaceOfUse;
    }

    public String getLeadId ()
    {
        return leadId;
    }

    public void setLeadId (String leadId)
    {
        this.leadId = leadId;
    }

    public String getCarrierName ()
    {
        return carrierName;
    }

    public void setCarrierName (String carrierName)
    {
        this.carrierName = carrierName;
    }

    public String getPublicIpRestriction ()
    {
        return publicIpRestriction;
    }

    public void setPublicIpRestriction (String publicIpRestriction)
    {
        this.publicIpRestriction = publicIpRestriction;
    }

    public String getMdnZipCode ()
    {
        return mdnZipCode;
    }

    public void setMdnZipCode (String mdnZipCode)
    {
        this.mdnZipCode = mdnZipCode;
    }

    

   /* public DeviceIds[] getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceIds[] deviceId) {
		this.deviceId = deviceId;
	}*/
    
    

	public CustomerName getCustomerName() {
		return customerName;
	}

	public DeviceId getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(DeviceId deviceId) {
		this.deviceId = deviceId;
	}

	public void setCustomerName(CustomerName customerName) {
		this.customerName = customerName;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [groupName = "+groupName+", accountName = "+accountName+", skuNumber = "+skuNumber+", customFields = "+customFields+", costCenterCode = "+costCenterCode+", carrierIpPoolName = "+carrierIpPoolName+", servicePlan = "+servicePlan+", primaryPlaceOfUse = "+primaryPlaceOfUse+", leadId = "+leadId+", carrierName = "+carrierName+", publicIpRestriction = "+publicIpRestriction+", mdnZipCode = "+mdnZipCode+ ", netSuiteId=  "+netSuiteId+" ,midwayMasterDeviceId=" + midwayMasterDeviceId+", deviceId = "+deviceId+"]";
    }
}
		