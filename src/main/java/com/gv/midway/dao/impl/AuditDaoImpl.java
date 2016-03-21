package com.gv.midway.dao.impl;

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

			Audit audit = new Audit();
			audit.setCarrier(exchange.getProperty("bsCarrier").toString());
			audit.setSource(exchange.getProperty("sourceName").toString());
			audit.setApiAction(exchange.getIn().getHeader(Exchange.HTTP_PATH)
					.toString());
			audit.setInboundURL(exchange.getFromEndpoint().toString());
			audit.setTransactionId(exchange.getProperty("TransactionId")
					.toString());
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

			Audit audit = new Audit();
			audit.setCarrier(exchange.getProperty(IConstant.BSCARRIER)
					.toString());
			audit.setSource(exchange.getProperty(IConstant.SOURCE_NAME)
					.toString());
			audit.setApiAction(exchange.getFromEndpoint().toString());
			audit.setInboundURL(exchange.getFromEndpoint().toString());
			audit.setTransactionId(exchange.getProperty("TransactionId")
					.toString());
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

			Audit audit = new Audit();
			audit.setCarrier(exchange.getProperty(IConstant.BSCARRIER)
					.toString());
			audit.setSource(exchange.getProperty(IConstant.SOURCE_NAME)
					.toString());
			audit.setApiAction(exchange.getFromEndpoint().toString());
			audit.setInboundURL(exchange.getFromEndpoint().toString());
			audit.setTransactionId(exchange.getProperty("TransactionId")
					.toString());
			audit.setPayload(responseBody);
			mongoTemplate.save(audit);

		} catch (Exception e) {
			log.info("auditExternalExceptionResponseCall" + e.getMessage());
		}
	}

}
