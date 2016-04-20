package com.gv.midway.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.VerizonSessionTokenExpirationException;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.verizon.VerizonErrorResponse;

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
		log.info("----.getStatusCode()----------"
				+ exception.getStatusCode());

		
	
		Header responseHeader = new Header();
		
		
		responseHeader.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
		responseHeader.setRegion(exchange.getProperty(IConstant.REGION).toString());
		
		responseHeader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT).toString());
		responseHeader.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
		responseHeader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
	
		responseHeader.setTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
		responseHeader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());

		Response response = new Response();

		//TODO SAME Functionality
		if (exception.getStatusCode() == 401 ) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "401");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Token");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Not able to retrieve  valid authentication token");
			throw new VerizonSessionTokenExpirationException("401", "401");
		}else{
		
			ObjectMapper mapper= new ObjectMapper();
			
			VerizonErrorResponse responsePayload = mapper.readValue(exception.getResponseBody(),
				VerizonErrorResponse.class);

			log.info("----response payload is----------"
					+ responsePayload.toString());
			
			response.setResponseCode(IResponse.INVALID_PAYLOAD);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(responsePayload.getErrorMessage());
			
		
		}

		log.info("exchange endpoint of error........."+exchange.getFromEndpoint().toString());
		
		if ("Endpoint[direct://deviceInformationCarrier]".equals(exchange
				.getFromEndpoint().toString())) {
            
			log.info("----log info inside deviceInfo ----------");
			
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
