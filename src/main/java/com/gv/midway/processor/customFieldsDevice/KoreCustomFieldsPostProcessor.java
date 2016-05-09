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

public class KoreCustomFieldsPostProcessor implements Processor {

	Logger log = Logger
			.getLogger(KoreCustomFieldsPostProcessor.class.getName());

	Environment newEnv;

	public KoreCustomFieldsPostProcessor() {

	}

	public KoreCustomFieldsPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("Begin::KoreCustomFieldsPostProcessor");

		CustomFieldsDeviceResponse customFieldsUpdateDeviceResponse = new CustomFieldsDeviceResponse();

		CustomFieldsDeviceResponseDataArea customFieldsUpdateDeviceResponseDataArea = new CustomFieldsDeviceResponseDataArea();

		Header responseheader = new Header();

		Response response = new Response();

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

		customFieldsUpdateDeviceResponse
				.setDataArea(customFieldsUpdateDeviceResponseDataArea);

		exchange.getIn().setBody(customFieldsUpdateDeviceResponse);

		log.info("End::KoreCustomFieldsPostProcessor");

	}

}
