package com.gv.midway.processor.activateDevice;

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

public class KoreActivationWithCustomFieldPreProcessor implements Processor {

    /**
     * Call back the Netsuite endPoint
     */
    private static final Logger LOGGER = Logger.getLogger(KoreActivationWithCustomFieldPreProcessor.class.getName());

    private Environment newEnv;

    public KoreActivationWithCustomFieldPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:KoreActivationWithCustomFieldPreProcessor.");

        final CustomFieldsDeviceRequest proxyPayload = (CustomFieldsDeviceRequest) exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_PAYLOAD);

        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : proxyPayload.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

        final CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
        customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

        final KeyValuePair[] customFieldsArr = proxyPayload.getDataArea().getCustomFieldsToUpdate();

        customFieldsDeviceRequestKore.updateCustomFields(customFieldsArr);

        final Message message = exchange.getIn();
        MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/modifyDeviceCustomInfo", customFieldsDeviceRequestKore);

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:KoreActivationWithCustomFieldPreProcessor");
    }
}