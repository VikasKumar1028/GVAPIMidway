package com.gv.midway.service;

import javax.servlet.ServletContext;

import org.apache.camel.Exchange;

public interface ISessionService {

    public String getContextVzSessionToken();

    public String getContextVzAuthorizationToken();

    public void setContextTokenInExchange(Exchange exchange);

    public void setVzToken(Exchange exchange);

    public void synchronizeDBContextToken();

    public String checkToken(Exchange exchange);

    public void setServletContext(ServletContext servletContext);

}
