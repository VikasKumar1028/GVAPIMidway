package com.gv.midway.pojo.GetDeviceRequest;

public class DeviceId
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
    public String toString()
    {
        return "DeviceId [id = "+id+", kind = "+kind+"]";
    }
}
			