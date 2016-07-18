package com.gv.midway.processor.callbacks;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackError;
import com.gv.midway.pojo.callback.Netsuite.KafkaNetSuiteCallBackEvent;
import com.gv.midway.pojo.callback.Netsuite.NetSuiteCallBackProvisioningRequest;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;

public class CallbackPreProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(CallbackPreProcessor.class);
    Environment newEnv;

    public CallbackPreProcessor(Environment env) {
        super();
        this.newEnv = env;

    }

    @Override
    public void process(Exchange exchange) throws Exception {
        /**
         * formatting the verizon callback response to expected target response
         * 
         * 
         * */
        LOGGER.info(" Inside CallbackPreProcessor ");
        CallBackVerizonRequest req = (CallBackVerizonRequest) exchange.getIn()
                .getBody(CallBackVerizonRequest.class);

        exchange.setProperty(IConstant.VERIZON_CALLBACK_RESPONE, req);

        NetSuiteCallBackProvisioningRequest netSuiteCallBackProvisioningRequest = new NetSuiteCallBackProvisioningRequest();
        netSuiteCallBackProvisioningRequest.setDeviceIds(req.getDeviceIds());

        if (req.getFaultResponse() != null) {

            netSuiteCallBackProvisioningRequest.setStatus("fail");
            netSuiteCallBackProvisioningRequest.setResponse(req
                    .getFaultResponse().getFaultstring());

            KafkaNetSuiteCallBackError netSuiteCallBackError = new KafkaNetSuiteCallBackError();

            netSuiteCallBackError.setApp("Midway");
            netSuiteCallBackError.setCategory("Verizon Call Back Error");
            netSuiteCallBackError.setLevel("Error");
            netSuiteCallBackError.setTimestamp(new Date().getTime());
            netSuiteCallBackError.setVersion("1");
            netSuiteCallBackError.setException(req.getFaultResponse()
                    .getFaultstring());
            netSuiteCallBackError.setMsg("Error in Call Back from Verizon.");

            exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackError);

        }

        else {

            netSuiteCallBackProvisioningRequest.setStatus("success");

            KafkaNetSuiteCallBackEvent netSuiteCallBackEvent = new KafkaNetSuiteCallBackEvent();

            netSuiteCallBackEvent.setApp("Midway");
            netSuiteCallBackEvent.setCategory("Verizon Call Back Success");
            netSuiteCallBackEvent.setLevel("Info");
            netSuiteCallBackEvent.setTimestamp(new Date().getTime());
            netSuiteCallBackEvent.setVersion("1");

            netSuiteCallBackEvent.setMsg("Succesfull Call Back from Verizon.");

            exchange.setProperty(IConstant.KAFKA_OBJECT, netSuiteCallBackEvent);

        }

        exchange.getIn().setBody(netSuiteCallBackProvisioningRequest);
    }

}
