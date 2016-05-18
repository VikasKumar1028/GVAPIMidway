package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.service.ISchedulerService;

@Service
public class SchedulerServiceImpl implements ISchedulerService{
	
	@Autowired
	ISchedulerDao schedulerDao;
	
	Logger log = Logger.getLogger(SchedulerServiceImpl.class);

	@Override
	public void saveDeviceConnectionHistory(Exchange exchange) {
		
		schedulerDao.saveDeviceConnectionHistory(exchange);
		
	}

	@Override
	public void saveDeviceUsageHistory(Exchange exchange) {
		
		schedulerDao.saveDeviceUsageHistory(exchange);
	}

}
