package com.gv.midway.processor.customFieldsDevice;

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
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;

public class KoreCustomFieldsPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreCustomFieldsPreProcessor.class.getName());

    private Environment newEnv;

    public KoreCustomFieldsPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin::KoreCustomFieldsPreProcessor");

        final Message message = exchange.getIn();
        final Transaction transaction = exchange.getIn().getBody(Transaction.class);
        final CustomFieldsDeviceRequest changeDeviceServicePlansRequest = (CustomFieldsDeviceRequest) transaction.getDevicePayload();

        final String deviceId = changeDeviceServicePlansRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getId();

        final CustomFieldsDeviceRequestKore customFieldsDeviceRequestKore = new CustomFieldsDeviceRequestKore();
        customFieldsDeviceRequestKore.setDeviceNumber(deviceId);

        final CustomFieldsToUpdate[] customFieldsArr = changeDeviceServicePlansRequest.getDataArea().getCustomFieldsToUpdate();

        customFieldsDeviceRequestKore.updateCustomFields(customFieldsArr);

        message.setHeader(Exchange.CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
        message.setHeader(Exchange.HTTP_METHOD, "POST");
        message.setHeader("Authorization", newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
        message.setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo");
        message.setBody(customFieldsDeviceRequestKore);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End::KoreCustomFieldsPreProcessor");
    }
}