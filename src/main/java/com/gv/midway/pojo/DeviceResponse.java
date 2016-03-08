package com.gv.midway.pojo;

public class DeviceResponse  {
	 	private String lastConnectionDate;

	    private LstFeatures lstFeatures;

	    private String futureSMSPlan;

	    private String lastActivationBy;

	    private CustomFields[] customFields;

	    private String connected;

	    private CarrierInformations carrierInformations;

	    private String[] groupNames;

	    private String monthlySMSThreshold;

	    private String monthlyDataThreshold;

	    private String accountName;

	    private String lstExtFeatures;

	    private String dailyDataThreshold;

	    private String billingCycleEndDate;

	    private String createdAt;

	    private String futureDataPlan;

	    private DeviceIds deviceIds;

	    private String currentSMSPlan;

	    private String dailySMSThreshold;

	    private String lstHistoryOverLastYear;

	    private ExtendedAttributes extendedAttributes;

	    private String ipAddress;

	    private String lastActivationDate;

	    public String getLastConnectionDate ()
	    {
	        return lastConnectionDate;
	    }

	    public void setLastConnectionDate (String lastConnectionDate)
	    {
	        this.lastConnectionDate = lastConnectionDate;
	    }

	    public LstFeatures getLstFeatures ()
	    {
	        return lstFeatures;
	    }

	    public void setLstFeatures (LstFeatures lstFeatures)
	    {
	        this.lstFeatures = lstFeatures;
	    }

	    public String getFutureSMSPlan ()
	    {
	        return futureSMSPlan;
	    }

	    public void setFutureSMSPlan (String futureSMSPlan)
	    {
	        this.futureSMSPlan = futureSMSPlan;
	    }

	    public String getLastActivationBy ()
	    {
	        return lastActivationBy;
	    }

	    public void setLastActivationBy (String lastActivationBy)
	    {
	        this.lastActivationBy = lastActivationBy;
	    }

	    public CustomFields[] getCustomFields ()
	    {
	        return customFields;
	    }

	    public void setCustomFields (CustomFields[] customFields)
	    {
	        this.customFields = customFields;
	    }

	    public String getConnected ()
	    {
	        return connected;
	    }

	    public void setConnected (String connected)
	    {
	        this.connected = connected;
	    }

	    public CarrierInformations getCarrierInformations ()
	    {
	        return carrierInformations;
	    }

	    public void setCarrierInformations (CarrierInformations carrierInformations)
	    {
	        this.carrierInformations = carrierInformations;
	    }

	    public String[] getGroupNames ()
	    {
	        return groupNames;
	    }

	    public void setGroupNames (String[] groupNames)
	    {
	        this.groupNames = groupNames;
	    }

	    public String getMonthlySMSThreshold ()
	    {
	        return monthlySMSThreshold;
	    }

	    public void setMonthlySMSThreshold (String monthlySMSThreshold)
	    {
	        this.monthlySMSThreshold = monthlySMSThreshold;
	    }

	    public String getMonthlyDataThreshold ()
	    {
	        return monthlyDataThreshold;
	    }

	    public void setMonthlyDataThreshold (String monthlyDataThreshold)
	    {
	        this.monthlyDataThreshold = monthlyDataThreshold;
	    }

	    public String getAccountName ()
	    {
	        return accountName;
	    }

	    public void setAccountName (String accountName)
	    {
	        this.accountName = accountName;
	    }

	    public String getLstExtFeatures ()
	    {
	        return lstExtFeatures;
	    }

	    public void setLstExtFeatures (String lstExtFeatures)
	    {
	        this.lstExtFeatures = lstExtFeatures;
	    }

	    public String getDailyDataThreshold ()
	    {
	        return dailyDataThreshold;
	    }

	    public void setDailyDataThreshold (String dailyDataThreshold)
	    {
	        this.dailyDataThreshold = dailyDataThreshold;
	    }

	    public String getBillingCycleEndDate ()
	    {
	        return billingCycleEndDate;
	    }

	    public void setBillingCycleEndDate (String billingCycleEndDate)
	    {
	        this.billingCycleEndDate = billingCycleEndDate;
	    }

	    public String getCreatedAt ()
	    {
	        return createdAt;
	    }

	    public void setCreatedAt (String createdAt)
	    {
	        this.createdAt = createdAt;
	    }

	    public String getFutureDataPlan ()
	    {
	        return futureDataPlan;
	    }

	    public void setFutureDataPlan (String futureDataPlan)
	    {
	        this.futureDataPlan = futureDataPlan;
	    }

	    public DeviceIds getDeviceIds ()
	    {
	        return deviceIds;
	    }

	    public void setDeviceIds (DeviceIds deviceIds)
	    {
	        this.deviceIds = deviceIds;
	    }

	    public String getCurrentSMSPlan ()
	    {
	        return currentSMSPlan;
	    }

	    public void setCurrentSMSPlan (String currentSMSPlan)
	    {
	        this.currentSMSPlan = currentSMSPlan;
	    }

	    public String getDailySMSThreshold ()
	    {
	        return dailySMSThreshold;
	    }

	    public void setDailySMSThreshold (String dailySMSThreshold)
	    {
	        this.dailySMSThreshold = dailySMSThreshold;
	    }

	    public String getLstHistoryOverLastYear ()
	    {
	        return lstHistoryOverLastYear;
	    }

	    public void setLstHistoryOverLastYear (String lstHistoryOverLastYear)
	    {
	        this.lstHistoryOverLastYear = lstHistoryOverLastYear;
	    }

	    public ExtendedAttributes getExtendedAttributes ()
	    {
	        return extendedAttributes;
	    }

	    public void setExtendedAttributes (ExtendedAttributes extendedAttributes)
	    {
	        this.extendedAttributes = extendedAttributes;
	    }

	    public String getIpAddress ()
	    {
	        return ipAddress;
	    }

	    public void setIpAddress (String ipAddress)
	    {
	        this.ipAddress = ipAddress;
	    }

	    public String getLastActivationDate ()
	    {
	        return lastActivationDate;
	    }

	    public void setLastActivationDate (String lastActivationDate)
	    {
	        this.lastActivationDate = lastActivationDate;
	    }

	    @Override
	    public String toString()
	    {
	        return "DeviceResponse [lastConnectionDate = "+lastConnectionDate+", lstFeatures = "+lstFeatures+", futureSMSPlan = "+futureSMSPlan+", lastActivationBy = "+lastActivationBy+", customFields = "+customFields+", connected = "+connected+", carrierInformations = "+carrierInformations+", groupNames = "+groupNames+", monthlySMSThreshold = "+monthlySMSThreshold+", monthlyDataThreshold = "+monthlyDataThreshold+", accountName = "+accountName+", lstExtFeatures = "+lstExtFeatures+", dailyDataThreshold = "+dailyDataThreshold+", billingCycleEndDate = "+billingCycleEndDate+", createdAt = "+createdAt+", futureDataPlan = "+futureDataPlan+", deviceIds = "+deviceIds+", currentSMSPlan = "+currentSMSPlan+", dailySMSThreshold = "+dailySMSThreshold+", lstHistoryOverLastYear = "+lstHistoryOverLastYear+", extendedAttributes = "+extendedAttributes+", ipAddress = "+ipAddress+", lastActivationDate = "+lastActivationDate+"]";
	    }
	}

