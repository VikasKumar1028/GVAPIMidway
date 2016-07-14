package com.gv.midway.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.utility.CommonUtil;

public class HeaderProcessor implements Processor {

	Logger log = Logger.getLogger(HeaderProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("Start:HeaderProcessor");

		// populate MidwayTransationID
		String midwayTransactionID = CommonUtil.getMidwayTransactionID();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
				midwayTransactionID);

		String derivedCarrierName = CommonUtil.getDerivedCarrierName(exchange
				.getProperty(IConstant.BSCARRIER));

		Object bs_carrier = exchange.getProperty(IConstant.BSCARRIER);
		Object sourceName = exchange.getProperty(IConstant.SOURCE_NAME);
		
		Object dateFormat = exchange.getProperty(IConstant.DATE_FORMAT);
		Object organization = exchange.getProperty(IConstant.ORGANIZATION);
		Object gv_transactionId = exchange
				.getProperty(IConstant.GV_TRANSACTION_ID);

		if (exchange.getProperty(IConstant.DATE_FORMAT) != null)

		{
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");

			try {
				Date timestampDate = formatter.parse(exchange.getProperty(
						IConstant.DATE_FORMAT).toString());

			} catch (ParseException e1) {

				exchange.setProperty(IConstant.RESPONSE_CODE,
						IResponse.INVALID_PAYLOAD);
				exchange.setProperty(IConstant.RESPONSE_STATUS,
						IResponse.ERROR_MESSAGE);
				exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
						IResponse.ERROR_DESCRIPTION_DATE__TIMESTAMP_MIDWAYDB);
				throw new MissingParameterException(
						IResponse.INVALID_PAYLOAD.toString(),
						IResponse.ERROR_DESCRIPTION_DATE__TIMESTAMP_MIDWAYDB);
			}
		}

		if (bs_carrier == null || sourceName == null || dateFormat == null
				|| organization == null || gv_transactionId == null) {

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
						.equalsIgnoreCase("KORE"))
				|| (exchange.getFromEndpoint().toString()
						.matches("(.*)reactivateDevice(.*)") && derivedCarrierName
						.equalsIgnoreCase("VERIZON"))
				|| (exchange.getFromEndpoint().toString()
						.matches("(.*)retrieveDeviceUsageHistoryCarrier(.*)") && derivedCarrierName
						.equalsIgnoreCase("KORE"))
				|| (exchange.getFromEndpoint().toString()
						.matches("(.*)getDeviceConnectionHistoryInfoDB(.*)") && derivedCarrierName
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
