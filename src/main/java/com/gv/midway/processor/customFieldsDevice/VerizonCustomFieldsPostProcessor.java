package com.gv.midway.processor.customFieldsDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponseDataArea;

public class VerizonCustomFieldsPostProcessor implements Processor{

	Logger log = Logger.getLogger(VerizonCustomFieldsPostProcessor.class.getName());
	
	
	Environment newEnv;

	public VerizonCustomFieldsPostProcessor(Environment env) {
		super();

		this.newEnv = env;

	}
	@Override
	public void process(Exchange exchange) throws Exception {
		
		log.info("Begin::VerizonCustomFieldsPostProcessor");
		

		CustomFieldsDeviceResponse customFieldsUpdateDeviceResponse = new CustomFieldsDeviceResponse();

		CustomFieldsDeviceResponseDataArea customFieldsUpdateDeviceResponseDataArea = new CustomFieldsDeviceResponseDataArea();


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

		
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		customFieldsUpdateDeviceResponse.setHeader(responseheader);
		customFieldsUpdateDeviceResponse.setResponse(response);
		customFieldsUpdateDeviceResponseDataArea.setOrderNumber(exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
		
		

		customFieldsUpdateDeviceResponse
				.setDataArea(customFieldsUpdateDeviceResponseDataArea);

		exchange.getIn().setBody(customFieldsUpdateDeviceResponse);

		log.info("End::VerizonCustomFieldsPostProcessor");
	}

}
