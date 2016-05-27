package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionEvent;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceEvent;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.service.ISchedulerService;
import com.gv.midway.utility.CommonUtil;

@Service
public class SchedulerServiceImpl implements ISchedulerService {

	@Autowired
	ISchedulerDao schedulerDao;

	Logger log = Logger.getLogger(SchedulerServiceImpl.class);

	@Override
	public void saveDeviceConnectionHistory(Exchange exchange) {
		
		
		schedulerDao.saveDeviceConnectionHistory((DeviceConnection) exchange
				.getIn().getBody());

	}

	@Override
	public void saveDeviceUsageHistory(Exchange exchange) {

		
		schedulerDao.saveDeviceUsageHistory((DeviceUsage) exchange.getIn()
				.getBody());

	}


}
