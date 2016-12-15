package com.gv.midway.processor.deviceInformation;


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
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceInformationPreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ATTJasperDeviceInformationPreProcessor.class.getName());

    private Environment newEnv;

    public ATTJasperDeviceInformationPreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.debug("Begin:ATT_JasperDeviceInformationPreProcessor");

        final Message message = exchange.getIn();
        final DeviceInformationRequest request = message.getBody(DeviceInformationRequest.class);

        final String iccId = request.getDataArea().getDeviceId().getId();

        final GetTerminalDetailsRequest.Iccids iccids = new GetTerminalDetailsRequest.Iccids();
        final List<String> iccIdList = iccids.getIccid();

        iccIdList.add(iccId);

        final ATTJasperProperties properties = EnvironmentParser.getATTJasperProperties(newEnv);

        final GetTerminalDetailsRequest getTerminalDetailsRequest = new GetTerminalDetailsRequest();
        getTerminalDetailsRequest.setIccids(iccids);
        getTerminalDetailsRequest.setLicenseKey(properties.licenseKey);
        getTerminalDetailsRequest.setMessageId("" + new Date().getTime());
        getTerminalDetailsRequest.setVersion(properties.version);

        LOGGER.info("size of iccId..............." + getTerminalDetailsRequest.getIccids().getIccid().size());

        final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(properties.username, properties.password);

        message.setBody(getTerminalDetailsRequest);
        message.setHeader(CxfConstants.OPERATION_NAME, "GetTerminalDetails");
        message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
        message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails");
        message.setHeader(Header.HEADER_LIST, soapHeaders);

        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, request.getDataArea().getNetSuiteId());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.debug("End:ATT_JasperDeviceInformationPreProcessor");
    }
}