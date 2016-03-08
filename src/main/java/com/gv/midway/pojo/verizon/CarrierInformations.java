package com.gv.midway.pojo.verizon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrierInformations {
	private String state;

    private String servicePlan;

    private String carrierName;

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getServicePlan ()
    {
        return servicePlan;
    }

    public void setServicePlan (String servicePlan)
    {
        this.servicePlan = servicePlan;
    }

    public String getCarrierName ()
    {
        return carrierName;
    }

    public void setCarrierName (String carrierName)
    {
        this.carrierName = carrierName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [state = "+state+", servicePlan = "+servicePlan+", carrierName = "+carrierName+"]";
    }

}
