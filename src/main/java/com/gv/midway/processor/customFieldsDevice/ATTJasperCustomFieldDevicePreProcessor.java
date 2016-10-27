package com.gv.midway.processor.customFieldsDevice;


import java.util.Date;
import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
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
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperCustomFieldDevicePreProcessor.class.getName());

	Environment newEnv;

	public ATTJasperCustomFieldDevicePreProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	public ATTJasperCustomFieldDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		LOGGER.info("Begin:ATTJasperCustomFieldDevicePreProcessor");

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		CustomFieldsDeviceRequest customFieldRequest = (CustomFieldsDeviceRequest) transaction
				.getDevicePayload();
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());

		String deviceId = customFieldRequest.getDataArea().getDevices()[0]
				.getDeviceIds()[0].getId();

		LOGGER.info("deviceId::::" + deviceId);
		

		EditTerminalRequest getEditTerminalRequest = new EditTerminalRequest();

		String version = newEnv.getProperty("attJasper.version");

		String licenseKey = newEnv.getProperty("attJasper.licenseKey");
		
		
		getEditTerminalRequest.setChangeType(CommonUtil.getAttJasperCustomField(customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getKey()));
		getEditTerminalRequest.setTargetValue(customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getValue());
		
		exchange.setProperty(IConstant.ATT_CUSTOMFIELD_TO_UPDATE,customFieldRequest.getDataArea().getCustomFieldsToUpdate()[0].getKey());
		
		/*LocalDateTime currentUTCTime = LocalDateTime.now(); // using system timezone
               XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(currentUTCTime.toString());
		
		getEditTerminalRequest.setEffectiveDate(xmlDate);*/
		
		getEditTerminalRequest.setIccid(deviceId);

		getEditTerminalRequest.setLicenseKey(licenseKey);
		getEditTerminalRequest.setMessageId("" + new Date().getTime());
		getEditTerminalRequest.setVersion(version);

		exchange.getIn().setBody(getEditTerminalRequest);

		exchange.getIn().setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
		exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE,
				"http://api.jasperwireless.com/ws/schema");
		exchange.getIn()
				.setHeader("soapAction",
						"http://api.jasperwireless.com/ws/service/terminal/EditTerminal");

		String username = newEnv.getProperty("attJasper.userName");

		String password = newEnv.getProperty("attJasper.password");

		List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(username,
				password);

		exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperCustomFieldDevicePreProcessor");
	}
}
