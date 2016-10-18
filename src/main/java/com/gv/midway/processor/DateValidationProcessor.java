package com.gv.midway.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		LOGGER.info("Begin:DateValidationProcessor");

		final String endpoint = exchange.getFromEndpoint().toString();
		if (("Endpoint[direct://retrieveDeviceUsageHistoryCarrier]").equals(endpoint)) {
			final UsageInformationRequest request = exchange.getIn().getBody(UsageInformationRequest.class);
			final UsageInformationRequestDataArea usageInformationRequestDataArea = request.getDataArea();

			nullCheckEarliestAndLatest(exchange, usageInformationRequestDataArea.getEarliest(), usageInformationRequestDataArea.getLatest());
		}

		if (("Endpoint[direct://deviceConnectionStatus]").equals(endpoint)) {
			final ConnectionInformationRequest request = exchange.getIn().getBody(ConnectionInformationRequest.class);
			final ConnectionInformationRequestDataArea connectionInformationRequestDataArea = request.getDataArea();

			nullCheckEarliestAndLatest(exchange, connectionInformationRequestDataArea.getEarliest(), connectionInformationRequestDataArea.getLatest());
		}

		if (("Endpoint[direct://deviceSessionBeginEndInfo]").equals(endpoint)) {
			final ConnectionInformationRequest request = exchange.getIn().getBody(ConnectionInformationRequest.class);
			final ConnectionInformationRequestDataArea connectionInformationRequestDataArea = request.getDataArea();

			nullCheckEarliestAndLatest(exchange, connectionInformationRequestDataArea.getEarliest(), connectionInformationRequestDataArea.getLatest());
		}
		LOGGER.info("End:DateValidationProcessor");
	}

	private void nullCheckEarliestAndLatest(Exchange exchange, String earliest, String latest) throws MissingParameterException {
		try {
			if (earliest == null && latest == null) {
				blowUp(exchange);

			} else if (earliest != null && latest != null) {
				final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				formatter.parse(earliest);
				formatter.parse(latest);
			}
		}
		catch (ParseException e1) {
			blowUp(exchange);
		}
	}

	private void blowUp(Exchange exchange) throws MissingParameterException {
		exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD);
		exchange.setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_MESSAGE);
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
		throw new MissingParameterException(IResponse.INVALID_PAYLOAD.toString(), IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB);
	}
}