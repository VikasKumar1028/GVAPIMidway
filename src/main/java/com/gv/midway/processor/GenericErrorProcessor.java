package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public class GenericErrorProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		ResponseHeader responseheader = new ResponseHeader();
		Response response = new Response();
		responseheader.setApplicationName("Midway");
		responseheader.setRegion("USA");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName(exchange.getIn().getHeader("sourceName")
				.toString());

		
		if(exchange.getProperty(IConstant.RESPONSE_CODE)!=null)
		{
			
			response.setResponseCode(exchange.getProperty(IConstant.RESPONSE_CODE).toString());
			response.setResponseStatus(exchange.getProperty(IConstant.RESPONSE_STATUS).toString());
			response.setResponseDescription(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION).toString());
		}else{
		
		response.setResponseCode("Conenction Error");
		response.setResponseStatus("Conenction Error");
		response.setResponseDescription("Conenction Error");
		}
		
		String TransactionId = (String) exchange.getProperty("TransactionId");
		responseheader.setTransactionId(TransactionId);

		if ("Endpoint[direct://deviceInformation]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
			deviceInformationResponse.setHeader(responseheader);
			deviceInformationResponse.setResponse(response);
			exchange.getIn().setBody(deviceInformationResponse);

		}
	}
}
