package com.gv.midway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.job.JobinitializedResponse;
import com.gv.midway.pojo.notification.DeviceOverageNotification;
import com.gv.midway.pojo.server.ServerDetail;
import com.gv.midway.service.IJobService;
import com.gv.midway.utility.CommonUtil;

@Service
public class JobServiceImpl implements IJobService {

	Logger log = Logger.getLogger(JobServiceImpl.class);

	@Autowired
	private IJobDao iJobDao;

	@EndpointInject(uri = "")
	ProducerTemplate producer;

	@Override
	public List fetchDevices(Exchange exchange) {

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");

		if (jobDetail.getType().toString()
				.equalsIgnoreCase(JobType.RERUN.toString())) {

			String currentServerIp = CommonUtil.getIpAddress();

			log.info("currentServerIp:::::::" + currentServerIp);

			ServerDetail serverDetail = null;
			serverDetail = iJobDao.fetchServerIp(currentServerIp);

			// Send currentServerIp and fetch serverDetail;
			// get the jobType of the serverDEtail

			if (serverDetail != null
					&& "ODD".equalsIgnoreCase(serverDetail.getJobType())) {
				return iJobDao.fetchOddDevices(exchange);
			}

			if (serverDetail != null
					&& "EVEN".equalsIgnoreCase(serverDetail.getJobType())) {
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

		// iJobDao.insertBulkRecords();

	}

	@Override
	public void updateJobDetails(Exchange exchange) {
		iJobDao.updateJobDetails(exchange);
	}

	public IJobDao getiJobDao() {
		return iJobDao;
	}

	public void setiJobDao(IJobDao iJobDao) {
		this.iJobDao = iJobDao;
	}

	public void setJobDetails(Exchange exchange, String carrierName,
			JobName jobName) {

		JobDetail jobDetail = new JobDetail();
		jobDetail.setType(JobType.NEW);
		jobDetail.setCarrierName(carrierName);
		jobDetail.setName(jobName);

		// New Job Will Run Today but for Previous day(Current -1 day) data so
		// setting the Job date to previous day not current date

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, IConstant.DURATION);
		jobDetail.setDate(dateFormat.format(cal.getTime()));

		exchange.getIn().setBody(jobDetail);

	}

	public void deleteDeviceUsageRecords(Exchange exchange) {

		JobDetail jobDetail = (JobDetail) exchange.getProperty("jobDetail");
		if (JobType.RERUN.toString().equals(jobDetail.getType().toString())) {
			iJobDao.deleteDeviceUsageRecords(exchange);
		}

	}

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
			exchange.setProperty("jobStartTime",
					verizondateFormat.format(cal.getTime()));
			cal.add(Calendar.HOUR, 24);
			exchange.setProperty("jobEndTime",
					verizondateFormat.format(cal.getTime()));

		} catch (Exception ex) {
			log.error("................Error in Setting Job Dates" + ex);
		}

	}

	@Override
	public List fetchTransactionFailureDevices(Exchange exchange) {

		return iJobDao.fetchTransactionFailureDevices(exchange);
	}

	@Override
	public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange) {

		iJobDao.deleteTransactionFailureDeviceUsageRecords(exchange);

	}

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
			log.info("Notification  :::::::::::::::::: "
					+ notification.getNetSuiteId());
		}

	}

	/**
	 * Method To Schedule the jobs through camel route
	 */
	@Override
	public void scheduleJob(Exchange exchange) {

		log.info("scheduleJob  :::::::::::::::::: ");
		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
		producer.requestBody("direct:startJob", jobDetail);

	}

}
