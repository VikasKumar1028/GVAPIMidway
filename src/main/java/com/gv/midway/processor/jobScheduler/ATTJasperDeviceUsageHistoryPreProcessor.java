package com.gv.midway.processor.jobScheduler;

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

import com.gv.midway.attjasper.GetTerminalDetailsRequest;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceUsageHistoryPreProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceUsageHistoryPreProcessor.class.getName());

	Environment newEnv;

	public ATTJasperDeviceUsageHistoryPreProcessor() {
		// Empty Constructor
	}

	public ATTJasperDeviceUsageHistoryPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:ATTJasperDeviceUsageHistoryPreProcessor");

		DeviceInformation deviceInfo = (DeviceInformation) exchange.getIn()
				.getBody();

		ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();
		DeviceId device = new DeviceId();

		// Fetching Recommended device Identifiers
		DeviceId recommendedDeviceId = CommonUtil
				.getRecommendedDeviceIdentifier(deviceInfo.getDeviceIds());

		device.setId(recommendedDeviceId.getId());
		device.setKind(recommendedDeviceId.getKind());
		dataArea.setDeviceId(device);

		GetTerminalDetailsRequest getTerminalDetailsRequest = new GetTerminalDetailsRequest();

		GetTerminalDetailsRequest.Iccids iccids = new GetTerminalDetailsRequest.Iccids();
		List<String> iccIdList = iccids.getIccid();

		iccIdList.add(recommendedDeviceId.getId());

		getTerminalDetailsRequest.setIccids(iccids);

		String version = newEnv.getProperty("attJapser.version");

		String licenseKey = newEnv.getProperty("attJapser.licenseKey");

		getTerminalDetailsRequest.setLicenseKey(licenseKey);
		getTerminalDetailsRequest.setMessageId("" + new Date().getTime());
		getTerminalDetailsRequest.setVersion(version);

		LOGGER.info("szie of iccId..............."
				+ getTerminalDetailsRequest.getIccids().getIccid().size());

		exchange.getIn().setBody(getTerminalDetailsRequest);

		exchange.setProperty("DeviceId", device);

		exchange.getIn().setHeader(CxfConstants.OPERATION_NAME,
				"GetTerminalDetails");
		exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE,
				"http://api.jasperwireless.com/ws/schema");
		exchange.getIn()
				.setHeader("soapAction",
						"http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails");

		exchange.setProperty(IConstant.MIDWAY_NETSUITE_ID,
				deviceInfo.getNetSuiteId());
		exchange.setProperty("CarrierName", deviceInfo.getBs_carrier());

		String username = newEnv.getProperty("attJapser.userName");

		String password = newEnv.getProperty("attJapser.password");

		List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(username,
				password);

		exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperDeviceUsageHistoryPreProcessor");

	}

}
