package com.gv.midway.processor.jobScheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;

public class VerizonDeviceHistoryPreProcessor implements Processor {

	Logger log = Logger.getLogger(VerizonDeviceHistoryPreProcessor.class
			.getName());

	public VerizonDeviceHistoryPreProcessor() {

	}

	Environment newEnv;
	
	@Override
	public void process(Exchange arg0) throws Exception {
		log.info("Begin::VerizonDeviceHistoryPreProcessor");
		
		DateFormat dateFormat = new SimpleDateFormat(
				newEnv.getProperty(IConstant.DATE_FORMAT));
		Date date = new Date();
		String earliest = dateFormat.format(date);
		String latest = null;
		
		ConnectionInformationRequest request = new ConnectionInformationRequest();
		ConnectionInformationRequestDataArea dataArea =  new ConnectionInformationRequestDataArea();
		DeviceId device = new DeviceId();
		device.setId(null);
		device.setKind(null);
		dataArea.setDeviceId(device);
		dataArea.setEarliest(null);
		dataArea.setLatest(earliest);
		
		log.info("End::VerizonDeviceHistoryPreProcessor");
		
	}

}
