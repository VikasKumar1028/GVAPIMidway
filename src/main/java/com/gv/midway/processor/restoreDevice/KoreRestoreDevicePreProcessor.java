package com.gv.midway.processor.restoreDevice;

import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.restoreDevice.kore.request.RestoreDeviceRequestKore;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreRestoreDevicePreProcessor implements Processor {
	private static final Logger LOGGER = Logger.getLogger(KoreRestoreDevicePreProcessor.class.getName());

	private Environment newEnv;

	public KoreRestoreDevicePreProcessor(Environment env) {
		super();
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.debug("Begin:KoreRestoreDevicePreProcessor.." + exchange.getIn().getBody());

		final Message message = exchange.getIn();
		final Transaction transaction = message.getBody(Transaction.class);
		final RestoreDeviceRequest restoreDeviceRequest = (RestoreDeviceRequest) transaction.getDevicePayload();
        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : restoreDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

		final RestoreDeviceRequestKore restoreDeviceRequestKore = new RestoreDeviceRequestKore();
		restoreDeviceRequestKore.setDeviceNumber(deviceId);

		MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/restoreDevice", restoreDeviceRequestKore);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.debug("End:KoreRestoreDevicePreProcessor");
	}
}