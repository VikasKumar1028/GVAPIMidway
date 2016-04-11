package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.utility.CommonUtil;

public class HeaderProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");

		// populate MidwayTransationID
		String midwayTransationID = CommonUtil.getmidwayTransationId();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
				midwayTransationID);

	}

}
