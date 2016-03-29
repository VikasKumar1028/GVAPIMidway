package com.gv.midway.processor.activateDevice;

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
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.processor.deviceInformation.VerizonDeviceInformationPostProcessor;

public class VerizonActivateDevicePostProcessor implements Processor {

	static int i = 0;

	Logger log = Logger.getLogger(VerizonDeviceInformationPostProcessor.class
			.getName());

	/*
	 * public VerizonActivateDevicePostProcessor() {
	 * 
	 * }
	 */
	Environment newEnv;

	public VerizonActivateDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {

		log.info("Start:VerizonDeviceInformationPostProcessor");

		System.out.println("-----------------"
				+ exchange.getIn().getBody().toString());

		ActivateDeviceResponse activateDeviceResponse = new ActivateDeviceResponse();
		ActivateDeviceResponseDataArea activateDeviceResponseDataArea = new ActivateDeviceResponseDataArea();
		ResponseHeader responseheader = new ResponseHeader();
		Response response = new Response();

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {
			// Without error
			/*VerizonResponse verizonResponse = exchange.getIn().getBody(
					VerizonResponse.class);*/
			response.setResponseCode(newEnv
					.getProperty(IConstant.RESPONSES_CODE));
			response.setResponseStatus(newEnv
					.getProperty(IConstant.RESPONSE_STATUS_SUCCESS));

		} else {
			
			response.setResponseCode("200");
			response.setResponseStatus("errorMessage");
			response.setResponseDescription(exchange.getIn().getBody().toString());
		}

		
		responseheader.setApplicationName(newEnv
				.getProperty(IConstant.APPLICATION_NAME));
		responseheader.setRegion(newEnv.getProperty(IConstant.REGION));
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setOrganization(newEnv
				.getProperty(IConstant.ORGANIZATION));
		responseheader.setSourceName(newEnv
				.getProperty(IConstant.SOURCE_NAME_VERIZON));
		String TransactionId = (String) exchange.getProperty(newEnv
				.getProperty(IConstant.EXCHANEGE_PROPERTY));
		responseheader.setTransactionId(TransactionId);

		responseheader.setBsCarrier(newEnv
				.getProperty(IConstant.BSCARRIER_VERIZON));

		activateDeviceResponse.setHeader(responseheader);
		activateDeviceResponse.setResponse(response);

		//activateDeviceResponseDataArea.setRequestId("requestId");
		//activateDeviceResponse.setDataArea(activateDeviceResponseDataArea);

		exchange.getIn().setBody(activateDeviceResponse);

	}

}
