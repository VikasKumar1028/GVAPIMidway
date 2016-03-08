package com.gv.midway.pojo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceInformationRequest  extends BaseRequest{
	
	private DeviceInformationRequestDataArea dataArea;
	
	public DeviceInformationRequestDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DeviceInformationRequestDataArea dataArea) {
		this.dataArea = dataArea;
	}

    /*public static void main(String aap[]) throws JsonGenerationException, JsonMappingException, IOException
    {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	DeviceInformationRequest obj = new DeviceInformationRequest();
    	DeviceInformationRequestDataArea objec =new DeviceInformationRequestDataArea();
    	//Object to JSON in file
    	//mapper.writeValue(new File("c:\\file.json"), obj);

    	//Object to JSON in String
    	String jsonInString = mapper.writeValueAsString(objec);
    	
    	System.out.println("jsonInString"+jsonInString);
    	
    	
    }
*/
	

}
