package com.gv.midway.processor.deviceInformation;

import java.util.Date;

import com.gv.midway.pojo.KeyValuePair;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.gv.midway.constant.IResponse;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponseDataArea;
import com.gv.midway.pojo.verizon.DeviceId;

public class StubATTJasperInformationProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(StubATTJasperInformationProcessor.class.getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:StubATTJasperInformationProcessor");
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

		KeyValuePair customerCustom1 = new KeyValuePair();
		KeyValuePair customerCustom2 = new KeyValuePair();
		KeyValuePair customerCustom3 = new KeyValuePair();
		KeyValuePair customerCustom4 = new KeyValuePair();
		KeyValuePair customerCustom5 = new KeyValuePair();

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

		KeyValuePair operatorCustomFields1 = new KeyValuePair();
		KeyValuePair operatorCustomFields2 = new KeyValuePair();
		KeyValuePair operatorCustomFields3 = new KeyValuePair();
		KeyValuePair operatorCustomFields4 = new KeyValuePair();
		KeyValuePair operatorCustomFields5 = new KeyValuePair();

		operatorCustomFields1.setKey("operatorCustom1");
		operatorCustomFields1.setValue("");

		operatorCustomFields2.setKey("operatorCustom2");
		operatorCustomFields2.setValue("");

		operatorCustomFields3.setKey("operatorCustom3");
		operatorCustomFields3.setValue("");

		operatorCustomFields4.setKey("operatorCustom4");
		operatorCustomFields4.setValue("");

		operatorCustomFields5.setKey("operatorCustom5");
		operatorCustomFields5.setValue("");

		KeyValuePair customFields = new KeyValuePair();
		KeyValuePair customFields1 = new KeyValuePair();
		KeyValuePair customFields2 = new KeyValuePair();
		KeyValuePair customFields3 = new KeyValuePair();
		KeyValuePair customFields4 = new KeyValuePair();
		KeyValuePair customFields5 = new KeyValuePair();
		KeyValuePair customFields6 = new KeyValuePair();
		KeyValuePair customFields7 = new KeyValuePair();
		KeyValuePair customFields8 = new KeyValuePair();
		KeyValuePair customFields9 = new KeyValuePair();
		KeyValuePair customFields10 = new KeyValuePair();

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

		KeyValuePair[] arr = { customFields, customFields1, customFields2,
				customFields3, customFields4, customFields5, customFields6,
				customFields7, customFields8, customFields9, customFields10 };

		KeyValuePair[] operatorCustomFieldsArray = { operatorCustomFields1,
				operatorCustomFields2, operatorCustomFields3,
				operatorCustomFields4, operatorCustomFields5 };

		KeyValuePair[] customerCustomFieldsarray = { customerCustom1,
				customerCustom2, customerCustom3, customerCustom4,
				customerCustom5 };

		deviceInformation.setCustomFields(arr);
		deviceInformation.setOperatorCustomFields(operatorCustomFieldsArray);

		deviceInformation.setCustomerCustomFields(customerCustomFieldsarray);
		deviceInformationResponseDataArea.setDevices(deviceInformation);

		deviceInformationResponse
				.setDataArea(deviceInformationResponseDataArea);

		exchange.getIn().setBody(deviceInformationResponse);

		LOGGER.debug("End:StubATTJasperInformationProcessor");
	}
}
