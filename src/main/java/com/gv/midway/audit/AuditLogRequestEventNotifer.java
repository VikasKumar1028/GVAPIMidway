package com.gv.midway.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.TimeZone;

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
			String jsonInString = mapper.writeValueAsString(exchange.getIn()
					.getBody());

			if (exchange.getIn().getBody() instanceof BaseRequest && exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)==null) {
				logger.info("In Audit log Request*************************************");
				BaseRequest baseRequest = (BaseRequest) exchange.getIn()
						.getBody();
				String msgBody = (String) exchange.getIn().getBody().toString();

				long timestamp = System.currentTimeMillis();
				String TransactionId = Long.toString(timestamp);
				exchange.setProperty(IConstant.AUDIT_TRANSACTION_ID,
						TransactionId);

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat(
						"dd/MM/yyyy:HH:mm:ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT"));
				
				String requestEndpint =exchange.getFromEndpoint().toString();
				String requestEndpintSpilt[] =requestEndpint.split("//");
				
				
				logger.info("requestEndpintSpilt::"+requestEndpintSpilt[1].replaceAll("]", " "));
				
				String apiOperationName= "GV_"+requestEndpintSpilt[1].replaceAll("]", "")+"_ProxyRequest";
				logger.info("apiOperationName"+apiOperationName);
				
				
				Audit audit = new Audit();
				/*
				 * audit.setCarrier(baseRequest.getHeader().getBsCarrier());
				 * audit.setSource(baseRequest.getHeader().getSourceName());
				 * audit.setApiAction(exchange.getFromEndpoint().toString());
				 * audit.setInboundURL(exchange.getFromEndpoint().toString());
				 * audit.setTransactionId(TransactionId);
				 */
				audit.setApi_OpreationName(apiOperationName);
				audit.setFrom(baseRequest.getHeader().getSourceName());
				audit.setTo(exchange.getFromEndpoint().toString());
				audit.setTimeStamp(localTime);
				audit.setAuditTransationID(TransactionId);
				// audit.setStatus(exchange.getProperty(name));

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
