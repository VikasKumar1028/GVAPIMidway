package com.gv.midway.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ITransactionalDao;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;
import com.mongodb.WriteResult;

@Service
public class TransactionalDaoImpl implements ITransactionalDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(TransactionalDaoImpl.class);

	public void populateActivateDBPayload(Exchange exchange) {
		log.info("Inside populateActivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		String currentDataTime = CommonUtil.getCurrentTimeStamp();

		ActivateDeviceRequest req = (ActivateDeviceRequest) exchange.getIn()
				.getBody();

		ActivateDeviceRequestDataArea activateDeviceRequestDataArea = (ActivateDeviceRequestDataArea) req
				.getDataArea();

		ActivateDevices[] activateDevices = activateDeviceRequestDataArea
				.getDevices();

		ActivateDeviceRequest dbPayload = new ActivateDeviceRequest();
		dbPayload.setHeader(req.getHeader());
	
		Kryo kryo = new Kryo();
		for (ActivateDevices activateDevice : activateDevices) {

			ActivateDevices[] businessPayLoadDevicesArray = new ActivateDevices[1];
			ActivateDevices businessPayLoadActivateDevices = new ActivateDevices();
			ActivateDeviceId[] businessPayloadDeviceId = new ActivateDeviceId[activateDevice
					.getDeviceIds().length];

			for (int i = 0; i < activateDevice.getDeviceIds().length; i++) {
				ActivateDeviceId activateDeviceId = activateDevice
						.getDeviceIds()[i];

				ActivateDeviceId businessPayLoadActivateDeviceId = new ActivateDeviceId();

				businessPayLoadActivateDeviceId.seteAPCode(activateDeviceId
						.geteAPCode());
				businessPayLoadActivateDeviceId.setId(activateDeviceId.getId());
				businessPayLoadActivateDeviceId.setKind(activateDeviceId
						.getKind());

				businessPayloadDeviceId[i] = businessPayLoadActivateDeviceId;

			}
			businessPayLoadActivateDevices
					.setDeviceIds(businessPayloadDeviceId);
			businessPayLoadDevicesArray[0] = businessPayLoadActivateDevices;
		
			ActivateDeviceRequestDataArea copyDataArea = kryo.copy(req
					.getDataArea());

			copyDataArea.setDevices(businessPayLoadDevicesArray);
			dbPayload.setDataArea(copyDataArea);

			// copy.getDataArea().setDevices();

			try {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(dbPayload);

				Transaction transaction = new Transaction();

				transaction.setMidwayTransationID(exchange.getProperty(
						IConstant.MIDWAY_TRANSACTION_ID).toString());
				// TODO if number of devices are more
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj
						.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(msgBody);
				transaction
						.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(
						IConstant.BSCARRIER).toString());
				transaction.setTimeStampReceived(currentDataTime);
				transaction.setAuditTransationID(exchange.getProperty(
						IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(exchange.getFromEndpoint()
						.toString());

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

		if (exchange.getProperty(IConstant.SOURCE_NAME).toString()
				.equals("KORE")) {

			exchange.getIn().setBody(list);
		}
	}

	// santosh:new method
	public void populateDeactivateDBPayload(Exchange exchange) {

		log.info("Inside populateDeactivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		String currentDataTime = CommonUtil.getCurrentTimeStamp();

		DeactivateDeviceRequest req = (DeactivateDeviceRequest) exchange
				.getIn().getBody();
		DeactivateDeviceRequestDataArea deActivateDeviceRequestDataArea = (DeactivateDeviceRequestDataArea) req
				.getDataArea();
		DeactivateDevices[] deactivateDevices = deActivateDeviceRequestDataArea
				.getDevices();
		DeactivateDeviceRequest dbPayload = new DeactivateDeviceRequest();
		dbPayload.setHeader(req.getHeader());
		Kryo kryo = new Kryo();

		for (DeactivateDevices deactivateDevice : deactivateDevices) {
			DeactivateDevices[] businessPayloadDeviceArray = new DeactivateDevices[1];
			DeactivateDevices businessPayLoadDeactivateDevices = new DeactivateDevices();
			DeactivateDeviceId[] businessPayloadDeviceId = new DeactivateDeviceId[deactivateDevice
					.getDeviceIds().length];

			for (int i = 0; i < deactivateDevice.getDeviceIds().length; i++) {
				DeactivateDeviceId deactivateDeviceId = deactivateDevice
						.getDeviceIds()[i];
				DeactivateDeviceId businesspayLoadDeactivateDeviceId = new DeactivateDeviceId();
				businesspayLoadDeactivateDeviceId
						.setFlagScrap(deactivateDeviceId.getFlagScrap());
				businesspayLoadDeactivateDeviceId.setId(deactivateDeviceId
						.getId());
				businesspayLoadDeactivateDeviceId.setKind(deactivateDeviceId
						.getKind());
				businessPayloadDeviceId[i] = businesspayLoadDeactivateDeviceId;
			}

			businessPayLoadDeactivateDevices
					.setDeviceIds(businessPayloadDeviceId);
			businessPayloadDeviceArray[0] = businessPayLoadDeactivateDevices;
			// payLoadDevices =(ActivateDevices[])kryo.copy(activateDevices);

			DeactivateDeviceRequestDataArea copyDataArea = kryo.copy(req
					.getDataArea());

			copyDataArea.setDevices(businessPayloadDeviceArray);
			dbPayload.setDataArea(copyDataArea);

			try {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(dbPayload);

				Transaction transaction = new Transaction();
				transaction.setMidwayTransationID(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID).toString());
				ObjectMapper obj = new ObjectMapper();
				String strDeviceNumber = obj
						.writeValueAsString(businessPayloadDeviceId);
				transaction.setDeviceNumber(strDeviceNumber);
				transaction.setDevicePayload(msgBody);
				transaction
						.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(
						IConstant.BSCARRIER).toString());
				transaction.setTimeStampReceived(currentDataTime);
				transaction.setAuditTransationID(exchange.getProperty(
						IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(exchange.getFromEndpoint()
						.toString());

				list.add(transaction);

			} catch (Exception ex) {
				log.error("Inside populateDeactivateDBPayload");

			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.SOURCE_NAME).toString()
				.equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}

	public void populateVerizonTransactionalResponse(Exchange exchange) {

		Query searchUserQuery = new Query(Criteria.where("midwayTransationID")
				.is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

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

			update.set("callBackPayload", exchange.getIn().getBody().toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody()
					.toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody()
					.toString());
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());

		} else {

			update.set("callBackPayload", exchange.getIn().getBody().toString());

			// TODO
			// update.set("carrierErrorDecription",
			// exchange.getIn().getBody().toString());
			// update.set("carrierErrorDecription",
			// exchange.getIn().getBody().toString());

			String responseId = exchange.getIn().getBody().toString();

			System.out
					.println("****************************responseId****************"
							+ responseId);
			/*
			 * JSONObject obj= new JSONObject(); obj.put("value", responseId);
			 * 
			 * JSONObject object = (JSONObject) obj.get("value"); Object reqId =
			 * object.get("requestId"); System.out.println("--------" +
			 * reqId.toString());
			 */

			update.set("carrierStatus", "Pending");
			update.set("carrierTransationID", responseId);
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());

		}
		mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);

	}

	public void populateKoreTransactionalErrorResponse(Exchange exchange) {

		Query searchUserQuery = new Query(
				Criteria.where("midwayTransationID")
						.is(exchange
								.getProperty(IConstant.MIDWAY_TRANSACTION_ID))
						.andOperator(
								Criteria.where("deviceNumber")
										.is(exchange
												.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

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
		CxfOperationException exception = (CxfOperationException) exchange
				.getProperty(Exchange.EXCEPTION_CAUGHT);

		String errorResponseBody = exception.getResponseBody();

		Update update = new Update();
		update.set("callBackPayload", errorResponseBody);
		update.set("carrierErrorDecription", errorResponseBody);
		update.set("carrierErrorDecription", errorResponseBody);
		update.set("carrierStatus", "Error");
		update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
		mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);

		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, errorResponseBody);
		exchange.setProperty(IConstant.RESPONSE_STATUS, errorResponseBody);
		exchange.setProperty(IConstant.RESPONSE_CODE, errorResponseBody);

	}

	public void populateVerizonTransactionalErrorResponse(Exchange exchange) {
		// TODO Auto-generated method stub

	}

	public void populateKoreTransactionalResponse(Exchange exchange) {
		// TODO Auto-generated method stub

	}

	public void populateConnectionErrorResponse(Exchange exchange,
			String errorType) {

		System.out
				.println("**************************ERROR TYPE**************************************"
						+ errorType);
		Query searchUserQuery = null;
		if ("KORE".equals(exchange.getProperty(IConstant.SOURCE_NAME))) {
			searchUserQuery = new Query(
					Criteria.where("midwayTransationID")
							.is(exchange
									.getProperty(IConstant.MIDWAY_TRANSACTION_ID))
							.andOperator(
									Criteria.where("deviceNumber")
											.is(exchange
													.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));
			String errorResponseBody = IConstant.MIDWAY_CONNECTION_ERROR;

			Update update = new Update();
			update.set("callBackPayload", errorResponseBody);
			update.set("carrierErrorDecription", errorResponseBody);
			update.set("carrierErrorDecription", errorResponseBody);
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			mongoTemplate.updateMulti(searchUserQuery, update,
					Transaction.class);

		} else if ("VERIZON"
				.equals(exchange.getProperty(IConstant.SOURCE_NAME))) {

			System.out
					.println("**************************1111111111111**************************************"
							+ exchange.getIn().getBody().toString());

			searchUserQuery = new Query(Criteria.where("midwayTransationID")
					.is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

			Update update = new Update();
			update.set("callBackPayload", "CONNECTION_ERROR".toString());
			update.set("carrierErrorDecription", "CONNECTION_ERROR".toString());
			update.set("carrierErrorDecription", "CONNECTION_ERROR".toString());
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			WriteResult result = mongoTemplate.updateMulti(searchUserQuery,
					update, Transaction.class);

			System.out.println("*******************************************"
					+ result);

		}

		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, "CONNECTION_ERROR");
		exchange.setProperty(IConstant.RESPONSE_STATUS, "CONNECTION_ERROR");
		exchange.setProperty(IConstant.RESPONSE_CODE, "CONNECTION_ERROR");

	}

	public void populatePendingKoreCheckStatus(Exchange exchange) {
		// TODO Auto-generated method stub
		Query searchPendingCheckStatusQuery = new Query(Criteria
				.where("carrierStatus").is("Pending")
				.andOperator(Criteria.where("carrierName").is("KORE")));

		List<Transaction> transactionListPendingStatus = mongoTemplate.find(
				searchPendingCheckStatusQuery, Transaction.class);
		exchange.getIn().setBody(transactionListPendingStatus);
	}

}
