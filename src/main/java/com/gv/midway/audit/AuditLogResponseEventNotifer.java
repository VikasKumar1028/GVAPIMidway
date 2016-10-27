package com.gv.midway.audit;

import java.util.Date;
import java.util.EventObject;

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
import com.gv.midway.pojo.job.JobinitializedResponse;

//TODO-Jeff Spelling
public class AuditLogResponseEventNotifer extends EventNotifierSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogResponseEventNotifer.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void notify(EventObject event) throws Exception {
        if (event instanceof ExchangeCompletedEvent) {
            final ExchangeCompletedEvent create = (ExchangeCompletedEvent) event;
            final Exchange exchange = create.getExchange();

            final Object messageBody = exchange.getIn().getBody();

            //TODO-Jeff JobinitializedResponse is not a child of BaseResponse so the second condition check here is pointless
            if (messageBody instanceof BaseResponse && !(messageBody instanceof JobinitializedResponse)) {
                LOGGER.info("In Audit log Response4");
                if (!(messageBody instanceof TargetResponse)) {
                    final BaseResponse baseResponse = (BaseResponse) messageBody;

                    final String TransactionId = (String) exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID);

                    final String responseEndpoint = exchange.getFromEndpoint().toString();
                    final String responseEndpointSplit[] = responseEndpoint.split("//");

                    LOGGER.info("responseEndpointSplit::" + responseEndpointSplit[1].replaceAll("]", " "));

                    final String apiOperationName = "GV_" + responseEndpointSplit[1].replaceAll("]", "") + "_ProxyResponse";
                    LOGGER.info("apiOperationName" + apiOperationName);

                    final Audit audit = new Audit();

                    if (!("GV_jobResponse_ProxyResponse").equals(apiOperationName)) {
                        audit.setApiOperationName(apiOperationName);
                        audit.setFrom(baseResponse.getHeader().getSourceName());
                        audit.setTo(exchange.getFromEndpoint().toString());
                        audit.setTimeStamp(new Date());
                        audit.setAuditTransactionId(TransactionId);
                        audit.setGvTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
                        audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME).toString());
                        audit.setPayload(messageBody);
                    }

                    if (!IResponse.SUCCESS_CODE.equals(baseResponse.getResponse().getResponseCode())) {
                        audit.setErrorDetails(baseResponse.getResponse().getResponseDescription());
                        audit.setErrorProblem(baseResponse.getResponse().getResponseStatus());
                        audit.setErrorCode(baseResponse.getResponse().getResponseCode());
                    }

                    audit.setPayload(messageBody);

                    mongoTemplate.save(audit);
                }
            }
        }
    }

    @Override
    public boolean isEnabled(EventObject event) {
        // we only want the sent events
        return event instanceof ExchangeCompletedEvent;
    }

    @Override
    protected void doStart() throws Exception {
        // noop
    }

    @Override
    protected void doStop() throws Exception {
        // noop
    }
}