package com.gv.midway.processor.deviceInformation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.gv.midway.pojo.verizon.CustomFields;
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

		LOGGER.info("Begin:ATT_JasperDeviceInformationPostProcessor");

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

		CustomFields[] customFields = new CustomFields[10];

		CustomFields customFields1 = new CustomFields();
		customFields1.setKey("CustomField1");
		customFields1.setValue(terminalType.get(0).getCustom1());

		CustomFields customFields2 = new CustomFields();
		customFields2.setKey("CustomField2");
		customFields2.setValue(terminalType.get(0).getCustom2());

		CustomFields customFields3 = new CustomFields();
		customFields3.setKey("CustomField3");
		customFields3.setValue(terminalType.get(0).getCustom3());

		CustomFields customFields4 = new CustomFields();
		customFields4.setKey("CustomField4");
		customFields4.setValue(terminalType.get(0).getCustom4());

		CustomFields customFields5 = new CustomFields();
		customFields5.setKey("CustomField5");
		customFields5.setValue(terminalType.get(0).getCustom5());

		CustomFields customFields6 = new CustomFields();
		customFields6.setKey("CustomField6");
		customFields6.setValue(terminalType.get(0).getCustom6());

		CustomFields customFields7 = new CustomFields();
		customFields7.setKey("CustomField7");
		customFields7.setValue(terminalType.get(0).getCustom7());

		CustomFields customFields8 = new CustomFields();
		customFields8.setKey("CustomField8");
		customFields8.setValue(terminalType.get(0).getCustom8());

		CustomFields customFields9 = new CustomFields();
		customFields9.setKey("CustomField9");
		customFields9.setValue(terminalType.get(0).getCustom9());

		CustomFields customFields10 = new CustomFields();
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

		CustomFields[] customerCustomFields = new CustomFields[5];

		CustomFields customerCustomFields1 = new CustomFields();
		customerCustomFields1.setKey("customerCustom1");
		customerCustomFields1.setValue(terminalType.get(0).getCustomerCustom1());

		CustomFields customerCustomFields2 = new CustomFields();
		customerCustomFields2.setKey("customerCustom2");
		customerCustomFields2.setValue(terminalType.get(0).getCustomerCustom2());

		CustomFields customerCustomFields3 = new CustomFields();
		customerCustomFields3.setKey("customerCustom3");
		customerCustomFields3.setValue(terminalType.get(0).getCustomerCustom3());

		CustomFields customerCustomFields4 = new CustomFields();
		customerCustomFields4.setKey("customerCustom4");
		customerCustomFields4.setValue(terminalType.get(0).getCustomerCustom4());

		CustomFields customerCustomFields5 = new CustomFields();
		customerCustomFields5.setKey("customerCustom5");
		customerCustomFields5.setValue(terminalType.get(0).getCustomerCustom5());

		customerCustomFields[0] = customerCustomFields1;
		customerCustomFields[1] = customerCustomFields2;
		customerCustomFields[2] = customerCustomFields3;
		customerCustomFields[3] = customerCustomFields4;
		customerCustomFields[4] = customerCustomFields5;

		deviceInformation.setCustomerCustomFields(customerCustomFields);

		CustomFields[] operatorCustomFields = new CustomFields[5];

		CustomFields operatorCustomFields1 = new CustomFields();
		operatorCustomFields1.setKey("operatorCustom1");
		operatorCustomFields1.setValue(terminalType.get(0).getOperatorCustom1());

		CustomFields operatorCustomFields2 = new CustomFields();
		operatorCustomFields2.setKey("operatorCustom2");
		operatorCustomFields2.setValue(terminalType.get(0).getOperatorCustom2());

		CustomFields operatorCustomFields3 = new CustomFields();
		operatorCustomFields3.setKey("operatorCustom3");
		operatorCustomFields3.setValue(terminalType.get(0).getOperatorCustom3());

		CustomFields operatorCustomFields4 = new CustomFields();
		operatorCustomFields4.setKey("operatorCustom4");
		operatorCustomFields4.setValue(terminalType.get(0).getOperatorCustom4());

		CustomFields operatorCustomFields5 = new CustomFields();
		operatorCustomFields5.setKey("operatorCustom5");
		operatorCustomFields5.setValue(terminalType.get(0).getOperatorCustom5());


		operatorCustomFields[0] = operatorCustomFields1;
		operatorCustomFields[1] = operatorCustomFields2;
		operatorCustomFields[2] = operatorCustomFields3;
		operatorCustomFields[3] = operatorCustomFields4;
		operatorCustomFields[4] = operatorCustomFields5;

		deviceInformation.setOpeartorCustomFields(operatorCustomFields);

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
		LOGGER.info("End:ATTJasperDeviceInformationPostProcessor");
	}
}