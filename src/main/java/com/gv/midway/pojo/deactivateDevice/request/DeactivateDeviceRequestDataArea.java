package com.gv.midway.pojo.deactivateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeactivateDeviceRequestDataArea 
{
	 private String groupName;

	    private String accountName;

	    private CustomFields[] customFields;

	    private String servicePlan;

	    private String reasonCode;

	    private DeviceIds[] deviceIds;

	    private String etfWaiver;

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

	    public CustomFields[] getCustomFields ()
	    {
	        return customFields;
	    }

	    public void setCustomFields (CustomFields[] customFields)
	    {
	        this.customFields = customFields;
	    }

	    public String getServicePlan ()
	    {
	        return servicePlan;
	    }

	    public void setServicePlan (String servicePlan)
	    {
	        this.servicePlan = servicePlan;
	    }

	    public String getReasonCode ()
	    {
	        return reasonCode;
	    }

	    public void setReasonCode (String reasonCode)
	    {
	        this.reasonCode = reasonCode;
	    }

	    public DeviceIds[] getDeviceIds ()
	    {
	        return deviceIds;
	    }

	    public void setDeviceIds (DeviceIds[] deviceIds)
	    {
	        this.deviceIds = deviceIds;
	    }

	    public String getEtfWaiver ()
	    {
	        return etfWaiver;
	    }

	    public void setEtfWaiver (String etfWaiver)
	    {
	        this.etfWaiver = etfWaiver;
	    }

	    @Override
		public String toString() {
			return "DeactivateDeviceRequestDataArea [groupName=" + groupName
					+ ", accountName=" + accountName + ", servicePlan="
					+ servicePlan + ", reasonCode=" + reasonCode
					+ ", etfWaiver=" + etfWaiver + "]";
		}
}
