package com.gv.midway.processor.reactivate;

import com.gv.midway.utility.MessageStuffer;
import com.gv.midway.pojo.MidWayDeviceId;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest;
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequestKore;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreReactivateDevicePreProcessor implements Processor {

	private static final Logger LOGGER = Logger.getLogger(KoreReactivateDevicePreProcessor.class.getName());

	private Environment newEnv;

	public KoreReactivateDevicePreProcessor(Environment env) {
		this.newEnv = env;
	}

	@Override
	public void process(Exchange exchange) throws Exception {

		LOGGER.debug("Begin:KoreReActivateDevicePreProcessor");
		LOGGER.debug("*************Testing********" + exchange.getIn().getBody());

		final Message message = exchange.getIn();
		final Transaction transaction = message.getBody(Transaction.class);
		final ReactivateDeviceRequest reactivateDeviceRequest = (ReactivateDeviceRequest) transaction.getDevicePayload();
        String deviceId = "";
        for (MidWayDeviceId midWayDeviceId : reactivateDeviceRequest.getDataArea().getDevices()[0].getDeviceIds()) {
            deviceId = midWayDeviceId.getId();
            if ("SIM".equalsIgnoreCase(midWayDeviceId.getKind())) { //Prefer SIM over other deviceIds
                break;
            }
        }

		final ReactivateDeviceRequestKore reactivationDeviceRequestKore = new ReactivateDeviceRequestKore();
		reactivationDeviceRequestKore.setDeviceNumber(deviceId);

		MessageStuffer.setKorePOSTRequest(message, newEnv, "/json/reactivateDevice", reactivationDeviceRequestKore);

		exchange.setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber());
		exchange.setPattern(ExchangePattern.InOut);

		LOGGER.debug("End:KoreReactivateDevicePreProcessor");
	}
}