package com.gv.midway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.CarrierType;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.JobName;
import com.gv.midway.constant.JobType;
import com.gv.midway.dao.IJobDao;
import com.gv.midway.job.JobDetail;
import com.gv.midway.service.IJobService;

@Service
public class JobServiceImpl implements IJobService {

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
}
