package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.pojo.BaseRequest;
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
				.getProperty(IConstant.BSCARRIER));
		
		Object bs_carrier=exchange.getProperty(IConstant.BSCARRIER);
		Object sourceName=exchange.getProperty(IConstant.SOURCE_NAME);
		Object applicationName=exchange.getProperty(IConstant.APPLICATION_NAME);
		Object region=exchange.getProperty(IConstant.REGION);
		Object dateFormat=exchange.getProperty(IConstant.DATE_FORMAT);
		Object organization=exchange.getProperty(IConstant.ORGANIZATION);
		Object gv_transactionId=exchange.getProperty(IConstant.GV_TRANSACTION_ID);
		
		if(bs_carrier==null||sourceName==null||applicationName==null||region==null||dateFormat==null||
				organization==null||gv_transactionId==null
				)
		{
			
			exchange.setProperty(IConstant.RESPONSE_CODE, "402");
			exchange.setProperty(IConstant.RESPONSE_STATUS, "Missing Parameter");
			exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
					"Pass all the required header parameters. ");
			throw new MissingParameterException("402",
					"Pass all the required header parameters.");
			
		}

		if (derivedCarrierName == null
				|| ((exchange.getFromEndpoint().toString()
						.matches("(.*)deviceConnectionStatus(.*)") || exchange
						.getFromEndpoint().toString()
						.matches("(.*)deviceSessionBeginEndInfo(.*)")) && derivedCarrierName
						.equalsIgnoreCase("KORE"))||(exchange.getFromEndpoint().toString()
								.matches("(.*)reactivateDevice(.*)") && derivedCarrierName
								.equalsIgnoreCase("VERIZON"))||(exchange.getFromEndpoint().toString()
										.matches("(.*)retrieveDeviceUsageHistory(.*)") && derivedCarrierName
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
