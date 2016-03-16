package com.gv.midway.dao.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.dao.IAuditDao;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.audit.Audit;

@Service
public class AuditDaoImpl implements IAuditDao {

	Logger log = Logger.getLogger(AuditDaoImpl.class.getName());

	@Autowired
	MongoTemplate mongoTemplate;

	public void auditExternalRequestCall(Exchange exchange) {
		// TODO Auto-generated method stub
		log.info("Start-AuditDaoImpl :auditExternalRequestCall");

		try {
			if (exchange.getIn().getBody() instanceof BaseRequest) {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(exchange.getIn()
						.getBody());

				BaseRequest baseRequest = (BaseRequest) exchange.getIn()
						.getBody();

				log.info("auditExternalRequestCall-jsonInString::" + msgBody);
				long timestamp = System.currentTimeMillis();
				String TransactionId = Long.toString(timestamp);
				exchange.setProperty("TransactionId", TransactionId);

				Audit audit = new Audit();
				audit.setCarrier(baseRequest.getHeader().getBsCarrier());
				audit.setSource(baseRequest.getHeader().getSourceName());
				audit.setApiAction(exchange.getFromEndpoint().toString());
				audit.setInboundURL(exchange.getFromEndpoint().toString());
				audit.setTransactionId(TransactionId);
				audit.setPayload(msgBody);
				mongoTemplate.save(audit);

			}
		} catch (Exception e) {
			log.info("auditExternalRequestCall-Exception" + e.getMessage());
		}

		log.info("End-AuditDaoImpl :auditExternalRequestCall");

	}


	public void auditExternalResponseCall(Exchange exchange) {
		// TODO Auto-generated method stub

		log.info("Start-AuditDaoImpl:auditExternalResponseCall");

		try {
			if (exchange.getIn().getBody() instanceof BaseResponse) {

				BaseResponse baseResponse = (BaseResponse) exchange.getIn()
						.getBody();
				
				String TransactionId = (String) exchange
						.getProperty("TransactionId");

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(exchange.getIn()
						.getBody());

				log.info("auditExternalResponseCall-jsonInString::" + msgBody);

				Audit audit = new Audit();
				audit.setCarrier(baseResponse.getHeader().getBsCarrier());
				audit.setSource(baseResponse.getHeader().getSourceName());
				audit.setApiAction(exchange.getFromEndpoint().toString());
				audit.setInboundURL(exchange.getFromEndpoint().toString());
				audit.setTransactionId(TransactionId);
				audit.setPayload(msgBody);
				mongoTemplate.save(audit);

			}
		} catch (Exception e) {
			log.info("auditExternalResponseCall-Exception" + e.getMessage());
		}

		log.info("End-AuditDaoImpl:auditExternalResponseCall");

	}

}
