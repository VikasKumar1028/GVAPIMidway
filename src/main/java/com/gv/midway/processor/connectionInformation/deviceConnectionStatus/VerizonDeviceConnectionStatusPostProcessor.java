package com.gv.midway.processor.connectionInformation.deviceConnectionStatus;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponseDataArea;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;

public class VerizonDeviceConnectionStatusPostProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(VerizonDeviceConnectionStatusPostProcessor.class
                    .getName());

    Environment newEnv;

    public VerizonDeviceConnectionStatusPostProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin:VerizonDeviceConnectionStatusPostProcessor");

        ConnectionStatusResponse businessResponse = new ConnectionStatusResponse();
        ConnectionStatusResponseDataArea connectionStatusResponseDataArea = new ConnectionStatusResponseDataArea();
        Response response = new Response();

        LOGGER.debug("exchange.getIn().getBody()****************************" + exchange.getIn().getBody());

        if (!exchange.getIn().getBody().toString().contains("errorMessage=")) {

            ConnectionInformationResponse connectionResponse = exchange.getIn().getBody(ConnectionInformationResponse.class);

            String deviceStatus = null;
            int totalConnectionHistory = connectionResponse.getConnectionHistory().length;
            if (totalConnectionHistory > 0) {
                totalConnectionHistory = totalConnectionHistory - 1;
                int totalConnectionHistoryEvents = connectionResponse
                        .getConnectionHistory()[totalConnectionHistory]
                        .getConnectionEventAttributes().length;
                totalConnectionHistoryEvents = totalConnectionHistoryEvents - 1;
                for (int i = 0; i < totalConnectionHistoryEvents; i++) {
                    if (connectionResponse.getConnectionHistory()[totalConnectionHistory]
                            .getConnectionEventAttributes()[i].getKey()
                            .equalsIgnoreCase("Event")) {
                        deviceStatus = connectionResponse
                                .getConnectionHistory()[totalConnectionHistory]
                                .getConnectionEventAttributes()[i].getValue();
                        break;
                    }

                }
            }

            String status = null;
            if (deviceStatus == null) {
                status = IConstant.NO_RECORD;
            } else if (deviceStatus.equalsIgnoreCase(IConstant.EVENT_START)) {
                status = IConstant.DEVICE_IN_SESSION;
            } else if (deviceStatus.equalsIgnoreCase(IConstant.EVENT_STOP)) {
                status = IConstant.DEVICE_NOT_IN_SESSION;
            }

            LOGGER.debug("RequestID::" + exchange.getIn().getBody().toString());
            response.setResponseCode(IResponse.SUCCESS_CODE);
            response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
            response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_CONNECTION_STATUS);
            connectionStatusResponseDataArea.setConnectionStatus(status);

        } else {

            response.setResponseCode(400);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(exchange.getIn().getBody()
                    .toString());

        }

        Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

        businessResponse.setHeader(responseHeader);
        businessResponse.setResponse(response);

        businessResponse.setDataArea(connectionStatusResponseDataArea);

        exchange.getIn().setBody(businessResponse);

        LOGGER.debug("End:VerizonDeviceConnectionStatusPostProcessor");

    }

}
