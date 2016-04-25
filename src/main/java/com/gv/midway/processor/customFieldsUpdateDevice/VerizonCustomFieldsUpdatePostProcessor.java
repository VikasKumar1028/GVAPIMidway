package com.gv.midway.processor.customFieldsUpdateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponse;
import com.gv.midway.pojo.customFieldsUpdateDevice.response.CustomFieldsUpdateDeviceResponseDataArea;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;

public class VerizonCustomFieldsUpdatePostProcessor implements Processor{

	Logger log = Logger.getLogger(VerizonCustomFieldsUpdatePostProcessor.class.getName());
	
	
	Environment newEnv;

	public VerizonCustomFieldsUpdatePostProcessor(Environment env) {
		super();

		this.newEnv = env;

	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Start::VerizonCustomFieldsUpdatePostProcessor");
		

		CustomFieldsUpdateDeviceResponse customFieldsUpdateDeviceResponse = new CustomFieldsUpdateDeviceResponse();

		CustomFieldsUpdateDeviceResponseDataArea customFieldsUpdateDeviceResponseDataArea = new CustomFieldsUpdateDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		
		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {


			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
			customFieldsUpdateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
					IConstant.MIDWAY_TRANSACTION_ID).toString());

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());


		} 

		
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);

		responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());

		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

		customFieldsUpdateDeviceResponse.setHeader(responseheader);
		customFieldsUpdateDeviceResponse.setResponse(response);
		customFieldsUpdateDeviceResponseDataArea.setOrderNumber(exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
		
		customFieldsUpdateDeviceResponseDataArea.setOrderNumber("testing");

		customFieldsUpdateDeviceResponse
				.setDataArea(customFieldsUpdateDeviceResponseDataArea);

		exchange.getIn().setBody(customFieldsUpdateDeviceResponse);

		log.info("End::VerizonCustomFieldsUpdatePostProcessor");
	}

}
