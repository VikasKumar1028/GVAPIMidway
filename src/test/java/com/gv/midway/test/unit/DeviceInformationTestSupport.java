package com.gv.midway.test.unit;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.BaseRequest;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;

/**
 * Created by ryan.tracy on 10/20/2016.
 */
public class DeviceInformationTestSupport extends MidwayTestSupport {

    protected DeviceInformationRequest getDeviceInformationRequest() {
        DeviceId deviceId = new DeviceId();
        deviceId.setId("89014103277405947099");
        deviceId.setKind("ICCID");

        DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();
        dataArea.setDeviceId(deviceId);
        dataArea.setNetSuiteId(123456789);

        DeviceInformationRequest request = new DeviceInformationRequest();
        request.setHeader(getHeader());
        request.setDataArea(dataArea);

        return request;
    }
}
