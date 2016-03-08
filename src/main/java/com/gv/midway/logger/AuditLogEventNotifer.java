package com.gv.midway.logger;

import java.util.EventObject;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCreatedEvent;
//ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gv.midway.dao.impl.DeviceDaoImpl;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.audit.Audit;

public class AuditLogEventNotifer extends EventNotifierSupport {
	
	 private static final Logger logger = LoggerFactory.getLogger(AuditLogEventNotifer.class); // Initializing
		

	@Autowired
	MongoTemplate mongoTemplate;

	public void notify(EventObject event) throws Exception {
		if (event instanceof ExchangeCreatedEvent) {
			ExchangeCreatedEvent create = (ExchangeCreatedEvent) event;
			Exchange exchange = create.getExchange();
logger.info("In Audit log");
			System.out.println("--------"+exchange.getIn().getBody().getClass());
			
			
			BaseRequest baseRequest = (BaseRequest) exchange.getIn().getBody();
			String msgBody = (String) exchange.getIn().getBody().toString();

		
			// Audit audit = new Audit();
			long timestamp = System.currentTimeMillis();
			String TransactionId = Long.toString(timestamp);
			exchange.setProperty("TransactionId", TransactionId);

			if (!create.getSource().toString().startsWith("Mongo")) {
				Audit audit = new Audit();

				audit.setCarrier(baseRequest.getHeader().getBsCarrier());
				// audit.setAudit_id(audit_id);
				audit.setSource(baseRequest.getHeader().getSourceName());
				audit.setApiAction(exchange.getFromEndpoint().toString());
				audit.setInboundURL(exchange.getFromEndpoint().toString());
				audit.setTransactionId(TransactionId);

				// audit.setOutboundURL(create.getEndpoint().toString());

				// audit.setPayload(msgBody);
				mongoTemplate.save(audit);

			}

			/*
			 * log.info("Took " + sent.getTimeTaken() + " millis to send to: " +
			 * sent.getEndpoint());
			 */}

	}

	public boolean isEnabled(EventObject event) {
		// we only want the sent events
		return event instanceof ExchangeCreatedEvent;
	}

	protected void doStart() throws Exception {
		// noop
	}

	protected void doStop() throws Exception {
		// noop
	}
}
