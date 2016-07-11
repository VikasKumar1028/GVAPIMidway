package com.gv.midway.dao.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfOperationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.esotericsoftware.kryo.Kryo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.constant.ITransaction;
import com.gv.midway.constant.NetSuiteRequestType;
import com.gv.midway.constant.RequestType;
import com.gv.midway.dao.ITransactionalDao;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.callback.Netsuite.KeyValues;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequestDataArea;
import com.gv.midway.pojo.checkstatus.kore.KoreCheckStatusResponse;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.kore.KoreErrorResponse;
import com.gv.midway.pojo.kore.KoreProvisoningResponse;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequestDataArea;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequestDataArea;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequestDataArea;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.VerizonErrorResponse;
import com.gv.midway.pojo.verizon.VerizonProvisoningResponse;
import com.gv.midway.utility.CommonUtil;

@Service
public class TransactionalDaoImpl implements ITransactionalDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(TransactionalDaoImpl.class);

	public void populateActivateDBPayload(Exchange exchange) {
		log.info("Inside populateActivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		ActivateDeviceRequest req = (ActivateDeviceRequest) exchange.getIn().getBody();

		ActivateDeviceRequestDataArea activateDeviceRequestDataArea = (ActivateDeviceRequestDataArea) req.getDataArea();

		//ActivateDevices[] activateDevices = activateDeviceRequestDataArea.getDevices();
		
		ActivateDevices activateDevices = activateDeviceRequestDataArea.getDevices();
		
		ActivateDevices[] activateDevicesArr=new ActivateDevices[1];
		
		if(activateDevices!=null){
			activateDevicesArr[0]=activateDevices;
		}

		Kryo kryo = new Kryo();
		for (ActivateDevices activateDevice : activateDevicesArr) {

			ActivateDeviceRequest dbPayload = new ActivateDeviceRequest();
			dbPayload.setHeader(req.getHeader());
            Integer netSuiteId=activateDevice.getNetSuiteId();
			//ActivateDevices[] businessPayLoadDevicesArray = new ActivateDevices[1];
			ActivateDevices businessPayLoadActivateDevices = new ActivateDevices();
			ActivateDeviceId[] businessPayloadDeviceId = new ActivateDeviceId[activateDevice.getDeviceIds().length];

			for (int i = 0; i < activateDevice.getDeviceIds().length; i++) {
				ActivateDeviceId activateDeviceId = activateDevice.getDeviceIds()[i];

				ActivateDeviceId businessPayLoadActivateDeviceId = new ActivateDeviceId();

				/*
				 * businessPayLoadActivateDeviceId.seteAPCode(activateDeviceId
				 * .geteAPCode());
				 */
				businessPayLoadActivateDeviceId.setId(activateDeviceId.getId());
				businessPayLoadActivateDeviceId.setKind(activateDeviceId.getKind());

				businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;

			}
			businessPayLoadActivateDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayLoadActivateDevices.setNetSuiteId(activateDevice.getNetSuiteId());
			businessPayLoadActivateDevices.setMacAddress(activateDevice.getMacAddress());
			businessPayLoadActivateDevices.setSerialNumber(activateDevice.getSerialNumber());
			businessPayLoadActivateDevices.setCustomFields(activateDevice.getCustomFields());
			businessPayLoadActivateDevices.setTitle(activateDevice.getTitle());
			businessPayLoadActivateDevices.setMiddleName(activateDevice.getMiddleName());
			businessPayLoadActivateDevices.setAddress(activateDevice.getAddress());
			businessPayLoadActivateDevices.setServicePlan(activateDevice.getServicePlan());
			//businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;

			ActivateDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			//copyDataArea.setDevices(businessPayLoadDevicesArray);
			copyDataArea.setDevices(businessPayLoadActivateDevices);
			dbPayload.setDataArea(copyDataArea);

			// copy.getDataArea().setDevices();

			try {

				Transaction transaction = new Transaction();

				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				
				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(businessPayloadDeviceId,  (ActivateDeviceId a,ActivateDeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				// TODO if number of devices are more
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.ACTIVATION);
				transaction.setCallBackReceived(false);
                transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Inside populateActivateDBPayload");
			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		// activateDeviceRequestDataArea.setDevices(activateDevices);

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}
	}

	// santosh:new method
	public void populateDeactivateDBPayload(Exchange exchange) {

		log.info("Inside populateDeactivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		DeactivateDeviceRequest req = (DeactivateDeviceRequest) exchange.getIn().getBody();
		DeactivateDeviceRequestDataArea deActivateDeviceRequestDataArea = (DeactivateDeviceRequestDataArea) req.getDataArea();
		DeactivateDevices[] deactivateDevices = deActivateDeviceRequestDataArea.getDevices();

		Kryo kryo = new Kryo();

		for (DeactivateDevices deactivateDevice : deactivateDevices) {
			DeactivateDeviceRequest dbPayload = new DeactivateDeviceRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=deactivateDevice.getNetSuiteId();
			DeactivateDevices[] businessPayloadDeviceArray = new DeactivateDevices[1];
			DeactivateDevices businessPayLoadDeactivateDevices = new DeactivateDevices();
			DeactivateDeviceId[] businessPayloadDeviceId = new DeactivateDeviceId[deactivateDevice.getDeviceIds().length];
			DeviceId[] transactionPayloadDeviceId = new DeviceId[deactivateDevice.getDeviceIds().length];
			

			for (int i = 0; i < deactivateDevice.getDeviceIds().length; i++) {
				
				DeactivateDeviceId deactivateDeviceId = deactivateDevice.getDeviceIds()[i];
				DeactivateDeviceId businesspayLoadDeactivateDeviceId = new DeactivateDeviceId();
				
				//Removing the falseScrap from Deactivate Device (only kind and id inserted in deviceNumber)
				DeviceId transactionDeviceId=new DeviceId();
				transactionDeviceId.setId(deactivateDeviceId.getId());
				transactionDeviceId.setKind(deactivateDeviceId.getKind());
				transactionPayloadDeviceId[i]=transactionDeviceId;
				
				businesspayLoadDeactivateDeviceId.setFlagScrap(deactivateDeviceId.getFlagScrap());
				businesspayLoadDeactivateDeviceId.setId(deactivateDeviceId.getId());
				businesspayLoadDeactivateDeviceId.setKind(deactivateDeviceId.getKind());
				businessPayloadDeviceId[i] = businesspayLoadDeactivateDeviceId;
			}

			businessPayLoadDeactivateDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayloadDeviceArray[0] = businessPayLoadDeactivateDevices;
			// payLoadDevices =(ActivateDevices[])kryo.copy(activateDevices);

			DeactivateDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayloadDeviceArray);
			dbPayload.setDataArea(copyDataArea);

			try {

				Transaction transaction = new Transaction();
				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				
				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(transactionPayloadDeviceId,  (DeviceId a,DeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(transactionPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.DEACTIVATION);
				transaction.setCallBackReceived(false);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Inside populateDeactivateDBPayload");

			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}

	public void populateSuspendDBPayload(Exchange exchange) {

		log.info("Inside populateSuspendDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		SuspendDeviceRequest req = (SuspendDeviceRequest) exchange.getIn().getBody();
		SuspendDeviceRequestDataArea suspendDeviceRequestDataArea = (SuspendDeviceRequestDataArea) req.getDataArea();
		MidWayDevices[] suspendDevices = suspendDeviceRequestDataArea.getDevices();

		Kryo kryo = new Kryo();

		for (MidWayDevices suspendDevice : suspendDevices) {
			SuspendDeviceRequest dbPayload = new SuspendDeviceRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=suspendDevice.getNetSuiteId();
			MidWayDevices[] businessPayloadDeviceArray = new MidWayDevices[1];
			MidWayDevices businessPayLoadSuspendDevices = new MidWayDevices();
			MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[suspendDevice.getDeviceIds().length];

			for (int i = 0; i < suspendDevice.getDeviceIds().length; i++) {
				MidWayDeviceId suspendDeviceId = suspendDevice.getDeviceIds()[i];
				MidWayDeviceId businesspayLoadSuspendDeviceId = new MidWayDeviceId();
				businesspayLoadSuspendDeviceId.setId(suspendDeviceId.getId());
				businesspayLoadSuspendDeviceId.setKind(suspendDeviceId.getKind());
				businessPayloadDeviceId[i] = businesspayLoadSuspendDeviceId;
			}

			businessPayLoadSuspendDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayloadDeviceArray[0] = businessPayLoadSuspendDevices;
			SuspendDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayloadDeviceArray);
			dbPayload.setDataArea(copyDataArea);

			try {

				Transaction transaction = new Transaction();
				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(businessPayloadDeviceId,  (MidWayDeviceId a,MidWayDeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.SUSPEND);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Inside populateSuspendDBPayload");

			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}

	public void populateVerizonTransactionalResponse(Exchange exchange) {

		Map map = exchange.getIn().getBody(Map.class);
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

		/*
		 * String carrierTransationID;//Call Back Thread String
		 * carrierStatus;//Call Back Thread String
		 * LastTimeStampUpdated;//CallBack Thread String
		 * carrierErrorDescription;//CallBack Thread String
		 * callBackPayload;//CallBack Thread Boolean
		 * callBackDelivered;//CallBack Thread Boolean
		 * callBackReceived;//CallBack Thread String
		 * callBackFailureToNetSuitReason;//CallBack Thread
		 */

		Update update = new Update();
		ObjectMapper mapper = new ObjectMapper();

		try {
			String jsonString = jsonString = mapper.writeValueAsString(map);
			if (map.containsKey("errorMessage")) {

				VerizonErrorResponse responsePayload = mapper.readValue(jsonString, VerizonErrorResponse.class);

				update.set(ITransaction.CALL_BACK_PAYLOAD, responsePayload);
				update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, responsePayload.getErrorMessage());
				update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
				update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
				

			} else {

				VerizonProvisoningResponse responsePayload = mapper.readValue(jsonString, VerizonProvisoningResponse.class);

				update.set(ITransaction.CALL_BACK_PAYLOAD, responsePayload);

				update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_PENDING);
				update.set(ITransaction.CARRIER_TRANSACTION_ID, responsePayload.getRequestId());
				

			}
			update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
			mongoTemplate.updateMulti(searchQuery, update, Transaction.class);
		} catch (Exception ex) {
			log.error("Error in populateVerizonTransactionalResponse" + ex);
		}

	}

	public void populateKoreTransactionalErrorResponse(Exchange exchange) {

		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

		/*
		 * String carrierTransationID;//Call Back Thread String
		 * carrierStatus;//Call Back Thread String
		 * LastTimeStampUpdated;//CallBack Thread String
		 * carrierErrorDescription;//CallBack Thread String
		 * callBackPayload;//CallBack Thread Boolean
		 * callBackDelivered;//CallBack Thread Boolean
		 * callBackReceived;//CallBack Thread String
		 * callBackFailureToNetSuitReason;//CallBack Thread
		 */
		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		String errorResponseBody = exception.getResponseBody();
		ObjectMapper mapper = new ObjectMapper();
		KoreErrorResponse errorResponsePayload = null;
		try {
			errorResponsePayload = mapper.readValue(errorResponseBody, KoreErrorResponse.class);
		} catch (Exception e) {
		}

		Update update = new Update();

		update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, errorResponsePayload.getErrorMessage());

		update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
		update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
		update.set(ITransaction.CALL_BACK_PAYLOAD, errorResponsePayload);
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);

		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, errorResponseBody);
		exchange.setProperty(IConstant.RESPONSE_STATUS, errorResponseBody);
		exchange.setProperty(IConstant.RESPONSE_CODE, errorResponseBody);

	}

	public void populateVerizonTransactionalErrorResponse(Exchange exchange) {

		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		String errorResponseBody = exception.getResponseBody();

		ObjectMapper mapper = new ObjectMapper();
		try {
			VerizonErrorResponse responsePayload = mapper.readValue(errorResponseBody, VerizonErrorResponse.class);

			Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

			Update update = new Update();
			//update.set(ITransaction.CALL_BACK_PAYLOAD, responsePayload);
			update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, responsePayload.getErrorMessage());
			update.set(ITransaction.CALL_BACK_PAYLOAD, responsePayload);
			update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
			update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());

			mongoTemplate.updateMulti(searchQuery, update, Transaction.class);
		} catch (Exception ex) {
			log.error("Error in populateVerizonTransactionalErrorResponse");
		}

	}

	public void populateKoreTransactionalResponse(Exchange exchange) {
		// TODO Auto-generated method stub

		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

		Update update = new Update();
		
		Object object=exchange.getIn().getBody();
		
		if(object instanceof KoreProvisoningResponse) 
		{
			KoreProvisoningResponse koreProvisoningResponse = (KoreProvisoningResponse) object;
			update.set(ITransaction.CARRIER_TRANSACTION_ID, koreProvisoningResponse.getD().getTrackingNumber());
			update.set(ITransaction.CALL_BACK_PAYLOAD, koreProvisoningResponse);
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_PENDING);
		}
		
		// For change custom field and change service plan
		else
		{
			log.info("the object type for ........"+object.toString());
			update.set(ITransaction.CALL_BACK_PAYLOAD, object);
			update.set(ITransaction.CALL_BACK_RECEIVED, true);
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS);
			
		}
		
		/*KoreProvisoningResponse koreProvisoningResponse = (KoreProvisoningResponse) exchange.getIn().getBody();*/
	
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		

		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);

	}

	public void populateConnectionErrorResponse(Exchange exchange, String errorType) {

		// Applicable for provisioning request where we have Transaction Table
		// Not Applicable for device Information
		log.info("populate connection error....." + exchange.getFromEndpoint().toString());
		log.info("device number is.........." + exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER));
		if (CommonUtil.isProvisioningMethod(exchange.getFromEndpoint().toString())) {
			Query searchQuery = null;
			if ("KORE".equals(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {
				searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

				log.info("device number in Kore is.........." + exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER));
				Update update = new Update();
				update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				update.set(ITransaction.CALL_BACK_PAYLOAD, IConstant.MIDWAY_CONNECTION_ERROR);
				update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, IConstant.MIDWAY_CONNECTION_ERROR);

				update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
				update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
				mongoTemplate.updateFirst(searchQuery, update, Transaction.class);

			} else if ("VERIZON".equals(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME))) {

				searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

				Update update = new Update();

				update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
				update.set(ITransaction.CALL_BACK_PAYLOAD, IConstant.MIDWAY_CONNECTION_ERROR);
				update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, IConstant.MIDWAY_CONNECTION_ERROR);
				update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);

				update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
				mongoTemplate.updateMulti(searchQuery, update, Transaction.class);

			}

		}
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_DESCRIPTION_CONNECTION_MIDWAYDB);
		exchange.setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_MESSAGE);
		exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.CONNECTION_ERROR_CODE);

	}

	public void populatePendingKoreCheckStatus(Exchange exchange) {
		// TODO Auto-generated method stub
		
		
		/*Query searchPendingCheckStatusQuery =  new Query(Criteria.where(ITransaction.CARRIER_NAME).is("KORE").
				andOperator(Criteria.where(ITransaction.MIDWAY_STATUS).is(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING).
				andOperator(Criteria.where(ITransaction.CARRIER_STATUS).is(IConstant.CARRIER_TRANSACTION_STATUS_PENDING).
						orOperator(Criteria.where(ITransaction.CARRIER_STATUS).is(IConstant.CARRIER_TRANSACTION_STATUS_ERROR)))		
						));*/
		
		
		Query searchPendingCheckStatusQuery =  new Query(Criteria.where(ITransaction.CARRIER_NAME).is("KORE").andOperator
				(Criteria.where(ITransaction.MIDWAY_STATUS).is(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING)));
				
		
		
		List<Transaction> transactionListPendingStatus = mongoTemplate.find(searchPendingCheckStatusQuery, Transaction.class);
		
		log.info("size of pending device list for Kore............."+transactionListPendingStatus.size());
		exchange.getIn().setBody(transactionListPendingStatus);
	}

	public void populateCallbackDBPayload(Exchange exchange) {

		log.info("TransactionDaoImpl-populateCallbackDBPayload");
		log.info("Exchange inside" + exchange.getIn().getBody());

		CallBackVerizonRequest callBackVerizonRequest = (CallBackVerizonRequest) exchange.getIn().getBody(CallBackVerizonRequest.class);
		String requestId = callBackVerizonRequest.getRequestId();
		DeviceId[] businesscallbackDevices = new DeviceId[callBackVerizonRequest.getDeviceIds().length];
		
		for(int i = 0; i<callBackVerizonRequest.getDeviceIds().length; i++){
			DeviceId callbackDevices = new DeviceId();
			callbackDevices.setId(callBackVerizonRequest.getDeviceIds()[i].getId());
			callbackDevices.setKind(callBackVerizonRequest.getDeviceIds()[i].getKind());
			businesscallbackDevices[i] = callbackDevices;
		}
		
		
		Arrays.sort(businesscallbackDevices,  (DeviceId a, DeviceId b) -> a.getKind().compareTo(b.getKind()));
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String strDeviceNumber = "";
		try {
			strDeviceNumber = objectMapper.writeValueAsString(businesscallbackDevices);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Query searchUserQuery = new Query(Criteria.where(ITransaction.CARRIER_TRANSACTION_ID).is(requestId).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(strDeviceNumber)));

		/*
		 * String carrierTransationID;//Call Back Thread String
		 * carrierStatus;//Call Back Thread String
		 * LastTimeStampUpdated;//CallBack Thread String
		 * carrierErrorDecription;//CallBack Thread String
		 * callBackPayload;//CallBack Thread Boolean
		 * callBackDelivered;//CallBack Thread Boolean
		 * callBackReceived;//CallBack Thread String
		 * callBackFailureToNetSuitReason;//CallBack Thread
		 */

		Update update = new Update();
		if (exchange.getIn().getBody().toString().contains("errorMessage=")) {
			update.set(ITransaction.CALL_BACK_PAYLOAD, exchange.getIn().getBody().toString());
			update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, exchange.getIn().getBody().toString());
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
			update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
			update.set(ITransaction.CALL_BACK_RECEIVED, true);
			update.set(ITransaction.MIDWAY_STATUS,IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);

		} else {

			update.set(ITransaction.CALL_BACK_PAYLOAD, callBackVerizonRequest);
			update.set(ITransaction.CALL_BACK_RECEIVED, true);
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS);
			update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
			update.set(ITransaction.MIDWAY_STATUS,IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);

		}
		mongoTemplate.upsert(searchUserQuery, update, Transaction.class);
		
		

	}

	/**
	 * fetching midwayTransactionID from MongoDB as per requestID and device
	 * number and setting it to target response
	 * 
	 * 
	 * 
	 * */
	public void findMidwayTransactionId(Exchange exchange) {

		log.info("CallbackTransactionDaoImpl-getCallbackMidwayTransactionID");
		log.info("Exchange inside" + exchange.getIn().getBody());

	
		CallBackVerizonRequest req = (CallBackVerizonRequest)exchange.getProperty(IConstant.VERIZON_CALLBACK_RESPONE);
		
		String requestId=req.getRequestId();
		
		DeviceId[] deviceIds=req.getDeviceIds();
		
        DeviceId[] targetCallbackDevices = new DeviceId[deviceIds.length];
		
		for(int i = 0; i<deviceIds.length; i++){
			DeviceId callbackDevices = new DeviceId();
			callbackDevices.setId(deviceIds[i].getId());
			callbackDevices.setKind(deviceIds[i].getKind());
			targetCallbackDevices[i] = callbackDevices;
		}
		
		Arrays.sort(targetCallbackDevices,  (DeviceId a, DeviceId b) -> a.getKind().compareTo(b.getKind()));
		
		String strDeviceNumber = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			strDeviceNumber = objectMapper.writeValueAsString(targetCallbackDevices);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Query searchUserQuery = new Query(Criteria.where(ITransaction.CARRIER_TRANSACTION_ID).is(requestId).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(strDeviceNumber)));
		
		Transaction findOne = mongoTemplate.findOne(searchUserQuery, Transaction.class);
		String midWayTransactionID = findOne.getMidwayTransactionId() != null ? findOne.getMidwayTransactionId() : "";
		
		BaseRequest devicePayload = (BaseRequest) findOne.getDevicePayload();
		Header header= devicePayload.getHeader();
		
		RequestType requestType=findOne.getRequestType();
		
        KeyValues keyValues1=new KeyValues();
		
		keyValues1.setK("transactionId");
		keyValues1.setV(header.getTransactionId());
		
        KeyValues keyValues2=new KeyValues();
		
        keyValues2.setK("orderNumber");
		keyValues2.setV(midWayTransactionID);
		
		KeyValues keyValues3=new KeyValues();
		
	    keyValues3.setK("deviceIds");
	    keyValues3.setV(strDeviceNumber.replace("'\'", ""));
	    
        KeyValues keyValues4=new KeyValues();
		
	    keyValues4.setK("netSuiteID");
	    keyValues4.setV(""+findOne.getNetSuiteId());
		
		KeyValues[] keyValuesArr=new KeyValues[4];
		
		keyValuesArr[0]=keyValues1;
		keyValuesArr[1]=keyValues2;
		keyValuesArr[2]=keyValues3;
		keyValuesArr[3]=keyValues4;
		
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				findOne.getDeviceNumber());

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
				midWayTransactionID);
		
		NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest=(NetSuiteCallBackProvisioningRequest)exchange.getIn().getBody();
		
		netSuiteCallBackProvisioningRequest.setNetSuiteID(""+findOne.getNetSuiteId());
		netSuiteCallBackProvisioningRequest.setCarrierOrderNumber(findOne.getMidwayTransactionId());
		
		//netSuiteCallBackProvisioningRequest.setRequestType(requestType);
		
		Object kafkaObject=exchange.getProperty(IConstant.KAFKA_OBJECT);
		
		if(kafkaObject instanceof KafkaNetSuiteCallBackError){
			
			KafkaNetSuiteCallBackError netSuiteCallBackError=(KafkaNetSuiteCallBackError) kafkaObject;
			
			String desc="Error in callBack from Verizon For "+strDeviceNumber +", transactionId "+midWayTransactionID +"and request Type is "+requestType;
			
			netSuiteCallBackError.setDesc(desc);
			netSuiteCallBackError.setId(requestType.toString());
			netSuiteCallBackError.setBody(devicePayload);
			netSuiteCallBackError.setKeyValues(keyValuesArr);
			exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);
			
			  switch (requestType) {
				case ACTIVATION:

					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
					
					break;

				case DEACTIVATION:

					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
					

					break;

				case REACTIVATION:

					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
					

					break;

				case RESTORE:

					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
					

					break;

				case SUSPEND:

					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
					

					break;

				case CHANGESERVICEPLAN:

					//To do
					ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest=(ChangeDeviceServicePlansRequest)findOne.getDevicePayload();
					log.info("change devcie servcie plan data area...."+changeDeviceServicePlansRequest.getDataArea().toString());
					String oldServicePlan=changeDeviceServicePlansRequest.getDataArea().getCurrentServicePlan();
					String newServicePlan=changeDeviceServicePlansRequest.getDataArea().getServicePlan();
					log.info("service plan new is..."+newServicePlan+" old service plan is....."+oldServicePlan);
					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SERVICE_PLAN);
					netSuiteCallBackProvisioningRequest.setOldServicePlan(oldServicePlan);
					netSuiteCallBackProvisioningRequest.setNewServicePlan(newServicePlan);
					break;

				case CHANGECUSTOMFIELDS:

					//To do
					
					netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
					break;

				default:
					break;
				}
		}
		
		else{
			KafkaNetSuiteCallBackEvent netSuiteCallBackEvent=(KafkaNetSuiteCallBackEvent) kafkaObject;
			
			String desc="Succesfull callBack from Verizon For "+strDeviceNumber +", transactionId "+midWayTransactionID +"and request Type is "+requestType;
				
		    netSuiteCallBackEvent.setDesc(desc);
		    netSuiteCallBackEvent.setId(requestType.toString());
		    netSuiteCallBackEvent.setBody(devicePayload);
		    netSuiteCallBackEvent.setKeyValues(keyValuesArr);
		    exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);
		    
		    switch (requestType) {
			case ACTIVATION:

				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.ACTIVATION);
				netSuiteCallBackProvisioningRequest.setResponse("Device successfully activated.");
				break;

			case DEACTIVATION:

				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.DEACTIVATION);
				netSuiteCallBackProvisioningRequest.setResponse("Device successfully DeActivated.");

				break;

			case REACTIVATION:

				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.REACTIVATION);
				netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReActivated.");

				break;

			case RESTORE:

				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.RESTORATION);
				netSuiteCallBackProvisioningRequest.setResponse("Device successfully ReStored.");

				break;

			case SUSPEND:

				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SUSPENSION);
				netSuiteCallBackProvisioningRequest.setResponse("Device successfully Suspended.");

				break;

			case CHANGESERVICEPLAN:

				//To do
				ChangeDeviceServicePlansRequest changeDeviceServicePlansRequest=(ChangeDeviceServicePlansRequest)findOne.getDevicePayload();
				log.info("change devcie servcie plan data area...."+changeDeviceServicePlansRequest.getDataArea().toString());
				String oldServicePlan=changeDeviceServicePlansRequest.getDataArea().getCurrentServicePlan();
				String newServicePlan=changeDeviceServicePlansRequest.getDataArea().getServicePlan();
				log.info("service plan new is..."+newServicePlan+" old service plan is....."+oldServicePlan);
				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.SERVICE_PLAN);
				netSuiteCallBackProvisioningRequest.setOldServicePlan(oldServicePlan);
				netSuiteCallBackProvisioningRequest.setNewServicePlan(newServicePlan);
				netSuiteCallBackProvisioningRequest.setResponse("Device Service Plan Changed successfully.");

				break;

			case CHANGECUSTOMFIELDS:

				//To do
				netSuiteCallBackProvisioningRequest.setRequestType(NetSuiteRequestType.CUSTOM_FIELDS);
				netSuiteCallBackProvisioningRequest.setResponse("Device Custom Fields Changed successfully.");

				break;

			default:
				break;
			}
		}
		
		
		exchange.getIn().setBody(netSuiteCallBackProvisioningRequest);
	}

	public void populateReactivateDBPayload(Exchange exchange) {

		log.info("Inside populateReactivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		ReactivateDeviceRequest req = (ReactivateDeviceRequest) exchange.getIn().getBody();

		ReactivateDeviceRequestDataArea reActivateDeviceRequestDataArea = (ReactivateDeviceRequestDataArea) req.getDataArea();

		MidWayDevices[] reActivateDevices = reActivateDeviceRequestDataArea.getDevices();

		Kryo kryo = new Kryo();
		for (MidWayDevices reActivateDevice : reActivateDevices) {

			ReactivateDeviceRequest dbPayload = new ReactivateDeviceRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=reActivateDevice.getNetSuiteId();
			MidWayDevices[] businessPayLoadDevicesArray = new MidWayDevices[1];
			MidWayDevices businessPayLoadActivateDevices = new MidWayDevices();
			MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[reActivateDevice.getDeviceIds().length];

			for (int i = 0; i < reActivateDevice.getDeviceIds().length; i++) {
				MidWayDeviceId reActivateDeviceId = reActivateDevice.getDeviceIds()[i];

				MidWayDeviceId businessPayLoadActivateDeviceId = new MidWayDeviceId();

				/*
				 * businessPayLoadActivateDeviceId.seteAPCode(activateDeviceId
				 * .geteAPCode());
				 */
				businessPayLoadActivateDeviceId.setId(reActivateDeviceId.getId());
				businessPayLoadActivateDeviceId.setKind(reActivateDeviceId.getKind());

				businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;

			}
			businessPayLoadActivateDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;

			ReactivateDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayLoadDevicesArray);
			dbPayload.setDataArea(copyDataArea);

			// copy.getDataArea().setDevices();

			try {

				Transaction transaction = new Transaction();

				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				// TODO if number of devices are more
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.REACTIVATION);
				transaction.setCallBackReceived(false);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Exception populateReactivateDBPayload");
			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}
	
	//method to store payload in database for Restore Operation
	public void populateRestoreDBPayload(Exchange exchange) {
		log.info("Inside populateRestoreDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		RestoreDeviceRequest req = (RestoreDeviceRequest) exchange.getIn().getBody();

		RestoreDeviceRequestDataArea restoreDeviceRequestDataArea = (RestoreDeviceRequestDataArea) req.getDataArea();

		MidWayDevices[] restoreDevices = restoreDeviceRequestDataArea.getDevices();

		Kryo kryo = new Kryo();
		for (MidWayDevices restoreDevice : restoreDevices) {

			RestoreDeviceRequest dbPayload = new RestoreDeviceRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=restoreDevice.getNetSuiteId();
			MidWayDevices[] businessPayLoadDevicesArray = new MidWayDevices[1];
			MidWayDevices businessPayLoadRestoreDevices = new MidWayDevices();
			MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[restoreDevice.getDeviceIds().length];

			for (int i = 0; i < restoreDevice.getDeviceIds().length; i++) {
				MidWayDeviceId deviceId = restoreDevice.getDeviceIds()[i];

				MidWayDeviceId businessPayLoadRestoreDeviceId = new MidWayDeviceId();

				
				businessPayLoadRestoreDeviceId.setId(deviceId.getId());
				businessPayLoadRestoreDeviceId.setKind(deviceId.getKind());

				businessPayloadDeviceId[i] = businessPayLoadRestoreDeviceId;

			}
			businessPayLoadRestoreDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayLoadDevicesArray[0] = businessPayLoadRestoreDevices;

			RestoreDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayLoadDevicesArray);
			dbPayload.setDataArea(copyDataArea);

		
			try {

				Transaction transaction = new Transaction();

				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				
				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(businessPayloadDeviceId,  (MidWayDeviceId a,MidWayDeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				// TODO if number of devices are more
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.RESTORE);
				transaction.setCallBackReceived(false);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Exception populateRestoreDBPayload");
			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}
	}

	public void populateCustomeFieldsDBPayload(Exchange exchange) {
		// TODO Auto-generated method stub
		log.info("Inside populateCustomeFieldsDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		CustomFieldsDeviceRequest req = (CustomFieldsDeviceRequest) exchange.getIn().getBody();

		CustomFieldsDeviceRequestDataArea customFieldsDeviceRequestDataArea = (CustomFieldsDeviceRequestDataArea) req.getDataArea();

		MidWayDevices[] customFieldsDevices = customFieldsDeviceRequestDataArea.getDevices();

		Kryo kryo = new Kryo();
		for (MidWayDevices customFieldsDevice : customFieldsDevices) {

			CustomFieldsDeviceRequest dbPayload = new CustomFieldsDeviceRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=customFieldsDevice.getNetSuiteId();
			MidWayDevices[] businessPayLoadDevicesArray = new MidWayDevices[1];
			MidWayDevices businessPayLoadActivateDevices = new MidWayDevices();
			MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[customFieldsDevice.getDeviceIds().length];

			for (int i = 0; i < customFieldsDevice.getDeviceIds().length; i++) {
				MidWayDeviceId customFieldsDeviceId = customFieldsDevice.getDeviceIds()[i];

				MidWayDeviceId businessPayLoadActivateDeviceId = new MidWayDeviceId();

				/*
				 * businessPayLoadActivateDeviceId.seteAPCode(activateDeviceId
				 * .geteAPCode());
				 */
				businessPayLoadActivateDeviceId.setId(customFieldsDeviceId.getId());
				businessPayLoadActivateDeviceId.setKind(customFieldsDeviceId.getKind());

				businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;

			}
			businessPayLoadActivateDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;

			CustomFieldsDeviceRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayLoadDevicesArray);
			dbPayload.setDataArea(copyDataArea);

			// copy.getDataArea().setDevices();

			try {

				Transaction transaction = new Transaction();

				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				
				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(businessPayloadDeviceId,  (MidWayDeviceId a,MidWayDeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				// TODO if number of devices are more
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.CHANGECUSTOMFIELDS);
				transaction.setCallBackReceived(false);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Exception populateCustomeFieldsDBPayload");
			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		// activateDeviceRequestDataArea.setDevices(activateDevices);

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}
	
	public void populateChangeDeviceServicePlansDBPayload(Exchange exchange) {
		// TODO Auto-generated method stub
		
		log.info("Inside populateChangeDeviceServicePlansDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		ChangeDeviceServicePlansRequest req = (ChangeDeviceServicePlansRequest) exchange.getIn().getBody();
		ChangeDeviceServicePlansRequestDataArea changeDeviceServicePlansRequestDataArea = (ChangeDeviceServicePlansRequestDataArea) req.getDataArea();
		MidWayDevices[] changeServicePlansDevices = changeDeviceServicePlansRequestDataArea.getDevices();

		Kryo kryo = new Kryo();

		for (MidWayDevices changeServicePlansDevice : changeServicePlansDevices) {
			ChangeDeviceServicePlansRequest dbPayload = new ChangeDeviceServicePlansRequest();
			dbPayload.setHeader(req.getHeader());
			Integer netSuiteId=changeServicePlansDevice.getNetSuiteId();
			MidWayDevices[] businessPayloadDeviceArray = new MidWayDevices[1];
			MidWayDevices businessPayLoadChangeServicePlansDevices = new MidWayDevices();
			MidWayDeviceId[] businessPayloadDeviceId = new MidWayDeviceId[changeServicePlansDevice.getDeviceIds().length];

			for (int i = 0; i < changeServicePlansDevice.getDeviceIds().length; i++) {
				MidWayDeviceId changeServicePlansDeviceId = changeServicePlansDevice.getDeviceIds()[i];
				MidWayDeviceId businesspayLoadChangeServicePlansDeviceId = new MidWayDeviceId();
				businesspayLoadChangeServicePlansDeviceId.setId(changeServicePlansDeviceId.getId());
				businesspayLoadChangeServicePlansDeviceId.setKind(changeServicePlansDeviceId.getKind());
				businessPayloadDeviceId[i] = businesspayLoadChangeServicePlansDeviceId;
			}

			businessPayLoadChangeServicePlansDevices.setDeviceIds(businessPayloadDeviceId);
			businessPayloadDeviceArray[0] = businessPayLoadChangeServicePlansDevices;
			ChangeDeviceServicePlansRequestDataArea copyDataArea = kryo.copy(req.getDataArea());

			copyDataArea.setDevices(businessPayloadDeviceArray);
			dbPayload.setDataArea(copyDataArea);

			try {

				Transaction transaction = new Transaction();
				transaction.setMidwayTransactionId(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());

				//Sorting the device id by kind and inserting into deviceNumber
				Arrays.sort(businessPayloadDeviceId,  (MidWayDeviceId a,MidWayDeviceId b) -> a.getKind().compareTo(b.getKind()));
				
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(dbPayload);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString());
				transaction.setTimeStampReceived(new Date());
				transaction.setAuditTransactionId(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(RequestType.CHANGESERVICEPLAN);
				transaction.setCallBackReceived(false);
				transaction.setNetSuiteId(netSuiteId);
				list.add(transaction);

			} catch (Exception ex) {
				log.error("Exception populateChangeDeviceServicePlansDBPayload");

			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}



	}

	@Override
	public void populateKoreCheckStatusResponse(Exchange exchange) {
		// TODO Auto-generated method stub
		
		KoreCheckStatusResponse koreCheckStatusResponse=(KoreCheckStatusResponse)exchange.getIn().getBody();
		
		
		Query searchQuery = new Query(Criteria.where(ITransaction.CARRIER_TRANSACTION_ID).is(exchange.getProperty(IConstant.CARRIER_TRANSACTION_ID)));

		
		String provisioningRequestStatus=koreCheckStatusResponse.getD().getProvisioningRequestStatus();
		
		Update update = new Update();
		
		log.info("koreCheckStatusResponse is ............"+provisioningRequestStatus);
		
		if(provisioningRequestStatus.equals(IConstant.KORE_CHECKSTATUS_COMPLETED))
		{
			
			update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS);
			update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
			update.set(ITransaction.CALL_BACK_PAYLOAD, koreCheckStatusResponse);
			update.set(ITransaction.CALL_BACK_RECEIVED, true);
		}
		
		else
		{
			log.info("koreCheckStatusResponse other then completed is ............"+provisioningRequestStatus);
			
		}
		
		update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, "");
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);
		
	}
	
	@Override
	public void populateKoreCheckStatusConnectionResponse(Exchange exchange) {
		// TODO Auto-generated method stub
		
		
		
		Query searchQuery = new Query(Criteria.where(ITransaction.CARRIER_TRANSACTION_ID).is(exchange.getProperty(IConstant.CARRIER_TRANSACTION_ID)));

		
		Update update = new Update();
		
	
		
		update.set(ITransaction.CARRIER_ERROR_DESCRIPTION,IConstant.KORE_CHECKSTATUS_CONNECTION_ERROR);
		
		
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);
		
	}
	
	@Override
	public void populateKoreCheckStatusErrorResponse(Exchange exchange) {
		// TODO Auto-generated method stub
		
		
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

		Update update = new Update();
		
		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		/**
		 * To check whether exception comes while calling the getcheckStatus for KORE or it was already error when sent for the device provisioning request.
		 */
		if(exception!=null){
			String errorResponseBody = exception.getResponseBody();
			ObjectMapper mapper = new ObjectMapper();
			KoreErrorResponse errorResponsePayload = null;
			try {
				errorResponsePayload = mapper.readValue(errorResponseBody, KoreErrorResponse.class);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			update.set(ITransaction.CARRIER_ERROR_DESCRIPTION, errorResponsePayload.getErrorMessage());
			update.set(ITransaction.CALL_BACK_PAYLOAD, errorResponsePayload);
		}
		
		else{
			
			log.info("no cxf exception........");
		}
		

		update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_ERROR);
		update.set(ITransaction.CARRIER_STATUS, IConstant.CARRIER_TRANSACTION_STATUS_ERROR);
		
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);

		
		
	}
	
	@Override
	public void populateKoreCustomChangeResponse(Exchange exchange) {
		// TODO Auto-generated method stub
		
		
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

		Update update = new Update();

		update.set(ITransaction.MIDWAY_STATUS, IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);

		
		
	}

	@Override
	public void updateNetSuiteCallBackResponse(Exchange exchange)
	{
		log.info("is netsuite callback error......."+exchange.getProperty("isNetSuiteCallBackError"));
		
		Map map = exchange.getIn().getBody(Map.class);
		
		ObjectMapper mapper=new ObjectMapper();
		
		String jsonstring=null;
		
		try {
			jsonstring=mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("is netsuite callback response......."+exchange.getIn().getBody().toString()+"   "+jsonstring);
		
		if(exchange.getProperty("isNetSuiteCallBackError")==null)
		{
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));	
	
		
		Update update = new Update();

		update.set(ITransaction.CALL_BACK_DELIVERED ,true);
		update.set(ITransaction.NETSUITE_RESPONSE, jsonstring);
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);
		
		}
		
		else
		{
			log.info("error while sending callback to Netsuite");
			
		}
	
	}
	
	@Override
	public void updateNetSuiteCallBackError(Exchange exchange){
		
		log.info("net suite call back error.............");
	
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));
		
		Update update = new Update();
		
		Exception exception =  (Exception)exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		
		
		
		if(exception instanceof CxfOperationException)
		{
	    CxfOperationException cxfOperationException = (CxfOperationException)exception;
	    update.set(ITransaction.CALL_BACK_FAILURE_TO_NETSUITE_REASON ,cxfOperationException.getResponseBody());
	    
		}
		
		else
		{
			
			
	     update.set(ITransaction.CALL_BACK_FAILURE_TO_NETSUITE_REASON ,exception.getMessage());
			
		}
		
		
		
		update.set(ITransaction.CALL_BACK_DELIVERED ,false);
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);
		
		exchange.setProperty("isNetSuiteCallBackError", true);
	
	}

	@Override
	public void updateNetSuiteCallBackRequest(Exchange exchange){
		
		log.info("net suite call back request.............");
	
		Query searchQuery = new Query(Criteria.where(ITransaction.MIDWAY_TRANSACTION_ID).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where(ITransaction.DEVICE_NUMBER).is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));
		
		Update update = new Update();
		
		update.set(ITransaction.NETSUITE_REQUEST ,exchange.getIn().getBody());
		
		update.set(ITransaction.LAST_TIME_STAMP_UPDATED, new Date());
		
		mongoTemplate.updateFirst(searchQuery, update, Transaction.class);
		
		
	
	}


}
