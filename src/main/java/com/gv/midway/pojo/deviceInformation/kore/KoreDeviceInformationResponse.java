package com.gv.midway.pojo.deviceInformation.kore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KoreDeviceInformationResponse
{
    private D d;

    public D getD ()
    {
        return d;
    }

    public void setD (D d)
    {
        this.d = d;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [d = "+d+"]";
    }
}