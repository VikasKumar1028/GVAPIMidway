package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gv.midway.constant.RecordType;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.KeyValuePair;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ITransactionalDao;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.service.ITransactionalService;
import com.gv.midway.utility.CommonUtil;

@Service
public class TransactionalServiceImpl implements ITransactionalService {
    private static final Logger LOGGER = Logger.getLogger(TransactionalServiceImpl.class.getName());

    @Autowired
    private ITransactionalDao transactionalDao;

    @SuppressWarnings("unused")  //Needed for framework
    public TransactionalServiceImpl() {
    }

    //Added this constructor so that this class could be tested.
    public TransactionalServiceImpl(ITransactionalDao transactionalDao) {
        this.transactionalDao = transactionalDao;
    }

    @Override
    public void populateActivateDBPayload(Exchange exchange) {
        transactionalDao.populateActivateDBPayload(exchange);
    }

    @Override
    public void populateDeactivateDBPayload(Exchange exchange) {
        transactionalDao.populateDeactivateDBPayload(exchange);
    }

    @Override
    public void populateSuspendDBPayload(Exchange exchange) {
        transactionalDao.populateSuspendDBPayload(exchange);
    }

    @Override
    public void populateVerizonTransactionalResponse(Exchange exchange) {
        transactionalDao.populateVerizonTransactionalResponse(exchange);
    }

    @Override
    public void populateVerizonTransactionalErrorResponse(Exchange exchange) {
        transactionalDao.populateVerizonTransactionalErrorResponse(exchange);
    }

    @Override
    public void populateKoreTransactionalErrorResponse(Exchange exchange) {
        transactionalDao.populateKoreTransactionalErrorResponse(exchange);
    }

    @Override
    public void populateKoreTransactionalResponse(Exchange exchange) {
        transactionalDao.populateKoreTransactionalResponse(exchange);
    }

    @Override
    public void populatePendingKoreCheckStatus(Exchange exchange) {
        transactionalDao.populatePendingKoreCheckStatus(exchange);
    }

    @Override
    public void populateConnectionErrorResponse(Exchange exchange, String errorType) {
        transactionalDao.populateConnectionErrorResponse(exchange, errorType);
    }

    @Override
    public void populateCallbackDBPayload(Exchange exchange) {
        transactionalDao.populateCallbackDBPayload(exchange);
    }

    @Override
    public void findMidwayTransactionId(Exchange exchange) {
        transactionalDao.findMidwayTransactionId(exchange);
    }

    @Override
    public void populateReactivateDBPayload(Exchange exchange) {
        transactionalDao.populateReactivateDBPayload(exchange);
    }

    @Override
    public void populateRestoreDBPayload(Exchange exchange) {
        transactionalDao.populateRestoreDBPayload(exchange);
    }

    @Override
    public void populateCustomeFieldsDBPayload(Exchange exchange) {
        transactionalDao.populateCustomeFieldsDBPayload(exchange);
    }

    @Override
    public void populateChangeDeviceServicePlansDBPayload(Exchange exchange) {
        transactionalDao.populateChangeDeviceServicePlansDBPayload(exchange);
    }

    @Override
    public void populateKoreCheckStatusResponse(Exchange exchange) {
        transactionalDao.populateKoreCheckStatusResponse(exchange);
    }

    @Override
    public void populateKoreCheckStatusConnectionResponse(Exchange exchange) {
        transactionalDao.populateKoreCheckStatusConnectionResponse(exchange);
    }

    @Override
    public void populateKoreCheckStatusErrorResponse(Exchange exchange) {
        transactionalDao.populateKoreCheckStatusErrorResponse(exchange);
    }

    @Override
    public void updateNetSuiteCallBackResponse(Exchange exchange) {
        transactionalDao.updateNetSuiteCallBackResponse(exchange);
    }

    @Override
    public void updateNetSuiteCallBackError(Exchange exchange) {
        transactionalDao.updateNetSuiteCallBackError(exchange);
    }

    @Override
    public void updateNetSuiteCallBackRequest(Exchange exchange) {
        transactionalDao.updateNetSuiteCallBackRequest(exchange);
    }

    @Override
    public void populateKoreCustomChangeResponse(Exchange exchange) {
        transactionalDao.populateKoreCustomChangeResponse(exchange);
    }

    @Override
    public void populateATTJasperTransactionalResponse(Exchange exchange) {
        transactionalDao.populateATTJasperTransactionalResponse(exchange);
    }

    @Override
    public void populateATTJasperTransactionalErrorResponse(Exchange exchange) {
        transactionalDao.populateATTJasperTransactionalErrorResponse(exchange);
    }

    @Override
    public void populateATTCustomeFieldsDBPayload(Exchange exchange) {
        transactionalDao.populateATTCustomeFieldsDBPayload(exchange);
    }

    @Override
    public void updateKoreActivationCustomeFieldsDBPayloadError(Exchange exchange) {
        transactionalDao.updateKoreActivationCustomeFieldsDBPayloadError(exchange);
    }


    public void updateKoreActivationCustomeFieldsDBPayload(Exchange exchange) {
        transactionalDao.updateKoreActivationCustomeFieldsDBPayload(exchange);
    }

    @Override
    public void setActivateCustomFieldListInExchange(Exchange exchange) {
        CommonUtil.setListInWireTap(exchange,
                (List<Transaction>) exchange.getProperty(IConstant.ATT_ACTIVATION_WITH_CUSTOMFIELDS_LIST));
    }


    @Override
    public void setActivateServicePlanListInExchange(Exchange exchange) {
        CommonUtil.setListInWireTap(exchange,
                (List<Transaction>) exchange.getProperty(IConstant.ATT_ACTIVATION_WITH_SERVICEPLAN_LIST));
    }

    @Override
    public void fetchAttPendingCallback(Exchange exchange) {
        transactionalDao.fetchAttPendingCallback(exchange);
    }

    @Override
    public void updateCallBackStatusOfSecondaryField(Exchange exchange) {
        transactionalDao.updateCallBackStatusOfSecondaryField(exchange);
    }

    @Override
    public void updateAttNetSuiteCallBackError(Exchange exchange) {
        transactionalDao.updateAttNetSuiteCallBackError(exchange);
    }

    @Override
    public void updateAttNetSuiteCallBackRequest(Exchange exchange) {
        transactionalDao.updateAttNetSuiteCallBackRequest(exchange);
    }

    @Override
    public void updateAttNetSuiteCallBackResponse(Exchange exchange) {
        transactionalDao.updateAttNetSuiteCallBackResponse(exchange);
    }


    @Override
    public void populateActivateDBPayloadMock(Exchange exchange) {

        ActivateDeviceRequest dbPayload = exchange.getIn().getBody(ActivateDeviceRequest.class);

        ArrayList<Transaction> list = new ArrayList<Transaction>();

        String carrierName = exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString();

        Transaction transaction = new Transaction();

        transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

        transaction.setDeviceNumber("[{\"id\":\"89014103277405945812\",\"kind\":\"SIM\"}]");
        transaction.setDevicePayload(dbPayload);
        transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
        transaction.setCarrierName(carrierName);
        transaction.setTimeStampReceived(new Date());
        transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
        transaction.setRequestType(RequestType.ACTIVATION);
        transaction.setCallBackReceived(false);

        ActivateDevices activateDevice = new ActivateDevices();

        boolean hasCustomFields = exchange.getProperty("hasCustomFields") != null ? Boolean.parseBoolean(exchange.getProperty("hasCustomFields").toString()) : false;

        boolean hasServicePlan = exchange.getProperty("hasServicePlan") != null ? Boolean.parseBoolean(exchange.getProperty("hasServicePlan").toString()) : false;

        if (hasCustomFields) {
            KeyValuePair fields = new KeyValuePair();
            fields.setKey("cust1");
            fields.setValue("value");
            activateDevice.setCustomFields(new KeyValuePair[] {fields});
        }
        if (hasServicePlan) {
            activateDevice.setServicePlan("PLAN001044");
        }

        // if activate device request comes with custom fields then set the recordType Field as Primary
        if (activateDevice.getCustomFields()!=null && activateDevice.getCustomFields().length>0 ) {
            transaction.setRecordType(RecordType.PRIMARY);
        }

        // if activate device request comes with servicePlan for ATTJapser then set the recordType Field as Primary
        if (IConstant.BSCARRIER_SERVICE_ATTJASPER.equals(carrierName)
                && (activateDevice.getServicePlan()!=null
                && activateDevice.getServicePlan().length()>0 ) ) {
            transaction.setRecordType(RecordType.PRIMARY);
        }
        transaction.setNetSuiteId(212974);
        list.add(transaction);


        CommonUtil.setListInWireTap(exchange, list);
    }
}