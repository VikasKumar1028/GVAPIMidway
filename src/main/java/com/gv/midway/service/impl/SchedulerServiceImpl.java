package com.gv.midway.service.impl;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.service.ISchedulerService;

@Service
public class SchedulerServiceImpl implements ISchedulerService {

    @Autowired
    ISchedulerDao schedulerDao;

    private static final Logger LOGGER = Logger.getLogger(SchedulerServiceImpl.class);

    @Override
    public void saveDeviceConnectionHistory(Exchange exchange) {
        LOGGER.info("SchedulerServiceImpl");

        schedulerDao.saveDeviceConnectionHistory((DeviceConnection) exchange
                .getIn().getBody());

    }

    @Override
    public void saveDeviceUsageHistory(Exchange exchange) {
        LOGGER.info("SchedulerServiceImpl");
        schedulerDao.saveDeviceUsageHistory((DeviceUsage) exchange.getIn()
                .getBody());

    }

}
