package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;

public class NetSuiteIdValidationProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(NetSuiteIdValidationProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:NetSuiteIdValidationProcessor");
		final DeviceInformationRequest request = exchange.getIn().getBody(DeviceInformationRequest.class);
		final DeviceInformationRequestDataArea deviceInformationRequestDataArea = request.getDataArea();

		if (deviceInformationRequestDataArea == null || deviceInformationRequestDataArea.getNetSuiteId() == null) {
			missingNetSuiteId(exchange);
		}
		LOGGER.info("End:NetSuiteIdValidationProcessor");
	}

	private void missingNetSuiteId(Exchange exchange) throws MissingParameterException {
		exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD);
		exchange.setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_MESSAGE);
		throw new MissingParameterException(IResponse.INVALID_PAYLOAD.toString(), IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
	}
}