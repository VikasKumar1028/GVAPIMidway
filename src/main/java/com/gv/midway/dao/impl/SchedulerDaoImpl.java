package com.gv.midway.dao.impl;

import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchedulerDaoImpl implements ISchedulerDao {

    private static final Logger LOGGER = Logger.getLogger(SchedulerDaoImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveDeviceConnectionHistory(DeviceConnection deviceConnection) {
        LOGGER.debug("saveDeviceConnectionHistory for NetSuite id......" + deviceConnection.getNetSuiteId());
        mongoTemplate.insert(deviceConnection);
    }

    @Override
    public void saveDeviceUsageHistory(DeviceUsage deviceUsage) {
        LOGGER.debug("saveDeviceUsageHistory for NetSuite id......" + deviceUsage.getNetSuiteId());
        mongoTemplate.insert(deviceUsage);
    }
}