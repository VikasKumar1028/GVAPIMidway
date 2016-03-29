package com.gv.midway.audit;

import java.util.EventObject;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCreatedEvent;
//ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.audit.Audit;

public class AuditLogRequestEventNotifer extends EventNotifierSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(AuditLogRequestEventNotifer.class); // Initializing

	@Autowired
	MongoTemplate mongoTemplate;

	public void notify(EventObject event) throws Exception {
		if (event instanceof ExchangeCreatedEvent) {
			ExchangeCreatedEvent create = (ExchangeCreatedEvent) event;
			Exchange exchange = create.getExchange();
			logger.info("In Audit log Request");

	
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString( exchange.getIn().getBody());

			
			
			if (exchange.getIn().getBody() instanceof 
				BaseRequest) {
				logger.info("In Audit log Request1");
				BaseRequest baseRequest = (BaseRequest) exchange.getIn()
						.getBody();
				String msgBody = (String) exchange.getIn().getBody().toString();
				
				long timestamp = System.currentTimeMillis();
				String TransactionId = Long.toString(timestamp);
				exchange.setProperty(IConstant.AUDIT_TRANSACTION_ID, TransactionId);

				Audit audit = new Audit();
				audit.setCarrier(baseRequest.getHeader().getBsCarrier());
				audit.setSource(baseRequest.getHeader().getSourceName());
				audit.setApiAction(exchange.getFromEndpoint().toString());
				audit.setInboundURL(exchange.getFromEndpoint().toString());
				audit.setTransactionId(TransactionId);
				audit.setPayload(jsonInString);
				mongoTemplate.save(audit);

			}
		}

	}

	public boolean isEnabled(EventObject event) {
		return event instanceof ExchangeCreatedEvent;
	}

	protected void doStart() throws Exception {
		// noop
	}

	protected void doStop() throws Exception {
		// noop
	}
}
