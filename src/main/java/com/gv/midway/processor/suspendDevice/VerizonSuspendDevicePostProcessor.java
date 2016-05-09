package com.gv.midway.processor.suspendDevice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class VerizonSuspendDevicePostProcessor implements Processor {

	static int i = 0;

	Logger log = Logger.getLogger(VerizonSuspendDevicePostProcessor.class
			.getName());

	Environment newEnv;

	public VerizonSuspendDevicePostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	public void process(Exchange exchange) throws Exception {

		log.info("Begin:VerizonSuspendDevicePostProcessor");

		SuspendDeviceResponse suspendDeviceResponse = new SuspendDeviceResponse();
		SuspendDeviceResponseDataArea suspendDeviceResponseDataArea = new SuspendDeviceResponseDataArea();
		Header responseheader = new Header();
		Response response = new Response();

		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));

		Date date = new Date();

		System.out
				.println("exchange.getIn().getBody().toString()***************************************"
						+ exchange.getIn().getBody().toString());

		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			log.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_SUSPEND_MIDWAY);
			suspendDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
					IConstant.MIDWAY_TRANSACTION_ID).toString());

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(exchange.getIn().getBody()
					.toString());

		}

		responseheader.setApplicationName(exchange.getProperty(
				IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION)
				.toString());
		responseheader.setOrganization(exchange.getProperty(
				IConstant.ORGANIZATION).toString());

		/*
		 * responseheader.setTimestamp(exchange.getProperty(
		 * IConstant.DATE_FORMAT).toString());
		 */

		responseheader.setTimestamp(dateFormat.format(date));
		responseheader.setSourceName(exchange
				.getProperty(IConstant.SOURCE_NAME).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());
		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());

		suspendDeviceResponse.setHeader(responseheader);
		suspendDeviceResponse.setResponse(response);

		suspendDeviceResponse.setDataArea(suspendDeviceResponseDataArea);

		exchange.getIn().setBody(suspendDeviceResponse);

		log.info("End:VerizonSuspendDevicePostProcessor");

	}

}
