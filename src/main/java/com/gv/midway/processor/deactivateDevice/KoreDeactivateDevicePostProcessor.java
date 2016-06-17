package com.gv.midway.processor.deactivateDevice;



import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;

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
		DeactivateDeviceResponseDataArea deactivateDeviceResponseDataArea = new DeactivateDeviceResponseDataArea();

		//Header responseheader = new Header();

		Response response = new Response();
		if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_ACTIVATE_MIDWAY);
			deactivateDeviceResponse
					.setDataArea(deactivateDeviceResponseDataArea);

		} else {

			response.setResponseCode(400);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			//response.setResponseDescription(exchange.getIn().getBody().toString());
			response.setResponseDescription(exchange.getIn().getMessageId());
		}

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

		responseheader.setTransactionId(exchange.getProperty(
				IConstant.GV_TRANSACTION_ID).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER)
				.toString());*/
		
		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		deactivateDeviceResponse.setHeader(responseheader);

		deactivateDeviceResponse.setResponse(response);
		deactivateDeviceResponseDataArea.setOrderNumber(exchange.getProperty(
				IConstant.MIDWAY_TRANSACTION_ID).toString());

		exchange.getIn().setBody(deactivateDeviceResponse);
	}
}
