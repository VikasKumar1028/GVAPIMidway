package com.gv.midway.processor.changeDeviceServicePlans;

import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.changeDeviceServicePlans.kore.request.ChangeDeviceServicePlansRequestKore;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreChangeDeviceServicePlansPreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(KoreChangeDeviceServicePlansPreProcessor.class.getName());
    private Environment newEnv;

    public KoreChangeDeviceServicePlansPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin::KoreChangeDeviceServicePlansPreProcessor");

        final Message message = exchange.getIn();
        final Transaction transaction = message.getBody(Transaction.class);
        final ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest =
                (ChangeDeviceServicePlansRequest) transaction.getDevicePayload();

        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : changeDeviceServicePlansRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }
        final String planCode = changeDeviceServicePlansRequest.getDataArea().getServicePlan();

        final ChangeDeviceServicePlansRequestKore changeDeviceServicePlansRequestKore = new ChangeDeviceServicePlansRequestKore();
        changeDeviceServicePlansRequestKore.setDeviceNumber(deviceId);
        changeDeviceServicePlansRequestKore.setPlanCode(planCode);

        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/modifyDevicePlanForNextPeriod", changeDeviceServicePlansRequestKore);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End::KoreChangeDeviceServicePlansPreProcessor");
    }
}