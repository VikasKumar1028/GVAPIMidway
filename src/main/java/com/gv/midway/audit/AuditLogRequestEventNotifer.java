package com.gv.midway.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.audit.Audit;
import com.gv.midway.utility.CommonUtil;

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

			if (exchange.getIn().getBody() instanceof BaseRequest
					&& exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID) == null) {
				logger.info("In Audit log Request*************************************");
				BaseRequest baseRequest = (BaseRequest) exchange.getIn()
						.getBody();

				long timestamp = System.currentTimeMillis();
				String TransactionId = Long.toString(timestamp);
				exchange.setProperty(IConstant.AUDIT_TRANSACTION_ID,
						TransactionId);

				Date localTime = new Date();
				DateFormat converter = new SimpleDateFormat(
						"dd/MM/yyyy:HH:mm:ss");
				converter.setTimeZone(TimeZone.getTimeZone("GMT"));

				String requestEndpint = exchange.getFromEndpoint().toString();
				String requestEndpintSpilt[] = requestEndpint.split("//");

				logger.info("requestEndpintSpilt::"
						+ requestEndpintSpilt[1].replaceAll("]", " "));

				String apiOperationName = "GV_"
						+ requestEndpintSpilt[1].replaceAll("]", "")
						+ "_ProxyRequest";
				logger.info("apiOperationName" + apiOperationName);

				Audit audit = new Audit();

				audit.setApiOperationName(apiOperationName);
				audit.setFrom(baseRequest.getHeader().getSourceName());
				audit.setTo(exchange.getFromEndpoint().toString());
				audit.setTimeStamp(localTime);
				audit.setAuditTransactionId(TransactionId);

				exchange.setProperty(IConstant.BSCARRIER, baseRequest
						.getHeader().getBsCarrier());
				exchange.setProperty(IConstant.SOURCE_NAME, baseRequest
						.getHeader().getSourceName());
				exchange.setProperty(IConstant.APPLICATION_NAME, baseRequest
						.getHeader().getApplicationName());
				exchange.setProperty(IConstant.REGION, baseRequest.getHeader()
						.getRegion());
				exchange.setProperty(IConstant.DATE_FORMAT, baseRequest
						.getHeader().getTimestamp());
				exchange.setProperty(IConstant.ORGANIZATION, baseRequest
						.getHeader().getOrganization());

				exchange.setProperty(IConstant.GV_TRANSACTION_ID, baseRequest
						.getHeader().getTransactionId());
				exchange.setProperty(IConstant.GV_HOSTNAME,
						CommonUtil.getIpAddress());

				audit.setGvTransactionId(exchange.getProperty(
						IConstant.GV_TRANSACTION_ID).toString());
				audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME)
						.toString());

				audit.setPayload(exchange.getIn().getBody());
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
