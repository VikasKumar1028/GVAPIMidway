package com.gv.midway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.dao.IJobDao;
import com.gv.midway.dao.impl.JobDaoImpl;
import com.gv.midway.job.JobDetail;
import com.gv.midway.service.IJobService;

@Service
public class JobServiceImpl implements IJobService {
	
	Logger log = Logger.getLogger(JobServiceImpl.class);

	@Autowired
	private IJobDao iJobDao;

	@Override
	public List fetchDevices(Exchange exchange) {
		return iJobDao.fetchDevices(exchange);
	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		iJobDao.insertJobDetails(exchange);

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

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, IConstant.DURATION);
		jobDetail.setDate(dateFormat.format(cal.getTime()));

		exchange.getIn().setBody(jobDetail);

	}

	public void deleteDeviceUsageRecords(Exchange exchange) {
		iJobDao.deleteDeviceUsageRecords(exchange);
	}
	
	/**
	 * Function is used for setting the latest Start Time and End Time For running the Job
	 * For Now we have set the verizon Api start and end time in exchange
	 * @param exchange
	 */
	public void setJobStartandEndTime(Exchange exchange) {
		
		JobDetail jobDetail = (JobDetail) exchange.getIn().getBody();
		// finding the Start and end time of Job and Setting in exchange as
		// parameter
		try {
			DateFormat verizondateFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormat.parse(jobDetail.getDate()));
			exchange.setProperty("jobStartTime",
					verizondateFormat.format(cal.getTime()));
			cal.add(Calendar.HOUR, 24);
			exchange.setProperty("jobEndTime",
					verizondateFormat.format(cal.getTime()));

		} catch (Exception ex) {
			log.error("................Error in Setting Job Dates");
		}

	}
	
}
