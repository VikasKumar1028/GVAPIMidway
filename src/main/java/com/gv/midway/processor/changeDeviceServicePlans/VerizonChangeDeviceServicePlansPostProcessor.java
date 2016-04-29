package com.gv.midway.processor.changeDeviceServicePlans;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponseDataArea;

public class VerizonChangeDeviceServicePlansPostProcessor implements Processor {

	Logger log = Logger
			.getLogger(VerizonChangeDeviceServicePlansPostProcessor.class
					.getName());

	Environment newEnv;

	public VerizonChangeDeviceServicePlansPostProcessor() {

	}

	public VerizonChangeDeviceServicePlansPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("Start::VerizonCustomFieldsUpdatePostProcessor");

		ChangeDeviceServicePlansResponse changeDeviceServicePlansResponse = new ChangeDeviceServicePlansResponse();

		ChangeDeviceServicePlansResponseDataArea changeDeviceServicePlansResponseDataArea = new ChangeDeviceServicePlansResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
			changeDeviceServicePlansResponseDataArea.setOrderNumber(exchange
					.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

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

		changeDeviceServicePlansResponse.setHeader(responseheader);
		changeDeviceServicePlansResponse.setResponse(response);
		changeDeviceServicePlansResponseDataArea.setOrderNumber(exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

		changeDeviceServicePlansResponseDataArea.setOrderNumber("testing");

		changeDeviceServicePlansResponse
				.setDataArea(changeDeviceServicePlansResponseDataArea);

		exchange.getIn().setBody(changeDeviceServicePlansResponse);

		log.info("End::VerizonCustomFieldsUpdatePostProcessor");

	}

}
