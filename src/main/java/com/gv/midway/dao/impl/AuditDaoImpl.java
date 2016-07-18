package com.gv.midway.dao.impl;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.dao.IAuditDao;
import com.gv.midway.pojo.audit.Audit;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.verizon.VerizonErrorResponse;

@Service
public class AuditDaoImpl implements IAuditDao {

    private static final Logger LOGGER = Logger.getLogger(AuditDaoImpl.class.getName());

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method Saves the Audit Object for External Request
     */
    @Override
    public void auditExternalRequestCall(Exchange exchange) {
        LOGGER.info("Begin-AuditDaoImpl :auditExternalRequestCall"
                + exchange.getIn().getBody());

        try {

            LOGGER.info("auditExternalRequestCall-jsonInString::"
                    + exchange.getIn().getBody().toString());

            String requestEndpint = exchange.getFromEndpoint().toString();
            String requestEndpintSpilt[] = requestEndpint.split("//");

            LOGGER.info("requestEndpintSpilt::"
                    + requestEndpintSpilt[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + requestEndpintSpilt[1].replaceAll("]", "")
                    + "_BusinessRequest";
            if (exchange
                    .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
                apiOperationName = apiOperationName
                        + "_deviceNumber_"
                        + exchange.getProperty(
                                IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
                                .toString();
            }

            LOGGER.info("apiOperationName" + apiOperationName);

            Audit audit = new Audit();

            Date localTime = new Date();

            audit.setApiOperationName(apiOperationName);
            audit.setFrom(exchange.getProperty("sourceName").toString());
            audit.setTo(exchange.getFromEndpoint().toString());
            audit.setTimeStamp(localTime);
            audit.setAuditTransactionId(exchange.getProperty(
                    IConstant.AUDIT_TRANSACTION_ID).toString());

            audit.setGvTransactionId(exchange.getProperty(
                    IConstant.GV_TRANSACTION_ID).toString());
            audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
                    .toString());
            audit.setPayload(exchange.getIn().getBody());
            mongoTemplate.insert(audit);

        } catch (Exception e) {
            LOGGER.info("auditExternalRequestCall-Exception" + e);
        }

        LOGGER.info("End-AuditDaoImpl :auditExternalRequestCall");

    }

    /**
     * Method Saves the Audit Object for External Response
     */
    @Override
    public void auditExternalResponseCall(Exchange exchange) {

        LOGGER.info("Start-AuditDaoImpl:auditExternalResponseCall");

        try {

            String responseEndpint = exchange.getFromEndpoint().toString();
            String responseEndpintSpilt[] = responseEndpint.split("//");

            LOGGER.info("requestEndpintSpilt::"
                    + responseEndpintSpilt[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseEndpintSpilt[1].replaceAll("]", "")
                    + "_BusinessResponse";

            if (exchange
                    .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
                apiOperationName = apiOperationName
                        + "_deviceNumber_"
                        + exchange.getProperty(
                                IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
                                .toString();
            }
            LOGGER.info("apiOperationName" + apiOperationName);

            Audit audit = new Audit();

            Date localTime = new Date();

            audit.setApiOperationName(apiOperationName);
            audit.setFrom(exchange.getProperty("sourceName").toString());
            audit.setTo(exchange.getFromEndpoint().toString());
            audit.setTimeStamp(localTime);
            audit.setAuditTransactionId(exchange.getProperty(
                    IConstant.AUDIT_TRANSACTION_ID).toString());
            audit.setGvTransactionId(exchange.getProperty(
                    IConstant.GV_TRANSACTION_ID).toString());
            audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
                    .toString());

            LOGGER.info("business resposne is.........."
                    + exchange.getIn().getBody());

            audit.setPayload(exchange.getIn().getBody());
            mongoTemplate.insert(audit);

        } catch (Exception e) {
            LOGGER.info("auditExternalResponseCall-Exception" + e);
        }

        LOGGER.info("End-AuditDaoImpl:auditExternalResponseCall");

    }

    /**
     * Method Saves the Audit Object for External Exception
     */
    @Override
    public void auditExternalExceptionResponseCall(Exchange exchange) {

        LOGGER.info("Begin-AuditDaoImpl:auditExternalExceptionResponseCall");
        String responseBody = "";
        // populate into below object
        // KoreErrorResponse errorBody

        CxfOperationException exception;

        if (exchange.getProperty(Exchange.EXCEPTION_CAUGHT) != null) {
            exception = (CxfOperationException) exchange
                    .getProperty(Exchange.EXCEPTION_CAUGHT);
            responseBody = exception.getResponseBody();
        }

        try {
            // changes for the audit

            String responseExceptionEndpint = exchange.getFromEndpoint()
                    .toString();
            String responseExceptionEndpintSpilt[] = responseExceptionEndpint
                    .split("//");

            LOGGER.info("requestEndpintSpilt::"
                    + responseExceptionEndpintSpilt[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseExceptionEndpintSpilt[1].replaceAll("]", "")
                    + "_BusinessExternalError";

            if (exchange
                    .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
                apiOperationName = apiOperationName
                        + "_deviceNumber_"
                        + exchange.getProperty(
                                IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
                                .toString();
            }

            LOGGER.info("apiOperationName" + apiOperationName);

            Audit audit = new Audit();

            Date localTime = new Date();

            audit.setApiOperationName(apiOperationName);
            audit.setFrom(exchange.getProperty("sourceName").toString());
            audit.setTo(exchange.getFromEndpoint().toString());
            audit.setTimeStamp(localTime);
            audit.setAuditTransactionId(exchange.getProperty(
                    IConstant.AUDIT_TRANSACTION_ID).toString());
            audit.setGvTransactionId(exchange.getProperty(
                    IConstant.GV_TRANSACTION_ID).toString());
            audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
                    .toString());

            ObjectMapper mapper = new ObjectMapper();

            try {
                if ("VERIZON".equals(exchange
                        .getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {

                    VerizonErrorResponse responsePayload = mapper.readValue(
                            responseBody, VerizonErrorResponse.class);

                    audit.setErrorDetails(responsePayload.getErrorMessage());
                    audit.setErrorProblem(responsePayload.getErrorCode());
                    // TODO Change the error code
                    audit.setErrorCode(400);

                    audit.setPayload(responsePayload);

                } else if ("KORE".equals(exchange
                        .getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {
                    KoreErrorResponse responsePayload = mapper.readValue(
                            responseBody, KoreErrorResponse.class);

                    audit.setErrorDetails(responsePayload.getErrorMessage());
                    audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
                    audit.setErrorCode(Integer.parseInt(responsePayload
                            .getErrorCode()));
                    audit.setPayload(responsePayload);
                }

            } catch (Exception ex) {
                LOGGER.error("Error in Parsing Json"+ex);
            }

            mongoTemplate.save(audit);

        } catch (Exception e) {
            LOGGER.info("auditExternalExceptionResponseCall" + e);
        }
    }

    /**
     * Method Saves the Audit Object for Connection Error Response
     */
    @Override
    public void auditExternalConnectionExceptionResponseCall(Exchange exchange) {

        LOGGER.info("Start-auditExternalConnectionExceptionResponseCall");
        String responseBody = "";

        if (exchange.getProperty(Exchange.EXCEPTION_CAUGHT) != null) {

            String str = exchange.getProperty(Exchange.EXCEPTION_CAUGHT)
                    .toString();
            responseBody = str;
        }

        try {
            // changes for the audit

            String responseExceptionEndpint = exchange.getFromEndpoint()
                    .toString();
            String responseExceptionEndpintSpilt[] = responseExceptionEndpint
                    .split("//");

            LOGGER.info("requestEndpintSpilt::"
                    + responseExceptionEndpintSpilt[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseExceptionEndpintSpilt[1].replaceAll("]", "")
                    + "_BusinessConnectionError";

            if (exchange
                    .getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
                apiOperationName = apiOperationName
                        + "_deviceNumber_"
                        + exchange.getProperty(
                                IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
                                .toString();
            }

            LOGGER.info("apiOperationName" + apiOperationName);

            Audit audit = new Audit();

            Date localTime = new Date();

            audit.setApiOperationName(apiOperationName);
            audit.setFrom(exchange.getProperty("sourceName").toString());
            audit.setTo(exchange.getFromEndpoint().toString());
            audit.setTimeStamp(localTime);
            audit.setAuditTransactionId(exchange.getProperty(
                    IConstant.AUDIT_TRANSACTION_ID).toString());
            audit.setGvTransactionId(exchange.getProperty(
                    IConstant.GV_TRANSACTION_ID).toString());
            audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
                    .toString());
            audit.setErrorDetails(exchange.getProperty(
                    IConstant.RESPONSE_DESCRIPTION).toString());
            audit.setErrorProblem(exchange.getProperty(
                    IConstant.RESPONSE_STATUS).toString());
            audit.setErrorCode(IResponse.CONNECTION_ERROR_CODE);
            audit.setPayload(responseBody);

            mongoTemplate.save(audit);

        } catch (Exception e) {
            LOGGER.info("auditExternalConnectionExceptionResponseCall" + e);
        }
    }

}
