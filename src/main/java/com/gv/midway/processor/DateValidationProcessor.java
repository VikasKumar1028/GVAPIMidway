package com.gv.midway.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;

public class DateValidationProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(DateValidationProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.info("DateValidationProcessor");
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");

        if (("Endpoint[direct://retrieveDeviceUsageHistoryCarrier]")
                .equals(exchange.getFromEndpoint().toString())) {

            UsageInformationRequest request = (UsageInformationRequest) exchange
                    .getIn().getBody(UsageInformationRequest.class);

            UsageInformationRequestDataArea usageInformationRequestDataArea = request
                    .getDataArea();

            try {

                if (usageInformationRequestDataArea.getEarliest() == null
                        && usageInformationRequestDataArea.getLatest() == null)

                {
                    exchange.setProperty(IConstant.RESPONSE_CODE,
                            IResponse.INVALID_PAYLOAD);
                    exchange.setProperty(IConstant.RESPONSE_STATUS,
                            IResponse.ERROR_MESSAGE);
                    exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                    throw new MissingParameterException(
                            IResponse.INVALID_PAYLOAD.toString(),
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);

                } else if (usageInformationRequestDataArea.getEarliest() != null
                        && usageInformationRequestDataArea.getLatest() != null) {
                    Date earliestDate = formatter
                            .parse(usageInformationRequestDataArea
                                    .getEarliest());
                    Date latestDate = formatter
                            .parse(usageInformationRequestDataArea.getLatest());

                }

            }

            catch (ParseException e1) {

                exchange.setProperty(IConstant.RESPONSE_CODE,
                        IResponse.INVALID_PAYLOAD);
                exchange.setProperty(IConstant.RESPONSE_STATUS,
                        IResponse.ERROR_MESSAGE);
                exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                throw new MissingParameterException(
                        IResponse.INVALID_PAYLOAD.toString(),
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
            }

        }

        if (("Endpoint[direct://deviceConnectionStatus]").equals(exchange
                .getFromEndpoint().toString()))

        {

            ConnectionInformationRequest request = (ConnectionInformationRequest) exchange
                    .getIn().getBody(ConnectionInformationRequest.class);

            ConnectionInformationRequestDataArea connectionInformationRequestDataArea = request
                    .getDataArea();

            try {

                if (connectionInformationRequestDataArea.getEarliest() == null
                        && connectionInformationRequestDataArea.getLatest() == null)

                {
                    exchange.setProperty(IConstant.RESPONSE_CODE,
                            IResponse.INVALID_PAYLOAD);
                    exchange.setProperty(IConstant.RESPONSE_STATUS,
                            IResponse.ERROR_MESSAGE);
                    exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                    throw new MissingParameterException(
                            IResponse.INVALID_PAYLOAD.toString(),
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);

                } else if (connectionInformationRequestDataArea.getEarliest() != null
                        && connectionInformationRequestDataArea.getLatest() != null) {
                    Date earliestDate = formatter
                            .parse(connectionInformationRequestDataArea
                                    .getEarliest());
                    Date latestDate = formatter
                            .parse(connectionInformationRequestDataArea
                                    .getLatest());
                }

            }

            catch (ParseException e1) {

                exchange.setProperty(IConstant.RESPONSE_CODE,
                        IResponse.INVALID_PAYLOAD);
                exchange.setProperty(IConstant.RESPONSE_STATUS,
                        IResponse.ERROR_MESSAGE);
                exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                throw new MissingParameterException(
                        IResponse.INVALID_PAYLOAD.toString(),
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
            }

        }

        if (("Endpoint[direct://deviceSessionBeginEndInfo]").equals(exchange
                .getFromEndpoint().toString())) {

            ConnectionInformationRequest request = (ConnectionInformationRequest) exchange
                    .getIn().getBody(ConnectionInformationRequest.class);

            ConnectionInformationRequestDataArea connectionInformationRequestDataArea = request
                    .getDataArea();

            try {

                if (connectionInformationRequestDataArea.getEarliest() == null
                        && connectionInformationRequestDataArea.getLatest() == null)

                {
                    exchange.setProperty(IConstant.RESPONSE_CODE,
                            IResponse.INVALID_PAYLOAD);
                    exchange.setProperty(IConstant.RESPONSE_STATUS,
                            IResponse.ERROR_MESSAGE);
                    exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                    throw new MissingParameterException(
                            IResponse.INVALID_PAYLOAD.toString(),
                            IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);

                } else if (connectionInformationRequestDataArea.getEarliest() != null
                        && connectionInformationRequestDataArea.getLatest() != null) {
                    Date earliestDate = formatter
                            .parse(connectionInformationRequestDataArea
                                    .getEarliest());
                    Date latestDate = formatter
                            .parse(connectionInformationRequestDataArea
                                    .getLatest());
                }

            }

            catch (ParseException e1) {

                exchange.setProperty(IConstant.RESPONSE_CODE,
                        IResponse.INVALID_PAYLOAD);
                exchange.setProperty(IConstant.RESPONSE_STATUS,
                        IResponse.ERROR_MESSAGE);
                exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
                throw new MissingParameterException(
                        IResponse.INVALID_PAYLOAD.toString(),
                        IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
            }

        }
    }
}
