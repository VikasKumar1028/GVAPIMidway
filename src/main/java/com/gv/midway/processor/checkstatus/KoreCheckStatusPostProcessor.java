package com.gv.midway.processor.checkstatus;

import java.util.List;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.Response;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponse;
import com.gv.midway.pojo.callback.common.response.CallbackCommonResponseDataArea;
import com.gv.midway.pojo.verizon.DeviceId;

public class KoreCheckStatusPostProcessor implements Processor {

	/**
	 * Call back the Netsuite endPoint
	 */

	Logger log = Logger.getLogger(KoreCheckStatusPostProcessor.class.getName());

	private Environment newEnv;

	public KoreCheckStatusPostProcessor() {

	}

	public KoreCheckStatusPostProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		log.info("kore check status post processor");

		Message message = exchange.getIn();

		String midWayTransactionDeviceNumber = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER);

		String midWayTransactionId = (String) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_ID);

		RequestType requestType = (RequestType) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE);

		CallbackCommonResponse callbackCommonResponse = new CallbackCommonResponse();

		Header header = (Header) exchange
				.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_HEADER);

		Response response = new Response();

		response.setResponseCode(IResponse.SUCCESS_CODE);
		response.setResponseStatus(IResponse.SUCCESS_MESSAGE);
		switch (requestType) {
		case ACTIVATION:

			response.setResponseDescription("Device Activated Successfully");
			break;

		case DEACTIVATION:

			response.setResponseDescription("Device DeActivated Successfully");

			break;

		case REACTIVATION:

			response.setResponseDescription("Device ReActivated Successfully");

			break;

		case RESTORE:

			response.setResponseDescription("Device ReStored Successfully");

			break;

		case SUSPEND:

			response.setResponseDescription("Device Suspend Successfully");

			break;

		case CHANGESERVICEPLAN:

			response.setResponseDescription("Device Service Plan Changed Successfully.");

			break;

		case CHANGECUSTOMFIELDS:

			response.setResponseDescription("Device Custom Fields Changed Successfully.");

			break;

		default:
			break;
		}

		CallbackCommonResponseDataArea callbackCommonResponseDataArea = new CallbackCommonResponseDataArea();

		callbackCommonResponseDataArea.setRequestId(midWayTransactionId);
		callbackCommonResponseDataArea.setRequestType(requestType);
		callbackCommonResponseDataArea.setRequestStatus(true);

		List<DeviceId> deviceIdlist = new ObjectMapper().readValue(
				midWayTransactionDeviceNumber, TypeFactory.defaultInstance()
						.constructCollectionType(List.class, DeviceId.class));

		DeviceId[] deviceIds = new DeviceId[deviceIdlist.size()];
		deviceIds = deviceIdlist.toArray(deviceIds);

		callbackCommonResponseDataArea.setDeviceIds(deviceIds);

		callbackCommonResponse.setHeader(header);
		callbackCommonResponse.setResponse(response);

		callbackCommonResponse.setDataArea(callbackCommonResponseDataArea);

	}

}
