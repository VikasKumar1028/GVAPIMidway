package com.gv.midway.environment;

import org.springframework.core.env.Environment;

public class EnvironmentParser {
    private EnvironmentParser() {
    }

    public static ATTJasperProperties getATTJasperProperties(Environment environment) {
        return new ATTJasperProperties(
                environment.getProperty("attJasper.version")
                , environment.getProperty("attJasper.licenseKey")
                , environment.getProperty("attJasper.userName")
                , environment.getProperty("attJasper.password")
        );
    }

    public static NetSuiteOAuthHeaderProperties getNetSuiteOAuthHeaderProperties(Environment environment) {
        return new NetSuiteOAuthHeaderProperties(
                environment.getProperty("netSuite.oauthConsumerKey")
                , environment.getProperty("netSuite.oauthTokenId")
                , environment.getProperty("netSuite.oauthTokenSecret")
                , environment.getProperty("netSuite.oauthConsumerSecret")
                , environment.getProperty("netSuite.realm")
                , environment.getProperty("netSuite.endPoint")
        );
    }
}