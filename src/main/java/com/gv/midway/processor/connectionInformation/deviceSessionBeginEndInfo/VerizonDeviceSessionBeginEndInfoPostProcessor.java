package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import java.util.ArrayList;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.DeviceSession;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponseDataArea;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;

public class VerizonDeviceSessionBeginEndInfoPostProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(VerizonDeviceSessionBeginEndInfoPostProcessor.class
                    .getName());

    Environment newEnv;

    public VerizonDeviceSessionBeginEndInfoPostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:VerizonDeviceSessionBeginEndInfoPostProcessor");

        SessionBeginEndResponse businessResponse = new SessionBeginEndResponse();
        SessionBeginEndResponseDataArea sessionBeginEndResponseDataArea = new SessionBeginEndResponseDataArea();
        Response response = new Response();

        LOGGER.info("exchange.getIn().getBody().toString()***************************************"
                + exchange.getIn().getBody().toString());

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            Map map = exchange.getIn().getBody(Map.class);
            ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            ConnectionInformationResponse connectionResponse = mapper
                    .convertValue(map, ConnectionInformationResponse.class);

            int totalConnectionHistory = connectionResponse
                    .getConnectionHistory().length;

            ArrayList<DeviceSession> deviceSessions = new ArrayList<DeviceSession>();
            if (totalConnectionHistory > 0) {

                DeviceSession deviceSession = new DeviceSession();
                int eventStatus = 0;
                for (int i = 0; i < totalConnectionHistory; i++) {

                    int totalConnectionHistoryEvents = connectionResponse
                            .getConnectionHistory()[i]
                            .getConnectionEventAttributes().length;

                    for (int j = 0; j < totalConnectionHistoryEvents; j++) {

                        if (connectionResponse.getConnectionHistory()[i]
                                .getConnectionEventAttributes()[j].getKey()
                                .equalsIgnoreCase(IConstant.EVENT)
                                && connectionResponse.getConnectionHistory()[i]
                                        .getConnectionEventAttributes()[j]
                                        .getValue().equalsIgnoreCase(
                                                IConstant.EVENT_STOP) && i == 0) {

                            deviceSession.setEnd(connectionResponse
                                    .getConnectionHistory()[i].getOccurredAt());
                            deviceSession.setBegin(null);
                            deviceSessions.add(deviceSession);
                            deviceSession = new DeviceSession();
                            break;

                        } else if (connectionResponse.getConnectionHistory()[i]
                                .getConnectionEventAttributes()[j].getKey()
                                .equalsIgnoreCase(IConstant.EVENT)
                                && connectionResponse.getConnectionHistory()[i]
                                        .getConnectionEventAttributes()[j]
                                        .getValue().equalsIgnoreCase(
                                                IConstant.EVENT_START)
                                && i == (totalConnectionHistory - 1)) {

                            deviceSession.setEnd(null);
                            deviceSession.setBegin(connectionResponse
                                    .getConnectionHistory()[i].getOccurredAt());
                            deviceSessions.add(deviceSession);
                            break;

                        } else if (connectionResponse.getConnectionHistory()[i]
                                .getConnectionEventAttributes()[j].getKey()
                                .equalsIgnoreCase(IConstant.EVENT)
                                && connectionResponse.getConnectionHistory()[i]
                                        .getConnectionEventAttributes()[j]
                                        .getValue().equalsIgnoreCase(
                                                IConstant.EVENT_START)) {

                            LOGGER.info("Exception on " + i);
                            if (eventStatus == 0) {
                                eventStatus = 1;
                                deviceSession.setBegin(connectionResponse
                                        .getConnectionHistory()[i]
                                        .getOccurredAt());
                                break;
                            } else {
                                break;
                            }
                        } else if (connectionResponse.getConnectionHistory()[i]
                                .getConnectionEventAttributes()[j].getKey()
                                .equalsIgnoreCase(IConstant.EVENT)
                                && connectionResponse.getConnectionHistory()[i]
                                        .getConnectionEventAttributes()[j]
                                        .getValue().equalsIgnoreCase(
                                                IConstant.EVENT_STOP)) {

                            if (eventStatus == 1) {
                                eventStatus = 0;
                                deviceSession.setEnd(connectionResponse
                                        .getConnectionHistory()[i]
                                        .getOccurredAt());
                                deviceSessions.add(deviceSession);
                                if (i != (totalConnectionHistory - 1))
                                    deviceSession = new DeviceSession();

                                break;
                            } else {
                                break;
                            }
                        }
                    }

                }

                LOGGER.info("RequestID::" + exchange.getIn().getBody().toString());
                response.setResponseCode(IResponse.SUCCESS_CODE);
                response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
                response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);

                sessionBeginEndResponseDataArea.setDeviceSession(deviceSessions
                        .toArray(new DeviceSession[deviceSessions.size()]));

			} else {
				response.setResponseCode(IResponse.NO_DATA_FOUND_CODE);
				response.setResponseStatus(IResponse.ERROR_MESSAGE);
				response.setResponseDescription(IResponse.ERROR_DESCRIPTION_NODATA_DEVICESESSIONBEGINENDINFO_CARRIER);

			}
		} else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody()
                    .toString());

        }

        Header responseheader = (Header) exchange.getProperty(IConstant.HEADER);

        businessResponse.setHeader(responseheader);
        businessResponse.setResponse(response);

        businessResponse.setDataArea(sessionBeginEndResponseDataArea);

        exchange.getIn().setBody(businessResponse);

        LOGGER.info("End:VerizonDeviceSessionBeginEndInfoPostProcessor");

    }

}
