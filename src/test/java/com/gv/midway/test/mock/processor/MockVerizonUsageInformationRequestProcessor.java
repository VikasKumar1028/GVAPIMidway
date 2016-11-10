package com.gv.midway.test.mock.processor;

import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by ryan.tracy on 11/1/2016.
 */
public class MockVerizonUsageInformationRequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        //Mocking the mongodb call process
        SessionRequest sessionRequest = exchange.getIn().getBody(SessionRequest.class);

        UsageInformationRequestDataArea usageInformationRequestDataArea = new UsageInformationRequestDataArea();
        DeviceId deviceId = new DeviceId();
        deviceId.setId("A10000438DBBE7");
        deviceId.setKind("meid");
        usageInformationRequestDataArea.setDeviceId(deviceId);
        UsageInformationRequest usageInformationRequest = new UsageInformationRequest();
        usageInformationRequest.setHeader(sessionRequest.getHeader());
        usageInformationRequest.setDataArea(usageInformationRequestDataArea);
        exchange.getIn().setBody(usageInformationRequest);
    }
}
