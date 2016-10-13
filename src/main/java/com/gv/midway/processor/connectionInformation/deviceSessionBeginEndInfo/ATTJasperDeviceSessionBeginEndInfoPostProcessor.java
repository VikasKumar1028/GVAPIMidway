package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import java.util.ArrayList;
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
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.DeviceSession;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponseDataArea;

public class ATTJasperDeviceSessionBeginEndInfoPostProcessor implements
		Processor {
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceSessionBeginEndInfoPostProcessor.class
					.getName());

	Environment newEnv;

	public ATTJasperDeviceSessionBeginEndInfoPostProcessor(Environment env) {
		super();
		this.newEnv = env;

	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Begin:ATTJasperDeviceSessionBeginEndInfoPostProcessor");

		SessionBeginEndResponse businessResponse = new SessionBeginEndResponse();
		SessionBeginEndResponseDataArea sessionBeginEndResponseDataArea = new SessionBeginEndResponseDataArea();
		DeviceSession deviceSession = new DeviceSession();
		ArrayList<DeviceSession> deviceSessions = new ArrayList<DeviceSession>();
		Response response = new Response();

		GetSessionInfoResponse getSessionInfoResponse = (GetSessionInfoResponse) exchange
				.getIn().getBody(GetSessionInfoResponse.class);

		GetSessionInfoResponse.SessionInfo sessionInfo = getSessionInfoResponse
				.getSessionInfo();

		List<SessionInfoType> getsessionInfoType = sessionInfo.getSession();

		if (!(getsessionInfoType.size() == 0)) {

			for (int i = 0; i < getsessionInfoType.size(); i++) {

				deviceSession.setBegin(getsessionInfoType.get(i)
						.getDateSessionStarted().toString());
				if (getsessionInfoType.get(i).getDateSessionEnded()!=null) {
					deviceSession.setEnd(getsessionInfoType.get(i)
							.getDateSessionEnded().toString());
				}

			}
			deviceSessions.add(deviceSession);

			sessionBeginEndResponseDataArea.setDeviceSession(deviceSessions
					.toArray(new DeviceSession[deviceSessions.size()]));

			LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
			response.setResponseCode(IResponse.SUCCESS_CODE);
			response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
			response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);

		} else {
			response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
			response.setResponseStatus(IResponse.ERROR_MESSAGE);
			response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVICESESSIONBEGINENDINFO_CARRIER);
		}

		Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

		businessResponse.setHeader(responseheader);
		businessResponse.setResponse(response);

		businessResponse.setDataArea(sessionBeginEndResponseDataArea);

		exchange.getIn().setBody(businessResponse);

		LOGGER.info("End:ATTJasperDeviceSessionBeginEndInfoPostProcessor");
	}
}
