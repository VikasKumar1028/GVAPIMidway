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

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
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

			/*ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(exchange.getIn()
					.getBody());*/

			log.info("auditExternalRequestCall-jsonInString::" + exchange.getIn().getBody().toString());

			String requestEndpint = exchange.getFromEndpoint().toString();
			String requestEndpintSpilt[] = requestEndpint.split("//");

			log.info("requestEndpintSpilt::"
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

			log.info("apiOperationName" + apiOperationName);

			Audit audit = new Audit();

			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));

			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(
					IConstant.AUDIT_TRANSACTION_ID).toString());

			audit.setGvTransationId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
			audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME).toString());
			audit.setPayload(exchange.getIn().getBody());
			mongoTemplate.insert(audit);


		} catch (Exception e) {
			log.info("auditExternalRequestCall-Exception" + e);
		}

		log.info("End-AuditDaoImpl :auditExternalRequestCall");

	}

	public void auditExternalResponseCall(Exchange exchange) {
		// TODO Auto-generated method stub

		log.info("Start-AuditDaoImpl:auditExternalResponseCall");

		try {

			/*ObjectMapper mapper = new ObjectMapper();
			String msgBody = mapper.writeValueAsString(exchange.getIn()
					.getBody());*/

			String responseEndpint = exchange.getFromEndpoint().toString();
			String responseEndpintSpilt[] = responseEndpint.split("//");

			log.info("requestEndpintSpilt::"
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
			log.info("apiOperationName" + apiOperationName);

			Audit audit = new Audit();

			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));

			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(
					IConstant.AUDIT_TRANSACTION_ID).toString());
			audit.setGvTransationId(exchange.getProperty(
					IConstant.GV_TRANSACTION_ID).toString());
			audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
					.toString());
			/*
			 * audit.setErrorDetais(exchange.getProperty(IConstant.
			 * RESPONSE_DESCRIPTION).toString());
			 * audit.setErrorProblem(exchange.
			 * getProperty(IConstant.ERROR_MESSAGE).toString());
			 * audit.setErrorCode
			 * (exchange.getProperty(IConstant.RESPONSE_CODE).toString());
			 */

			audit.setPayload(exchange.getIn().getBody());
			mongoTemplate.insert(audit);

			// }
		} catch (Exception e) {
			log.info("auditExternalResponseCall-Exception" + e);
		}

		log.info("End-AuditDaoImpl:auditExternalResponseCall");

	}

	public void auditExternalExceptionResponseCall(Exchange exchange) {

		log.info("Start-AuditDaoImpl:auditExternalExceptionResponseCall");
		String responseBody = "";
		//TODO populate into below object
		//KoreErrorResponse errorBody 
		
		CxfOperationException exception = null;

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

			log.info("requestEndpintSpilt::"
					+ responseExceptionEndpintSpilt[1].replaceAll("]", " "));

			String apiOperationName = "GV_"
					+ responseExceptionEndpintSpilt[1].replaceAll("]", "")
					+ "_BusinessResponse";

			if (exchange
					.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
				apiOperationName = apiOperationName
						+ "_deviceNumber_"
						+ exchange.getProperty(
								IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
								.toString();
			}

			log.info("apiOperationName" + apiOperationName);

			Audit audit = new Audit();

			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));

			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(
					IConstant.AUDIT_TRANSACTION_ID).toString());
			audit.setGvTransationId(exchange.getProperty(
					IConstant.GV_TRANSACTION_ID).toString());
			audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
					.toString());
			/*
			 * audit.setErrorDetais(exchange.getProperty(
			 * IConstant.RESPONSE_DESCRIPTION).toString());
			 * audit.setErrorProblem(exchange.getProperty(
			 * IConstant.RESPONSE_STATUS).toString());
			 * 
			 * audit.setErrorCode(exchange.getProperty(IConstant.RESPONSE_CODE)
			 * .toString());
			 */
			// TODO

			audit.setErrorDetails("ERROR");
			audit.setErrorProblem("ERROR ");
			audit.setErrorCode(400);

			audit.setPayload(responseBody);
			mongoTemplate.save(audit);

		} catch (Exception e) {
			log.info("auditExternalExceptionResponseCall" + e);
		}
	}

	public void auditExternalConnectionExceptionResponseCall(Exchange exchange) {

		log.info("Start-auditExternalConnectionExceptionResponseCall");
		String responseBody = "";

		CxfOperationException exception = null;

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

			log.info("requestEndpintSpilt::"
					+ responseExceptionEndpintSpilt[1].replaceAll("]", " "));

			String apiOperationName = "GV_"
					+ responseExceptionEndpintSpilt[1].replaceAll("]", "")
					+ "_BusinessResponse";

			if (exchange
					.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER) != null) {
				apiOperationName = apiOperationName
						+ "_deviceNumber_"
						+ exchange.getProperty(
								IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)
								.toString();
			}

			log.info("apiOperationName" + apiOperationName);

			Audit audit = new Audit();

			Date localTime = new Date();
			DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			converter.setTimeZone(TimeZone.getTimeZone("GMT"));

			audit.setApi_OpreationName(apiOperationName);
			audit.setFrom(exchange.getProperty("sourceName").toString());
			audit.setTo(exchange.getFromEndpoint().toString());
			audit.setTimeStamp(localTime);
			audit.setAuditTransationID(exchange.getProperty(
					IConstant.AUDIT_TRANSACTION_ID).toString());
			audit.setGvTransationId(exchange.getProperty(
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
			log.info("auditExternalConnectionExceptionResponseCall" + e);
		}
	}

}
