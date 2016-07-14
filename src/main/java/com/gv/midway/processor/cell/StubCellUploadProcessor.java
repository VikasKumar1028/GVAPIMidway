package com.gv.midway.processor.cell;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.device.request.DeviceDataArea;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;

public class StubCellUploadProcessor implements Processor {

	Logger log = Logger.getLogger(StubCellUploadProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		log.info("StubCellUploadProcessor");
		log.info("StubCellUploadProcessor Called......................");

		UpdateDeviceResponse updateDeviceResponse = new UpdateDeviceResponse();


		DeviceInformation deviceInformation = new DeviceInformation();

		DeviceDataArea deviceDataArea = new DeviceDataArea();
		SingleDevice singleDevice = new SingleDevice();
		deviceDataArea.setDevices(deviceInformation);
		singleDevice.setDataArea(deviceDataArea);
		Header responseheader = new Header();

		Response response = new Response();


		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Record succesfully uploaded in the Midway");
		response.setResponseStatus("SUCCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("setRegion");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("VERIZON");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("VERIZON");

		updateDeviceResponse.setHeader(responseheader);
		updateDeviceResponse.setResponse(response);
	

		exchange.getIn().setBody(updateDeviceResponse);
	}

}
