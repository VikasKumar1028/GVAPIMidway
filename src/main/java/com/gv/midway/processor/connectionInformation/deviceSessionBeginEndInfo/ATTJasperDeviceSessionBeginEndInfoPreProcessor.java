package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo;

import java.util.ArrayList;
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

import com.gv.midway.attjasper.GetSessionInfoRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.utility.CommonUtil;

public class ATTJasperDeviceSessionBeginEndInfoPreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(ATTJasperDeviceSessionBeginEndInfoPreProcessor.class.getName());

	private Environment newEnv;

	public ATTJasperDeviceSessionBeginEndInfoPreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:ATTJasperDeviceSessionBeginEndInfoPreProcessor");

		final Message message = exchange.getIn();
		final ConnectionInformationRequest proxyRequest = (ConnectionInformationRequest) message.getBody();

		final DeviceId deviceId = new DeviceId();
		deviceId.setId(proxyRequest.getDataArea().getDeviceId().getId());
		deviceId.setKind(proxyRequest.getDataArea().getDeviceId().getKind());

		final List<String> iccidvalue = new ArrayList<>();
		iccidvalue.add(0, proxyRequest.getDataArea().getDeviceId().getId());

		LOGGER.info("proxyRequest.getDataArea().getDeviceId().getId().toString() " + proxyRequest.getDataArea().getDeviceId().getId());

		final ATTJasperProperties properties = EnvironmentParser.getATTJasperProperties(newEnv);

		final GetSessionInfoRequest getSessionInfoRequest = new GetSessionInfoRequest();
		getSessionInfoRequest.getIccid().add(iccidvalue.get(0));
		getSessionInfoRequest.setLicenseKey(properties.licenseKey);
		getSessionInfoRequest.setMessageId("" + new Date().getTime());
		getSessionInfoRequest.setVersion(properties.version);

		final List<SoapHeader> soapHeaders = CommonUtil.getSOAPHeaders(properties.username, properties.password);

		message.setBody(getSessionInfoRequest);
		message.setHeader(CxfConstants.OPERATION_NAME, "GetSessionInfo");
		message.setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema");
		message.setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/GetSessionInfo");
		message.setHeader(Header.HEADER_LIST, soapHeaders);

		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.info("End:ATTJasperDeviceSessionBeginEndInfoPreProcessor");
	}
}