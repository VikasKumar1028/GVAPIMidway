package com.gv.midway.processor.activateDevice;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;

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
        LOGGER.info("Begin:KoreActivationWithCustomFieldPreProcessor.");

        final CustomFieldsDeviceRequest proxyPayload = (CustomFieldsDeviceRequest) exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD);

        final String deviceId = proxyPayload.getDataArea().getDevices()[0].getDeviceIds()[0].getId();

        final CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
        customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

        final CustomFieldsToUpdate[] customFieldsArr = proxyPayload.getDataArea().getCustomFieldsToUpdate();

        customFieldsDeviceRequestKore.updateCustomFields(customFieldsArr);

        final Message message = exchange.getIn();
        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
        message.setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo");
        message.setBody(customFieldsDeviceRequestKore);

        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End:KoreActivationWithCustomFieldPreProcessor");
    }
}