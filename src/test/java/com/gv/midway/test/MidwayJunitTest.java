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

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;


public class MidwayJunitTest extends Assert {
    private AbstractApplicationContext applicationContext;
    private ProducerTemplate template;
    
    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("camel-config.xml");
        CamelContext camelContext = getCamelContext();
        template = camelContext.createProducerTemplate();
    }
    
    @Test
    public void testCafeRoute() throws Exception {
    	DeviceInformationRequest req= new DeviceInformationRequest();
    	Header header=new Header();
    	DeviceInformationRequestDataArea dataArea=new DeviceInformationRequestDataArea();
    	req.setHeader(header);
    	req.setDataArea(dataArea);
        template.sendBody("direct:deviceInformation", req);

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