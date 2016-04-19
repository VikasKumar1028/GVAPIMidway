package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.utility.CommonUtil;

public class HeaderProcessor implements Processor {

	private String name = null;
	Logger log = Logger.getLogger(HeaderProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");

		// populate MidwayTransationID
		String midwayTransactionID = CommonUtil.getMidwayTransactionID();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
				midwayTransactionID);
		
		String derivedCarrierName =CommonUtil.getDerivedCarrierName(exchange.getProperty(IConstant.BSCARRIER).toString());

		System.out.println("derivedSourceName::"+derivedCarrierName);
		
		exchange.getIn().setHeader(IConstant.MIDWAY_DERIVED_CARRIER_NAME,	derivedCarrierName);
		exchange.setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME, derivedCarrierName);
	}

}
