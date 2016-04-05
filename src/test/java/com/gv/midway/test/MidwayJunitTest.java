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
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.gv.midway.pojo.DeviceId;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;

public class MidwayJunitTest extends Assert {
	/*private AbstractApplicationContext applicationContext;
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
	@Test
	public void testHeaderInformation() throws Exception {

		Header header = new Header();
		ActivateDeviceRequest req = new ActivateDeviceRequest();

		header.setApplicationName("WEB");
		header.setTimestamp("2016-03-08T21:49:45");
		header.setOrganization("Grant Victor");
		header.setSourceName("KORE");
		header.setTransactionId("cde2131ksjd");
		header.setBsCarrier("KORE");

		req.setHeader(header);

		// System.out.println("Request in Junit Test:"+req);
		testData.setExpectedHeader(req);
		template.sendBody("direct:activateDevice", req);
		testData.verifyHeaderData();
	}

	// test case for device information
	@Test
	public void testDeviceInformation() throws Exception {

		Header header = new Header();
		DeviceInformationRequest req = new DeviceInformationRequest();

		DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
		DeviceId deviceIds = new DeviceId();
		deviceIds.setId("89014103277405946190");

		DeviceId[] deviceIdsArray = { deviceIds };

		// dataArea.setDeviceId(deviceIdsArray);
		dataArea.setAccountName("Test");
		req.setDataArea(dataArea);

		header.setSourceName("KORE");
		req.setHeader(header);

		// System.out.println("Request in Junit Test:"+req);
		testData.setExpectedDeviceInformation(req);
		template.sendBody("direct:deviceInformation", req);
		testData.verifyDeviceInformationData();
	}

	// test case for Activate Device
	@Test
	public void testActivateDevice() throws Exception {

		ActivateDeviceRequest req = new ActivateDeviceRequest();
		Header header = new Header();

		ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

		ActivateDeviceId[] deviceId = new ActivateDeviceId[1];
		deviceId[0] = new ActivateDeviceId();
		deviceId[0].setId("89014103277405946190");
		deviceId[0].setKind("ghgjg");
		deviceId[0].seteAPCode("eAPCode");
		dataArea.setDeviceId(deviceId);

		req.setDataArea(dataArea);

		header.setSourceName("KORE");
		req.setHeader(header);

		System.out.println("Request in Junit Test:" + req);
		testData.setExpectedActivateDevice(req);
		template.sendBody("direct:activateDevice", req);
		testData.verifyDeviceActivationData();

	}

	// Test Case for Deactivate Device
	@Test
	public void testDeactivateDevice() throws Exception {

		DeactivateDeviceRequest req = new DeactivateDeviceRequest();
		Header header = new Header();

		DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();

		DeviceId[] deviceId = new DeviceId[1];
		deviceId[0] = new DeviceId();
		deviceId[0].setId("89014103277405946190");
		deviceId[0].setKind("ghgjg");

		dataArea.setDeviceId(deviceId);

		req.setDataArea(dataArea);

		header.setSourceName("KORE");
		req.setHeader(header);

		System.out.println("Request in Junit Test:" + req);
		testData.setExpectedDeactivateDevice(req);
		template.sendBody("direct:deactivateDevice", req);
		testData.verifyDeviceDeactivationData();

	}

	@After
	public void tearDown() throws Exception {
		if (applicationContext != null) {
			applicationContext.stop();
		}
	}

	protected CamelContext getCamelContext() throws Exception {
		return applicationContext.getBean("camel", CamelContext.class);
	}*/

}