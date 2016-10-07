package com.gv.midway.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import com.gv.midway.constant.IConstant;
import com.gv.midway.constant.IResponse;
import com.gv.midway.exception.MissingParameterException;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest;
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequestDataArea;

public class ChangeDeviceServicePlanValidatorProcessor implements Processor {

	private static final Logger LOGGER = Logger
			.getLogger(ChangeDeviceServicePlanValidatorProcessor.class
					.getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.info("Begin:ChangeDeviceServicePlanValidatorProcessor");
		ChangeDeviceServicePlansRequest request = (ChangeDeviceServicePlansRequest) exchange
				.getIn().getBody(ChangeDeviceServicePlansRequest.class);

		ChangeDeviceServicePlansRequestDataArea changeDeviceServicePlansRequestDataArea = request
				.getDataArea();

		if (changeDeviceServicePlansRequestDataArea == null)

		{

			missingServicePlan(exchange);

		}

		else {

			String currentServicePlan = changeDeviceServicePlansRequestDataArea
					.getCurrentServicePlan();

			String servicePlan = changeDeviceServicePlansRequestDataArea
					.getServicePlan();

			if (currentServicePlan == null || servicePlan == null) {

				missingServicePlan(exchange);
			}
		}
		LOGGER.info("End:ChangeDeviceServicePlanValidatorProcessor");
	}

	private void missingServicePlan(Exchange exchange)
			throws MissingParameterException {

		exchange.setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD);
		exchange.setProperty(IConstant.RESPONSE_STATUS,
				IResponse.ERROR_DESCRIPTION_CHANGE_SERVICE_PLAN);
		exchange.setProperty(IConstant.RESPONSE_DESCRIPTION,
				IResponse.ERROR_MESSAGE);
		throw new MissingParameterException(
				IResponse.INVALID_PAYLOAD.toString(),
				IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB);
	}

}