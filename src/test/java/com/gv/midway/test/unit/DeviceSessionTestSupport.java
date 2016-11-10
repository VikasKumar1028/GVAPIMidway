package com.gv.midway.test.unit;

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.session.SessionRequestDataArea;

/**
 * Created by ryan.tracy on 10/27/2016.
 */
public class DeviceSessionTestSupport extends MidwayTestSupport {

    protected SessionRequest getSessionRequest() {
        SessionRequest request = new SessionRequest();
        Header header = getHeader();
        header.setBsCarrier("Verizon");
        request.setHeader(header);
        SessionRequestDataArea dataArea = new SessionRequestDataArea();
        dataArea.setNetSuiteId(40474); //Verizon netSuiteId
        dataArea.setEarliest("2016-10-25T00:00:01Z");
        dataArea.setLatest("2016-10-26T23:59:59Z");
        request.setDataArea(dataArea);
        return request;
    }
}
