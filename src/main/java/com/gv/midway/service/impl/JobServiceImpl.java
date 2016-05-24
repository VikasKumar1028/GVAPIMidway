package com.gv.midway.service.impl;

import java.util.List;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;

import com.gv.midway.dao.IJobDao;
import com.gv.midway.service.IJobService;

public class JobServiceImpl implements IJobService {

	@Autowired
	private IJobDao iJobDao;
	
	
	@Override
	public List fetchDevices(Exchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List insertJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List updateJobDetails(Exchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
