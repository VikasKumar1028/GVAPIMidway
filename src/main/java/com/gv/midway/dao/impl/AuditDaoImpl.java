package com.gv.midway.dao.impl;


import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
     * Method Saves the Audit Object for External SOAP Response
     */
    @Override
    public void auditExternalSOAPResponseCall(Exchange exchange) {

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
            
            audit.setErrorProblem(IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
            
          
            String message=soapFault.getMessage();
    		
    		LOGGER.info("soap fault code    ------------------" +message);
            
            audit.setErrorCode(Integer.parseInt(message));
            
			SOAPMessage soapMessage = MessageFactory.newInstance()
					.createMessage();
			SOAPEnvelope envelope = soapMessage.getSOAPPart().getEnvelope();

			SOAPBody soapBody = soapMessage.getSOAPBody();

			SOAPFault fault = soapBody.addFault();

			fault.setFaultCode(soapFault.getFaultCode());
			fault.setFaultString(soapFault.getMessage());
			Detail detail = fault.addDetail();

			NodeList nodeList = soapFault.getDetail().getChildNodes();
			
			
            String errorDetails="";
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);
				
				String nodeName=node.getNodeName();
				
				String nodeTextContent=node.getTextContent();

				LOGGER.info("-----*************--Node----Name------"
						+ nodeName);
				LOGGER.info("-----*************--Node-------Text-----"
						+ nodeTextContent);

				Name entryName = envelope.createName(nodeName);

				DetailEntry entry = detail.addDetailEntry(entryName);
				entry.addTextNode(nodeTextContent);
				
				if(nodeName.contains(":message")){
					
					errorDetails=nodeTextContent;
					
					exchange.setProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE, nodeTextContent);
				}

			}

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outStream);
			String payload = new String(outStream.toByteArray(),
					StandardCharsets.UTF_8);

			LOGGER.info("soap fault payload  soap message is ------------------"
					+ payload);

			audit.setPayload(payload);
           
    		
    		audit.setErrorDetails(errorDetails);
           
           
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
