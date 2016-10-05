package com.gv.midway.processor.deviceInformation;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.verizon.CustomFields;
import com.gv.midway.pojo.verizon.DeviceId;

public class StubATTJasperInformationProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(StubVerizonDeviceInformationProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.info("Begin:StubATTJasperInformationProcessor");
		DeviceInformationResponse deviceInformationResponse = new DeviceInformationResponse();

		DeviceInformationResponseDataArea deviceInformationResponseDataArea = new DeviceInformationResponseDataArea();

		DeviceInformation deviceInformation = new DeviceInformation();

		Header responseheader = new Header();

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);

		response.setResponseDescription("Device Information is fetched successfully");
		response.setResponseStatus("SUCESS");

		responseheader.setApplicationName("WEB");
		responseheader.setRegion("12133");
		responseheader.setTimestamp("2016-03-08T21:49:45");
		responseheader.setOrganization("Grant Victor");
		responseheader.setSourceName("NetSuit");
		responseheader.setTransactionId("cde2131ksjd");
		responseheader.setBsCarrier("ATTJASPER");

		Date datevalue = new Date();
		deviceInformationResponse.setHeader(responseheader);
		deviceInformationResponse.setResponse(response);

		deviceInformation.setNetSuiteId(11212321);

		deviceInformation.setAccountName("100732502");

		deviceInformation.setCreatedAt("2014-09-10T14:32:09Z");

		DeviceId deviceIds = new DeviceId();
		DeviceId deviceIds1 = new DeviceId();
		DeviceId deviceIds2 = new DeviceId();
		deviceIds.setId("89011702272013902587");

		deviceIds.setKind("iccId");

		deviceIds1.setId("89011702272013902629");
		deviceIds1.setKind("iccId");

		deviceIds2.setId("89011702272013902595");

		deviceIds2.setKind("iccId");

		DeviceId[] deviceIdsArray = { deviceIds, deviceIds1, deviceIds2 };
		deviceInformation.setDeviceIds(deviceIdsArray);

		deviceInformation.setDateActivated("2016-08-16T21:10:09.253Z");
		deviceInformation.setDateModified("2016-08-16T21:10:19.501Z");

		deviceInformation.setMonthToDateDataUsage("0.000");
		deviceInformation.setMonthToDateVoiceUsage("0");

		deviceInformation.setLastUpdated(datevalue);
		deviceInformation.setState("ACTIVATED_NAME");
		deviceInformation.setBs_carrier("ATTJASPER");

		CustomFields customerCustom1 = new CustomFields();
		CustomFields customerCustom2 = new CustomFields();
		CustomFields customerCustom3 = new CustomFields();
		CustomFields customerCustom4 = new CustomFields();
		CustomFields customerCustom5 = new CustomFields();

		customerCustom1.setKey("customerCustom1");
		customerCustom1.setValue("");

		customerCustom2.setKey("customerCustom1");
		customerCustom2.setValue("");

		customerCustom3.setKey("customerCustom1");
		customerCustom3.setValue("");

		customerCustom4.setKey("customerCustom1");
		customerCustom4.setValue("");

		customerCustom5.setKey("customerCustom1");
		customerCustom5.setValue("");

		CustomFields opeartorCustomFields1 = new CustomFields();
		CustomFields opeartorCustomFields2 = new CustomFields();
		CustomFields opeartorCustomFields3 = new CustomFields();
		CustomFields opeartorCustomFields4 = new CustomFields();
		CustomFields opeartorCustomFields5 = new CustomFields();

		opeartorCustomFields1.setKey("operatorCustom1");
		opeartorCustomFields1.setValue("");

		opeartorCustomFields2.setKey("operatorCustom2");
		opeartorCustomFields2.setValue("");

		opeartorCustomFields3.setKey("operatorCustom3");
		opeartorCustomFields3.setValue("");

		opeartorCustomFields4.setKey("operatorCustom4");
		opeartorCustomFields4.setValue("");

		opeartorCustomFields5.setKey("operatorCustom5");
		opeartorCustomFields5.setValue("");

		CustomFields customFields = new CustomFields();
		CustomFields customFields1 = new CustomFields();
		CustomFields customFields2 = new CustomFields();
		CustomFields customFields3 = new CustomFields();
		CustomFields customFields4 = new CustomFields();
		CustomFields customFields5 = new CustomFields();
		CustomFields customFields6 = new CustomFields();
		CustomFields customFields7 = new CustomFields();
		CustomFields customFields8 = new CustomFields();
		CustomFields customFields9 = new CustomFields();
		CustomFields customFields10 = new CustomFields();

		customFields.setKey("CustomField");
		customFields.setValue("");
		
		customFields1.setKey("CustomField1");
		customFields1.setValue("");
		customFields2.setKey("CustomField2");
		customFields2.setValue("");

		customFields3.setKey("CustomField3");
		customFields3.setValue("");

		customFields4.setKey("CustomField4");
		customFields4.setValue("");

		customFields5.setKey("CustomField5");
		customFields5.setValue("");

		customFields6.setKey("CustomField6");
		customFields6.setValue("");

		customFields7.setKey("CustomField7");
		customFields7.setValue("");

		customFields8.setKey("CustomField8");
		customFields8.setValue("");

		customFields9.setKey("CustomField9");
		customFields9.setValue("");

		customFields10.setKey("CustomField10");
		customFields10.setValue("");

		CustomFields[] arr = { customFields, customFields1, customFields2,
				customFields3, customFields4, customFields5, customFields6,
				customFields7, customFields8, customFields9, customFields10 };

		CustomFields[] opeartorCustomFieldsarray = { opeartorCustomFields1,
				opeartorCustomFields2, opeartorCustomFields3,
				opeartorCustomFields4, opeartorCustomFields5 };

		CustomFields[] customerCustomFieldsarray = { customerCustom1,
				customerCustom2, customerCustom3, customerCustom4,
				customerCustom5 };

		deviceInformation.setCustomFields(arr);
		deviceInformation.setOpeartorCustomFields(opeartorCustomFieldsarray);

		deviceInformation.setCustomerCustomFields(customerCustomFieldsarray);
		deviceInformationResponseDataArea.setDevices(deviceInformation);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);

		LOGGER.info("End:StubATTJasperInformationProcessor");
	}
}
