package com.gv.midway.dao;

import java.util.List;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.server.ServerDetail;

public interface IJobDao {

	public List fetchDevices(Exchange exchange);

	public List fetchOddDevices(Exchange exchange);

	public List fetchEvenDevices(Exchange exchange);

	public void insertJobDetails(Exchange exchange);

	public void updateJobDetails(Exchange exchange);

	public void deleteDeviceUsageRecords(Exchange exchange);

	public void deleteDeviceConnectionHistoryRecords(Exchange exchange);

	public List fetchTransactionFailureDevices(Exchange exchange);

	public void deleteTransactionFailureDeviceUsageRecords(Exchange exchange);

	public void deleteTransactionFailureDeviceConnectionHistoryRecords(
			Exchange exchange);

	public ServerDetail fetchServerIp(String currentServerIp);

}
