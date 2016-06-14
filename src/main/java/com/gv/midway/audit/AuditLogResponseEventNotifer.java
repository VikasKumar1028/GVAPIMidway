package com.gv.midway.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.TimeZone;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.BaseResponse;
import com.gv.midway.pojo.audit.Audit;
import com.gv.midway.pojo.callback.TargetResponse;

public class AuditLogResponseEventNotifer extends EventNotifierSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(AuditLogResponseEventNotifer.class); // Initializing

	@Autowired
	MongoTemplate mongoTemplate;

	public void notify(EventObject event) throws Exception {
		if (event instanceof ExchangeCompletedEvent) {
			ExchangeCompletedEvent create = (ExchangeCompletedEvent) event;
			Exchange exchange = create.getExchange();
			logger.info("In Audit log Response");

			if (exchange.getIn().getBody() instanceof BaseResponse) {
				logger.info("In Audit log Response4");
				if (!(exchange.getIn().getBody() instanceof TargetResponse)) {
					BaseResponse baseResponse = (BaseResponse) exchange.getIn()
							.getBody();

					String TransactionId = (String) exchange
							.getProperty(IConstant.AUDIT_TRANSACTION_ID);

					Date localTime = new Date();
					DateFormat converter = new SimpleDateFormat(
							"dd/MM/yyyy:HH:mm:ss");
					converter.setTimeZone(TimeZone.getTimeZone("GMT"));

					String responseEndpint = exchange.getFromEndpoint()
							.toString();
					String responseEndpintSpilt[] = responseEndpint.split("//");

					logger.info("responseEndpintSpilt::"
							+ responseEndpintSpilt[1].replaceAll("]", " "));

					String apiOperationName = "GV_"
							+ responseEndpintSpilt[1].replaceAll("]", "")
							+ "_ProxyResponse";
					logger.info("apiOperationName" + apiOperationName);

					Audit audit = new Audit();
                    
					if(!apiOperationName.equals("GV_jobResponse_ProxyResponse"))
					{
					audit.setApiOperationName(apiOperationName);
					audit.setFrom(baseResponse.getHeader().getSourceName());
					audit.setTo(exchange.getFromEndpoint().toString());
					audit.setTimeStamp(localTime);
					audit.setAuditTransactionId(TransactionId);
					audit.setGvTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
					audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME).toString());
					audit.setPayload(exchange.getIn().getBody());
					}
					if (IResponse.SUCCESS_CODE != baseResponse.getResponse()
							.getResponseCode()) {
						audit.setErrorDetails(baseResponse.getResponse().getResponseDescription());
						audit.setErrorProblem(baseResponse.getResponse().getResponseStatus());
						audit.setErrorCode(baseResponse.getResponse().getResponseCode());

					}

					audit.setPayload(exchange.getIn().getBody());

					mongoTemplate.save(audit);

				}
			}
		}

	}

	public boolean isEnabled(EventObject event) {
		// we only want the sent events
		return event instanceof ExchangeCompletedEvent;
	}

	protected void doStart() throws Exception {
		// noop
	}

	protected void doStop() throws Exception {
		// noop
	}
}
