package com.gv.midway.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.utility.CommonUtil;

public class HeaderProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(HeaderProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Start:HeaderProcessor");

        // populate MidwayTransationID
        String midwayTransactionID = CommonUtil.getMidwayTransactionID();
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID,
                midwayTransactionID);

       

        Object bs_carrier = exchange.getProperty(IConstant.BSCARRIER);
        Object sourceName = exchange.getProperty(IConstant.SOURCE_NAME);

        Object dateFormat = exchange.getProperty(IConstant.DATE_FORMAT);
        Object organization = exchange.getProperty(IConstant.ORGANIZATION);
        Object gv_transactionId = exchange
                .getProperty(IConstant.GV_TRANSACTION_ID);

        

        if (bs_carrier == null || sourceName == null || dateFormat == null
                || organization == null || gv_transactionId == null) {

            exchange.setProperty(IConstant.RESPONSE_CODE, "402");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Missing Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                    "Pass all the required header parameters. ");
            throw new MissingParameterException("402",
                    "Pass all the required header parameters.");

        }
        
          SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss");

            try {
                formatter.parse(dateFormat
                        .toString());

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
       
        
        String derivedCarrierName = CommonUtil.getDerivedCarrierName(exchange
                .getProperty(IConstant.BSCARRIER).toString());

        if (derivedCarrierName == null
                || ((exchange.getFromEndpoint().toString()
                        .matches("(.*)deviceConnectionStatus(.*)") || exchange
                        .getFromEndpoint().toString()
                        .matches("(.*)deviceSessionBeginEndInfo(.*)")) && IConstant.BSCARRIER_SERVICE_KORE
                        .equalsIgnoreCase(derivedCarrierName))
                || (exchange.getFromEndpoint().toString()
                        .matches("(.*)reactivateDevice(.*)") && IConstant.BSCARRIER_SERVICE_VERIZON
                        .equalsIgnoreCase(derivedCarrierName))
                || (exchange.getFromEndpoint().toString()
                        .matches("(.*)retrieveDeviceUsageHistoryCarrier(.*)") &&! IConstant.BSCARRIER_SERVICE_VERIZON
                        .equalsIgnoreCase(derivedCarrierName))
                || (exchange.getFromEndpoint().toString()
                        .matches("(.*)getDeviceConnectionHistoryInfoDB(.*)") && IConstant.BSCARRIER_SERVICE_KORE
                        .equalsIgnoreCase(derivedCarrierName))) {
            exchange.setProperty(IConstant.RESPONSE_CODE, "402");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                    "Invalid bsCarrier field value");
            throw new InvalidParameterException("402",
                    "Invalid bsCarrier field value");
        }

        LOGGER.info("derivedSourceName::" + derivedCarrierName);

        exchange.getIn().setHeader(IConstant.MIDWAY_DERIVED_CARRIER_NAME,
                derivedCarrierName);
        exchange.setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME,
                derivedCarrierName);
    }

}
