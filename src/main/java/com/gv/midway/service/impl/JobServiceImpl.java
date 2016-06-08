package com.gv.midway.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.CarrierType;
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
		jobDetail.setDate(new Date().toString());
		jobDetail.setCarrierName(carrierName);
		jobDetail.setName(jobName);
		exchange.getIn().setBody(jobDetail);

	}

}
