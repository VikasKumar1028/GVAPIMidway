package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;

public class GenericErrorProcessor implements Processor {
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {
	
		
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode("300");
		response.setResponseDescription("Device Information is fetched Failed");
		response.setResponseStatus("FAILED");
		
		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		

		
		System.out.println("------------ERROR------------"+ exchange.getUnitOfWork().getOriginalInMessage().toString());
	
		exchange.getIn().setBody(deviceInformationResponse);
	}
}
