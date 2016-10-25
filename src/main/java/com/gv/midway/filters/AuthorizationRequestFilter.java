package com.gv.midway.filters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gv.midway.constant.IConstant;
import com.gv.midway.controller.AdaptationLayerServiceImpl;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Created by ryan.tracy on 10/17/2016.
 */

public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdaptationLayerServiceImpl.class);
    @Resource
    Environment environment;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requestContext.getUriInfo().getPath().endsWith("/device/callback")) {

            if (requestContext.hasEntity()) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = requestContext.getEntityStream();
                String username = "";
                String password = "";
                try {
                    if (in.available() > 0) {
                        IOUtils.copy(in, out);
                        byte[] requestEntity = out.toByteArray();
                        ByteArrayInputStream bais = new ByteArrayInputStream(requestEntity);

                        ObjectMapper mapper = new ObjectMapper();
                        CallBackVerizonRequest callBack = mapper.readValue(requestEntity, CallBackVerizonRequest.class);
                        username = callBack.getUsername();
                        password = callBack.getPassword();

                        requestContext.setEntityStream(bais);
                    }
                } catch (IOException ex) {
                    requestContext.abortWith(createFaultResponse(ex.getMessage()));
                }
                String verizonCallbackUsername = environment.getProperty("verizon.callback.username");
                String verizonCallbackPassword = environment.getProperty("verizon.callback.password");
                System.out.println("*** /device/callback credentials: " + username + "/" + password);
                if (!verizonCallbackUsername.equalsIgnoreCase(username) || !verizonCallbackPassword.equals(password)) {
                    requestContext.abortWith(createFaultResponse());
                    return;
                }
            }

        } else {
            String keyName = environment.getProperty(IConstant.AUTHORIZATION_KEY);
            String authorization = requestContext.getHeaderString(keyName);
            if (authorization == null) { //If the key exists in the header then authorize access.
                requestContext.abortWith(createFaultResponse());
                return;
            } else {
                if (authorization.trim().length() > 0) {
                    try {
                        String jsonStr = new String(Base64.getUrlDecoder().decode(authorization));
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode json = mapper.readTree(jsonStr);
                        LOGGER.info("--- MIDWAY API authorization source: " + json.findValue("source").asText());
                    } catch (Exception ex) {
                        LOGGER.warn("*** authorization header could not parse the json: cause: " + ex.getMessage());
                    }
                }
            }
        }
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

    private InputStream reset(ContainerRequestContext requestContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();

        try {
            if (in.available() > 0) {
                IOUtils.copy(in, out);
                byte[] requestEntity = out.toByteArray();
                ByteArrayInputStream bais = new ByteArrayInputStream(requestEntity);
                requestContext.setEntityStream(bais);
            }
        } catch (IOException ex) {
            requestContext.abortWith(createFaultResponse(ex.getMessage()));
        }
        return in;
    }
}
