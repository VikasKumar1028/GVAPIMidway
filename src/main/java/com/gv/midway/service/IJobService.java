package com.gv.midway.service;

import java.util.List;

import org.apache.camel.Exchange;

import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier;

public interface IJobService {

    public List<DeviceInformation> fetchDevices(Exchange exchange);

    public void insertJobDetails(Exchange exchange);

    public void updateJobDetails(Exchange exchange);

    public void deleteDeviceUsageRecords(Exchange exchange);

    public void deleteDeviceConnectionHistoryRecords(Exchange exchange);

    public void setJobDetails(Exchange exchange, String carrierName,
            JobName jobName,int duration,JobType jobType);

    public void setJobStartandEndTime(Exchange exchange);

    public List fetchTransactionFailureDevices(Exchange exchange);

    public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange);

    public void deleteTransactionFailureDeviceConnectionHistoryRecords(
            Exchange exchange);

    public void processDeviceNotification(Exchange exchange);

    public void addNotificationList(Exchange exchange);

    public void checkNotificationList(Exchange exchange);

    public void scheduleJob(Exchange exchange);
    
    public void checkTimeOutDevices(Exchange exchange);
    
    public void checkTimeOutDevicesTransactionFailure(Exchange exchange);
    
    public void updateDeviceUsageView(Exchange exchange);
    
    public List<DevicesUsageByDayAndCarrier> fetchDeviceUsageView(Exchange exchange);
    
    public void fetchPreviousDeviceUsageData(Exchange exchange);

}
