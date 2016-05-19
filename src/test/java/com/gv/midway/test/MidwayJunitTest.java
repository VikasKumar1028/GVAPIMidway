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
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletContext;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponseDataArea;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequestDataArea;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponse;
import com.gv.midway.pojo.changeDeviceServicePlans.response.ChangeDeviceServicePlansResponseDataArea;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequestDataArea;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponse;
import com.gv.midway.pojo.customFieldsDevice.response.CustomFieldsDeviceResponseDataArea;
import com.gv.midway.pojo.deactivateDevice.kore.request.DeactivateDeviceRequestKore;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponse;
import com.gv.midway.pojo.deactivateDevice.response.DeactivateDeviceResponseDataArea;
import com.gv.midway.pojo.deactivateDevice.verizon.request.DeactivateDeviceRequestVerizon;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequestDataArea;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponse;
import com.gv.midway.pojo.reActivateDevice.response.ReactivateDeviceResponseDataArea;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequestDataArea;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponse;
import com.gv.midway.pojo.restoreDevice.response.RestoreDeviceResponseDataArea;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequestDataArea;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponse;
import com.gv.midway.pojo.suspendDevice.response.SuspendDeviceResponseDataArea;
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;
import com.gv.midway.processor.activateDevice.KoreActivateDevicePostProcessor;
import com.gv.midway.service.ISessionService;



    public class MidwayJunitTest extends Assert {
	private AbstractApplicationContext applicationContext;
	private ProducerTemplate template;
    private MockServletContext sc;
	
	
	Logger log = Logger.getLogger(MidwayJunitTest.class
			.getName());

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"camel-config.xml");
		 sc = new MockServletContext("camel-config.xml");
		//sc.setAttribute(ContextLoader.CONFIG_LOCATION_PARAM,"");
		sc.setAttribute(IConstant.VZ_SEESION_TOKEN, "1d1f8e7a-c8bb-4f3c-a924-cf612b562425");
		sc.setAttribute(IConstant.VZ_AUTHORIZATION_TOKEN, "89ba225e1438e95bd05c3cc288d3591");

		
		ISessionService sessionService =  (ISessionService) applicationContext.getBean("iSessionService");
		sessionService.setServletContext(sc);
		CamelContext camelContext = getCamelContext();

		template = camelContext.createProducerTemplate();
	}

	// 1.Test Activate device API for Kore with correct data.
	       @Test
			public void testActivateDeviceRequestKoreWithValiData() throws Exception {

				ActivateDeviceRequest req = new ActivateDeviceRequest();
				Header header = new Header();

				ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();
				ActivateDevices [] decs=new ActivateDevices[1];
				ActivateDevices adevices = new ActivateDevices();
				
				ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];
				 
				ActivateDeviceId deviceId= new ActivateDeviceId();
				
				deviceId.setId("89014103277405947099");
				ActivateDeviceIdArray[0] = deviceId;
				
				adevices.setDeviceIds(ActivateDeviceIdArray);
				decs[0] = adevices;
				dataArea.setDevices(decs);
				
				req.setDataArea(dataArea);
				dataArea.setAccountName("0442090022-00001");
				dataArea.seteAPCode("EAP02500797");
				dataArea.setMdnZipCode("38523");
				dataArea.setServicePlan("M2MPERMB");
				
				req.setDataArea(dataArea);
			 	
		 		header.setSourceName("NetSuit");
				header.setTransactionId("gv123666");
		 		header.setBsCarrier("KORE");
		 		header.setApplicationName("WEB");
		 		header.setOrganization("Grant Victor");
		        header.setRegion("USA");
		        header.setTimestamp("string");
		        req.setHeader(header);
				
				ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody("direct:activateDevice", req);
				
				log.info("Activate Device response is...................:"+response.getResponse().getResponseCode()+"---Response Data area is..:"+response.getDataArea().getOrderNumber());
				
				 assertEquals(response.getResponse().getResponseCode().toString(),"2000");
				 assertEquals(response.getResponse().getResponseStatus().toString(),"Success");
				 assertNotNull(response.getDataArea().getOrderNumber());	
			}
			
		// 12.Test Activate device API for Verizon with correct data.	   	
			@Test
				public void testActivateDeviceRequestVerizonWithValidData() throws Exception {

					ActivateDeviceRequest req = new ActivateDeviceRequest();
					Header header = new Header();

					ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();
                   
					ActivateDevices [] decs=new ActivateDevices[1];
					ActivateDevices adevices = new ActivateDevices();
					
					ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[2];
					 
					ActivateDeviceId deviceId1= new ActivateDeviceId();
					ActivateDeviceId deviceId2= new ActivateDeviceId();
				
					deviceId1.setId("89148000002034203015");
					deviceId1.setKind("ICCID");
					ActivateDeviceIdArray[0] = deviceId1;
					
					deviceId2.setId("353238061040837");
					deviceId2.setKind("IMEI");
					ActivateDeviceIdArray[1] = deviceId2;
					
					adevices.setDeviceIds(ActivateDeviceIdArray);
					decs[0] = adevices;
					dataArea.setDevices(decs);
					
					dataArea.setAccountName("0442090022-00001");
					dataArea.setMdnZipCode("38523");
					dataArea.setServicePlan("M2MPERMB");
					
					req.setDataArea(dataArea);
				 	
			 		header.setSourceName("NetSuit");
					header.setTransactionId("gv123666");
			 		header.setBsCarrier("VERIZON");
			 		header.setApplicationName("WEB");
			 		header.setOrganization("Grant Victor");
			        header.setRegion("USA");

			        req.setHeader(header);
					ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody("direct:activateDevice", req);
					
					log.info("Activate Device response is...................:"+(response.getResponse().getResponseCode()));
					
					 assertEquals(response.getResponse().getResponseCode().toString(),"2000");
					 assertEquals(response.getResponse().getResponseStatus().toString(),"Success");
					 assertNotNull(response.getDataArea().getOrderNumber());	
				}
			
		         	// 3. Test Activate Device API for Verizon with invalid data.By Passing wrong Id value and blank kind value for device Id.
					@Test
							public void testActivateDeviceRequestVerizonWithInvalidData() throws Exception {

								ActivateDeviceRequest req = new ActivateDeviceRequest();
								Header header = new Header();

								ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();
									
								ActivateDevices [] decs=new ActivateDevices[1];
								ActivateDevices adevices = new ActivateDevices();
								
								ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];
								 
								ActivateDeviceId deviceId1= new ActivateDeviceId();
								//ActivateDeviceId deviceId2= new ActivateDeviceId();
								
								//deviceId1.setId("89148000002034203015");
								deviceId1.setId("353238061040");
								deviceId1.setKind("");
								ActivateDeviceIdArray[0] = deviceId1;
								
								
								
								/*deviceId2.setId("353238061040837");
								deviceId2.setKind("IMEI");
								ActivateDeviceIdArray[1] = deviceId2;*/
								
								adevices.setDeviceIds(ActivateDeviceIdArray);
								decs[0] = adevices;
								dataArea.setDevices(decs);
								
								//dataArea.setAccountName("0442090022-00001");
								//dataArea.seteAPCode("EAP02500797");
								dataArea.setMdnZipCode("38523");
								dataArea.setServicePlan("M2MPERMB");
								
								req.setDataArea(dataArea);
							 	
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");

						        req.setHeader(header);
								ActivateDeviceResponse response = (ActivateDeviceResponse) template.requestBody("direct:activateDevice", req);
								
								log.info("Activate Device response Order No. is...................:"+(response.getDataArea().getOrderNumber()));
								
								assertEquals(response.getResponse().getResponseCode().toString(),"400");
								assertEquals(response.getResponse().getResponseStatus(),"Error");
								assertNull(response.getDataArea().getOrderNumber());
							}
	                          	/*..................test cases for Activate Device ends here ..............*/
		
		
                        /*..................test cases for Deactivate Device starts here ..............*/
	
					//4. Test Case for Deactivate Device for Kore with correct data
	@Test
	public void testDeactivateDeviceRequestKoreWithValidData() throws Exception {

		DeactivateDeviceRequestKore req = new DeactivateDeviceRequestKore();
		Header header = new Header();
       
		DeactivateDeviceRequestKore deactivateDeviceRequest = new DeactivateDeviceRequestKore();
		DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
		
		DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
		
		DeactivateDevices [] deDevices=new DeactivateDevices[1];
        DeactivateDevices ddevices = new DeactivateDevices();

        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[1];
 		 
 		DeactivateDeviceId deactivateDeviceId= new DeactivateDeviceId();
 		
 		deactivateDeviceId.setId("8901260761246107349");
 	
 		deactivateDeviceId.setFlagScrap(Boolean.FALSE);
 		
 		DeActivateDeviceIdArray[0] = deactivateDeviceId;
 		
 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
 		deDevices[0] = ddevices;
 		dataArea.setDevices(deDevices);
 		dataArea.setAccountName("0442090022-00001");
 		dataArea.setServicePlan("M2MPERMB");
 	
 		header.setSourceName("NetSuit");
		header.setTransactionId("gv123666");
 		header.setBsCarrier("KORE");
 		header.setApplicationName("WEB");
 		header.setOrganization("Grant Victor");
        header.setRegion("USA");
 		req.setFlagScrap(false);
		
 		deactivateRequest.setHeader(header);
		deactivateRequest.setDataArea(dataArea);
		DeactivateDeviceResponse response = (DeactivateDeviceResponse) template.requestBody("direct:deactivateDevice", deactivateRequest);
		
		log.info("Response in Junit Test for Deactivate....... :" + response.getResponse().getResponseCode());
		 assertEquals(response.getResponse().getResponseCode().toString(),"2000");
		 assertEquals(response.getResponse().getResponseStatus(),"Success");
		 assertNotNull(response.getDataArea().getOrderNumber());
		}
	
	       //5.Test Case for Deactivate Device for Verizon with Correct data
			@Test
			public void testDeactivateDeviceRequestVerizonWithValidData() throws Exception {

				DeactivateDeviceRequestVerizon req = new DeactivateDeviceRequestVerizon();
				Header header = new Header();
		       
				DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
				
				DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
				
				DeactivateDevices [] deDevices=new DeactivateDevices[1];
		        DeactivateDevices ddevices = new DeactivateDevices();

		        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[2];
		 		 
		 		DeactivateDeviceId deactivateDeviceId1= new DeactivateDeviceId();
		 		DeactivateDeviceId deactivateDeviceId2= new DeactivateDeviceId();
		 	
		 		deactivateDeviceId1.setId("353238063362759");
		 		deactivateDeviceId1.setKind("IMEI");
		 		deactivateDeviceId1.setFlagScrap(Boolean.FALSE);
		 		
		 		DeActivateDeviceIdArray[0] = deactivateDeviceId1;
		 		
		 		deactivateDeviceId2.setId("89148000002377519373");
		 		deactivateDeviceId2.setKind("ICCID");
		 		deactivateDeviceId2.setFlagScrap(Boolean.FALSE);
		 		
		 		DeActivateDeviceIdArray[1] = deactivateDeviceId2;
		 		
		 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
		 		deDevices[0] = ddevices;
		 		dataArea.setDevices(deDevices);
		 		//dataArea.setAccountName("0442090022-00001");
		 		//dataArea.setServicePlan("M2MPERMB");
		 		dataArea.setReasonCode("FF");
		 	
		 		header.setSourceName("NetSuit");
				header.setTransactionId("gv123666");
		 		header.setBsCarrier("VERIZON");
		 		header.setApplicationName("WEB");
		 		header.setOrganization("Grant Victor");
		        header.setRegion("USA");
		        header.setTimestamp("string");
		 		
		 		deactivateRequest.setHeader(header);
				deactivateRequest.setDataArea(dataArea);
				DeactivateDeviceResponse response = (DeactivateDeviceResponse) template.requestBody("direct:deactivateDevice", deactivateRequest);
				
				log.info("Response in Junit Test for Deactivate :" + response);
				assertEquals(response.getResponse().getResponseCode().toString(),"2000");
				assertEquals(response.getResponse().getResponseStatus(),"Success");
				assertNotNull(response.getDataArea().getOrderNumber());
				
			}
			
			//6.Test Case for Deactivate Device for Verizon with InValid data.By Passing Invalid Id for Device and null reasonCode.
			@Test
			public void testDeactivateDeviceRequestVerizonWithInvalidData() throws Exception {

				DeactivateDeviceRequestVerizon req = new DeactivateDeviceRequestVerizon();
				Header header = new Header();
		       
				DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();
				
				DeactivateDeviceRequest deactivateRequest =new DeactivateDeviceRequest();
				
				DeactivateDevices [] deDevices=new DeactivateDevices[1];
		        DeactivateDevices ddevices = new DeactivateDevices();

		        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[2];
		 		 
		 		DeactivateDeviceId deactivateDeviceId1= new DeactivateDeviceId();
		 		DeactivateDeviceId deactivateDeviceId2= new DeactivateDeviceId();
		 		
		 		deactivateDeviceId1.setId("353238063362759");
		 		deactivateDeviceId1.setKind("IMEI");
		 		deactivateDeviceId1.setFlagScrap(Boolean.FALSE);
		 		
		 		DeActivateDeviceIdArray[0] = deactivateDeviceId1;
		 		
		 		deactivateDeviceId2.setId("89148000002377519373");
		 		deactivateDeviceId2.setKind("ICCID");
		 		deactivateDeviceId2.setFlagScrap(Boolean.FALSE);
		 		
		 		DeActivateDeviceIdArray[1] = deactivateDeviceId2;
		 		
		 		ddevices.setDeviceIds(DeActivateDeviceIdArray);
		 		deDevices[0] = ddevices;
		 		dataArea.setDevices(deDevices);
		 		//dataArea.setAccountName("0442090022-00001");
		 		//dataArea.setReasonCode("FF");
		 		header.setSourceName("NetSuit");
				header.setTransactionId("gv123666");
		 		header.setBsCarrier("VERIZON");
		 		header.setApplicationName("WEB");
		 		header.setOrganization("Grant Victor");
		        header.setRegion("USA");
		        header.setTimestamp("string");
		 		
		 		deactivateRequest.setHeader(header);
				deactivateRequest.setDataArea(dataArea);
				DeactivateDeviceResponse response = (DeactivateDeviceResponse) template.requestBody("direct:deactivateDevice", deactivateRequest);
			
				log.info("Response in Junit Test for Deactivate :" + response.getDataArea());
				assertEquals(response.getResponse().getResponseCode().toString(),"400");
				assertEquals(response.getResponse().getResponseStatus(),"Error");
				assertNull(response.getDataArea());
			}
			/*..................test cases for Deactivate Device ends here ..............*/		
			
			/*..................test cases for Reactivate Device starts here ..............*/
			
			//7.test case for Reactivate Device for Kore with Correct data
			@Test
			public void testReactivateDeviceRequestKoreWithValidData() throws Exception {

				ReactivateDeviceRequest req = new ReactivateDeviceRequest();
				Header header = new Header();

				ReactivateDeviceRequestDataArea dataArea = new ReactivateDeviceRequestDataArea();
			
				Devices [] deDevices=new Devices[1];
				Devices ddevices = new Devices();

		        DeviceId[] reactivateDeviceIdArray = new DeviceId[1];
		 		 
		 		DeviceId reactivateDeviceId= new DeviceId();
		 		
		 		reactivateDeviceId.setId("89014103277405946190");
		 		reactivateDeviceId.setKind("");
		 		
		 		reactivateDeviceIdArray[0] = reactivateDeviceId;
		 		
		 		ddevices.setDeviceIds(reactivateDeviceIdArray);
		 		deDevices[0] = ddevices;
		 		dataArea.setDevices(deDevices);
		 		req.setDataArea(dataArea);
		 		
		 		header.setSourceName("NetSuit");
				header.setTransactionId("gv123666");
		 		header.setBsCarrier("KORE");
		 		header.setApplicationName("WEB");
		 		header.setOrganization("Grant Victor");
		        header.setRegion("USA");
		        header.setTimestamp("string");
		  		req.setHeader(header);
		  		
		  		log.info("Request in Junit Test:" + req);
				
				ReactivateDeviceResponse response = (ReactivateDeviceResponse) template.requestBody("direct:reactivateDevice", req);
				
				log.info("Response in Junit Test for Reactive....... :" + response.getResponse().getResponseCode());
				assertEquals(response.getResponse().getResponseCode().toString(),"2000");
				assertEquals(response.getResponse().getResponseStatus(),"Success");
				assertNotNull(response.getDataArea().getOrderNumber());
			}
			
			
			/*..................test cases for Reactivate Device ends here ..............*/
			
			/*..................test cases for Suspend Device starts here ..............*/
			
			//8. Test Case for Suspended Device Kore with valid data
					@Test
					public void testSuspendDeviceRequestKoreWithValidData() throws Exception {

						SuspendDeviceRequest req = new SuspendDeviceRequest();
						Header header = new Header();

						SuspendDeviceRequestDataArea dataArea = new SuspendDeviceRequestDataArea();
					
						Devices [] deDevices=new Devices[1];
						Devices ddevices = new Devices();

				        DeviceId[] suspendDeviceIdArray = new DeviceId[1];
				 		 
				 		DeviceId suspendDeviceId= new DeviceId();
				 		
				 		suspendDeviceId.setId("89014103277405946190");
				 		
				 		suspendDeviceIdArray[0] = suspendDeviceId;
				 		
				 		ddevices.setDeviceIds(suspendDeviceIdArray);
				 		deDevices[0] = ddevices;
				 		dataArea.setDevices(deDevices);
				 		dataArea.setAccountName("0442090022-00001");
				 		req.setDataArea(dataArea);
				         
				 		header.setSourceName("NetSuit");
						header.setTransactionId("gv123666");
				 		header.setBsCarrier("KORE");
				 		header.setApplicationName("WEB");
				 		header.setOrganization("Grant Victor");
				        header.setRegion("USA");
				        header.setTimestamp("string");
				  		req.setHeader(header);		  		
						
				  		SuspendDeviceResponse response = (SuspendDeviceResponse) template.requestBody("direct:suspendDevice", req);
						
				  		log.info("Response in Junit Test for Suspend....... :" + response.getResponse().getResponseCode());
						assertEquals(response.getResponse().getResponseCode().toString(),"2000");
						assertEquals(response.getResponse().getResponseStatus(),"Success");
						assertNotNull(response.getDataArea().getOrderNumber());

					}
					//9. Test Case for Suspended Device Verizon with valid data
					@Test
					public void testSuspendDeviceRequestVerizonWithValidData() throws Exception {

						SuspendDeviceRequest req = new SuspendDeviceRequest();
						Header header = new Header();

						SuspendDeviceRequestDataArea dataArea = new SuspendDeviceRequestDataArea();
						
						Devices [] deDevices=new Devices[1];
						Devices ddevices = new Devices();

				        DeviceId[] suspendDeviceIdArray = new DeviceId[2];
				 		 
				 		DeviceId suspendDeviceId1= new DeviceId();
				 		DeviceId suspendDeviceId2= new DeviceId();
				 		
				 		
				 		suspendDeviceId1.setId("353238063289994");
				 		suspendDeviceId1.setKind("IMEI");
				 		
				 		suspendDeviceId2.setId("89148000002377648495");
				 		suspendDeviceId2.setKind("ICCID");
				 		
				 		suspendDeviceIdArray[0] = suspendDeviceId1;
				 		suspendDeviceIdArray[1] = suspendDeviceId2;
				 		
				 		ddevices.setDeviceIds(suspendDeviceIdArray);
				 		deDevices[0] = ddevices;
				 		dataArea.setDevices(deDevices);
				 		dataArea.setAccountName("0442090022-00001");
				 		req.setDataArea(dataArea);
				         
				 		header.setSourceName("NetSuit");
						header.setTransactionId("gv123666");
				 		header.setBsCarrier("VERIZON");
				 		header.setApplicationName("WEB");
				 		header.setOrganization("Grant Victor");
				        header.setRegion("USA");
				        header.setTimestamp("string");
				  		req.setHeader(header);		  		
						
				  		SuspendDeviceResponse response = (SuspendDeviceResponse) template.requestBody("direct:suspendDevice", req);
						response.getResponse().getResponseCode();
						log.info("Response in Junit Test for Suspend....... :" + response.getResponse().getResponseCode());
						assertEquals(response.getResponse().getResponseCode().toString(),"2000");
						assertEquals(response.getResponse().getResponseStatus(),"Success");
						assertNotNull(response.getDataArea().getOrderNumber());
					}
					//10. Test Case for Suspended Device Verizon with InValid data.By passing null kind value for device.
					@Test
					public void testSuspendDeviceRequestVerizonWithInValidData() throws Exception {

						SuspendDeviceRequest req = new SuspendDeviceRequest();
						Header header = new Header();

						SuspendDeviceRequestDataArea dataArea = new SuspendDeviceRequestDataArea();
						
						Devices [] deDevices=new Devices[1];
						Devices ddevices = new Devices();

				        DeviceId[] suspendDeviceIdArray = new DeviceId[2];
				 		 
				 		DeviceId suspendDeviceId1= new DeviceId();
				 		DeviceId suspendDeviceId2= new DeviceId();
	
				 		
				 		suspendDeviceId1.setId("353238063289994");
				 		suspendDeviceId1.setKind("");
				 		
				 		suspendDeviceId2.setId("89148000002377648495");
				 		suspendDeviceId2.setKind("ICCID");
				 		
				 		suspendDeviceIdArray[0] = suspendDeviceId1;
				 		suspendDeviceIdArray[1] = suspendDeviceId2;
				 		
				 		ddevices.setDeviceIds(suspendDeviceIdArray);
				 		deDevices[0] = ddevices;
				 		dataArea.setDevices(deDevices);
				 		dataArea.setAccountName("0442090022-00001");
				 		req.setDataArea(dataArea);
				         
				 		header.setSourceName("NetSuit");
						header.setTransactionId("gv123666");
				 		header.setBsCarrier("VERIZON");
				 		header.setApplicationName("WEB");
				 		header.setOrganization("Grant Victor");
				        header.setRegion("USA");
				        header.setTimestamp("string");
				  		req.setHeader(header);		  		
						
				  		SuspendDeviceResponse response = (SuspendDeviceResponse) template.requestBody("direct:suspendDevice", req);
						response.getResponse().getResponseCode();
						log.info("Response in Junit Test for Suspend....... :" + response.getResponse().getResponseCode());
						assertEquals(response.getResponse().getResponseCode().toString(),"400");
						assertEquals(response.getResponse().getResponseStatus(),"Error");
						assertNull(response.getDataArea().getOrderNumber());
					}
	                	/*..................test cases for Suspend Device ends here ..............*/
		
					/*..................test cases for Restore Device starts here ..............*/
					// 11.Test Case for Restore Device Kore with valid data
						@Test
						public void testRestoreDeviceRequestKoreWithValidData() throws Exception {

							RestoreDeviceRequest req = new RestoreDeviceRequest();
							Header header = new Header();

							RestoreDeviceRequestDataArea dataArea = new RestoreDeviceRequestDataArea();
							
							Devices [] deDevices=new Devices[1];
							Devices ddevices = new Devices();

					        DeviceId[] restoreDeviceIdArray = new DeviceId[1];
					 		 
					 		DeviceId restoreDeviceId= new DeviceId();
					 		
					 		restoreDeviceId.setId("89014103277405946190");
					 		
					 		restoreDeviceIdArray[0] = restoreDeviceId;
					 		
					 		ddevices.setDeviceIds(restoreDeviceIdArray);
					 		deDevices[0] = ddevices;
					 		dataArea.setDevices(deDevices);
					 		dataArea.setAccountName("0442090022-00001");
					 		req.setDataArea(dataArea);
					 		
					 		header.setSourceName("NetSuit");
							header.setTransactionId("gv123666");
					 		header.setBsCarrier("KORE");
					 		header.setApplicationName("WEB");
					 		header.setOrganization("Grant Victor");
					        header.setRegion("USA");
					        header.setTimestamp("string");
					  		req.setHeader(header);
					  		
							RestoreDeviceResponse response = (RestoreDeviceResponse) template.requestBody("direct:restoreDevice", req);
							response.getResponse().getResponseCode();
							log.info("Response in Junit Test for Restore....... :" + response.getResponse().getResponseCode());
							assertEquals(response.getResponse().getResponseCode().toString(),"2000");
							assertEquals(response.getResponse().getResponseStatus(),"Success");
							assertNotNull(response.getDataArea().getOrderNumber());
							
						}

						
						// 12.Test Case for Restore Device Verizon with valid data
					    @Test
						public void testRestoreDeviceRequestVerizonWithValidData() throws Exception {

							RestoreDeviceRequest req = new RestoreDeviceRequest();
							Header header = new Header();

							RestoreDeviceRequestDataArea dataArea = new RestoreDeviceRequestDataArea();
						
							Devices [] deDevices=new Devices[1];
							Devices ddevices = new Devices();

					        DeviceId[] restoreDeviceIdArray = new DeviceId[2];
					 		 
					 		DeviceId restoreDeviceId1= new DeviceId();
					 		DeviceId restoreDeviceId2= new DeviceId();
					
					 		restoreDeviceId1.setId("353238063289994");
					 		restoreDeviceId1.setKind("IMEI");
					 		
					 		restoreDeviceId2.setId("89148000002377648495");
					 		restoreDeviceId2.setKind("ICCID");
					 		
					 		restoreDeviceIdArray[0] = restoreDeviceId1;
					 		restoreDeviceIdArray[1] = restoreDeviceId2;
					 		
					 		ddevices.setDeviceIds(restoreDeviceIdArray);
					 		deDevices[0] = ddevices;
					 		dataArea.setDevices(deDevices);
					 		dataArea.setAccountName("0442090022-00001");
					 		req.setDataArea(dataArea);
					 		
					 		header.setSourceName("NetSuit");
							header.setTransactionId("gv123666");
					 		header.setBsCarrier("VERIZON");
					 		header.setApplicationName("WEB");
					 		header.setOrganization("Grant Victor");
					        header.setRegion("USA");
					        header.setTimestamp("string");
					  		req.setHeader(header);
					  		
					  		log.info("Request in Junit Test:" + req);
							
							RestoreDeviceResponse response = (RestoreDeviceResponse) template.requestBody("direct:restoreDevice", req);
							response.getResponse().getResponseCode();
							log.info("Response in Junit Test for Rerstore....... :" + response.getResponse().getResponseCode());
							assertEquals(response.getResponse().getResponseCode().toString(),"2000");
							assertEquals(response.getResponse().getResponseStatus(),"Success");
							assertNotNull(response.getDataArea().getOrderNumber());
							
						}
						// 13.Test Case for Restore Device Verizon with Invalid data by setting accountname as null and blank kind value in device.
						@Test
							public void testRestoreDeviceRequestVerizonWithInValidData() throws Exception {

								RestoreDeviceRequest req = new RestoreDeviceRequest();
								Header header = new Header();

								RestoreDeviceRequestDataArea dataArea = new RestoreDeviceRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] restoreDeviceIdArray = new DeviceId[2];
						 		 
						 		DeviceId restoreDeviceId1= new DeviceId();
						 		DeviceId restoreDeviceId2= new DeviceId();
						 	
						 		restoreDeviceId1.setId("353238063289994");
						 		restoreDeviceId1.setKind("");
						 		
						 		restoreDeviceId2.setId("89148000002377648495");
						 		restoreDeviceId2.setKind("ICCID");
						 		
						 		
						 		restoreDeviceIdArray[0] = restoreDeviceId1;
						 		restoreDeviceIdArray[1] = restoreDeviceId2;
						 		
						 		ddevices.setDeviceIds(restoreDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		dataArea.setDevices(deDevices);
						 		//dataArea.setAccountName("0442090022-00001");
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
						  		log.info("Request in Junit Test:" + req);
								
								RestoreDeviceResponse response = (RestoreDeviceResponse) template.requestBody("direct:restoreDevice", req);
								response.getResponse().getResponseCode();
								log.info("Response in Junit Test for Rerstore....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"400");
								assertEquals(response.getResponse().getResponseStatus(),"Error");
								assertEquals(response.getDataArea().getOrderNumber(),null);
								
							}
							
							/*..................test cases for Restore Device ends here ..............*/
							
							/*..................test cases for Device Custom Fields starts here ..............*/
							
				//14.test case for Device Custom Fields for Kore with valid data
				         @Test
							public void testCustomFieldsDeviceRequestKoreWithValidData() throws Exception {

								CustomFieldsDeviceRequest req = new CustomFieldsDeviceRequest();
								Header header = new Header();

								CustomFieldsDeviceRequestDataArea dataArea = new CustomFieldsDeviceRequestDataArea();
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] customFiledsDeviceIdArray = new DeviceId[1];
						 		 
						 		DeviceId customFieldsDeviceId= new DeviceId();
						 		
						 		customFieldsDeviceId.setId("89014103277405946190");
						 	
						 		customFiledsDeviceIdArray[0] = customFieldsDeviceId;
						 		
						 		ddevices.setDeviceIds(customFiledsDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		dataArea.setDevices(deDevices);
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("KORE");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
						  		log.info("Request in Junit Test:" + req);
								
								CustomFieldsDeviceResponse response = (CustomFieldsDeviceResponse) template.requestBody("direct:customeFields", req);
								
								log.info("Response in Junit Test for Device Custom Fileds....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"2000");
								assertEquals(response.getResponse().getResponseStatus(),"Success");
								assertNotNull(response.getDataArea().getOrderNumber());
							}
							
							//15.test case for Device Custom Fields for Verizon with valid data
							@Test
							public void testCustomFieldsDeviceRequestVerizonWithValidData() throws Exception {

								CustomFieldsDeviceRequest req = new CustomFieldsDeviceRequest();
								Header header = new Header();

								CustomFieldsDeviceRequestDataArea dataArea = new CustomFieldsDeviceRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] customFiledsDeviceIdArray = new DeviceId[1];
						 		 
						 		DeviceId customFieldsDeviceId1= new DeviceId();
						 		//DeviceId customFieldsDeviceId2= new DeviceId();
						 	
						 		customFieldsDeviceId1.setId("353238063289994");
						 		customFieldsDeviceId1.setKind("IMEI");
						 								 		
						 		customFiledsDeviceIdArray[0] = customFieldsDeviceId1;
						 		//customFiledsDeviceIdArray[1] = customFieldsDeviceId2;
						 		
						 		ddevices.setDeviceIds(customFiledsDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		
						 		CustomFieldsToUpdate[]  customFieldsToUpdateArray = new CustomFieldsToUpdate[1];
						 		CustomFieldsToUpdate  customFieldsToUpdate = new CustomFieldsToUpdate();
						 		customFieldsToUpdate.setKey("CustomField1");
						 		customFieldsToUpdateArray[0]=customFieldsToUpdate;
						 		dataArea.setCustomFieldsToUpdate(customFieldsToUpdateArray);
						 		
						 		dataArea.setDevices(deDevices);
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
						  		log.info("Request in Junit Test:" + req);
								
								CustomFieldsDeviceResponse response = (CustomFieldsDeviceResponse) template.requestBody("direct:customeFields", req);
								log.info("Response in Junit Test for Custom Field....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"2000");
								assertEquals(response.getResponse().getResponseStatus(),"Success");
								assertNotNull(response.getDataArea().getOrderNumber());
							}
							
							//16.test case for Device Custom Fields for Verizon with Invalid data.
							@Test
							public void testCustomFieldsDeviceRequestVerizonWithInValidData() throws Exception {

								CustomFieldsDeviceRequest req = new CustomFieldsDeviceRequest();
								Header header = new Header();

								CustomFieldsDeviceRequestDataArea dataArea = new CustomFieldsDeviceRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] customFiledsDeviceIdArray = new DeviceId[1];
						 		 
						 		DeviceId customFieldsDeviceId1= new DeviceId();
						 		//DeviceId customFieldsDeviceId2= new DeviceId();
						 	
						 		customFieldsDeviceId1.setId("3532363289994");
						 		
						 								 		
						 		customFiledsDeviceIdArray[0] = customFieldsDeviceId1;
						 		//customFiledsDeviceIdArray[1] = customFieldsDeviceId2;
						 		
						 		ddevices.setDeviceIds(customFiledsDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		
						 		CustomFieldsToUpdate[]  customFieldsToUpdateArray = new CustomFieldsToUpdate[1];
						 		CustomFieldsToUpdate  customFieldsToUpdate = new CustomFieldsToUpdate();
						 		customFieldsToUpdate.setKey("CustomField1");
						 		customFieldsToUpdate.setValue("string");
						 		customFieldsToUpdateArray[0]=customFieldsToUpdate;
						 		dataArea.setCustomFieldsToUpdate(customFieldsToUpdateArray);
						 		
						 		dataArea.setDevices(deDevices);
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		log.info("Request in Junit Test:" + req);
								
								CustomFieldsDeviceResponse response = (CustomFieldsDeviceResponse) template.requestBody("direct:customeFields", req);
								log.info("Response in Junit Test for Custom Fields....... :" + response);
								assertEquals(response.getResponse().getResponseCode().toString(),"1905");
								assertEquals(response.getResponse().getResponseStatus(),"Error");
								assertEquals(response.getDataArea(),null);
							}
							
							/*..................test cases for Device Custom Fields ends here ..............*/
							
							/*..................test cases for change Device Service Plan starts here ..............*/
							
							//17.test case for change Device Service Plan for Kore with valid data
						    @Test
							public void testChangeDeviceServicePlanRequestKoreWithValidData() throws Exception {

								ChangeDeviceServicePlansRequest req = new ChangeDeviceServicePlansRequest();
								Header header = new Header();

								ChangeDeviceServicePlansRequestDataArea dataArea = new ChangeDeviceServicePlansRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] changeServicePlanDeviceIdArray = new DeviceId[1];
						 		 
						 		DeviceId changeServicePlanDeviceId= new DeviceId();
						 		
						 		changeServicePlanDeviceId.setId("8901260761246107398");
						 		changeServicePlanDeviceId.setKind("");
						 		
						 		changeServicePlanDeviceIdArray[0] = changeServicePlanDeviceId;
						 		
						 		ddevices.setDeviceIds(changeServicePlanDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		dataArea.setDevices(deDevices);
						 		dataArea.setAccountName("0442090022-00001");
						 		dataArea.setServicePlan("121093");
						 		dataArea.setCurrentServicePlan("M2M5MBASH");
						 		//dataArea.setPlanCode("121093");
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("KORE");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
						  		log.info("Request in Junit Test:" + req);
								
								ChangeDeviceServicePlansResponse response = (ChangeDeviceServicePlansResponse) template.requestBody("direct:changeDeviceServicePlans", req);
								
								log.info("Response in Junit Test for Device Change Service Plan....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"2000");
								assertEquals(response.getResponse().getResponseStatus(),"Success");
								assertNotNull(response.getDataArea().getOrderNumber());
							}
							
							//18.test case for change Device Service Plan for Verizon with valid data
							@Test
							public void testChangeDeviceServicePlanRequestVerizonWithValidData() throws Exception {

								ChangeDeviceServicePlansRequest req = new ChangeDeviceServicePlansRequest();
								Header header = new Header();

								ChangeDeviceServicePlansRequestDataArea dataArea = new ChangeDeviceServicePlansRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] changeServicePlanDeviceIdArray = new DeviceId[1];
						 		 
						 		DeviceId changeServicePlanDeviceId1= new DeviceId();
						 		//DeviceId changeServicePlanDeviceId2= new DeviceId();
						 		
						 		changeServicePlanDeviceId1.setId("353238063289994");
						 		changeServicePlanDeviceId1.setKind("IMEI");
						 		
						 		//changeServicePlanDeviceId2.setId("89148000002377648495");
						 		//changeServicePlanDeviceId2.setKind("ICCID");
						 		
						 		
						 		changeServicePlanDeviceIdArray[0] = changeServicePlanDeviceId1;
						 		//changeServicePlanDeviceIdArray[1] = changeServicePlanDeviceId2;
						 		
						 		ddevices.setDeviceIds(changeServicePlanDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		dataArea.setDevices(deDevices);
						 		//dataArea.setAccountName("0442090022-00001");
						 		dataArea.setServicePlan("121093");
						 		//dataArea.setCurrentServicePlan("M2M5MBASH");
						 		//dataArea.setPlanCode("121093");
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
						  		log.info("Request in Junit Test:" + req);
								
								ChangeDeviceServicePlansResponse response = (ChangeDeviceServicePlansResponse) template.requestBody("direct:changeDeviceServicePlans", req);
								
								log.info("Response in Junit Test for Device Change Service Plan....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"2000");
								assertEquals(response.getResponse().getResponseStatus(),"Success");
								assertNotNull(response.getDataArea().getOrderNumber());
								}
							
							//19.test case for change Device Service Plan for Verizon with Invalid data
							@Test
							public void testChangeDeviceServicePlanRequestVerizonWithInValidData() throws Exception {

								ChangeDeviceServicePlansRequest req = new ChangeDeviceServicePlansRequest();
								Header header = new Header();

								ChangeDeviceServicePlansRequestDataArea dataArea = new ChangeDeviceServicePlansRequestDataArea();
								
								Devices [] deDevices=new Devices[1];
								Devices ddevices = new Devices();

						        DeviceId[] changeServicePlanDeviceIdArray = new DeviceId[2];
						 		 
						 		DeviceId changeServicePlanDeviceId1= new DeviceId();
						 		DeviceId changeServicePlanDeviceId2= new DeviceId();
						 		
						 		changeServicePlanDeviceId1.setId("353238063289994");
						 							 		
						 		changeServicePlanDeviceId2.setId("89148000002377648495");
						 							 		
						 		
						 		changeServicePlanDeviceIdArray[0] = changeServicePlanDeviceId1;
						 		changeServicePlanDeviceIdArray[1] = changeServicePlanDeviceId2;
						 		
						 		ddevices.setDeviceIds(changeServicePlanDeviceIdArray);
						 		deDevices[0] = ddevices;
						 		dataArea.setDevices(deDevices);
						 		//dataArea.setAccountName("0442090022-00001");
						 		dataArea.setServicePlan("121093");
						 		dataArea.setCurrentServicePlan("M2M5MBASH");
						 		//dataArea.setPlanCode("121093");
						 		req.setDataArea(dataArea);
						 		
						 		header.setSourceName("NetSuit");
								header.setTransactionId("gv123666");
						 		header.setBsCarrier("VERIZON");
						 		header.setApplicationName("WEB");
						 		header.setOrganization("Grant Victor");
						        header.setRegion("USA");
						        header.setTimestamp("string");
						  		req.setHeader(header);
						  		
								ChangeDeviceServicePlansResponse response = (ChangeDeviceServicePlansResponse) template.requestBody("direct:changeDeviceServicePlans", req);
								
								log.info("Response in Junit Test for Device Change Service Plan....... :" + response.getResponse().getResponseCode());
								assertEquals(response.getResponse().getResponseCode().toString(),"1905");
								assertEquals(response.getResponse().getResponseStatus(),"Error");
								assertEquals(response.getDataArea(),null);
								}
							/*..................test cases for change Device Service Plan ends here ..............*/
												
							
	@After
	public void tearDown() throws Exception {
		if (applicationContext != null) {
			applicationContext.stop();
		}
	}

	protected CamelContext getCamelContext() throws Exception {
	
		return applicationContext.getBean("camel", CamelContext.class);
		//return sc.getBean("camel", CamelContext.class);
	}

}