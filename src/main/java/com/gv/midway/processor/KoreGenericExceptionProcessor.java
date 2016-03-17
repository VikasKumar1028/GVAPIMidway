package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;

import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public class KoreGenericExceptionProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreGenericExceptionProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----KoreGenericExceptionProcessor----------"
				+ exception.getResponseBody());

		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setApplicationName("Midway");
		responseHeader.setRegion("USA");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		responseHeader.setTimestamp(dateFormat.format(date));
		responseHeader.setOrganization("Grant Victor");
		responseHeader.setSourceName("KORE");
		String TransactionId = (String) exchange.getProperty("TransactionId");
		responseHeader.setTransactionId(TransactionId);
		responseHeader.setBsCarrier("KORE");

		Response response = new Response();
		response.setResponseCode("InValid Data");
		response.setResponseStatus("4");
		response.setResponseDescription(exception.getResponseBody());

		if ("Endpoint[direct://deviceInformation]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse responseObject = new DeviceInformationResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}

	}
}
