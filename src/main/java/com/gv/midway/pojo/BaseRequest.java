package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class BaseRequest {
	private Header header;
   
    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

   
    @Override
    public String toString()
    {
        return "ClassPojo [header = "+header+"]";
    }
}
