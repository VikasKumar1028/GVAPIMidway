package com.gv.midway.processor.checkstatus;

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
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;

public class KoreCheckStatusPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(KoreCheckStatusPreProcessor.class.getName());

    private Environment newEnv;

    public KoreCheckStatusPreProcessor() {
        // Empty Constructor
    }

    public KoreCheckStatusPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("*************Testing**************************************"
                + exchange.getIn().getBody());

        Message message = exchange.getIn();

        Transaction transaction = exchange.getIn().getBody(Transaction.class);

        String carrierStatus = transaction.getCarrierStatus();

        RequestType requestType = transaction.getRequestType();

        Object payload = transaction.getDevicePayload();

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD, payload);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
                transaction.getDeviceNumber());

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
                transaction.getMidwayTransactionId());

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE,
                requestType);

        exchange.setProperty(ITransaction.CARRIER_STATUS, carrierStatus);

        exchange.setProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC,
                transaction.getCarrierErrorDescription());

        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID,
                transaction.getNetSuiteId());
        
        // check if activation request with custom fields then set the property in exchange.
        if(RequestType.ACTIVATION.equals(transaction.getRequestType())&&RecordType.PRIMARY.equals(transaction.getRecordType())){
        	
        	exchange.setProperty("koreActivationWithCustomField", true);
        	
        	ActivateDeviceRequest activateDeviceRequestWithCustomFileds=(ActivateDeviceRequest)payload;
        	ActivateDeviceRequestDataArea activateDeviceRequestDataArea = activateDeviceRequestWithCustomFileds
                    .getDataArea();

            ActivateDevices activateDevices = activateDeviceRequestDataArea
                    .getDevices();
            
        	CustomFieldsDeviceRequest dbPayload = new CustomFieldsDeviceRequest();
            dbPayload.setHeader(activateDeviceRequestWithCustomFileds.getHeader());
            Integer netSuiteId = activateDevices.getNetSuiteId();
            MidWayDevices[] businessPayLoadDevicesArray = new MidWayDevices[1];
            MidWayDevices businessPayLoadActivateDevices = new MidWayDevices();
            MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[activateDevices
                    .getDeviceIds().length];

            for (int i = 0; i < activateDevices.getDeviceIds().length; i++) {
                ActivateDeviceId customFieldsDeviceId = activateDevices
                        .getDeviceIds()[i];

                MidWayDeviceId businessPayLoadActivateDeviceId = new MidWayDeviceId();

                businessPayLoadActivateDeviceId.setId(customFieldsDeviceId
                        .getId());
                businessPayLoadActivateDeviceId.setKind(customFieldsDeviceId
                        .getKind());

                businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;

            }
            businessPayLoadActivateDevices
                    .setDeviceIds(businessPayloadDeviceId);
            businessPayLoadActivateDevices.setNetSuiteId(netSuiteId);
            businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;

            // create custom field logic

            CustomFieldsDeviceRequestDataArea requestDataArea = new CustomFieldsDeviceRequestDataArea();

            CustomFieldsToUpdate[] customFieldsToUpdate = new CustomFieldsToUpdate[activateDevices
                    .getCustomFields().length];

            // If Kore all the custom fields in one transactiondao records
            // if AT&T indivisual records for custom file

            for (int i = 0; i < activateDevices.getCustomFields().length; i++) {
                CustomFieldsToUpdate newCustomField = new CustomFieldsToUpdate();

                newCustomField.setKey(activateDevices.getCustomFields()[i]
                        .getKey());
                newCustomField.setValue(activateDevices.getCustomFields()[i]
                        .getValue());
                customFieldsToUpdate[i] = newCustomField;
            }

            requestDataArea.setCustomFieldsToUpdate(customFieldsToUpdate);
            requestDataArea.setDevices(businessPayLoadDevicesArray);
            dbPayload.setDataArea(requestDataArea);
            
            exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD, dbPayload);
            if(transaction.getCarrierErrorDescription()!=null)
            {
            	
            	  exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERROR_DESCRIPTION, transaction.getCarrierErrorDescription());
                  exchange.setProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERRORPAYLOAD, transaction.getCallBackPayload());	
            }
        }

        /***
         * carrier status as Error . CallBack the Netsuite end point here and
         * write it in Kafka Queue.
         */

        if (carrierStatus.equals(IConstant.CARRIER_TRANSACTION_STATUS_ERROR)) {
            LOGGER.info("carrier status error is........." + carrierStatus);
            message.setHeader(IConstant.KORE_CHECK_STATUS, "error");

        }

        // carrier status as Success and request Type is Change CustomFileds or
        // Change Service Plans
        else if (carrierStatus
                .equals(IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS)) {

            message.setHeader(IConstant.KORE_CHECK_STATUS, "change");

        }

        /**
         * 
         * Check the status of Kore Device with tracking number for Activation
         * ,DeActivation , Suspend , Restore, ReActivation
         */
        else {
            LOGGER.info("carrier status not error is........." + carrierStatus);
            message.setHeader(IConstant.KORE_CHECK_STATUS, "forward");
            String carrierTransationID = transaction.getCarrierTransactionId();
            exchange.setProperty(IConstant.CARRIER_TRANSACTION_ID,
                    carrierTransationID);
            net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
            obj.put("trackingNumber", carrierTransationID);

            message.setHeader(Exchange.CONTENT_TYPE, "application/json");
            message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
            message.setHeader(Exchange.HTTP_METHOD, "POST");
            message.setHeader("Authorization",
                    newEnv.getProperty(IConstant.KORE_AUTHENTICATION));
            message.setHeader(Exchange.HTTP_PATH,
                    "/json/queryProvisioningRequestStatus");

            message.setBody(obj);

            exchange.setPattern(ExchangePattern.InOut);

        }

    }

}
