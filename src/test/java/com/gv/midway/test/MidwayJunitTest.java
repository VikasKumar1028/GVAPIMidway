/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gv.midway.test;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.device.request.DeviceDataArea;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.Cell;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.verizon.DeviceId;

public class MidwayJunitTest extends Assert {
	private AbstractApplicationContext applicationContext;
	private ProducerTemplate template;

	protected DeviceTestData testData = new DeviceTestData();

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"camel-config.xml");
		CamelContext camelContext = getCamelContext();
		template = camelContext.createProducerTemplate();
	}

	// test case for header Information-common for all API
	//@Test
	public void testHeaderInformation() throws Exception {

		Header header = new Header();
		ActivateDeviceRequest req = new ActivateDeviceRequest();

		header.setApplicationName("WEB");
		header.setTimestamp("2016-03-08T21:49:45");
		header.setOrganization("Grant Victor");
		header.setSourceName("KORE");
		header.setTransactionId("cde2131ksjd");
		header.setBsCarrier("KORE");
		header.setRegion("USA");
		
		req.setHeader(header);

		// System.out.println("Request in Junit Test:"+req);
		testData.setExpectedHeader(req);
		template.sendBody("direct:activateDevice", req);
		testData.verifyHeaderData();
	}

	// test case for device information
	//@Test
	public void testDeviceInformation() throws Exception {

		Header header = new Header();
		DeviceInformationRequest req = new DeviceInformationRequest();

		DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
		
		//deviceIds.setId("89014103277405946190");

		DeviceInformation deviceInformation = new DeviceInformation();
 		deviceInformation.setAccountName("Test");
 		
		DeviceDataArea deviceDataArea = new DeviceDataArea();
		deviceDataArea.setDevices(deviceInformation);
		
 		DeviceId deviceId = new DeviceId();
 		deviceId.setId("01");
 		deviceId.setKind("k01");
 		
 		dataArea.setDeviceId(deviceId);
 		
 		
        req.setDataArea(dataArea);
     
        req.setDataArea(dataArea);
       
 		header.setSourceName("KORE"); 				
 		 req.setHeader(header);
 		 

		// System.out.println("Request in Junit Test:"+req);
		testData.setExpectedDeviceInformation(req);
		template.sendBody("direct:deviceInformation", req);
		testData.verifyDeviceInformationData();
	}

	// test case for Activate Device
	//@Test
	public void testActivateDevice() throws Exception {

		ActivateDeviceRequest req = new ActivateDeviceRequest();
		Header header = new Header();

		ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

		ActivateDevices [] decs=new ActivateDevices[2];
		ActivateDevices adevices = new ActivateDevices();
		
		
		ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];
		 
		ActivateDeviceId deviceId= new ActivateDeviceId();
		
		deviceId.setId("89014103277405946190");
		deviceId.setKind("ghgjg");
	/*	deviceId.seteAPCode("eAPCode");*/
		

		ActivateDeviceIdArray[0] = deviceId;
		
		adevices.setDeviceIds(ActivateDeviceIdArray);
		decs[0] = adevices;
		dataArea.setDevices(decs);
		req.setDataArea(dataArea);

		header.setSourceName("KORE");
		req.setHeader(header);

		System.out.println("Request in Junit Test:" + req);
		testData.setExpectedActivateDevice(req);
		template.sendBody("direct:activateDevice", req);
		testData.verifyDeviceActivationData();

	}

	// Test Case for Deactivate Device
	//@Test
	public void testDeactivateDevice() throws Exception {

		DeactivateDeviceRequest req = new DeactivateDeviceRequest();
		Header header = new Header();

		DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();

		DeactivateDevices [] deDevices=new DeactivateDevices[1];
        DeactivateDevices ddevices = new DeactivateDevices();

        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[1];
 		 
 		DeactivateDeviceId deactivateDeviceId= new DeactivateDeviceId();
 		
 		deactivateDeviceId.setId("89014103277405946190");
 		deactivateDeviceId.setKind("ghgjg");
 		
 		DeActivateDeviceIdArray[0] = deactivateDeviceId;
 		
 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
 		deDevices[0] = ddevices;
 		dataArea.setDevices(deDevices);
 		dataArea.setFlagScrap(Boolean.FALSE);
 		req.setDataArea(dataArea);
         
  		 header.setSourceName("KORE");
  		 req.setHeader(header);
  		 

		System.out.println("Request in Junit Test:" + req);
		testData.setExpectedDeactivateDevice(req);
		template.sendBody("direct:deactivateDevice", req);
		testData.verifyDeviceDeactivationData();

	}
	
	//test case for Activate Device Response 
	//@Test
	public void testActivateDeviceResponse() throws Exception {

		Exchange exchange;
		Header header = new Header();
		ActivateDeviceRequest req = new ActivateDeviceRequest();		
		ActivateDeviceResponse activateDeviceResponse =new ActivateDeviceResponse();		
		ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();
		ActivateDeviceResponseDataArea responseDataArea =new ActivateDeviceResponseDataArea();
		
		responseDataArea.setOrderNumber("ORD001");
		activateDeviceResponse.setDataArea(responseDataArea);	
		req.setDataArea(dataArea);
		header.setSourceName("KORE");
		req.setHeader(header);

		System.out.println("Request in Junit Test:" + activateDeviceResponse);
		testData.setExpectedActivateDeviceResponse(activateDeviceResponse);
		
		//template.sendBody("direct:activateDevice", req);
		ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody(
			      "direct:activateDevice", req);
		DeviceInformationResponse  response1 =    (DeviceInformationResponse)template.requestBody(
			      "direct:deviceInformation", req);
		/*ActivateDeviceRequest request = (ActivateDeviceRequest) exchange
				.getIn().getBody(ActivateDeviceRequest.class);
		
		*/
		System.out.println("Rsponse in Junit Test:" + response);
		System.out.println("Device Information Response in Junit Test................:" + response1);
		testData.verifyDeviceActivationResponseData(response);

	}	
	
	
	//Test case for Activate callback 'Request Id'
	//@Test
	public void testActivateCallback() throws Exception {

		CallBackVerizonRequest callbackReq = new CallBackVerizonRequest();
	
		callbackReq.setRequestId("R001");
  		
		System.out.println("Request in Junit Test:" + callbackReq);
		testData.setExpectedActivateCallback(callbackReq);
		template.sendBody("direct:callbacks", callbackReq);
		testData.verifyActivateCallback();

	}
	
	//Test case for Cell 
	//@Test
	public void testCell() throws Exception{
		Cell cell =new Cell();
		cell.setEsn("ESN01");
		cell.setMdn("MDN01");
		cell.setSim("SIM01");
		System.out.println("Request in Junit Test:" + cell);
		testData.setExpectedCellInformation(cell);
		//template.sendBody("direct:cell", cell);
		testData.verifyCellInformation();
	}

	//Test case for cell Update
	//@Test
	public void testCellUpdate() throws Exception{
		Cell cell =new Cell();
		cell.setEsn("ESN01");
		cell.setMdn("MDN01");
		cell.setSim("SIM01");
		System.out.println("Request in Junit Test:" + cell);
		testData.setExpectedCellUpdateInformation(cell);
		//template.sendBody("direct:cell", cellUpdate);
		testData.verifyCellUpdateInformation();
	}

	@After
	public void tearDown() throws Exception {
		if (applicationContext != null) {
			applicationContext.stop();
		}
	}

	protected CamelContext getCamelContext() throws Exception {
		return applicationContext.getBean("camel", CamelContext.class);
	}

}