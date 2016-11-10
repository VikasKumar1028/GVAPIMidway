package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse;

public class GenericErrorProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(GenericErrorProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:GenericErrorProcessor");
        LOGGER.info("----.Generic exchange----------" + exchange.getFromEndpoint().toString());

        final Response response = new Response();

        if (exchange.getProperty(IConstant.RESPONSE_CODE) != null) {
            response.setResponseCode(Integer.parseInt(exchange.getProperty(IConstant.RESPONSE_CODE).toString()));
            response.setResponseStatus(exchange.getProperty(IConstant.RESPONSE_STATUS).toString());
            response.setResponseDescription(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION).toString());
        } else {
            response.setResponseCode(IResponse.CONNECTION_ERROR_CODE);
            response.setResponseStatus(IResponse.ERROR_MESSAGE);
            response.setResponseDescription(IResponse.ERROR_DESCRIPTION_CONNECTION_MIDWAYDB);
        }

        final Header responseHeader;
        if (exchange.getProperty(IConstant.HEADER) == null) {
            responseHeader = new Header();
        } else {
            responseHeader = (Header) exchange.getProperty(IConstant.HEADER);
        }

        LOGGER.info("endpoint is......" + exchange.getFromEndpoint().toString());

        switch (exchange.getFromEndpoint().toString()) {
            case "Endpoint[direct://deviceInformationCarrier]":
            case "Endpoint[direct://getDeviceInformationDB]":
                final DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();
                deviceInformationResponse.setHeader(responseHeader);
                deviceInformationResponse.setResponse(response);
                exchange.getIn().setBody(deviceInformationResponse);
                break;
            case "Endpoint[direct://updateDevicesDetailsBulk]":
                final BatchDeviceResponse batchDeviceResponse = new BatchDeviceResponse();
                batchDeviceResponse.setHeader(responseHeader);
                batchDeviceResponse.setResponse(response);
                exchange.getIn().setBody(batchDeviceResponse);
                break;
            case "Endpoint[direct://updateDeviceDetails]":
                final UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();
                updateDeviceResponse.setHeader(responseHeader);
                updateDeviceResponse.setResponse(response);
                exchange.getIn().setBody(updateDeviceResponse);
                break;
            case "Endpoint[direct://activateDevice]":
            case "Endpoint[direct://deactivateDevice]":
            case "Endpoint[direct://suspendDevice]":
            case "Endpoint[direct://customFields]":
            case "Endpoint[direct://changeDeviceServicePlans]":
            case "Endpoint[direct://reactivateDevice]":
            case "Endpoint[direct://restoreDevice]":
                final CarrierProvisioningDeviceResponse activateDeviceResponse = new CarrierProvisioningDeviceResponse();
                activateDeviceResponse.setHeader(responseHeader);
                activateDeviceResponse.setResponse(response);
                exchange.getIn().setBody(activateDeviceResponse);
                break;
            case "Endpoint[direct://deviceConnectionStatus]":
                final ConnectionStatusResponse connectionStatusResponse = new ConnectionStatusResponse();
                connectionStatusResponse.setHeader(responseHeader);
                connectionStatusResponse.setResponse(response);
                exchange.getIn().setBody(connectionStatusResponse);
                break;
            case "Endpoint[direct://deviceSessionBeginEndInfo]":
            case "Endpoint[direct://deviceSessionInfo]":
                final SessionBeginEndResponse sessionBeginEndResponse = new SessionBeginEndResponse();
                sessionBeginEndResponse.setHeader(responseHeader);
                sessionBeginEndResponse.setResponse(response);
                exchange.getIn().setBody(sessionBeginEndResponse);
                break;
            case "Endpoint[direct://retrieveDeviceUsageHistoryCarrier]":
            case "Endpoint[direct://deviceSessionUsage]":
                final UsageInformationResponse usageInformationResponse = new UsageInformationResponse();
                usageInformationResponse.setHeader(responseHeader);
                usageInformationResponse.setResponse(response);
                exchange.getIn().setBody(usageInformationResponse);
                break;
            case "Endpoint[direct://getDeviceUsageInfoDB]":
                final UsageInformationMidwayResponse usageInformationMidwayResponse = new UsageInformationMidwayResponse();
                usageInformationMidwayResponse.setHeader(responseHeader);
                usageInformationMidwayResponse.setResponse(response);
                exchange.getIn().setBody(usageInformationMidwayResponse);
                break;
            case "Endpoint[direct://getDeviceConnectionHistoryInfoDB]":
                final ConnectionInformationMidwayResponse connectionInformationMidwayResponse = new ConnectionInformationMidwayResponse();
                connectionInformationMidwayResponse.setHeader(responseHeader);
                connectionInformationMidwayResponse.setResponse(response);
                exchange.getIn().setBody(connectionInformationMidwayResponse);
                break;
            case "Endpoint[direct://getDevicesUsageByDayAndCarrierInfoDB]":
                final DevicesUsageByDayAndCarrierResponse devicesUsageByDayAndCarrierResponse = new DevicesUsageByDayAndCarrierResponse();
                devicesUsageByDayAndCarrierResponse.setHeader(responseHeader);
                devicesUsageByDayAndCarrierResponse.setResponse(response);
                exchange.getIn().setBody(devicesUsageByDayAndCarrierResponse);
                break;
            default:
                break;
        }

        LOGGER.info("End:GenericErrorProcessor");
    }
}
