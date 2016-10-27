package com.gv.midway.processor.reactivate;


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
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperReactivateDevicePreProcessor implements Processor {
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperReactivateDevicePreProcessor.class.getName());

	Environment newEnv;

	public ATTJasperReactivateDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperReactivateDevicePreProcessor");

		Transaction transaction = exchange.getIn().getBody(Transaction.class);

		ReactivateDeviceRequest reactivateDeviceRequest = (ReactivateDeviceRequest) transaction
				.getDevicePayload();

		String deviceId = reactivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()[0].getId();
				
		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER,
				transaction.getDeviceNumber());

		EditTerminalRequest editTerminalRequest = new EditTerminalRequest();

		editTerminalRequest.setIccid(deviceId);
		/*LocalDateTime currentUTCTime = LocalDateTime.now(); // using system
															// timezone
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(currentUTCTime.toString());
		editTerminalRequest.setEffectiveDate(xmlDate);*/
		editTerminalRequest.setChangeType(IConstant.ATTJASPER_SIM_CHANGETYPE);
		editTerminalRequest.setTargetValue(IConstant.ATTJASPER_ACTIVATED);

		String version = newEnv.getProperty("attJasper.version");

		String licenseKey = newEnv.getProperty("attJasper.licenseKey");

		editTerminalRequest.setLicenseKey(licenseKey);
		editTerminalRequest.setMessageId("" + new Date().getTime());
		editTerminalRequest.setVersion(version);

		LOGGER.info("Reactivate of iccId..............." + deviceId);

		exchange.getIn().setBody(editTerminalRequest);

		exchange.getIn().setHeader(CxfConstants.OPERATION_NAME, "EditTerminal");
		exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE,
				"http://api.jasperwireless.com/ws/schema");
		exchange.getIn()
				.setHeader("soapAction",
						"http://api.jasperwireless.com/ws/service/terminal/EditTerminal");

		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID,
				transaction.getNetSuiteId());

		String username = newEnv.getProperty("attJasper.userName");

		String password = newEnv.getProperty("attJasper.password");

		List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(username,
				password);

		exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperReactivateDevicePreProcessor");
	}
}
