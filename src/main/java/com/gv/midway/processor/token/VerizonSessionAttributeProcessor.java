package com.gv.midway.processor.token;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.constant.IConstant;
import com.gv.midway.device.request.pojo.DeviceInformation;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.token.VerizonSessionLoginResponse;

public class VerizonSessionAttributeProcessor implements Processor {
	
	static int i=0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {
		
		VerizonSessionLoginResponse resp =(VerizonSessionLoginResponse)exchange.getIn().getBody();
		
		exchange.setProperty(IConstant.VZ_SEESION_TOKEN, resp.getSessionToken());
		
	/*	String str=resp.getSessionToken();
		String str2=exchange.getProperty("access_token").toString();
		
		System.out.println(str +"***************************"+ str2);
		
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();
		DeviceInformation[] deviceInformationArray = new DeviceInformation[1];

		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		response.setResponseCode("200");
		response.setResponseDescription("Device Information is fetched Successfully");
		response.setResponseStatus("SUCCESS");
		
		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		

		
		System.out.println("------------SUCCESS------------"+ exchange.getUnitOfWork().getOriginalInMessage().toString());
	
		exchange.getIn().setBody(deviceInformationResponse);*/
	}
}
