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

	public void process(Exchange exchange) throws Exception {

		log.info("Begin::KoreSuspendDevicePostProcessor");

		SuspendDeviceResponse SuspendDeviceResponse = new SuspendDeviceResponse();

		SuspendDeviceResponseDataArea SuspendDeviceResponseDataArea = new SuspendDeviceResponseDataArea();

		//Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_SUSPEND_MIDWAY);

		/*responseheader.setApplicationName(exchange.getProperty(
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
				IConstant.GV_TRANSACTION_ID).toString());*/
		
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
