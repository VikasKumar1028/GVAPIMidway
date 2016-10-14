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

		LOGGER.info("Begin:HeaderProcessor");

        // populate MidwayTransationID
        String midwayTransactionID = CommonUtil.getMidwayTransactionID();
        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_ID, midwayTransactionID);



        Object bs_carrier = exchange.getProperty(IConstant.BSCARRIER);
        Object sourceName = exchange.getProperty(IConstant.SOURCE_NAME);

        Object dateFormat = exchange.getProperty(IConstant.DATE_FORMAT);
        Object organization = exchange.getProperty(IConstant.ORGANIZATION);
        Object gv_transactionId = exchange.getProperty(IConstant.GV_TRANSACTION_ID);


        if (bs_carrier == null || sourceName == null || dateFormat == null || organization == null || gv_transactionId == null) {

            exchange.setProperty(IConstant.RESPONSE_CODE, "402");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Missing Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, "Pass all the required header parameters. ");
            throw new MissingParameterException("402", "Pass all the required header parameters.");

        }

          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            try {
                formatter.parse(dateFormat.toString());
            } catch (ParseException e1) {
                exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD);
                exchange.setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_MESSAGE);
                exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_DESCRIPTION_DATE__TIMESTAMP_MIDWAYDB);
                throw new MissingParameterException(
                        IResponse.INVALID_PAYLOAD.toString(),
                        IResponse.ERROR_DESCRIPTION_DATE__TIMESTAMP_MIDWAYDB);
            }

        String derivedCarrierName;
        try {
            derivedCarrierName = CommonUtil.getDerivedCarrierName(exchange.getProperty(IConstant.BSCARRIER).toString());

            String endPoint = exchange.getFromEndpoint().toString();
            boolean isFromDeviceConnectionStatusEndPoint = endPoint.matches("(.*)deviceConnectionStatus(.*)");
            boolean isFromDeviceSessionBeginInfoEndPoint = endPoint.matches("(.*)deviceSessionBeginEndInfo(.*)");
            boolean isFromReactivateDeviceEndPoint = endPoint.matches("(.*)reactivateDevice(.*)");
            boolean isFromRetrieveDeviceUsageHistoryCarrierEndPoint = endPoint.matches("(.*)retrieveDeviceUsageHistoryCarrier(.*)");
            boolean isFromGetDeviceConnectionHistoryInfoDBEndPoint = endPoint.matches("(.*)getDeviceConnectionHistoryInfoDB(.*)");

            boolean isKore = IConstant.BSCARRIER_SERVICE_KORE.equalsIgnoreCase(derivedCarrierName);
            boolean isVerizon = IConstant.BSCARRIER_SERVICE_VERIZON.equalsIgnoreCase(derivedCarrierName);
            boolean isATTJasper = IConstant.BSCARRIER_SERVICE_ATTJASPER.equalsIgnoreCase(derivedCarrierName);

            if (isKore) {
                if (isFromDeviceConnectionStatusEndPoint) {
                    throw new InvalidParameterException("402", "DeviceConnectionStatus cannot have a bsCarrier of Kore.");
                } else if (isFromDeviceSessionBeginInfoEndPoint) {
                    throw new InvalidParameterException("402", "DeviceSessionBeginInfo cannot have a bsCarrier of Kore.");
                } else if (isFromRetrieveDeviceUsageHistoryCarrierEndPoint) {
                    throw new InvalidParameterException("402", "RetrieveDeviceUsageHistoryCarrier cannot have a bsCarrier of Kore.");
                } else if (isFromGetDeviceConnectionHistoryInfoDBEndPoint) {
                    throw new InvalidParameterException("402", "GetDeviceConnectionHistoryInfoDB cannot have a bsCarrier of Kore");
                }
            } else if (isVerizon && isFromReactivateDeviceEndPoint) {
                throw new InvalidParameterException("402", "ReactivateDevice cannot have a bsCarrier of Verizon.");
            }
            
            else if(isATTJasper && isFromRetrieveDeviceUsageHistoryCarrierEndPoint){
            	throw new InvalidParameterException("402", "RetrieveDeviceUsageHistoryCarrier cannot have a bsCarrier of ATTJasper.");
            }


        } catch (InvalidParameterException ex) {
            exchange.setProperty(IConstant.RESPONSE_CODE, "402");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, ex.getMessage());
            throw ex;
        }

        LOGGER.info("derivedSourceName::" + derivedCarrierName);

        exchange.getIn().setHeader(IConstant.MIDWAY_DERIVED_CARRIER_NAME, derivedCarrierName);
        exchange.setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME, derivedCarrierName);
        
        LOGGER.info("End:HeaderProcessor");
    }

}
