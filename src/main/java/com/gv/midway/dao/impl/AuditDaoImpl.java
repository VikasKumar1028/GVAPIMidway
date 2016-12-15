package com.gv.midway.dao.impl;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.cxf.binding.soap.SoapFault;
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
import com.gv.midway.utility.CommonUtil;

@Service
public class AuditDaoImpl implements IAuditDao {

    private static final String _BusinessResponse = "_BusinessResponse";
    private static final String _BusinessRequest = "_BusinessRequest";
    private static final String _BusinessExternalError = "_BusinessExternalError";
    private static final String _BusinessConnectionError = "_BusinessConnectionError";
    private static final Logger LOGGER = Logger.getLogger(AuditDaoImpl.class.getName());

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method Saves the Audit Object for External Request
     */
    @Override
    public void auditExternalRequestCall(Exchange exchange) {
        LOGGER.debug("Begin-AuditDaoImpl :auditExternalRequestCall" + exchange.getIn().getBody());

        try {
            LOGGER.debug("auditExternalRequestCall-jsonInString::" + exchange.getIn().getBody());

            final String apiOperationName = getAPIOperationName(exchange, _BusinessRequest, "requestEndpointSplit");

            LOGGER.info("apiOperationName" + apiOperationName);

            mongoTemplate.insert(newAudit(apiOperationName, exchange, exchange.getIn().getBody()));

        } catch (Exception e) {
            LOGGER.error("auditExternalRequestCall-Exception" + e);
            //TODO Should this really be ignored?
        }

        LOGGER.debug("End-AuditDaoImpl :auditExternalRequestCall");
    }

    /**
     * Method Saves the Audit Object for External Response
     */
    @Override
    public void auditExternalResponseCall(Exchange exchange) {

        LOGGER.debug("Start-AuditDaoImpl:auditExternalResponseCall");

        try {
            final String apiOperationName = getAPIOperationName(exchange, _BusinessResponse, "responseEndpointSplit");

            LOGGER.info("apiOperationName" + apiOperationName);
            LOGGER.debug("business response is.........." + exchange.getIn().getBody());

            mongoTemplate.insert(newAudit(apiOperationName, exchange, exchange.getIn().getBody()));

        } catch (Exception e) {
            LOGGER.error("auditExternalResponseCall-Exception" + e);
            //TODO Should this really be ignored?
        }

        LOGGER.debug("End-AuditDaoImpl:auditExternalResponseCall");
    }

    private String getAPIOperationName(Exchange exchange, String requestResponseDistinction, String logName) {
        final String endpoint = exchange.getFromEndpoint().toString();
        final String[] splitEndpoint = endpoint.split("//");

        LOGGER.info(logName + splitEndpoint[1].replaceAll("]", " "));

        String apiOperationName = "GV_" + splitEndpoint[1].replaceAll("]", "") + requestResponseDistinction;

        if (exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
            apiOperationName =
                    apiOperationName + "_deviceNumber_" + exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER).toString();
        }
        return apiOperationName;
    }

    private Audit newAudit(String operationName, Exchange exchange, Object payload) {
        final Audit audit = new Audit();
        audit.setApiOperationName(operationName);
        audit.setFrom(exchange.getProperty(IConstant.SOURCE_NAME).toString());
        audit.setTo(exchange.getFromEndpoint().toString());
        audit.setTimeStamp(new Date());
        audit.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
        audit.setGvTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
        audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME).toString());
        audit.setPayload(payload);
        return audit;
    }

    private Audit newErrorAudit(String operationName, Exchange exchange, Object payload, String problem, String message, String details) {
        final Audit audit = newAudit(operationName, exchange, payload);
        audit.setErrorProblem(problem);
        audit.setErrorCode(Integer.parseInt(message));
        audit.setErrorDetails(details);
        return audit;
    }

    /**
     * Method Saves the Audit Object for External SOAP Response
     */
    @Override
    public void auditExternalSOAPResponseCall(Exchange exchange) {

        LOGGER.debug("Start-AuditDaoImpl:auditExternalResponseCall");

        try {
            final String apiOperationName = getAPIOperationName(exchange, _BusinessResponse, "responseEndpointSplit");

            LOGGER.info("apiOperationName" + apiOperationName);
            LOGGER.debug("soap object of class type..."+exchange.getIn().getBody().getClass()+" "+exchange.getIn().getBody().toString());

            final String soapPayload = CommonUtil.getSOAPResponseFromExchange(exchange);

            mongoTemplate.insert(newAudit(apiOperationName, exchange, soapPayload));

        } catch (Exception e) {
            //TODO Should this really be ignored?
            LOGGER.error("auditExternalResponseCall-Exception" + e);
        }
        LOGGER.debug("End-AuditDaoImpl:auditExternalResponseCall");
    }

    /**
     * Method Saves the Audit Object for External Exception
     */
    @Override
    public void auditExternalExceptionResponseCall(Exchange exchange) {

        LOGGER.debug("Begin-AuditDaoImpl:auditExternalExceptionResponseCall");

        final Object exceptionCaught = exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        final String responseBody = exceptionCaught == null ? "" : ((CxfOperationException)exceptionCaught).getResponseBody();

        try {
            final String apiOperationName = getAPIOperationName(exchange, _BusinessExternalError, "responseExceptionEndpointSplit");

            LOGGER.info("apiOperationName" + apiOperationName);

            final Audit audit = newAudit(apiOperationName, exchange, null);

            final ObjectMapper mapper = new ObjectMapper();

            try {
                if (IConstant.BSCARRIER_SERVICE_VERIZON.equals(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {
                    final VerizonErrorResponse responsePayload = mapper.readValue(responseBody, VerizonErrorResponse.class);

                    audit.setErrorDetails(responsePayload.getErrorCode() + " - " + responsePayload.getErrorMessage());
                    audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
                    audit.setErrorCode(400); //Verizon's ErrorCodes come in as a string ie (REQUEST_FAILED.CarrierNotOnAccount)
                    audit.setPayload(responsePayload);

                } else if (IConstant.BSCARRIER_SERVICE_KORE.equals(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {
                    final KoreErrorResponse responsePayload = mapper.readValue(responseBody, KoreErrorResponse.class);

                    audit.setErrorDetails(responsePayload.getErrorMessage());
                    audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
                    audit.setErrorCode(Integer.parseInt(responsePayload.getErrorCode()));
                    audit.setPayload(responsePayload);
                }
            } catch (Exception ex) {
                LOGGER.error("Error in Parsing Json"+ex);
            }

            mongoTemplate.save(audit);

        } catch (Exception e) {
            //TODO Should this really be ignored?
            LOGGER.error("auditExternalExceptionResponseCall" + e);
        }
        LOGGER.debug("End-AuditDaoImpl:auditExternalExceptionResponseCall");
    }

    /**
     * Method Saves the Audit Object for External SOAP Exception
     */
    @Override
    public void auditExternalSOAPExceptionResponseCall(Exchange exchange) {

        LOGGER.debug("Begin-AuditDaoImpl:auditExternalSOAPExceptionResponseCall");

        final SoapFault soapFault = (SoapFault)exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

        try {
            final String apiOperationName = getAPIOperationName(exchange, _BusinessExternalError, "responseExceptionEndpointSplit");

            LOGGER.info("apiOperationName" + apiOperationName);

            final String message = soapFault.getMessage();
            LOGGER.warn("soap fault code    ------------------" + message);
            final String payload = CommonUtil.getSOAPErrorResponseFromExchange(exchange);
            LOGGER.warn("soap fault payload  soap message is ------------------" + payload);

            final Audit audit =
                    newErrorAudit(apiOperationName, exchange, payload, IConstant.CARRIER_TRANSACTION_STATUS_ERROR, message, exchange.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE).toString());

            mongoTemplate.save(audit);
        } catch (Exception e) {
            //TODO Should this really be ignored?
            LOGGER.error("auditExternalExceptionResponseCall" + e);
        }
        LOGGER.debug("End-AuditDaoImpl:auditExternalSOAPExceptionResponseCall");
    }

    /**
     * Method Saves the Audit Object for Connection Error Response
     */
    @Override
    public void auditExternalConnectionExceptionResponseCall(Exchange exchange) {

        LOGGER.debug("Start-auditExternalConnectionExceptionResponseCall");
        final String responseBody = exchange.getProperty(Exchange.EXCEPTION_CAUGHT) == null ?
                "" : exchange.getProperty(Exchange.EXCEPTION_CAUGHT).toString();

        try {
            final String apiOperationName = getAPIOperationName(exchange, _BusinessConnectionError, "requestEndpointSplit");
            LOGGER.info("apiOperationName" + apiOperationName);

            final Audit audit = newErrorAudit(apiOperationName, exchange, responseBody, exchange.getProperty(IConstant.RESPONSE_STATUS).toString(), IResponse.CONNECTION_ERROR_CODE.toString(), exchange.getProperty(IConstant.RESPONSE_DESCRIPTION).toString());

            mongoTemplate.save(audit);

        } catch (Exception e) {
            //TODO Should this really be ignored?
            e.printStackTrace();
            LOGGER.error("auditExternalConnectionExceptionResponseCall" + e);
        }
        LOGGER.debug("End-auditExternalConnectionExceptionResponseCall");
    }
}