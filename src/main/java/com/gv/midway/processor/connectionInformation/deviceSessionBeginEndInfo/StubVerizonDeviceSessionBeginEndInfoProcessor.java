package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.DeviceSession;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse;
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponseDataArea;

public class StubVerizonDeviceSessionBeginEndInfoProcessor implements Processor{
	
	Logger log = Logger.getLogger(StubVerizonDeviceSessionBeginEndInfoProcessor.class
			.getName());
	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("Begin::StubVerizonDeviceSessionBeginEndInfoProcessor");
		
		SessionBeginEndResponse sessionBeginEndResponse = new SessionBeginEndResponse();
		
		SessionBeginEndResponseDataArea dataArea =  new SessionBeginEndResponseDataArea();
		Response response = new Response();
		
		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseDescription("Request processed successfully");
		response.setResponseStatus("SUCCESS");
		
		Header responseheader = new Header();
		
		responseheader.setApplicationName("WEB");
		responseheader.setRegion("USA");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");
		
		sessionBeginEndResponse.setHeader(responseheader);
		sessionBeginEndResponse.setResponse(response);
		
		DeviceSession[] deviceSession = new DeviceSession[2];
		deviceSession[0] = new DeviceSession();
		deviceSession[0].setBegin("2016-04-27T10:49:45");
		deviceSession[0].setEnd("2016-04-27T12:12:34");
		
		deviceSession[1] = new DeviceSession();
		deviceSession[1].setBegin("2016-04-27T16:23:45");
		deviceSession[1].setEnd("2016-04-27T22:46:12");
		
		dataArea.setDeviceSession(deviceSession);
		sessionBeginEndResponse.setDataArea(dataArea);
		

		exchange.getIn().setBody(sessionBeginEndResponse);
		
		log.info("End::StubVerizonDeviceSessionBeginEndInfoProcessor");
		
	}

}
