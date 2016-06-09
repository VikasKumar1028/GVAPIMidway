package com.gv.midway.service;

import java.util.List;

import org.apache.camel.Exchange;

import com.gv.midway.constant.JobName;

public interface IJobService {
	
	public List fetchDevices(Exchange exchange);

	public void insertJobDetails(Exchange exchange);

	public void updateJobDetails(Exchange exchange);
	
	public void deleteDeviceUsageRecords(Exchange exchange) ;
	
	public void setJobDetails(Exchange exchange,String carrierName,JobName jobName);

}
	

	

