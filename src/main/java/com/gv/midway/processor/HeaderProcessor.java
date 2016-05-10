package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.utility.CommonUtil;

public class HeaderProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderProcessor.class.getName());

	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");

		// populate MidwayTransationID
		String midwayTransactionID = CommonUtil.getMidwayTransactionID();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
				midwayTransactionID);

		String derivedCarrierName = CommonUtil.getDerivedCarrierName(exchange
				.getProperty(IConstant.BSCARRIER).toString());

		if (derivedCarrierName == null
				|| ((exchange.getFromEndpoint().toString()
						.matches("(.*)deviceConnectionStatus(.*)") || exchange
						.getFromEndpoint().toString()
						.matches("(.*)deviceSessionBeginEndInfo(.*)")) && derivedCarrierName
						.equalsIgnoreCase("KORE"))) {
			exchange.setProperty(IConstant.RESPONSE_CODE, "402");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Invalid bsCarrier field value");
			throw new InvalidParameterException("402",
					"Invalid bsCarrier field value");
		}

		log.info("derivedSourceName::" + derivedCarrierName);

		exchange.getIn().setHeader(IConstant.MIDWAY_DERIVED_CARRIER_NAME,
				derivedCarrierName);
		exchange.setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME,
				derivedCarrierName);
	}

}
