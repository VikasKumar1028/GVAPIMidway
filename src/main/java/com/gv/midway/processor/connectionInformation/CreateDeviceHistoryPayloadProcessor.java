package com.gv.midway.processor.connectionInformation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class CreateDeviceHistoryPayloadProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(CreateDeviceHistoryPayloadProcessor.class
            .getName());

    @Override
    public void process(Exchange exchange) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar cal = Calendar.getInstance();

        DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
                .getBody();
        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
        DeviceId device = new DeviceId();
        device.setId(deviceInfo.getDeviceIds()[0].getId());
        device.setKind(deviceInfo.getDeviceIds()[0].getKind());
        dataArea.setDeviceId(device);

        exchange.setProperty("DeviceId", device);

        dataArea.setLatest(dateFormat.format(cal.getTime()));
        cal.add(Calendar.HOUR, -1);
        dataArea.setEarliest(dateFormat.format(cal.getTime()));

        ObjectMapper objectMapper = new ObjectMapper();

        String strRequestBody = objectMapper.writeValueAsString(dataArea);

        exchange.getIn().setBody(strRequestBody);

        Message message = CommonUtil.setMessageHeader(exchange);

        message.setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list");

    }

}
