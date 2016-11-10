package com.gv.midway.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.BatchDeviceResponse;
import com.gv.midway.pojo.device.response.BatchDeviceResponseDataArea;

public class BulkDeviceProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(BulkDeviceProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("End:BulkDeviceProcessor");

		final List<BatchDeviceId> errorList = (List<BatchDeviceId>)exchange.getProperty(IConstant.BULK_ERROR_LIST);
		final List<BatchDeviceId> successList = (List<BatchDeviceId>)exchange.getProperty(IConstant.BULK_SUCCESS_LIST);
		final Header responseHeader = (Header)exchange.getProperty(IConstant.HEADER);

		final Response response = new Response();
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVICEINFO_CARRIER);

		final BatchDeviceId[] successArr = new BatchDeviceId[successList.size()];
		final BatchDeviceId[] successArr1 = successList.toArray(successArr);

		final BatchDeviceId[] failedArr = new BatchDeviceId[errorList.size()];
		final BatchDeviceId[] failedArr1 = errorList.toArray(failedArr);

		final BatchDeviceResponseDataArea batchDeviceResponseDataArea = new BatchDeviceResponseDataArea();
		batchDeviceResponseDataArea.setFailedCount(errorList.size());
		batchDeviceResponseDataArea.setSuccessCount(successList.size());
		batchDeviceResponseDataArea.setFailedDevices(failedArr1);
		batchDeviceResponseDataArea.setSuccessDevices(successArr1);

		final BatchDeviceResponse batchDeviceResponse = new BatchDeviceResponse();
		batchDeviceResponse.setHeader(responseHeader);
		batchDeviceResponse.setResponse(response);
		batchDeviceResponse.setDataArea(batchDeviceResponseDataArea);

		LOGGER.info("batch device response value is........" + response.toString());
		LOGGER.info("batch device response header value is........" + responseHeader.toString());
		LOGGER.info("batch device response is........" + batchDeviceResponse.toString());
		LOGGER.info("batch device response  response value is........" + batchDeviceResponse.getHeader().toString());
		LOGGER.info("batch device response header header value is........" + batchDeviceResponse.getResponse().toString());
		LOGGER.info("******************Bulk device Processor body before********************" + exchange.getIn().getBody());

		exchange.getIn().setBody(batchDeviceResponse);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("******************Bulk device Processor body after********************" + exchange.getIn().getBody());
		LOGGER.info("End:BulkDeviceProcessor");
	}
}