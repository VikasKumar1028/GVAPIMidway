package com.gv.midway.processor.connectionInformation.deviceConnectionStatus;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.attjasper.GetSessionInfoResponse;
import com.gv.midway.attjasper.SessionInfoType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponseDataArea;

public class ATTJasperDeviceConnectionStatusPostProcessor implements Processor {
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceConnectionStatusPostProcessor.class
					.getName());

	Environment newEnv;

	public ATTJasperDeviceConnectionStatusPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperDeviceConnectionStatusPostProcessor");

		ConnectionStatusResponse businessResponse = new ConnectionStatusResponse();
		ConnectionStatusResponseDataArea connectionStatusResponseDataArea = new ConnectionStatusResponseDataArea();
		Response response = new Response();

		GetSessionInfoResponse getSessionInfoResponse = (GetSessionInfoResponse) exchange
				.getIn().getBody(GetSessionInfoResponse.class);

		GetSessionInfoResponse.SessionInfo sessionInfo = getSessionInfoResponse
				.getSessionInfo();

		List<SessionInfoType> getsessionInfoType = sessionInfo.getSession();

		String status = null;
		if (!(getsessionInfoType.size() == 0)) {

			LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
			status = IConstant.DEVICE_IN_SESSION;
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);
			connectionStatusResponseDataArea.setConnectionStatus(status);

		} else {
			status = IConstant.DEVICE_NOT_IN_SESSION;
			response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVICESESSIONBEGINENDINFO_CARRIER);
			connectionStatusResponseDataArea.setConnectionStatus(status);
		}

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		businessResponse.setHeader(responseheader);
		businessResponse.setResponse(response);

		businessResponse.setDataArea(connectionStatusResponseDataArea);

		exchange.getIn().setBody(businessResponse);

		LOGGER.info("End:ATTJasperDeviceConnectionStatusPostProcessor");

	}

}
