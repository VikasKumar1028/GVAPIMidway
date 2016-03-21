package com.gv.midway.pojo.deactivateDevice.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceIds
{
    private String id;

    private String kind;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
	public String toString() {
		return "DeviceIds [id=" + id + ", kind=" + kind + "]";
	}
}
			
			