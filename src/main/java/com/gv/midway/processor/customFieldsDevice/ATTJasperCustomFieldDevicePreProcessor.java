package com.gv.midway.processor.customFieldsDevice;


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
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperCustomFieldDevicePreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(ATTJasperCustomFieldDevicePreProcessor.class.getName());

	private Environment newEnv;

	public ATTJasperCustomFieldDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:ATTJasperCustomFieldDevicePreProcessor");

		final Message message = exchange.getIn();
		final Transaction transaction = message.getBody(Transaction.class);

		final CustomFieldsDeviceRequest customFieldRequest = (CustomFieldsDeviceRequest) transaction.getDevicePayload();

		final String deviceId = customFieldRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getId();

		LOGGER.info("deviceId::::" + deviceId);

		final ATTJasperProperties properties = EnvironmentParser.getATTJasperProperties(newEnv);

		final EditTerminalRequest getEditTerminalRequest = new EditTerminalRequest();
		getEditTerminalRequest.setChangeType(CommonUtil.getAttJasperCustomField(customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getKey()));
		getEditTerminalRequest.setTargetValue(customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getValue());
		getEditTerminalRequest.setIccid(deviceId);
		getEditTerminalRequest.setLicenseKey(properties.licenseKey);
		getEditTerminalRequest.setMessageId("" + new Date().getTime());
		getEditTerminalRequest.setVersion(properties.version);

		final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(properties.username, properties.password);

		message.setBody(getEditTerminalRequest);
		message.setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
		message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
		message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/EditTerminal");
		message.setHeader(Header.HEADER_LIST, soapHeaders);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
		exchange.setProperty(IConstant.ATT_CUSTOMFIELD_TO_UPDATE, customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getKey());
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperCustomFieldDevicePreProcessor");
	}
}