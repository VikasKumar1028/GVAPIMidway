package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.CarrierProvisioningDeviceResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.kore.KoreErrorResponse;


public class KoreGenericExceptionProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(KoreGenericExceptionProcessor.class.getName());

    Environment newEnv;

    public KoreGenericExceptionProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    public KoreGenericExceptionProcessor() {
        // Empty Constructor
    }

    @Override
    public void process(Exchange exchange) throws Exception {

    	LOGGER.info("Begin:KoreGenericExceptionProcessor");
        CxfOperationException exception = (CxfOperationException) exchange
                .getProperty(Exchange.EXCEPTION_CAUGHT);

        LOGGER.info("----KoreGenericExceptionProcessor----------"
                + exception.getResponseBody());
        LOGGER.info("----.getStatusCode()----------" + exception.getStatusCode());

        ObjectMapper mapper = new ObjectMapper();

        KoreErrorResponse responsePayload = mapper.readValue(
                exception.getResponseBody(), KoreErrorResponse.class);

        LOGGER.info("----response payload is----------"
                + responsePayload.toString());

        Response response = new Response();

        response.setResponseCode(IResponse.INVALID_PAYLOAD);

        response.setResponseStatus(IResponse.ERROR_MESSAGE);
        response.setResponseDescription(responsePayload.getErrorMessage());

        Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);
        
        switch (exchange.getFromEndpoint().toString()) {

		case "Endpoint[direct://deviceInformationCarrier]":
			DeviceInformationResponse responseObject = new DeviceInformationResponse();
			responseObject.setHeader(responseHeader);
			responseObject.setResponse(response);
			exchange.getIn().setBody(responseObject);
			break;
		case "Endpoint[direct://activateDevice]":
			CarrierProvisioningDeviceResponse activateResposne = new CarrierProvisioningDeviceResponse();
			activateResposne.setHeader(responseHeader);
			activateResposne.setResponse(response);
            exchange.getIn().setBody(activateResposne);
			break;
		case "Endpoint[direct://deactivateDevice]":
			CarrierProvisioningDeviceResponse deactivateDeviceResponse = new CarrierProvisioningDeviceResponse();
			deactivateDeviceResponse.setHeader(responseHeader);
			deactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(deactivateDeviceResponse);
			break;
		case "Endpoint[direct://suspendDevice]":
			CarrierProvisioningDeviceResponse suspendDeviceResponse = new CarrierProvisioningDeviceResponse();
			suspendDeviceResponse.setHeader(responseHeader);
			suspendDeviceResponse.setResponse(response);
			exchange.getIn().setBody(suspendDeviceResponse);
			break;
		case "Endpoint[direct://customeFields]":
			CarrierProvisioningDeviceResponse customFieldsDeviceResponse = new CarrierProvisioningDeviceResponse();
			customFieldsDeviceResponse.setHeader(responseHeader);
			customFieldsDeviceResponse.setResponse(response);
			exchange.getIn().setBody(customFieldsDeviceResponse);
			break;
		case "Endpoint[direct://changeDeviceServicePlans]":
			CarrierProvisioningDeviceResponse changeDeviceServicePlansResponse = new CarrierProvisioningDeviceResponse();
			changeDeviceServicePlansResponse.setHeader(responseHeader);
			changeDeviceServicePlansResponse.setResponse(response);
			exchange.getIn().setBody(changeDeviceServicePlansResponse);
			break;
		case "Endpoint[direct://reactivateDevice]":
			CarrierProvisioningDeviceResponse reactivateDeviceResponse = new CarrierProvisioningDeviceResponse();
			reactivateDeviceResponse.setHeader(responseHeader);
			reactivateDeviceResponse.setResponse(response);
			exchange.getIn().setBody(reactivateDeviceResponse);
			break;
		case "Endpoint[direct://restoreDevice]":
			CarrierProvisioningDeviceResponse restoreDeviceResponse = new CarrierProvisioningDeviceResponse();
			restoreDeviceResponse.setHeader(responseHeader);
			restoreDeviceResponse.setResponse(response);
			exchange.getIn().setBody(restoreDeviceResponse);
			break;
		default:
			break;
		}

     	LOGGER.info("End:KoreGenericExceptionProcessor");

    }
}
