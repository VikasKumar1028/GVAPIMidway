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
package com.gv.midway.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gv.midway.processor.DeviceInformationStubProcessor;
import com.gv.midway.processor.KoreProcessor;
import com.gv.midway.processor.VerizonPostProcessor;
import com.gv.midway.processor.VerizonPostProcessor1;
import com.gv.midway.processor.VerizonPostProcessor2;
import com.gv.midway.processor.VerizonProcessor;
import com.gv.midway.service.IDeviceService;

// this static import is needed for older versions of Camel than 2.5
// import static org.apache.camel.language.simple.SimpleLanguage.simple;

/**
 * The Camel route
 * 
 * @version
 */

@Component
public class CamelRoute extends RouteBuilder {

	/*
	 * @Autowired private ActivateService activateService;
	 */

	@Autowired
	private IDeviceService iDeviceService;

	private String uriRestVerizonEndPoint = "cxfrs://bean://rsVerizonClient";
	private String uriRestKoreEndPoint = "cxfrs://bean://rsKoreClient";

	@Override
	public void configure() throws Exception {

		from("direct:verizon").process(new VerizonProcessor()).doTry()
				.to(uriRestVerizonEndPoint).doCatch(Exception.class)
				.process(new VerizonPostProcessor()).end().choice()
				//Failure flow
				.when(header("CamelHttpResponseCode").isNotEqualTo("200"))
				// setting the original message
				.process(new VerizonPostProcessor1())
				// recursive calling
				//.to("direct:verizon").to("log:input")
				//Success flow
				.otherwise()
				.process(new VerizonPostProcessor2())
				.to("log:input").end();

		/*
		 * from("direct:verizon").process(new VerizonProcessor())
		 * .doTry().to(uriRestVerizonEndPoint
		 * ).doCatch(Exception.class).unmarshal() .json(JsonLibrary.Jackson,
		 * VerizonResponse.class)
		 * 
		 * .process(new VerizonPostProcessor()).to("log:input").end();
		 */

		
        from("direct:verizonStub").process(new DeviceInformationStubProcessor())
        
        .end();

		
		from("direct:Kore").process(new KoreProcessor())
				.to(uriRestKoreEndPoint).to("log:input");

		from("direct:insertDeviceDetails")
				.bean(iDeviceService, "insertDeviceDetails").to("log:input")
				.end();

		from("direct:updateDeviceDetails")
				.bean(iDeviceService, "updateDeviceDetails").to("log:input")
				.end();

		from("direct:insertDevicesDetailsInBatch")
				.bean(iDeviceService, "insertDevicesDetailsInBatch")
				.to("log:input").end();

		from("direct:updateDevicesDetailsInBatch")
				.bean(iDeviceService, "updateDevicesDetailsInBatch")
				.to("log:input").end();

	}
}
