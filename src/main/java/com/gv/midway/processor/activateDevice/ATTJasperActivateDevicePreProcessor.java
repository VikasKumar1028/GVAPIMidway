package com.gv.midway.processor.activateDevice;

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

import com.gv.midway.attjasper.EditTerminalRequest;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperActivateDevicePreProcessor implements Processor {

    private static final Logger LOGGER = Logger.getLogger(ATTJasperActivateDevicePreProcessor.class.getName());

    private Environment newEnv;

    public ATTJasperActivateDevicePreProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        LOGGER.info("Begin:ATTJasperActivateDevicePreProcessor");

        final Message message = exchange.getIn();
        final Transaction transaction = message.getBody(Transaction.class);
        final ActivateDeviceRequest activateDeviceRequest = (ActivateDeviceRequest)transaction.getDevicePayload();
        final String deviceId = activateDeviceRequest.getDataArea().getDevices().getDeviceIds()[0].getId();

        final ATTJasperProperties properties = EnvironmentParser.getATTJasperProperties(newEnv);

        final EditTerminalRequest editTerminalRequest = new EditTerminalRequest();
        editTerminalRequest.setIccid(deviceId);
        editTerminalRequest.setChangeType(IConstant.ATTJASPER_SIM_CHANGETYPE);
        editTerminalRequest.setTargetValue(IConstant.ATTJASPER_ACTIVATED);
        editTerminalRequest.setLicenseKey(properties.licenseKey);
        editTerminalRequest.setMessageId("" + new Date().getTime());
        editTerminalRequest.setVersion(properties.version);

        LOGGER.info("activate of iccId..............." + deviceId);

        final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(properties.username, properties.password);

        message.setBody(editTerminalRequest);
        message.setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
        message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
        message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/EditTerminal");
        message.setHeader(Header.HEADER_LIST, soapHeaders);

        exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
        exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID, transaction.getNetSuiteId());
        exchange.setPattern(ExchangePattern.InOut);

        LOGGER.info("End:ATTJasperActivateDevicePreProcessor");
    }
}