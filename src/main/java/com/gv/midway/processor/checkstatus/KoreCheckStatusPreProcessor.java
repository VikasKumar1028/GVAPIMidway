package com.gv.midway.processor.checkstatus;

import com.gv.midway.pojo.KeyValuePair;
import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.ITransaction;
import com.gv.midway.constant.RecordType;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequestDataArea;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusPreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(KoreCheckStatusPreProcessor.class.getName());

    private Environment newEnv;

    public KoreCheckStatusPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:KoreCheckStatusPreProcessor");
        LOGGER.debug("*************Testing**************************************" + exchange.getIn().getBody());

        final Message message = exchange.getIn();
        final Transaction transaction = exchange.getIn().getBody(Transaction.class);
        final String carrierStatus = transaction.getCarrierStatus();
        final RequestType requestType = transaction.getRequestType();
        final Object payload = transaction.getDevicePayload();

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD, payload);
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID, transaction.getMidwayTransactionId());
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE, requestType);
        exchange.setProperty(ITransaction.CARRIER_STATUS, carrierStatus);
        exchange.setProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC, transaction.getCarrierErrorDescription());
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, transaction.getNetSuiteId());

        // check if activation request with custom fields then set the property in exchange.
        if (RequestType.ACTIVATION.equals(transaction.getRequestType())
                && RecordType.PRIMARY.equals(transaction.getRecordType())) {

            exchange.setProperty("koreActivationWithCustomField", true);

            final ActivateDeviceRequest activateDeviceRequestWithCustomFields = (ActivateDeviceRequest) payload;
            final ActivateDeviceRequestDataArea activateDeviceRequestDataArea
                    = activateDeviceRequestWithCustomFields.getDataArea();
            final ActivateDevices activateDevices = activateDeviceRequestDataArea.getDevices();

            final CustomFieldsDeviceRequest dbPayload = new CustomFieldsDeviceRequest();
            dbPayload.setHeader(activateDeviceRequestWithCustomFields.getHeader());
            final Integer netSuiteId = activateDevices.getNetSuiteId();
            final MidWayDevices[] businessPayLoadDevicesArray = new MidWayDevices[1];
            final MidWayDevices businessPayLoadActivateDevices = new MidWayDevices();
            final MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[activateDevices.getDeviceIds().length];

            for (int i = 0; i < activateDevices.getDeviceIds().length; i++) {
                final ActivateDeviceId customFieldsDeviceId = activateDevices.getDeviceIds()[i];
                final MidWayDeviceId businessPayLoadActivateDeviceId
                        = new MidWayDeviceId(customFieldsDeviceId.getId(), customFieldsDeviceId.getKind());

                businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;
            }

            businessPayLoadActivateDevices.setDeviceIds(businessPayloadDeviceId);
            businessPayLoadActivateDevices.setNetSuiteId(netSuiteId);
            businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;

            // create custom field logic
            final CustomFieldsDeviceRequestDataArea requestDataArea = new CustomFieldsDeviceRequestDataArea();
            final KeyValuePair[] customFieldsToUpdate
                    = new KeyValuePair[activateDevices.getCustomFields().length];

            // If Kore all the custom fields in one transaction dao records
            // if AT&T individual records for custom fields
            for (int i = 0; i < activateDevices.getCustomFields().length; i++) {
                final KeyValuePair newCustomField = new KeyValuePair();

                newCustomField.setKey(activateDevices.getCustomFields()[i].getKey());
                newCustomField.setValue(activateDevices.getCustomFields()[i].getValue());
                customFieldsToUpdate[i] = newCustomField;
            }

            requestDataArea.setCustomFieldsToUpdate(customFieldsToUpdate);
            requestDataArea.setDevices(businessPayLoadDevicesArray);
            dbPayload.setDataArea(requestDataArea);

            exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_PAYLOAD, dbPayload);
            if (transaction.getCarrierErrorDescription() != null) {
                exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_ERROR_DESCRIPTION, transaction.getCarrierErrorDescription());
                exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_ERRORPAYLOAD, transaction.getCallBackPayload());
            }
        }

        switch (carrierStatus) {
            case IConstant.CARRIER_TRANSACTION_STATUS_ERROR:
                //carrier status as Error . CallBack the Netsuite end point here and write it in Kafka Queue.
                message.setHeader(IConstant.KORE_CHECK_STATUS, "error");
                break;
            case IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS:
                // carrier status as Success and request Type is Change KeyValuePair or Change Service Plans
                message.setHeader(IConstant.KORE_CHECK_STATUS, "change");
                break;
            default:
                //Check the status of Kore Device with tracking number for Activation ,DeActivation , Suspend , Restore, ReActivation
                LOGGER.debug("carrier status not error is........." + carrierStatus);
                message.setHeader(IConstant.KORE_CHECK_STATUS, "forward");
                final String carrierTransactionID = transaction.getCarrierTransactionId();
                exchange.setProperty(IConstant.CARRIER_TRANSACTION_ID, carrierTransactionID);
                final net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
                obj.put("trackingNumber", carrierTransactionID);

                MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/queryProvisioningRequestStatus", obj);
                exchange.setPattern(ExchangePattern.InOut);
                break;
        }
        LOGGER.debug("End:KoreCheckStatusPreProcessor");
    }
}