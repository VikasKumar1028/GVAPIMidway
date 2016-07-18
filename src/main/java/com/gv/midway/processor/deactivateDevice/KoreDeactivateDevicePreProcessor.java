package com.gv.midway.processor.deactivateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deactivateDevice.kore.request.DeactivateDeviceRequestKore;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreDeactivateDevicePreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreDeactivateDevicePreProcessor.class
            .getName());

    Environment newEnv;

    public KoreDeactivateDevicePreProcessor() {
        // Empty Constructor
    }

    public KoreDeactivateDevicePreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:KoreDeactivateDevicePreProcessor");

        LOGGER.info("*************Testing**************************************"
                + exchange.getIn().getBody());

        Message message = exchange.getIn();
        Transaction transaction = exchange.getIn().getBody(Transaction.class);

        DeactivateDeviceRequest deactivateDeviceRequest = (DeactivateDeviceRequest) transaction
                .getDevicePayload();
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
                transaction.getDeviceNumber());

        String deviceId = deactivateDeviceRequest.getDataArea().getDevices()[0]
                .getDeviceIds()[0].getId();

        DeactivateDeviceRequestKore deactivationDeviceRequestKore = new DeactivateDeviceRequestKore();
        deactivationDeviceRequestKore.setDeviceNumber(deviceId);

        if (deactivateDeviceRequest.getDataArea().getDevices()[0]
                .getDeviceIds()[0].getFlagScrap() != null) {
            deactivationDeviceRequestKore.setFlagScrap(deactivateDeviceRequest
                    .getDataArea().getDevices()[0].getDeviceIds()[0]
                    .getFlagScrap());
        }

        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization",
                newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
        message.setHeader(Exchange.HTTP_PATH, "/json/deactivateDevice");
        message.setBody(deactivationDeviceRequestKore);

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End:KoreDeactivateDevicePreProcessor");
    }
}
