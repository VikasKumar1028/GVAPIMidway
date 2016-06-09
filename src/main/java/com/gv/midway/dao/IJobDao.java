package com.gv.midway.dao;

import java.util.List;

import org.apache.camel.Exchange;

public interface IJobDao {

	public List fetchDevices(Exchange exchange);

	public void insertJobDetails(Exchange exchange);

	public void updateJobDetails(Exchange exchange);
	
	public void deleteDeviceUsageRecords(Exchange exchange);

}
