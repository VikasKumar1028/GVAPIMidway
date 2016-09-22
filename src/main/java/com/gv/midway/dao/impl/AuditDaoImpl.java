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

            String requestEndpoint = exchange.getFromEndpoint().toString();
            String requestEndpointSplit[] = requestEndpoint.split("//");

            LOGGER.info("requestEndpointSplit::"
                    + requestEndpointSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + requestEndpointSplit[1].replaceAll("]", "")
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

            String responseEndpoint = exchange.getFromEndpoint().toString();
            String responseEndpointSplit[] = responseEndpoint.split("//");

            LOGGER.info("requestEndpointSplit::"
                    + responseEndpointSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseEndpointSplit[1].replaceAll("]", "")
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

            LOGGER.info("business response is.........."
                    + exchange.getIn().getBody());

            audit.setPayload(exchange.getIn().getBody());
            mongoTemplate.insert(audit);

        } catch (Exception e) {
            LOGGER.info("auditExternalResponseCall-Exception" + e);
        }

        LOGGER.info("End-AuditDaoImpl:auditExternalResponseCall");

    }
    
    /**
     * Method Saves the Audit Object for External SOAP Response
     */
    @Override
    public void auditExternalSOAPResponseCall(Exchange exchange) {

        LOGGER.info("Start-AuditDaoImpl:auditExternalResponseCall");

        try {

            String responseEndpint = exchange.getFromEndpoint().toString();
            String requestEndpointSplit[] = responseEndpint.split("//");

            LOGGER.info("requestEndpointSplit::"
                    + requestEndpointSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + requestEndpointSplit[1].replaceAll("]", "")
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
            
            LOGGER.info("soap object of class type..."+exchange.getIn().getBody().getClass()+" "+exchange.getIn().getBody().toString());
            
            String soapPayload=CommonUtil.getSOAPResposneFromExchange(exchange);
            
            audit.setPayload(soapPayload);
            
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
            String responseExceptionEndpintSplit[] = responseExceptionEndpint
                    .split("//");

            LOGGER.info("responseExceptionEndpintSplit::"
                    + responseExceptionEndpintSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseExceptionEndpintSplit[1].replaceAll("]", "")
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
                    audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
                   
                    audit.setErrorCode(Integer.parseInt(responsePayload
                            .getErrorCode()));

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
     * Method Saves the Audit Object for External SOAP Exception
     */
    @Override
    public void auditExternalSOAPExceptionResponseCall(Exchange exchange) {

        LOGGER.info("Begin-AuditDaoImpl:auditExternalSOAPExceptionResponseCall");
        
       
        SoapFault soapFault = (SoapFault) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);


        try {
            // changes for the audit

            String responseExceptionEndpint = exchange.getFromEndpoint()
                    .toString();
            String responseExceptionEndpintSplit[] = responseExceptionEndpint
                    .split("//");

            LOGGER.info("responseExceptionEndpintSplit::"
                    + responseExceptionEndpintSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseExceptionEndpintSplit[1].replaceAll("]", "")
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
            
            audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
            
          
            String message=soapFault.getMessage();
    		
    		LOGGER.info("soap fault code    ------------------" +message);
            
            audit.setErrorCode(Integer.parseInt(message));
            
            
            String payload = CommonUtil
					.getSOAPErrorResposneFromExchange(exchange);

			LOGGER.info("soap fault payload  soap message is ------------------"
					+ payload);

			audit.setPayload(payload);
           
    		
    		audit.setErrorDetails(exchange.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE).toString());
           
           
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

            String responseExceptionEndpoint = exchange.getFromEndpoint()
                    .toString();
            String responseExceptionEndpointSplit[] = responseExceptionEndpoint
                    .split("//");

            LOGGER.info("requestEndpintSplit::"
                    + responseExceptionEndpointSplit[1].replaceAll("]", " "));

            String apiOperationName = "GV_"
                    + responseExceptionEndpointSplit[1].replaceAll("]", "")
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
