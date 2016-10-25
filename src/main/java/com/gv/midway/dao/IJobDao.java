package com.gv.midway.dao;

import java.util.List;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier;

public interface IJobDao {

    public List<DeviceInformation> fetchDevices(Exchange exchange);

    public List<DeviceInformation> fetchOddDevices(Exchange exchange);

    public List<DeviceInformation> fetchEvenDevices(Exchange exchange);

    public void insertJobDetails(Exchange exchange);

    public void updateJobDetails(Exchange exchange);

    public void deleteDeviceUsageRecords(Exchange exchange);

    public void deleteDeviceConnectionHistoryRecords(Exchange exchange);

    public List fetchTransactionFailureDevices(Exchange exchange);

    public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange);

    public void deleteTransactionFailureDeviceConnectionHistoryRecords(
            Exchange exchange);

    public ServerDetail fetchServerIp(String currentServerIp);

    public void processDeviceNotification(Exchange exchange);

    public void insertBulkRecords();

    public void insertTimeOutUsageRecords(Exchange exchange);

    public void insertTimeOutConnectionRecords(Exchange exchange);

    public void insertTimeOutUsageRecordsTransactionFailure(Exchange exchange);

    public void insertTimeOutConnectionRecordsTransactionFailure(
            Exchange exchange);

    public void getConnectionHistoryJobCounts(Exchange exchange);

    public void getDeviceUsageJobCounts(Exchange exchange);
    
    public void updateDeviceUsageView(Exchange exchange);
    
    public List<DevicesUsageByDayAndCarrier> fetchDeviceUsageView(Exchange exchange);

	public List fetchPreviousDeviceUsageDataUsed(Exchange exchange);
}
