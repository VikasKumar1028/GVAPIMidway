package com.gv.midway.processor.token;

import com.gv.midway.constant.IConstant;
import com.gv.midway.environment.EnvironmentParser;
import com.gv.midway.environment.VerizonCredentialsProperties;
import com.gv.midway.pojo.token.VerizonAuthorizationResponse;
import com.gv.midway.utility.MessageStuffer;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class VerizonSessionTokenProcessor implements Processor {
    private static final Logger LOGGER = Logger.getLogger(VerizonSessionTokenProcessor.class.getName());

    private Environment newEnv;

    public VerizonSessionTokenProcessor(Environment env) {
        super();
        this.newEnv = env;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        LOGGER.debug("Begin:VerizonSessionTokenProcessor");

        final VerizonAuthorizationResponse authResponse = (VerizonAuthorizationResponse) exchange.getIn().getBody();
        final VerizonCredentialsProperties credentials = EnvironmentParser.getVerizonCredentialsProperties(newEnv);

        LOGGER.debug("-----------------authResponse.getAccess_token()---------------" + authResponse.getAccess_token());

        final Message message = exchange.getIn();
        MessageStuffer.setVerizonPOSTRequest(message
                , authResponse.getAccess_token()
                , "/m2m/v1/session/login", credentials.asJson()
        );

        exchange.setProperty(IConstant.VZ_AUTHORIZATION_TOKEN, authResponse.getAccess_token());

        LOGGER.debug("End:VerizonSessionTokenProcessor");
    }
}