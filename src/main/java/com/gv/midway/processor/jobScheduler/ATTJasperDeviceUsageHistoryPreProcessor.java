package com.gv.midway.processor.jobScheduler;

import java.util.Date;
import java.util.List;

import com.gv.midway.environment.ATTJasperProperties;
import com.gv.midway.environment.EnvironmentParser;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.attjasper.GetTerminalDetailsRequest;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceUsageHistoryPreProcessor implements Processor {

    private static final Logger LOGGER = Logger
            .getLogger(ATTJasperDeviceUsageHistoryPreProcessor.class.getName());

    private Environment newEnv;

    public ATTJasperDeviceUsageHistoryPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:ATTJasperDeviceUsageHistoryPreProcessor");

        final Message message = exchange.getIn();
        final DeviceInformation deviceInfo = (DeviceInformation) message.getBody();

        final DeviceId recommendedDeviceId = CommonUtil.getRecommendedDeviceIdentifier(deviceInfo.getDeviceIds());

        final DeviceId device = new DeviceId();
        device.setId(recommendedDeviceId.getId());
        device.setKind(recommendedDeviceId.getKind());

        final GetTerminalDetailsRequest.Iccids iccids = new GetTerminalDetailsRequest.Iccids();
        final List<String> iccIdList = iccids.getIccid();
        iccIdList.add(recommendedDeviceId.getId());

        final ATTJasperProperties properties = EnvironmentParser.getATTJasperProperties(newEnv);

        final GetTerminalDetailsRequest getTerminalDetailsRequest = new GetTerminalDetailsRequest();
        getTerminalDetailsRequest.setIccids(iccids);
        getTerminalDetailsRequest.setLicenseKey(properties.licenseKey);
        getTerminalDetailsRequest.setVersion(properties.version);
        getTerminalDetailsRequest.setMessageId("" + new Date().getTime());

        LOGGER.debug("size of iccId..............." + getTerminalDetailsRequest.getIccids().getIccid().size());

        final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(properties.username, properties.password);

        message.setBody(getTerminalDetailsRequest);
        message.setHeader(CxfConstants.OPERATION_NAME, "GetTerminalDetails");
        message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
        message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails");
        message.setHeader(Header.HEADER_LIST, soapHeaders);

        exchange.setProperty("DeviceId", device);
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId());
        exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:ATTJasperDeviceUsageHistoryPreProcessor");
    }
}