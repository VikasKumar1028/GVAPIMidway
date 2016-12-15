package com.gv.midway.processor.customFieldsDevice;

import com.gv.midway.pojo.KeyValuePair;
import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreCustomFieldsPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreCustomFieldsPreProcessor.class.getName());

    private Environment newEnv;

    public KoreCustomFieldsPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin::KoreCustomFieldsPreProcessor");

        final Message message = exchange.getIn();
        final Transaction transaction = exchange.getIn().getBody(Transaction.class);
        final CustomFieldsDeviceRequest changeDeviceServicePlansRequest = (CustomFieldsDeviceRequest) transaction.getDevicePayload();

        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : changeDeviceServicePlansRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

        final CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
        customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

        final KeyValuePair[] customFieldsArr = changeDeviceServicePlansRequest.getDataArea().getCustomFieldsToUpdate();

        customFieldsDeviceRequestKore.updateCustomFields(customFieldsArr);

        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/modifyDeviceCustomInfo", customFieldsDeviceRequestKore);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End::KoreCustomFieldsPreProcessor");
    }
}