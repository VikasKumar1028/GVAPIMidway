package com.gv.midway.utility;

import com.gv.midway.constant.IConstant;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.core.env.Environment;

public class MessageStuffer {
    private MessageStuffer () { }

    private static final String AUTHORIZATION = "Authorization";
    private static final String HTTP_POST = "POST";
    private static final String HTTP_GET = "GET";
    private static final String HTTP_PUT = "PUT";
    private static final String APPLICATION_JSON = "application/json";

    public static void setKoreRequest(Message message, Environment environment, String httpMethod, String httpPath, Object body) {
        message.setHeader(Exchange.CONTENT_TYPE, APPLICATION_JSON);
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, APPLICATION_JSON);
        message.setHeader(Exchange.HTTP_METHOD, httpMethod);
        message.setHeader(AUTHORIZATION, environment.getProperty(IConstant.KORE_AUTHENTICATION));
        message.setHeader(Exchange.HTTP_PATH, httpPath);
        message.setBody(body);
    }

    public static void setKorePOSTRequest(Message message, Environment environment, String httpPath, Object body) {
        setKoreRequest(message, environment, HTTP_POST, httpPath, body);
    }

    public static void setKoreGETRequest(Message message, Environment environment, String httpPath, Object body) {
        setKoreRequest(message, environment, HTTP_GET, httpPath, body);
    }

    public static void setKorePUTRequest(Message message, Environment environment, String httpPath, Object body) {
        setKoreRequest(message, environment, HTTP_PUT, httpPath, body);
    }

    public static void setVerizonRequest(Message message, Environment environment, String httpMethod, String httpPath) {
        message.setHeader(AUTHORIZATION, environment.getProperty(IConstant.VERIZON_AUTHENTICATION));
        message.setHeader(Exchange.CONTENT_TYPE, "application/x-www-form-urlencoded");
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, APPLICATION_JSON);
        message.setHeader(Exchange.HTTP_METHOD, httpMethod);
        message.setHeader(Exchange.HTTP_PATH, httpPath);
        message.setHeader(Exchange.HTTP_QUERY, "grant_type=client_credentials");
    }

    public static void setVerizonRequest(Message message, String accessToken, String httpMethod, String httpPath, Object body) {
        message.setHeader(AUTHORIZATION, "Bearer " + accessToken);
        message.setHeader(Exchange.CONTENT_TYPE, APPLICATION_JSON);
        message.setHeader(Exchange.ACCEPT_CONTENT_TYPE, APPLICATION_JSON);
        message.setHeader(Exchange.HTTP_METHOD, httpMethod);
        message.setHeader(Exchange.HTTP_PATH, httpPath);
        message.setBody(body);
    }

    public static void setVerizonPOSTRequest(Message message, Environment environment, String httpPath) {
        setVerizonRequest(message, environment, HTTP_POST, httpPath);
    }

    public static void setVerizonPOSTRequest(Message message, String accessToken, String httpPath, Object body) {
        setVerizonRequest(message, accessToken, HTTP_POST, httpPath, body);
    }
}