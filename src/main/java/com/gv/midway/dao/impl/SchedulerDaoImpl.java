package com.gv.midway.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;

@Service
public class SchedulerDaoImpl implements ISchedulerDao {

    private static final Logger LOGGER = Logger.getLogger(SchedulerDaoImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveDeviceConnectionHistory(DeviceConnection deviceConnection) {

        mongoTemplate.insert(deviceConnection);

    }

    @Override
    public void saveDeviceUsageHistory(DeviceUsage deviceUsage) {

        mongoTemplate.insert(deviceUsage);

    }

}
