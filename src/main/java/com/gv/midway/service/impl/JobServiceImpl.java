package com.gv.midway.service.impl;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.dao.IJobDao;
import com.gv.midway.service.IJobService;
@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	private IJobDao iJobDao;

	@Override
	public List fetchDevices(Exchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub

		iJobDao.insertJobDetails(exchange);

	}

	@Override
	public void updateJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		iJobDao.updateJobDetails(exchange);
	}

	public IJobDao getiJobDao() {
		return iJobDao;
	}

	public void setiJobDao(IJobDao iJobDao) {
		this.iJobDao = iJobDao;
	}

}
