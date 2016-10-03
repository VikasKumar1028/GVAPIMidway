package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import java.util.ArrayList;
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

import com.gv.midway.attjasper.GetSessionInfoRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceSessionBeginEndInfoPreProcessor implements
		Processor {
	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceSessionBeginEndInfoPreProcessor.class
					.getName());

	Environment newEnv;

	public ATTJasperDeviceSessionBeginEndInfoPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public ATTJasperDeviceSessionBeginEndInfoPreProcessor() {

	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperDeviceSessionBeginEndInfoPreProcessor");

		ConnectionInformationRequestDataArea businessRequest = new ConnectionInformationRequestDataArea();
		ConnectionInformationRequest proxyRequest = (ConnectionInformationRequest) exchange
				.getIn().getBody();
		DeviceId deviceId = new DeviceId();
		deviceId.setId(proxyRequest.getDataArea().getDeviceId().getId());
		deviceId.setKind(proxyRequest.getDataArea().getDeviceId().getKind());
		businessRequest.setDeviceId(deviceId);

		GetSessionInfoRequest getSessionInfoRequest = new GetSessionInfoRequest();

		List<String> iccidvalue = new ArrayList<String>();

		iccidvalue.add(0, proxyRequest.getDataArea().getDeviceId().getId()
				.toString());

		LOGGER.info("proxyRequest.getDataArea().getDeviceId().getId().toString() "
				+ proxyRequest.getDataArea().getDeviceId().getId().toString());

		getSessionInfoRequest.getIccid().add(iccidvalue.get(0));

		String version = newEnv.getProperty("attJapser.version");

		String licenseKey = newEnv.getProperty("attJapser.licenseKey");

		getSessionInfoRequest.setLicenseKey(licenseKey);
		getSessionInfoRequest.setMessageId("" + new Date().getTime());
		getSessionInfoRequest.setVersion(version);

		exchange.getIn().setBody(getSessionInfoRequest);

		exchange.getIn().setHeader(CxfConstants.OPERATION_NAME,
				"GetSessionInfo");
		exchange.getIn().setHeader(CxfConstants.OPERATION_NAMESPACE,
				"http://api.jasperwireless.com/ws/schema");
		exchange.getIn()
				.setHeader("soapAction",
						"http://api.jasperwireless.com/ws/service/terminal/GetSessionInfo");

		String username = newEnv.getProperty("attJapser.userName");

		String password = newEnv.getProperty("attJapser.password");

		List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(username,
				password);

		exchange.getIn().setHeader(Header.HEADER_LIST, soapHeaders);
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperDeviceSessionBeginEndInfoPreProcessor");
	}
}
