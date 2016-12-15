package com.gv.midway.processor.deactivateDevice;

import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
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
    private static final Logger LOGGER = Logger.getLogger(KoreDeactivateDevicePreProcessor.class.getName());

    private Environment newEnv;

    public KoreDeactivateDevicePreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:KoreDeactivateDevicePreProcessor");
        LOGGER.debug("*************Testing**************************************" + exchange.getIn().getBody());

        final Message message = exchange.getIn();
        final Transaction transaction = message.getBody(Transaction.class);

        final DeactivateDeviceRequest deactivateDeviceRequest = (DeactivateDeviceRequest) transaction.getDevicePayload();

        String deviceId = "";
        for (DeactivateDeviceId midWayDeviceId : deactivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

        final DeactivateDeviceRequestKore deactivationDeviceRequestKore = new DeactivateDeviceRequestKore();
        deactivationDeviceRequestKore.setDeviceNumber(deviceId);

        if (deactivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getFlagScrap() != null) {
            deactivationDeviceRequestKore.setFlagScrap(deactivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getFlagScrap());
        } else {
            deactivationDeviceRequestKore.setFlagScrap(false);
        }

        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/deactivateDevice", deactivationDeviceRequestKore);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:KoreDeactivateDevicePreProcessor");
    }
}