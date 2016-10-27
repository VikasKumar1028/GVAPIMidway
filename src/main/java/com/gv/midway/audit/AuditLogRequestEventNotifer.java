package com.gv.midway.audit;

import java.util.Date;
import java.util.EventObject;

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

//TODO-Jeff Spelling
public class AuditLogRequestEventNotifer extends EventNotifierSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogRequestEventNotifer.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void notify(EventObject event) throws Exception {

        if (event instanceof ExchangeCreatedEvent) {
            final ExchangeCreatedEvent create = (ExchangeCreatedEvent) event;
            final Exchange exchange = create.getExchange();

            if (exchange.getIn().getBody() instanceof BaseRequest && exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID) == null) {
                LOGGER.info("In Audit log Request*************************************");
                final BaseRequest baseRequest = (BaseRequest) exchange.getIn().getBody();

                final String TransactionId = CommonUtil.getMidwayTransactionID();
                exchange.setProperty(IConstant.AUDIT_TRANSACTION_ID, TransactionId);

                final String requestEndpoint = exchange.getFromEndpoint().toString();
                final String requestEndpointSplit[] = requestEndpoint.split("//");

                LOGGER.info("requestEndpointSplit::" + requestEndpointSplit[1].replaceAll("]", " "));

                final String apiOperationName = "GV_" + requestEndpointSplit[1].replaceAll("]", "") + "_ProxyRequest";
                LOGGER.info("apiOperationName" + apiOperationName);

                final Audit audit = new Audit();
                audit.setApiOperationName(apiOperationName);
                audit.setFrom(baseRequest.getHeader().getSourceName());
                audit.setTo(exchange.getFromEndpoint().toString());
                audit.setTimeStamp(new Date());
                audit.setAuditTransactionId(TransactionId);

                exchange.setProperty(IConstant.HEADER, baseRequest.getHeader());

                if (baseRequest.getHeader() != null) {

                    exchange.setProperty(IConstant.BSCARRIER, baseRequest.getHeader().getBsCarrier());
                    exchange.setProperty(IConstant.SOURCE_NAME, baseRequest.getHeader().getSourceName());
                    exchange.setProperty(IConstant.APPLICATION_NAME, baseRequest.getHeader().getApplicationName());
                    exchange.setProperty(IConstant.REGION, baseRequest.getHeader().getRegion());
                    exchange.setProperty(IConstant.DATE_FORMAT, baseRequest.getHeader().getTimestamp());
                    exchange.setProperty(IConstant.ORGANIZATION, baseRequest.getHeader().getOrganization());
                    exchange.setProperty(IConstant.GV_TRANSACTION_ID, baseRequest.getHeader().getTransactionId());

                    audit.setGvTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
                }
                exchange.setProperty(IConstant.GV_HOSTNAME, CommonUtil.getIpAddress());

                audit.setHostName(exchange.getProperty(IConstant.GV_HOSTNAME).toString());

                audit.setPayload(exchange.getIn().getBody());
                mongoTemplate.save(audit);
            }
        }
    }

    @Override
    public boolean isEnabled(EventObject event) {
        return event instanceof ExchangeCreatedEvent;
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