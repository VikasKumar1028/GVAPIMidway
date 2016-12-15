package com.gv.midway.processor.suspendDevice;

import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.suspendDevice.kore.request.SuspendDeviceRequestKore;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreSuspendDevicePreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(KoreSuspendDevicePreProcessor.class.getName());

    private Environment newEnv;

    public KoreSuspendDevicePreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("*************Testing**************************************" + exchange.getIn().getBody());
        LOGGER.debug("Begin:KoreSuspendDevicePreProcessor");

        final Message message = exchange.getIn();
        final Transaction transaction = exchange.getIn().getBody(Transaction.class);
        final SuspendDeviceRequest suspendDeviceRequest = (SuspendDeviceRequest) transaction.getDevicePayload();
        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : suspendDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

        final SuspendDeviceRequestKore suspendDeviceRequestKore = new SuspendDeviceRequestKore();
        suspendDeviceRequestKore.setDeviceNumber(deviceId);

        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/suspendDevice", suspendDeviceRequestKore);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:KoreSuspendDevicePreProcessor");
    }
}