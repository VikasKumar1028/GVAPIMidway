package com.gv.midway.processor.deactivateDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.ResponseHeader;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.processor.deviceInformation.StubKoreDeviceInformationProcessor;

public class KoreDeactivateDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreDeactivateDevicePostProcessor.class
			.getName());
	public KoreDeactivateDevicePostProcessor() {

	}
	Environment newEnv;

	public KoreDeactivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {
		
		
		// TODO Auto-generated method stub
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
	

		deactivateDeviceResponse.setHeader(responseheader);
		deactivateDeviceResponse.setResponse(response);
	/*	deactivateDeviceResponse.setRequestId("ReaLKORErequestId");
		deactivateDeviceResponse.setTrackingNumber("ReaLKOREtrackingNumbe");*/
		exchange.getIn().setBody(deactivateDeviceResponse);
	}
}
