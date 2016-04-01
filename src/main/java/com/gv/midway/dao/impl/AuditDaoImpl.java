package com.gv.midway.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IAuditDao;
import com.gv.midway.pojo.audit.Audit;

@Service
public class AuditDaoImpl implements IAuditDao {

	Logger log = Logger.getLogger(AuditDaoImpl.class.getName());

	@Autowired
	MongoTemplate mongoTemplate;

	public void auditExternalRequestCall(Exchange exchange) {
		// TODO Auto-generated method stub
		log.info("Start-AuditDaoImpl :auditExternalRequestCall"
				+ exchange.getIn().getBody());

		try {

			ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(exchange.getIn()
					.getBody());

			log.info("auditExternalRequestCall-jsonInString::" + msgBody);
			
			String requestEndpint =exchange.getFromEndpoint().toString();
			String requestEndpintSpilt[] =requestEndpint.split("//");
			
			
			log.info("requestEndpintSpilt::"+requestEndpintSpilt[1].replaceAll("]", " "));
			
			String apiOperationName= "GV_"+requestEndpintSpilt[1].replaceAll("]", "")+"_ProxyRequest";
			log.info("apiOperationName"+apiOperationName);
		
			

			Audit audit = new Audit();
		
			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat(
					"dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
			
			audit.setPayload(msgBody);
			mongoTemplate.save(audit);

		} catch (Exception e) {
			log.info("auditExternalRequestCall-Exception" + e.getMessage());
		}

		log.info("End-AuditDaoImpl :auditExternalRequestCall");

	}

	public void auditExternalResponseCall(Exchange exchange) {
		// TODO Auto-generated method stub

		log.info("Start-AuditDaoImpl:auditExternalResponseCall");

		try {

			ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(exchange.getIn()
					.getBody());
			
			String responseEndpint =exchange.getFromEndpoint().toString();
			String responseEndpintSpilt[] =responseEndpint.split("//");
			
			
			log.info("requestEndpintSpilt::"+responseEndpintSpilt[1].replaceAll("]", " "));
			
			String apiOperationName= "GV_"+responseEndpintSpilt[1].replaceAll("]", "")+"_ProxyResponse";
			log.info("apiOperationName"+apiOperationName);
		

			Audit audit = new Audit();
		
			
			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat(
					"dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
			audit.setErrorDetais(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION).toString());
			audit.setErrorProblem(exchange.getProperty(IConstant.ERROR_MESSAGE).toString());
			audit.setErrorCode(exchange.getProperty(IConstant.RESPONSE_CODE).toString());
			
			audit.setPayload(msgBody);
			mongoTemplate.save(audit);

			// }
		} catch (Exception e) {
			log.info("auditExternalResponseCall-Exception" + e.getMessage());
		}

		log.info("End-AuditDaoImpl:auditExternalResponseCall");

	}

	public void auditExternalExceptionResponseCall(Exchange exchange) {

		log.info("Start-AuditDaoImpl:auditExternalExceptionResponseCall");

		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		String responseBody = exception.getResponseBody();
		
		

		try {
			//changes for the audit
			
			String responseExceptionEndpint =exchange.getFromEndpoint().toString();
			String responseExceptionEndpintSpilt[] =responseExceptionEndpint.split("//");
			
			
			log.info("requestEndpintSpilt::"+responseExceptionEndpintSpilt[1].replaceAll("]", " "));
			
			String apiOperationName= "GV_"+responseExceptionEndpintSpilt[1].replaceAll("]", "")+"_ProxyResponse";
			log.info("apiOperationName"+apiOperationName);
		

			Audit audit = new Audit();
		
			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat(
					"dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
			audit.setErrorDetais(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION).toString());
			audit.setErrorProblem(exchange.getProperty(IConstant.RESPONSE_STATUS).toString());
			audit.setErrorCode(exchange.getProperty(IConstant.RESPONSE_CODE).toString());
			audit.setPayload(responseBody);
			mongoTemplate.save(audit);

		} catch (Exception e) {
			log.info("auditExternalExceptionResponseCall" + e.getMessage());
		}
	}

}
