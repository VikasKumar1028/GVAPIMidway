package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;

public class VerizonGenericExceptionProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonGenericExceptionProcessor.class
			.getName());


	 Environment newEnv;

	
	public VerizonGenericExceptionProcessor(Environment env) {
		super();

		this.newEnv=env;
		
	}

	public VerizonGenericExceptionProcessor() {
		// TODO Auto-generated constructor stub
	}

	public void process(Exchange exchange) throws Exception {

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		log.info("----VerizonGenericExceptionProcessor----------"
				+ exception.getResponseBody());

		System.out.println("exception"+exception.getStatusText());
		System.out.println("exception"+exception.getStatusCode());
		
		//System.out.println("exception"+exception.getStatusText());
		
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setApplicationName(newEnv.getProperty(IConstant.APPLICATION_NAME));
		responseHeader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseHeader.setTimestamp(dateFormat.format(date));
		responseHeader.setOrganization(newEnv.getProperty(IConstant.ORGANIZATION));
		responseHeader.setSourceName(newEnv.getProperty(IConstant.SOURCE_NAME_VERIZON));
		String TransactionId = (String) exchange.getProperty(newEnv.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseHeader.setTransactionId(TransactionId);
		responseHeader.setBsCarrier(newEnv.getProperty(IConstant.BSCARRIER_VERIZON));

		Response response = new Response();
		response.setResponseCode("InValid Data");
		response.setResponseStatus("4");
		response.setResponseDescription(exception.getResponseBody());

		if (exception.getStatusCode() == 401) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "401");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Not able to retrieve  valid authentication token");
			throw new VerizonSessionTokenExpirationException("401", "401");
		}
		

		if (exception.getStatusCode() == 200) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "200");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Not able to retrieve  valid authentication token");
			throw new VerizonSessionTokenExpirationException("200", "200");
		}

		if ("Endpoint[direct://deviceInformation]".equals(exchange
				.getFromEndpoint().toString())) {

			DeviceInformationResponse responseObject = new DeviceInformationResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}
		
		if ("Endpoint[direct://deactivateDevice]".equals(exchange
				.getFromEndpoint().toString())) {
			System.out.println("getFromEndpoint---------------"+exchange.getFromEndpoint().toString());
			DeactivateDeviceResponse responseObject = new DeactivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}
		

		if ("Endpoint[direct://activateDevice]".equals(exchange
				.getFromEndpoint().toString())) {
			ActivateDeviceResponse responseObject = new ActivateDeviceResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
		}
	}
}
