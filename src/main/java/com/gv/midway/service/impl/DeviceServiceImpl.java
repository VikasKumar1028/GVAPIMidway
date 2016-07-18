package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao iDeviceDao;

   private static final Logger LOGGER = Logger.getLogger(DeviceServiceImpl.class.getName());

    @Override
    public UpdateDeviceResponse updateDeviceDetails(Exchange exchange) {

        SingleDevice device = (SingleDevice) exchange.getIn().getBody();

        return iDeviceDao.updateDeviceDetails(device);

    }

    @Override
    public DeviceInformationResponse getDeviceInformationDB(Exchange exchange) {

        DeviceInformationRequest deviceInformationRequest = (DeviceInformationRequest) exchange
                .getIn().getBody();

        LOGGER.info("device information is.........."
                + deviceInformationRequest.toString());

        return iDeviceDao.getDeviceInformationDB(deviceInformationRequest);
    }

    @Override
    public void updateDevicesDetailsBulk(Exchange exchange) {
        BulkDevices devices = (BulkDevices) exchange.getIn().getBody();

        DeviceInformation[] deviceInformationArr = devices.getDataArea()
                .getDevices();

        List<DeviceInformation> deviceInformationList = Arrays
                .asList(deviceInformationArr);

        List<BatchDeviceId> successList = new ArrayList<BatchDeviceId>();

        List<BatchDeviceId> failureList = new ArrayList<BatchDeviceId>();

        exchange.setProperty(IConstant.BULK_SUCCESS_LIST, successList);
        exchange.setProperty(IConstant.BULK_ERROR_LIST, failureList);

        LOGGER.info("******************Before doing bulk insertion using seda***************");

        exchange.getIn().setBody(deviceInformationList);

        LOGGER.info("******************In the end of bulk insert***************");

    }

    @Override
    public void setDeviceInformationDB(Exchange exchange) {
        iDeviceDao.setDeviceInformationDB(exchange);
    }

    @Override
    public void updateDeviceInformationDB(Exchange exchange) {
        iDeviceDao.updateDeviceInformationDB(exchange);
    }

    @Override
    public void bulkOperationDeviceSyncInDB(Exchange exchange) {
        iDeviceDao.bulkOperationDeviceUpload(exchange);
    }

    @Override
    public ArrayList<DeviceInformation> getAllDevices() {

        return iDeviceDao.getAllDevices();
    }

    @Override
    public UsageInformationMidwayResponse getDeviceUsageInfoDB(Exchange exchange) {
        UsageInformationMidwayRequest usageInformationMidwayRequest = (UsageInformationMidwayRequest) exchange
                .getIn().getBody();
        return iDeviceDao.getDeviceUsageInfoDB(usageInformationMidwayRequest);
    }

    @Override
    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            Exchange exchange) {

        ConnectionInformationMidwayRequest connectionInformationMidwayRequest = (ConnectionInformationMidwayRequest) exchange
                .getIn().getBody();
        return iDeviceDao
                .getDeviceConnectionHistoryInfoDB(connectionInformationMidwayRequest);
    }

}
