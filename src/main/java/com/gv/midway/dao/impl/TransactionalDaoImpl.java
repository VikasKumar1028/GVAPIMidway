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
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
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

		
		String midwayTransationID=CommonUtil.getmidwayTransationId();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID, midwayTransationID);
		
		String currentDataTime = CommonUtil.getCurrentTimeStamp();

		ActivateDeviceRequest req = (ActivateDeviceRequest) exchange.getIn().getBody();

		ActivateDeviceRequestDataArea activateDeviceRequestDataArea = (ActivateDeviceRequestDataArea) req.getDataArea();

		//ActivateDeviceId[] deviceIds = activateDeviceRequestDataArea.getDeviceId();
		Kryo kryo = new Kryo();

	/*	for (ActivateDeviceId actualDeviceId : deviceIds) {
			ActivateDeviceId[] payLoadDeviceIds = new ActivateDeviceId[1];
			ActivateDeviceId payLoadDeviceId = new ActivateDeviceId();
			payLoadDeviceId.setId(actualDeviceId.getId());
			payLoadDeviceId.setKind(actualDeviceId.getKind());
			payLoadDeviceIds[0] = payLoadDeviceId;
			ActivateDeviceRequest copy = kryo.copy(req);

			//copy.getDataArea().setDeviceId(payLoadDeviceIds);

			try {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(copy);

				
				 * String midwayTransationID; //Main Thread String
				 * deviceNumber;//Main Thread String devicePayload;//Main Thread
				 * String midwayStatus;//Main Thread String carrierName;//Main
				 * Thread String TimeStampReceived;//Main Thread String
				 * auditTransationID;//Main Thread String requestType;//Main
				 * Thread
				 * 
				 * String carrierTransationID;//Call Back Thread String
				 * carrierStatus;//Call Back Thread String
				 * LastTimeStampUpdated;//CallBack Thread String
				 * carrierErrorDecription;//CallBack Thread String
				 * callBackPayload;//CallBack Thread Boolean
				 * callBackDelivered;//CallBack Thread Boolean
				 * callBackReceived;//CallBack Thread String
				 * callBackFailureToNetSuitReason;//CallBack Thread
				 

				Transaction transaction = new Transaction();

				transaction.setMidwayTransationID(midwayTransationID);
				transaction.setDeviceNumber(actualDeviceId.getId());
				transaction.setDevicePayload(msgBody);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.BSCARRIER).toString());
				transaction.setTimeStampReceived(currentDataTime);
				transaction.setAuditTransationID(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(exchange.getFromEndpoint().toString());

				list.add(transaction);

			} catch (Exception ex) {
				//santosh:logging
				log.error("Inside populateActivateDBPayload");

			}

		}
		mongoTemplate.insertAll(list);
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.SOURCE_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}*/
	}
//santosh:new method
	public void populateDeactivateDBPayload(Exchange exchange) {

		log.info("Inside populateDeactivateDBPayload");
		ArrayList<Transaction> list = new ArrayList<Transaction>();

		String midwayTransationID=CommonUtil.getmidwayTransationId();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID, midwayTransationID);
		String currentDataTime = CommonUtil.getCurrentTimeStamp();

		DeactivateDeviceRequest req = (DeactivateDeviceRequest) exchange.getIn().getBody();

		DeactivateDeviceRequestDataArea deActivateDeviceRequestDataArea = (DeactivateDeviceRequestDataArea) req.getDataArea();

		//DeviceId[] deviceIds = deActivateDeviceRequestDataArea.getDeviceId();
		Kryo kryo = new Kryo();

		/*for (DeviceId actualDeviceId : deviceIds) {
			DeviceId[] payLoadDeviceIds = new DeviceId[1];
			DeviceId payLoadDeviceId = new DeviceId();
			payLoadDeviceId.setId(actualDeviceId.getId());
			payLoadDeviceId.setKind(actualDeviceId.getKind());
			payLoadDeviceIds[0] = payLoadDeviceId;
			DeactivateDeviceRequest copy = kryo.copy(req);

			//copy.getDataArea().setDeviceId(payLoadDeviceIds);

			try {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(copy);

				Transaction transaction = new Transaction();
				transaction.setMidwayTransationID(midwayTransationID);
				transaction.setDeviceNumber(actualDeviceId.getId());
				transaction.setDevicePayload(msgBody);
				transaction.setMidwayStatus(IConstant.MIDWAY_TRANSACTION_STATUS_PENDING);
				transaction.setCarrierName(exchange.getProperty(IConstant.BSCARRIER).toString());
				transaction.setTimeStampReceived(currentDataTime);
				transaction.setAuditTransationID(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID).toString());
				transaction.setRequestType(exchange.getFromEndpoint().toString());

				list.add(transaction);

			} catch (Exception ex) {
				log.error("Inside populateDeactivateDBPayload");

			}

		}
		mongoTemplate.insertAll(list);*/
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.SOURCE_NAME).toString().equals("KORE")) {

			exchange.getIn().setBody(list);
		}

	}

	public void populateVerizonTransactionalResponse(Exchange exchange) {

		Query searchUserQuery = new Query(Criteria.where("midwayTransationID").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

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
		if (exchange.getIn().getBody().toString().contains("errorMessage=")) {

			Update update = new Update();
			update.set("callBackPayload", exchange.getIn().getBody().toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody().toString());
			update.set("carrierErrorDecription", exchange.getIn().getBody().toString());
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);
		}
	}

	public void populateKoreTransactionalErrorResponse(Exchange exchange) {

		Query searchUserQuery = new Query(Criteria.where("midwayTransationID").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where("deviceNumber").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));

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
		CxfOperationException exception = (CxfOperationException) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);

		String errorResponseBody = exception.getResponseBody();

		Update update = new Update();
		update.set("callBackPayload", errorResponseBody);
		update.set("carrierErrorDecription", errorResponseBody);
		update.set("carrierErrorDecription", errorResponseBody);
		update.set("carrierStatus", "Error");
		update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
		mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);
		
		
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,errorResponseBody );
		exchange.setProperty(IConstant.RESPONSE_STATUS,errorResponseBody );
		exchange.setProperty(IConstant.RESPONSE_CODE,errorResponseBody );

	}

	public void populateVerizonTransactionalErrorResponse(Exchange exchange) {
		// TODO Auto-generated method stub

	}

	public void populateKoreTransactionalResponse(Exchange exchange) {
		// TODO Auto-generated method stub

	}

	public void populateConnectionErrorResponse(Exchange exchange, String errorType) {

		System.out.println("**************************ERROR TYPE**************************************" + errorType);
		Query searchUserQuery = null;
		if ("KORE".equals(exchange.getProperty(IConstant.SOURCE_NAME))) {
			searchUserQuery = new Query(Criteria.where("midwayTransationID").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).andOperator(Criteria.where("deviceNumber").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER))));
			String errorResponseBody = IConstant.MIDWAY_CONNECTION_ERROR;

			Update update = new Update();
			update.set("callBackPayload", errorResponseBody);
			update.set("carrierErrorDecription", errorResponseBody);
			update.set("carrierErrorDecription", errorResponseBody);
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);

		} else if ("VERIZON".equals(exchange.getProperty(IConstant.SOURCE_NAME))) {

			System.out.println("**************************1111111111111**************************************" + exchange.getIn().getBody().toString());

			searchUserQuery = new Query(Criteria.where("midwayTransationID").is(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)));

			Update update = new Update();
			update.set("callBackPayload", "CONNECTION_ERROR".toString());
			update.set("carrierErrorDecription", "CONNECTION_ERROR".toString());
			update.set("carrierErrorDecription", "CONNECTION_ERROR".toString());
			update.set("carrierStatus", "Error");
			update.set("lastTimeStampUpdated", CommonUtil.getCurrentTimeStamp());
			WriteResult result = mongoTemplate.updateMulti(searchUserQuery, update, Transaction.class);

			System.out.println("*******************************************" + result);

		}

		
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, "CONNECTION_ERROR");
		exchange.setProperty(IConstant.RESPONSE_STATUS, "CONNECTION_ERROR");
		exchange.setProperty(IConstant.RESPONSE_CODE, "CONNECTION_ERROR");

	}

	public void populatePendingKoreCheckStatus(Exchange exchange) {
		// TODO Auto-generated method stub
		Query searchPendingCheckStatusQuery = new Query(Criteria
				.where("carrierStatus")
				.is("Pending").andOperator(
						Criteria.where("carrierName")
						.is("KORE")));

		List<Transaction> transactionListPendingStatus = mongoTemplate.find(searchPendingCheckStatusQuery, Transaction.class);
		exchange.getIn().setBody(transactionListPendingStatus);
	}

}
