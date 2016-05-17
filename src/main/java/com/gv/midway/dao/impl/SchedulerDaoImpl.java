package com.gv.midway.dao.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.history.DeviceConnection;
import com.gv.midway.pojo.history.DeviceUsage;
import com.gv.midway.utility.CommonUtil;

public class SchedulerDaoImpl implements ISchedulerDao {
	
	Logger log = Logger.getLogger(SchedulerDaoImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void saveDeviceConnectionHistory(Exchange exchange) {
		
		ConnectionHistory req = (ConnectionHistory) exchange.getIn().getBody();

		
		DeviceConnection deviceConnection = new DeviceConnection();
		deviceConnection.setDevicePayload(req);
		deviceConnection.setTimestamp(CommonUtil.getCurrentTimeStamp());
		deviceConnection.setCarrierName("");
		mongoTemplate.insert(deviceConnection);
		
	}

	@Override
	public void saveDeviceUsageHistory(Exchange exchange) {
		
		//To be changed
		DeviceUsage req = (DeviceUsage) exchange.getIn().getBody();
		
		
		DeviceUsage deviceUsage = new DeviceUsage();
		deviceUsage.setDevicePayload(req);
		deviceUsage.setTimestamp(CommonUtil.getCurrentTimeStamp());
		deviceUsage.setCarrierName("");
		mongoTemplate.insert(deviceUsage);
		
		
	}

}
