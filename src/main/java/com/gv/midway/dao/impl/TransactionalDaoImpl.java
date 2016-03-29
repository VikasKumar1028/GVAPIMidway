package com.gv.midway.dao.impl;

import java.util.ArrayList;

import org.apache.camel.Exchange;
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

@Service
public class TransactionalDaoImpl implements ITransactionalDao {

	@Autowired
	MongoTemplate mongoTemplate;

	public void populateDBPayload(Exchange exchange) {
		ArrayList list = new ArrayList();

		System.out.println("Exchange inside"
				+ exchange.getIn().getBody().toString());

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

				Transaction transaction = new Transaction();
				transaction.setCarrierName(exchange.getProperty(IConstant.BSCARRIER).toString());
				transaction.setDevicePayload(msgBody);
				
				
				//transaction.setAuditTransationID(exchange.getProperty());
						
				/*transaction.setCarrierName(exchange.getProperties());
				transaction.setCarrier(exchang
						.getProperty(IConstant.BSCARRIER).toString());
				transaction.setSource(exchange.getProperty(
						IConstant.SOURCE_NAME).toString());
				transaction.setApiAction(exchange.getFromEndpoint().toString());
				transaction
						.setInboundURL(exchange.getFromEndpoint().toString());
				transaction.setTransactionId(exchange.getProperty(
						"TransactionId").toString());
				transaction.setPayload(msgBody);*/
				
				list.add(transaction);
				System.out
						.println(exchange.getProperty(IConstant.SOURCE_NAME).toString()+"***************&&&&&&&&&&&&&&&&&&&&&&*********************"
								+ copy.getDataArea().toString());
				mongoTemplate.save(transaction);
			} catch (Exception ex) {
				System.out
						.println("**************)))))))))))))))))))))))&&&&&&&&&&&&&&&&*********************"
								+ copy.getDataArea().toString());

			}
			
			System.out.println("LIST SIZE 2--------------------" + list.size());
			
			if(exchange.getProperty(IConstant.SOURCE_NAME).toString().equals("KORE"))
			{
				System.out.println("LIST SIZE ------------------------" + list.size());
			exchange.getIn().setBody(list);
			}

		}

	}

}
