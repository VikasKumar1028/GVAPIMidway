package com.gv.midway.dao.impl;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.esotericsoftware.kryo.Kryo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ITransactionalDao;
import com.gv.midway.pojo.DeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;

@Service
public class TransactionalDaoImpl implements ITransactionalDao {

	@Autowired
	MongoTemplate mongoTemplate;

	Logger log = Logger.getLogger(TransactionalDaoImpl.class);

	public void populateDBPayload(Exchange exchange) {
		log.info("Inside populateDBPayload");
		ArrayList<Transaction> list = new ArrayList();

		long timestamp = System.currentTimeMillis();
		String midwayTransationID = Long.toString(timestamp);
		String currentDataTime = CommonUtil.getCurrentTimeStamp();

		ActivateDeviceRequest req = (ActivateDeviceRequest) exchange.getIn()
				.getBody();

		ActivateDeviceRequestDataArea activateDeviceRequestDataArea = (ActivateDeviceRequestDataArea) req
				.getDataArea();

		DeviceId[] deviceIds = activateDeviceRequestDataArea.getDeviceId();
		Kryo kryo = new Kryo();

		for (DeviceId actualDeviceId : deviceIds) {
			DeviceId[] payLoadDeviceIds = new DeviceId[1];
			DeviceId payLoadDeviceId = new DeviceId();
			payLoadDeviceId.setId(actualDeviceId.getId());
			payLoadDeviceId.setKind(actualDeviceId.getKind());
			payLoadDeviceIds[0] = payLoadDeviceId;
			ActivateDeviceRequest copy = kryo.copy(req);

			copy.getDataArea().setDeviceId(payLoadDeviceIds);

			try {

				ObjectMapper mapper = new ObjectMapper();
				String msgBody = mapper.writeValueAsString(copy);

				/*
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
				 */

				Transaction transaction = new Transaction();

				transaction.setMidwayTransationID(midwayTransationID);
				transaction.setDeviceNumber(actualDeviceId.getId());
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
				log.error("Inside populateDBPayload");

			}

		

		}
		mongoTemplate.insertAll(list);

		
		System.out.println("***********************************" + list.size());
		// For Kore We Need Wire Tap and SEDA component So the body should
		// be set with arraylist of transaction for Verizon we simply add
		// into database and do not change the exchange body

		if (exchange.getProperty(IConstant.SOURCE_NAME).toString()
				.equals("KORE")) {

			exchange.getIn().setBody(list);
		}
	}

	public void callbackSaveDB(Exchange exchange) {
		// TODO Auto-generated method stub

	}

}
