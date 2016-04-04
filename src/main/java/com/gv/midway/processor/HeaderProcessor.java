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

		BaseRequest baseRequest = exchange.getIn()

				.getBody(BaseRequest.class);

		exchange.getIn().setHeader(IConstant.SOURCE_NAME,
				baseRequest.getHeader().getSourceName());
		
		exchange.setProperty(IConstant.BSCARRIER,
				baseRequest.getHeader().getBsCarrier());
		exchange.setProperty(IConstant.SOURCE_NAME, baseRequest.getHeader()
				.getSourceName());
		exchange.setProperty(IConstant.GV_TRANSACTION_ID, baseRequest.getHeader().getTransactionId());
		exchange.setProperty(IConstant.GV_HOSTNAME,CommonUtil.getIpAddress());
		

		
		
		
	}

}
