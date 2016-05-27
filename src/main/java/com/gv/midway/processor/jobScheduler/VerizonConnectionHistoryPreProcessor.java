package com.gv.midway.processor.jobScheduler;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionEvent;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceEvent;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class VerizonConnectionHistoryPreProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		Map map = exchange.getIn().getBody(Map.class);
		ObjectMapper mapper = new ObjectMapper();
		ConnectionInformationResponse connectionResponse = mapper.convertValue(
				map, ConnectionInformationResponse.class);
		DeviceConnection deviceConnection = new DeviceConnection();
		deviceConnection.setCarrierName((String) exchange
				.getProperty("CarrierName"));
		deviceConnection.setDeviceId((DeviceId) exchange
				.getProperty("DeviceId"));

		ArrayList<DeviceEvent> deviceEventList = new ArrayList<DeviceEvent>();

		if (connectionResponse.getConnectionHistory() != null) {
			for (ConnectionHistory history : connectionResponse
					.getConnectionHistory()) {

				int count = 0;

				for (ConnectionEvent events : history
						.getConnectionEventAttributes()) {

					DeviceEvent event = new DeviceEvent();

					if (events.getKey().toString()
							.equalsIgnoreCase("BytesUsed")) {
						event.setBytesUsed(events.getValue());
						count++;
					} else if (events.getKey().toString()
							.equalsIgnoreCase("Event")) {
						event.setEventType(events.getValue());
						count++;
					}
					if (count == 2) {
						deviceEventList.add(event);
						break;
					}

				}
			}
			deviceConnection.setEvent(deviceEventList
					.toArray(new DeviceEvent[deviceEventList.size()]));
		} else {
			deviceConnection.setEvent(null);
		}

		deviceConnection.setTimestamp(CommonUtil.getCurrentTimeStamp());
		deviceConnection.setTransactionErrorReason(null);
		deviceConnection
				.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceConnection.setNetSuiteId((String) exchange
				.getProperty("NetSuiteId"));
		deviceConnection.setIsValid(true);

		exchange.getIn().setBody(deviceConnection);

	}

}
