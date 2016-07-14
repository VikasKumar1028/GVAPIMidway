package com.gv.midway.processor.suspendDevice;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponseDataArea;

public class KoreSuspendDevicePostProcessor implements Processor {

	Logger log = Logger.getLogger(KoreSuspendDevicePostProcessor.class
			.getName());

	public KoreSuspendDevicePostProcessor() {

	}

	Environment newEnv;

	public KoreSuspendDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}
	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Begin::KoreSuspendDevicePostProcessor");

		SuspendDeviceResponse SuspendDeviceResponse = new SuspendDeviceResponse();

		SuspendDeviceResponseDataArea SuspendDeviceResponseDataArea = new SuspendDeviceResponseDataArea();


		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_SUSPEND_MIDWAY);

		
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		SuspendDeviceResponse.setHeader(responseheader);
		SuspendDeviceResponse.setResponse(response);
		SuspendDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());

		SuspendDeviceResponse.setDataArea(SuspendDeviceResponseDataArea);

		exchange.getIn().setBody(SuspendDeviceResponse);
		log.info("End::KoreSuspendDevicePostProcessor");
	}
}
