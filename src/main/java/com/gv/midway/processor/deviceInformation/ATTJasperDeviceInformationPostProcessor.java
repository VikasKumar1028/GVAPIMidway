package com.gv.midway.processor.deviceInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gv.midway.pojo.KeyValuePair;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.attjasper.GetTerminalDetailsResponse;
import com.gv.midway.attjasper.GetTerminalDetailsResponse.Terminals;
import com.gv.midway.attjasper.TerminalType;

public class ATTJasperDeviceInformationPostProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ATTJasperDeviceInformationPostProcessor.class.getName());

	Environment newEnv;

	public ATTJasperDeviceInformationPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	public ATTJasperDeviceInformationPostProcessor() {
		// Empty ConstructorATT_JasperDeviceInformationPostProcessor
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		LOGGER.debug("Begin:ATT_JasperDeviceInformationPostProcessor");

		GetTerminalDetailsResponse getTerminalDetailsResponse = exchange
				.getIn().getBody(GetTerminalDetailsResponse.class);

		LOGGER.info("----exchange_Body- Post Processor-===++++++++++++---------"
				+ getTerminalDetailsResponse.toString());

		DeviceInformation deviceInformation = (DeviceInformation) exchange
				.getProperty(IConstant.MIDWAY_DEVICEINFO_DB);

		if (deviceInformation == null) {

			deviceInformation = new DeviceInformation();
		}

		if (deviceInformation.getBs_carrier() == null || "".equals(deviceInformation.getBs_carrier().trim())) {
			deviceInformation.setBs_carrier(exchange.getProperty(IConstant.BSCARRIER).toString());
		}


		Terminals terminals = getTerminalDetailsResponse.getTerminals();
		List<TerminalType> terminalType = terminals.getTerminal();

		LOGGER.info("getAccountId:::::::::" + terminalType.get(0).getAccountId());
		LOGGER.info("getIccid::::::" + terminalType.get(0).getIccid());
		LOGGER.info("getImei::" + terminalType.get(0).getImei());
		LOGGER.info("getCtdSessionCount:::" + terminalType.get(0).getCtdSessionCount());
		LOGGER.info("getCustom1:::::::" + terminalType.get(0).getCustom1());


		deviceInformation.setAccountName(String.valueOf(terminalType.get(0).getAccountId()));
		deviceInformation.setState(terminalType.get(0).getStatus());
		deviceInformation.setCurrentServicePlan(terminalType.get(0).getRatePlan());

		deviceInformation.setDateActivated(terminalType.get(0).getDateActivated().toString());
		deviceInformation.setCreatedAt(terminalType.get(0).getDateAdded().toString());
		deviceInformation.setDateModified(terminalType.get(0).getDateModified().toString());

		deviceInformation.setMonthToDateDataUsage("" + terminalType.get(0).getMonthToDateUsage());
		deviceInformation.setMonthToDateVoiceUsage("" + terminalType.get(0).getMonthToDateVoiceUsage());

		deviceInformation.setIpAddress(terminalType.get(0).getFixedIpAddress());


		List<DeviceId> deviceIdList = new ArrayList<DeviceId>();

		String iccId = terminalType.get(0).getIccid();
		String imei = terminalType.get(0).getImei();
		String imsi = terminalType.get(0).getImsi();
		String msisdn = terminalType.get(0).getMsisdn();

		if (imei != null && !"".equals(imei.trim())) {
			DeviceId deviceId1 = new DeviceId();
			deviceId1.setKind("imei");
			deviceId1.setId(imei);
			deviceIdList.add(deviceId1);
		}

		if (imsi != null && !"".equals(imsi.trim())) {
			DeviceId deviceId2 = new DeviceId();
			deviceId2.setKind("imsi");
			deviceId2.setId(imsi);
			deviceIdList.add(deviceId2);
		}

		DeviceId deviceId3 = new DeviceId();
		deviceId3.setId(iccId);
		deviceId3.setKind("iccId");
		deviceIdList.add(deviceId3);

		if (msisdn != null && !"".equals(msisdn.trim())) {
			DeviceId deviceId4 = new DeviceId();
			deviceId4.setKind("msisdn");
			deviceId4.setId(msisdn);
			deviceIdList.add(deviceId4);
		}

		DeviceId[] deviceIds = new DeviceId[deviceIdList.size()];
		for (int i = 0; i < deviceIds.length; i++) {
			deviceIds[i] = deviceIdList.get(i);
		}

		deviceInformation.setDeviceIds(deviceIds);

		KeyValuePair[] customFields = new KeyValuePair[10];

		KeyValuePair customFields1 = new KeyValuePair();
		customFields1.setKey("CustomField1");
		customFields1.setValue(terminalType.get(0).getCustom1());

		KeyValuePair customFields2 = new KeyValuePair();
		customFields2.setKey("CustomField2");
		customFields2.setValue(terminalType.get(0).getCustom2());

		KeyValuePair customFields3 = new KeyValuePair();
		customFields3.setKey("CustomField3");
		customFields3.setValue(terminalType.get(0).getCustom3());

		KeyValuePair customFields4 = new KeyValuePair();
		customFields4.setKey("CustomField4");
		customFields4.setValue(terminalType.get(0).getCustom4());

		KeyValuePair customFields5 = new KeyValuePair();
		customFields5.setKey("CustomField5");
		customFields5.setValue(terminalType.get(0).getCustom5());

		KeyValuePair customFields6 = new KeyValuePair();
		customFields6.setKey("CustomField6");
		customFields6.setValue(terminalType.get(0).getCustom6());

		KeyValuePair customFields7 = new KeyValuePair();
		customFields7.setKey("CustomField7");
		customFields7.setValue(terminalType.get(0).getCustom7());

		KeyValuePair customFields8 = new KeyValuePair();
		customFields8.setKey("CustomField8");
		customFields8.setValue(terminalType.get(0).getCustom8());

		KeyValuePair customFields9 = new KeyValuePair();
		customFields9.setKey("CustomField9");
		customFields9.setValue(terminalType.get(0).getCustom9());

		KeyValuePair customFields10 = new KeyValuePair();
		customFields10.setKey("CustomField10");
		customFields10.setValue(terminalType.get(0).getCustom10());

		customFields[0] = customFields1;
		customFields[1] = customFields2;
		customFields[2] = customFields3;
		customFields[3] = customFields4;
		customFields[4] = customFields5;
		customFields[5] = customFields6;
		customFields[6] = customFields7;
		customFields[7] = customFields8;
		customFields[8] = customFields9;
		customFields[9] = customFields10;

		deviceInformation.setCustomFields(customFields);

		KeyValuePair[] customerCustomFields = new KeyValuePair[5];

		KeyValuePair customerCustomFields1 = new KeyValuePair();
		customerCustomFields1.setKey("customerCustom1");
		customerCustomFields1.setValue(terminalType.get(0).getCustomerCustom1());

		KeyValuePair customerCustomFields2 = new KeyValuePair();
		customerCustomFields2.setKey("customerCustom2");
		customerCustomFields2.setValue(terminalType.get(0).getCustomerCustom2());

		KeyValuePair customerCustomFields3 = new KeyValuePair();
		customerCustomFields3.setKey("customerCustom3");
		customerCustomFields3.setValue(terminalType.get(0).getCustomerCustom3());

		KeyValuePair customerCustomFields4 = new KeyValuePair();
		customerCustomFields4.setKey("customerCustom4");
		customerCustomFields4.setValue(terminalType.get(0).getCustomerCustom4());

		KeyValuePair customerCustomFields5 = new KeyValuePair();
		customerCustomFields5.setKey("customerCustom5");
		customerCustomFields5.setValue(terminalType.get(0).getCustomerCustom5());

		customerCustomFields[0] = customerCustomFields1;
		customerCustomFields[1] = customerCustomFields2;
		customerCustomFields[2] = customerCustomFields3;
		customerCustomFields[3] = customerCustomFields4;
		customerCustomFields[4] = customerCustomFields5;

		deviceInformation.setCustomerCustomFields(customerCustomFields);

		KeyValuePair[] operatorCustomFields = new KeyValuePair[5];

		KeyValuePair operatorCustomFields1 = new KeyValuePair();
		operatorCustomFields1.setKey("operatorCustom1");
		operatorCustomFields1.setValue(terminalType.get(0).getOperatorCustom1());

		KeyValuePair operatorCustomFields2 = new KeyValuePair();
		operatorCustomFields2.setKey("operatorCustom2");
		operatorCustomFields2.setValue(terminalType.get(0).getOperatorCustom2());

		KeyValuePair operatorCustomFields3 = new KeyValuePair();
		operatorCustomFields3.setKey("operatorCustom3");
		operatorCustomFields3.setValue(terminalType.get(0).getOperatorCustom3());

		KeyValuePair operatorCustomFields4 = new KeyValuePair();
		operatorCustomFields4.setKey("operatorCustom4");
		operatorCustomFields4.setValue(terminalType.get(0).getOperatorCustom4());

		KeyValuePair operatorCustomFields5 = new KeyValuePair();
		operatorCustomFields5.setKey("operatorCustom5");
		operatorCustomFields5.setValue(terminalType.get(0).getOperatorCustom5());


		operatorCustomFields[0] = operatorCustomFields1;
		operatorCustomFields[1] = operatorCustomFields2;
		operatorCustomFields[2] = operatorCustomFields3;
		operatorCustomFields[3] = operatorCustomFields4;
		operatorCustomFields[4] = operatorCustomFields5;

		deviceInformation.setOperatorCustomFields(operatorCustomFields);

		deviceInformation.setLastUpdated(new Date());

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		response.setResponseDescription(IResponse.SUCCESS_DESCRIPTION_DEVICEINFO_CARRIER);

		Header responseHeader = (Header) exchange.getProperty(IConstant.HEADER);

		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		deviceInformationResponse.setHeader(responseHeader);
		deviceInformationResponse.setResponse(response);


		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();
		deviceInformationResponseDataArea.setDevices(deviceInformation);

		deviceInformationResponse.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);
		LOGGER.debug("End:ATTJasperDeviceInformationPostProcessor");
	}
}