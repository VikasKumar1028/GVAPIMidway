package com.gv.midway.processor.deactivateDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.activateDevice.verizon.VerizonResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;

public class VerizonDeactivateDevicePostProcessor implements Processor {

	
	Logger log = Logger.getLogger(VerizonDeactivateDevicePostProcessor.class
			.getName());
	Environment newEnv;

	public VerizonDeactivateDevicePostProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public VerizonDeactivateDevicePostProcessor() {

	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
	
		log.info("Start::VerizonDeactivateDevicePostProcessor");
		
		DeactivateDeviceResponse deactivateDeviceResponse = new DeactivateDeviceResponse();
		ResponseHeader responseheader = new ResponseHeader();

		Response response = new Response();
		
		responseheader.setApplicationName(newEnv.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();
		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv.getProperty(IConstant.ORGANIZATION));
		responseheader.setSourceName(newEnv	.getProperty(IConstant.SOURCE_NAME_VERIZON));
		String TransactionId = (String) exchange.getProperty(newEnv	.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);
		responseheader.setBsCarrier(newEnv.getProperty(IConstant.BSCARRIER_VERIZON));
	
		
		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			response.setResponseCode(newEnv
					.getProperty(IConstant.RESPONSES_CODE));
			response.setResponseStatus(newEnv
					.getProperty(IConstant.RESPONSE_STATUS_SUCCESS));

		} else {

			response.setResponseCode("200");
			response.setResponseStatus("errorMessage");
			response.setResponseDescription(exchange.getIn().getBody().toString());
		}

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);
		
		exchange.getIn().setBody(deactivateDeviceResponse);
		log.info("End::VerizonDeactivateDevicePostProcessor");
	}
}
