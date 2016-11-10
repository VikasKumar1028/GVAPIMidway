package com.gv.midway.test.mock.processor;

import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.session.SessionRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by ryan.tracy on 11/3/2016.
 */
public class MockConnectionInformationRequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        //Mocking the mongodb call process
        SessionRequest sessionRequest = exchange.getIn().getBody(SessionRequest.class);

        SessionRequestDataArea requestDataArea = sessionRequest.getDataArea();
        ConnectionInformationRequest connectionInformationRequest = new ConnectionInformationRequest();
        connectionInformationRequest.setHeader(sessionRequest.getHeader());
        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        DeviceId deviceId = new DeviceId();
        if ("Verizon".equalsIgnoreCase(sessionRequest.getHeader().getBsCarrier())) {
            deviceId.setId("A10000438DBBE7");
            deviceId.setKind("meid");
        } else if ("ATTJasper".equalsIgnoreCase(sessionRequest.getHeader().getBsCarrier())) {
            deviceId.setId("89011702272014211871");
            deviceId.setKind("iccid");
        }
        dataArea.setDeviceId(deviceId);
        dataArea.setEarliest(requestDataArea.getEarliest());
        dataArea.setLatest(requestDataArea.getLatest());
        connectionInformationRequest.setDataArea(dataArea);
        exchange.getIn().setBody(connectionInformationRequest);
    }
}
