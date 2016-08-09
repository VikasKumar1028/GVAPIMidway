package com.gv.midway.dao;

import java.util.List;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.server.ServerDetail;

public interface IJobDao {

    public List fetchDevices(Exchange exchange);

    public List fetchOddDevices(Exchange exchange);

    public List fetchEvenDevices(Exchange exchange);

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
    
    public void insertTimeOutConnectionRecordsTransactionFailure(Exchange exchange);

}
