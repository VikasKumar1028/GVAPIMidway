package com.gv.midway.processor.jobScheduler;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionEvent;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionHistory;
import com.gv.midway.pojo.connectionInformation.verizon.response.ConnectionInformationResponse;
import com.gv.midway.pojo.deviceHistory.DeviceConnection;
import com.gv.midway.pojo.deviceHistory.DeviceEvent;
import com.gv.midway.pojo.job.JobDetail;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonDeviceConnectionHistoryPostProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(VerizonDeviceConnectionHistoryPostProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:VerizonDeviceConnectionHistoryPostProcessor");
		final Map map = exchange.getIn().getBody(Map.class);
		final ObjectMapper mapper = new ObjectMapper();
		final ConnectionInformationResponse connectionResponse = mapper.convertValue(map, ConnectionInformationResponse.class);
		final JobDetail jobDetail = (JobDetail) exchange.getProperty(IConstant.JOB_DETAIL);

		final ArrayList<DeviceEvent> deviceEventList = new ArrayList<>();

		final DeviceEvent[] deviceEvents;
		if (connectionResponse.getConnectionHistory() != null) {
			for (ConnectionHistory history : connectionResponse.getConnectionHistory()) {

				int count = 0;
				final DeviceEvent event = new DeviceEvent();

				for (ConnectionEvent events : history.getConnectionEventAttributes()) {

					if ("BytesUsed".equalsIgnoreCase(events.getKey())) {
						event.setBytesUsed(events.getValue());
						count++;
					} else if ("Event".equalsIgnoreCase(events.getKey())) {
						event.setEventType(events.getValue());
						count++;
					}

					if (count == 2) {
						break;
					}
				}
				event.setOccurredAt(history.getOccurredAt());
				deviceEventList.add(event);
			}
			deviceEvents = deviceEventList.toArray(new DeviceEvent[deviceEventList.size()]);
		} else {
			deviceEvents = null;
		}

		final DeviceConnection deviceConnection = new DeviceConnection();
		deviceConnection.setCarrierName((String) exchange.getProperty("CarrierName"));
		deviceConnection.setDeviceId((DeviceId) exchange.getProperty("DeviceId"));
		deviceConnection.setEvent(deviceEvents);
		deviceConnection.setDate(jobDetail.getDate()); // The Day for which Job Ran
		deviceConnection.setTransactionErrorReason(null);
		deviceConnection.setTransactionStatus(IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS);
		deviceConnection.setNetSuiteId((Integer) exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID));
		deviceConnection.setIsValid(true);
		deviceConnection.setJobId( jobDetail.getJobId());

		exchange.getIn().setBody(deviceConnection);

		LOGGER.info("End:VerizonDeviceConnectionHistoryPostProcessor");
	}
}