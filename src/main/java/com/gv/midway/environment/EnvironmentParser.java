package com.gv.midway.environment;

import com.gv.midway.constant.IConstant;
import org.springframework.core.env.Environment;

public class EnvironmentParser {
    private EnvironmentParser() {
    }

    public static ATTJasperProperties getATTJasperProperties(Environment environment) {
        return new ATTJasperProperties(
                environment.getProperty(IConstant.ATTJASPER_VERSION)
                , environment.getProperty(IConstant.ATTJASPER_LICENSE_KEY)
                , environment.getProperty(IConstant.ATTJASPER_USERNAME)
                , environment.getProperty(IConstant.ATTJASPER_PASSWORD)
        );
    }

    public static NetSuiteOAuthHeaderProperties getNetSuiteOAuthHeaderProperties(Environment environment) {
        return new NetSuiteOAuthHeaderProperties(
                environment.getProperty(IConstant.NETSUITE_OAUTH_CONSUMER_KEY)
                , environment.getProperty(IConstant.NETSUITE_OAUTH_TOKEN_ID)
                , environment.getProperty(IConstant.NETSUITE_OAUTH_TOKEN_SECRET)
                , environment.getProperty(IConstant.NETSUITE_OAUTH_CONSUMER_SECRET)
                , environment.getProperty(IConstant.NETSUITE_REALM)
                , environment.getProperty(IConstant.NETSUITE_END_POINT)
        );
    }

    public static VerizonCredentialsProperties getVerizonCredentialsProperties(Environment environment) {
        return new VerizonCredentialsProperties(
                environment.getProperty(IConstant.VERIZON_API_USERNAME)
                , environment.getProperty(IConstant.VERIZON_API_PASSWORD)
        );
    }
}