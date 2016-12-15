package com.gv.midway.filters;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.pojo.audit.Audit;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;

/**
 * Created by ryan.tracy on 10/17/2016.
 */

public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationRequestFilter.class);

    @Context
    private HttpServletRequest request;

    @Resource
    Environment environment;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.debug("incoming request: " + requestContext.getUriInfo().getPath());
        String sourceDetail = "";
        String payload = "";
        if (requestContext.getUriInfo().getPath().endsWith("/device/callback")) {
            InputStream in = requestContext.getEntityStream();
            payload = IOUtils.toString(in, "UTF-8");
            LOGGER.debug("incoming payload:\n" + payload);

            Audit audit = new Audit();
            audit.setApiOperationName(requestContext.getUriInfo().getPath());
            audit.setErrorProblem("Callback: Payload");
            audit.setPayload(payload);
            audit.setFrom("Verizon");
            audit.setPayload(payload);
            audit.setTimeStamp(new Date());
            audit.setTo("Midway");
            mongoTemplate.save(audit);

            if (requestContext.hasEntity()) {
                LOGGER.debug("*** hasEntity");
                String username = "";
                String password = "";
                try {
                    if (!payload.startsWith("{")) {
                        LOGGER.error("**************************************************************************************");
                        LOGGER.error("********************* PAYLOAD IS MISSING STARTING CURLY BRACE ************************");
                        LOGGER.error("********************* Received payload                        ************************");
                        LOGGER.error(payload);

                        Audit errorAudit = new Audit();
                        errorAudit.setApiOperationName(requestContext.getUriInfo().getPath());
                        errorAudit.setErrorProblem("Callback: Bad payload from verizon");
                        errorAudit.setErrorDetails("Payload is missing starting curly brace.");
                        errorAudit.setFrom("Verizon");
                        errorAudit.setPayload(payload);
                        errorAudit.setTimeStamp(new Date());
                        errorAudit.setTo("Midway");
                        mongoTemplate.save(errorAudit);

                        LOGGER.error("**************************************************************************************");
                        LOGGER.error("********************* Fixed payload                           ************************");
                        payload = "{" + payload;
                        LOGGER.error(payload);
                        LOGGER.error("**************************************************************************************");
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    JsonFactory factory = new JsonFactory();
                    CallBackVerizonRequest callBack = mapper.readValue(factory.createParser(payload), CallBackVerizonRequest.class);
                    username = callBack.getUsername();
                    password = callBack.getPassword();
                    LOGGER.debug("*** /device/callback obtained credentials: " + username + "/<hidden>");

                    ByteArrayInputStream bais = new ByteArrayInputStream(payload.getBytes("UTF-8"));
                    requestContext.setEntityStream(bais);
                    sourceDetail = username;

                } catch (Exception ex) {
                    LOGGER.warn("\nUnable to authenticate Verizon Callback. Cause:\n" + ex.getMessage() +
                            "\npayload:\n" + payload);
                    requestContext.abortWith(createFaultResponse(ex.getMessage()));
                    return;
                }
                String verizonCallbackUsername = environment.getProperty("verizon.callback.username");
                String verizonCallbackPassword = environment.getProperty("verizon.callback.password");
                LOGGER.info("*** /device/callback authenticating with credentials: " + username + "/<hidden>");
                if (!verizonCallbackUsername.equalsIgnoreCase(username) || !verizonCallbackPassword.equals(password)) {
                    LOGGER.warn("Unable to authenticate Verizon Callback with payload:\n" + payload);
                    requestContext.abortWith(createFaultResponse());
                    return;
                }
                LOGGER.debug("\nCallback payload:\n" + payload);
            } else {
                LOGGER.warn("Unable to accept Verizon Callback with requestContext.hasEntity() of " + requestContext.hasEntity());
                requestContext.abortWith(createFaultResponse("Unable to accept Verizon Callback with requestContext.hasEntity() of " + requestContext.hasEntity()));
                return;
            }

        } else {
            String keyName = environment.getProperty(IConstant.AUTHORIZATION_KEY);
            String authorization = requestContext.getHeaderString(keyName);
            if (authorization != null) { //If the key exists in the header then authorize access.
                if (authorization.trim().length() > 0) {  //They could send an empty value which is a legitimate value unless decided otherwise
                    try {
                        String jsonStr = new String(Base64.getUrlDecoder().decode(authorization));
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode json = mapper.readTree(jsonStr);
                        sourceDetail = json.findValue("source").asText();
                        LOGGER.debug("--- MIDWAY API authorization source: " + sourceDetail);
                    } catch (Exception ex) {
                        LOGGER.warn("*** authorization header could not parse the json: cause: " + ex.getMessage());
                        requestContext.abortWith(createFaultResponse("*** authorization header could not parse the json: cause: " + ex.getMessage()));
                        return;
                    }
                }
            } else {
                LOGGER.warn("*** authorization header could not be found.");
                requestContext.abortWith(createFaultResponse("*** authorization header could not be found."));
                return;
            }
        }
        LOGGER.info("==> " + request.getRequestURL() + " " + request.getMethod() + " [" + request.getRemoteHost() + "/" + sourceDetail + "]");
    }

    private Response createFaultResponse() {
        return createFaultResponse(null);
    }

    private Response createFaultResponse(String message) {
        String errorMessage = message != null && message.length() > 0 ? message : "User cannot access the resource.";
        return Response.status(Response.Status.UNAUTHORIZED.getStatusCode())
                .entity(errorMessage)
                .build();

    }
}
