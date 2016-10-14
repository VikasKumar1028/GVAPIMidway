package com.gv.midway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.dao.IJobDao;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.notification.DeviceOverageNotification;
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier;
import com.gv.midway.service.IJobService;
import com.gv.midway.utility.CommonUtil;

@Service
public class JobServiceImpl implements IJobService {

    private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);

    @Autowired
    private IJobDao iJobDao;

    @EndpointInject(uri = "")
    ProducerTemplate producer;

    /**
     * Fetching the Device List , In Case of New Job it will look for server
     * details collection and fetch devices with odd /Even Netsuite Id In Case
     * if the Server Details are missing then it will return complete device
     * list
     */
    @Override
    public List fetchDevices(Exchange exchange) {

        List list = fetchDevicesDependingServerDetails(exchange);

        if (list != null) {

            exchange.setProperty("JobTotalCount", list.size());
        } else {
            exchange.setProperty("JobTotalCount", 0);
        }

        return list;

    }

    public List fetchDevicesDependingServerDetails(Exchange exchange) {

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

        if (jobDetail.getType().toString()
                .equalsIgnoreCase(JobType.NEW.toString())) {

            String currentServerIp = CommonUtil.getIpAddress();

            LOGGER.info("currentServerIp:::::::" + currentServerIp);

            ServerDetail serverDetail;
            serverDetail = iJobDao.fetchServerIp(currentServerIp);

            // Send the currentServerIp and fetch serverDetail
            // get the jobType of the serverDetail

            if (serverDetail != null
                    && IConstant.JOB_TYPE_ODD.equalsIgnoreCase(serverDetail
                            .getJobType())) {
                return iJobDao.fetchOddDevices(exchange);
            }

            if (serverDetail != null
                    && IConstant.JOB_TYPE_EVEN.equalsIgnoreCase(serverDetail
                            .getJobType())) {
                return iJobDao.fetchEvenDevices(exchange);
            }

        }

        return iJobDao.fetchDevices(exchange);

    }

    @Override
    public void insertJobDetails(Exchange exchange) {
        iJobDao.insertJobDetails(exchange);

        // To be removed just to populate dummy bulk data in deviceInfo and
        // device Usage

        // iJobDao.insertBulkRecords()

    }

    /**
     * Updating the Job Details on Completion
     */
    @Override
    public void updateJobDetails(Exchange exchange) {
        iJobDao.updateJobDetails(exchange);
    }

    /**
     * Getter
     * 
     * @return
     */
    public IJobDao getiJobDao() {
        return iJobDao;
    }

    /**
     * Setter
     * 
     * @return
     */
    public void setiJobDao(IJobDao iJobDao) {
        this.iJobDao = iJobDao;
    }

    /**
     * Setting the Job Details for New Job Only
     */
    @Override
    public void setJobDetails(Exchange exchange, String carrierName,
            JobName jobName,int duration,JobType jobType) {

        JobDetail jobDetail = new JobDetail();
        jobDetail.setType(jobType);
        jobDetail.setCarrierName(carrierName);
        jobDetail.setName(jobName);

        // New Job Will Run Today but for Previous day(Current -1 day) data so
        // setting the Job date to previous day not current date

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, duration);
        jobDetail.setDate(dateFormat.format(cal.getTime()));

        exchange.getIn().setBody(jobDetail);

    }

    /**
     * Deleting Records for Device Usage if the jobType is Rerun,Reason for not
     * running on New Job as it would run on two servers so in case of time lag
     * it would delete records inserted by other server
     */
    @Override
    public void deleteDeviceUsageRecords(Exchange exchange) {

        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
        if (JobType.RERUN.toString().equals(jobDetail.getType().toString())) {
            iJobDao.deleteDeviceUsageRecords(exchange);
        }

    }

    /**
     * Deleting Records for Device Connection History if the jobType is
     * Rerun,Reason for not running on New Job as it would run on two servers so
     * in case of time lag it would delete records inserted by other server
     */
    @Override
    public void deleteDeviceConnectionHistoryRecords(Exchange exchange) {
        JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
        if (JobType.RERUN.toString().equals(jobDetail.getType().toString())) {
            iJobDao.deleteDeviceConnectionHistoryRecords(exchange);
        }
    }

    /**
     * Function is used for setting the latest Start Time and End Time For
     * running the Job For Now we have set the verizon Api start and end time in
     * exchange
     * 
     * @param exchange
     */
    @Override
    public void setJobStartandEndTime(Exchange exchange) {

        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
        // finding the Start and end time of Job and Setting in exchange as
        // parameter
        try {
            DateFormat verizondateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss'Z'");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateFormat.parse(jobDetail.getDate()));
            Date jobStartTime=cal.getTime();
            jobStartTime.setSeconds(1);
            exchange.setProperty("jobStartTime",
                    verizondateFormat.format(jobStartTime));
            cal.add(Calendar.HOUR, 24);
            exchange.setProperty("jobEndTime",
                    verizondateFormat.format(cal.getTime()));

        } catch (Exception ex) {
            LOGGER.error("................Error in Setting Job Dates" + ex);
        }

    }

    /**
     * Fetching the Transactional Failure Records
     */
    @Override
    public List fetchTransactionFailureDevices(Exchange exchange) {

        List list = iJobDao.fetchTransactionFailureDevices(exchange);
        
        if (list != null) {

            exchange.setProperty("JobTotalCount", list.size());
        } else {
            exchange.setProperty("JobTotalCount", 0);
        }


        return list;
    }

    /**
     * Deleting the Transactional Failure Records for device Usage
     */
    @Override
    public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange) {

        iJobDao.deleteTransactionFailureDeviceUsageRecords(exchange);

    }

    /**
     * Deleting the Transactional Failure Records for device Connection
     */
    @Override
    public void deleteTransactionFailureDeviceConnectionHistoryRecords(
            Exchange exchange) {
        iJobDao.deleteTransactionFailureDeviceConnectionHistoryRecords(exchange);

    }

    /**
     * This Method is used for processing the device Overage notifications
     */
    @Override
    public void processDeviceNotification(Exchange exchange) {
        iJobDao.processDeviceNotification(exchange);

    }

    /**
     * This method is used to create a new notification list, so that SEDA
     * components can add device overage to this list
     */
    @Override
    public void addNotificationList(Exchange exchange) {

        exchange.setProperty("NotificationLsit",
                new ArrayList<DeviceOverageNotification>());

    }

    /**
     * This method is used to check the notification list
     */
    @Override
    public void checkNotificationList(Exchange exchange) {

        List<DeviceOverageNotification> notificationList = (List) exchange
                .getProperty("NotificationLsit");

        for (DeviceOverageNotification notification : notificationList) {
            LOGGER.info("Notification  :::::::::::::::::: "
                    + notification.getNetSuiteId());
        }

    }

    /**
     * Method To Schedule the jobs through camel route
     */
    @Override
    public void scheduleJob(Exchange exchange) {

        LOGGER.info("scheduleJob  :::::::::::::::::: ");
        JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
        producer.requestBody("direct:startJob", jobDetail);

    }

    @Override
    public void checkTimeOutDevices(Exchange exchange) {

        LOGGER.info("checkTimeOutDevices  :::::::::::::::::: ");

        List<DeviceInformation> timeOutDeviceList = (List<DeviceInformation>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        if (timeOutDeviceList.size() > 0) {

            String jobName = (String) exchange.getProperty("jobName");

            // check for usage and connection History Job

            if (jobName.endsWith("DEVICE_USAGE")) {
                LOGGER.info("insert timeOut devcies in Usage  :::::::::::::::::: ");
                iJobDao.insertTimeOutUsageRecords(exchange);
            }

            // Connection History Job
            else {
                LOGGER.info("insert timeOut devcies in Connection  :::::::::::::::::: ");
                iJobDao.insertTimeOutConnectionRecords(exchange);

            }

        }

    }

    @Override
    public void checkTimeOutDevicesTransactionFailure(Exchange exchange) {

        LOGGER.info("checkTimeOutDevices for TransactionFailure :::::::::::::::::: ");

        List<Object> timeOutDeviceList = (List<Object>) exchange
                .getProperty(IConstant.TIMEOUT_DEVICE_LIST);

        if (timeOutDeviceList.size() > 0) {

            String jobName = (String) exchange.getProperty("jobName");

            // check for usage and connection History Job

            if (jobName.endsWith("DeviceUsageJob")) {
                LOGGER.info("insert timeOut devcies in TransactionFailure Usage  :::::::::::::::::: ");
                iJobDao.insertTimeOutUsageRecordsTransactionFailure(exchange);
            }

            // Connection History Job
            else {
                LOGGER.info("insert timeOut devcies in TransactionFailure Connection  :::::::::::::::::: ");
                iJobDao.insertTimeOutConnectionRecordsTransactionFailure(exchange);

            }

        }

    }
    
    @Override
    public void updateDeviceUsageView(Exchange exchange){
        iJobDao.updateDeviceUsageView( exchange);
    }

	@Override
	public List<DevicesUsageByDayAndCarrier> fetchDeviceUsageView(
			Exchange exchange) {
		return iJobDao.fetchDeviceUsageView(exchange);

	}

}
