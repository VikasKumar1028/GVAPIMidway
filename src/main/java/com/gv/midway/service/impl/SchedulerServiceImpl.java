package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.dao.ISchedulerDao;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionEvent;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceEvent;
import com.gv.midway.pojo.deviceHistory.DeviceUsage;
import com.gv.midway.pojo.usageInformation.verizon.response.UsageInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.service.ISchedulerService;
import com.gv.midway.utility.CommonUtil;

@Service
public class SchedulerServiceImpl implements ISchedulerService {

	@Autowired
	ISchedulerDao schedulerDao;

	Logger log = Logger.getLogger(SchedulerServiceImpl.class);

	@Override
	public void saveDeviceConnectionHistory(Exchange exchange) {
		
		DeviceConnection deviceConnection = deviceConnectionHistoryMapping(exchange);
		schedulerDao.saveDeviceConnectionHistory(deviceConnection);

	}

	@Override
	public void saveDeviceUsageHistory(Exchange exchange) {

		DeviceUsage deviceUsage = deviceUsageHistoryMapping(exchange);
		schedulerDao.saveDeviceUsageHistory(deviceUsage);

	}

	public DeviceUsage deviceUsageHistoryMapping(Exchange exchange){
		
		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper(); 
		UsageInformationResponse usageResponse = mapper
				.convertValue(map, UsageInformationResponse.class);
		DeviceUsage deviceUsage = new DeviceUsage();
		
		
		return deviceUsage;
	}
	public DeviceConnection deviceConnectionHistoryMapping(Exchange exchange) {

		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper(); 
		ConnectionInformationResponse connectionResponse = mapper
				.convertValue(map, ConnectionInformationResponse.class);
		DeviceConnection deviceConnection = new DeviceConnection();
		deviceConnection.setCarrierName("Verizon");
		deviceConnection.setDeviceId((DeviceId) exchange
				.getProperty("DeviceId"));

		ArrayList<DeviceEvent> deviceEventList = new ArrayList<DeviceEvent>();
		
		
		if(connectionResponse
				.getConnectionHistory() != null){
			for (ConnectionHistory history : connectionResponse
				.getConnectionHistory()) {
			
			int count = 0;
			
			for (ConnectionEvent events : history
					.getConnectionEventAttributes()) {

				DeviceEvent event = new DeviceEvent();
				
				if (events.getKey().toString().equalsIgnoreCase("BytesUsed")) {
					event.setBytesUsed(events.getValue());
					count++;
				} else if (events.getKey().toString().equalsIgnoreCase("Event")) {
					event.setEventType(events.getValue());
					count++;
				}
				if (count == 2) {
					deviceEventList.add(event);
					break;
				}

			}
		}
			deviceConnection.setEvent(deviceEventList.toArray(new DeviceEvent[deviceEventList.size()]));
		}else{
			deviceConnection.setEvent(null);
		}
		
		deviceConnection.setTimestamp(CommonUtil.getCurrentTimeStamp());
		deviceConnection.setTransactionErrorReason(null);
		deviceConnection.setTransactionStatus("Success");
		deviceConnection.setNetSuiteId("NetSuiteId");
		deviceConnection.setIsValid(true);
		return deviceConnection;
	}

}
