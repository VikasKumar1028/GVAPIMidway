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
	
	private Logger log = Logger.getLogger(BulkDeviceProcessor.class
			.getName());

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		log.info("******************Bulk device Processor invoked********************");
		
		List<BatchDeviceId> errorList=(List<BatchDeviceId>) exchange.getProperty(IConstant.BULK_ERROR_LIST);
		List<BatchDeviceId> successList=(List<BatchDeviceId>) exchange.getProperty(IConstant.BULK_SUCCESS_LIST);
		
		
		
		Header responseheader = new Header();

		Response response = new Response();
		
		response.setResponseCode(IResponse.SUCCESS_CODE);

		
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_CARRIER);

		responseheader.setApplicationName(exchange.getProperty(IConstant.APPLICATION_NAME).toString());
		responseheader.setRegion(exchange.getProperty(IConstant.REGION).toString());
		
		responseheader.setTimestamp(exchange.getProperty(IConstant.DATE_FORMAT).toString());
		responseheader.setOrganization(exchange.getProperty(IConstant.ORGANIZATION).toString());
		responseheader.setSourceName(exchange.getProperty(IConstant.SOURCE_NAME).toString());
	
		responseheader.setTransactionId(exchange.getProperty(IConstant.GV_TRANSACTION_ID).toString());
		responseheader.setBsCarrier(exchange.getProperty(IConstant.BSCARRIER).toString());
		
		BatchDeviceResponse batchDeviceResponse=new BatchDeviceResponse();
		
		batchDeviceResponse.setHeader(responseheader);
		batchDeviceResponse.setResponse(response);
		
		BatchDeviceResponseDataArea batchDeviceResponseDataArea=new BatchDeviceResponseDataArea();
		
		batchDeviceResponseDataArea.setFailedCount(errorList.size());
		batchDeviceResponseDataArea.setSuccessCount(successList.size());
		
		
		BatchDeviceId succesArr[] = new BatchDeviceId[successList.size()];
		
		BatchDeviceId[] succesArr1=successList.toArray(succesArr);
		
		batchDeviceResponseDataArea.setSuccessDevices(succesArr1);
		
		
		BatchDeviceId failedArr[] = new BatchDeviceId[errorList.size()];
		
		BatchDeviceId[] failedArr1=errorList.toArray(failedArr);
		
		batchDeviceResponseDataArea.setFailedDevices(failedArr1);
		
		
		
		batchDeviceResponse.setDataArea(batchDeviceResponseDataArea);
		
		
		
		log.info("batch device resposne value is........"+response.toString());
		
		log.info("batch device resposne header value is........"+responseheader.toString());
		
		log.info("batch device resposne is........"+batchDeviceResponse.toString());
		
		log.info("batch device resposne  resposne value is........"+batchDeviceResponse.getHeader().toString());
		
		log.info("batch device resposne header header value is........"+batchDeviceResponse.getResponse().toString());
		
		log.info("******************Bulk device Processor body before********************"+exchange.getIn().getBody());
		
        exchange.getIn().setBody(batchDeviceResponse);
        
        exchange.setPattern(ExchangePattern.InOut);
        
        log.info("******************Bulk device Processor body after********************"+exchange.getIn().getBody());
	}


}
