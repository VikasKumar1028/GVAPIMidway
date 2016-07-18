package com.gv.midway.service.impl;

import javax.servlet.ServletContext;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ISessionDao;
import com.gv.midway.pojo.session.SessionBean;
import com.gv.midway.service.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService, ServletContextAware {

    private static final Logger LOGGER = Logger.getLogger(SessionServiceImpl.class.getName());
    private ServletContext servletContext;

    @Autowired
    private ISessionDao sessionDao;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String getContextVzSessionToken() {

        return servletContext.getAttribute(IConstant.VZ_SEESION_TOKEN) != null ? servletContext
                .getAttribute(IConstant.VZ_SEESION_TOKEN).toString() : "";

    }

    @Override
    public String getContextVzAuthorizationToken() {

        return servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN) != null ? servletContext
                .getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN).toString() : "";

    }

    @Override
    public void setVzToken(Exchange exchange) {

        LOGGER.info(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)
                + "Inside Session Set Attributes ***********************************"
                + exchange.getProperty(IConstant.VZ_SEESION_TOKEN));
        // Set to DB remove Servlet Context
        SessionBean sessionBean = new SessionBean();
        sessionBean.setVzAuthorizationToken(exchange.getProperty(
                IConstant.VZ_AUTHORIZATION_TOKEN).toString());
        sessionBean.setVzSessionToken(exchange.getProperty(
                IConstant.VZ_SEESION_TOKEN).toString());
        sessionBean.setIsValid("0");
        sessionDao.saveSesionBean(sessionBean);
    }

    @Override
    public void synchronizeDBContextToken() {

        LOGGER.info("**************synchronizeDBContextToken*****************************");

        SessionBean sessionBean = sessionDao.getSessionBean();

        if (sessionBean != null) {
            servletContext.setAttribute(IConstant.VZ_AUTHORIZATION_TOKEN,
                    sessionBean.getVzAuthorizationToken());

            servletContext.setAttribute(IConstant.VZ_SEESION_TOKEN,
                    sessionBean.getVzSessionToken());
        }

    }

    @Override
    public String checkToken(Exchange exchange) {

        SessionBean sessionBean = sessionDao.getSessionBean();
        if (sessionBean == null)
            return "true";

        LOGGER.info(sessionBean.getVzSessionToken() + "*******check Token*******"
                + getContextVzSessionToken());
        LOGGER.info(sessionBean.getVzAuthorizationToken()
                + "*******check Token*******"
                + getContextVzAuthorizationToken());

        if (sessionBean.getVzSessionToken().equals(getContextVzSessionToken())
                && sessionBean.getVzAuthorizationToken().equals(
                        getContextVzAuthorizationToken()))
            return "true";
        else
            return "false";

    }

    @Override
    public void setContextTokenInExchange(Exchange exchange) {

        if (servletContext.getAttribute(IConstant.VZ_SEESION_TOKEN) != null) {
            exchange.setProperty(IConstant.VZ_SEESION_TOKEN, servletContext
                    .getAttribute(IConstant.VZ_SEESION_TOKEN).toString());
        }
        if (servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
            exchange.setProperty(
                    IConstant.VZ_AUTHORIZATION_TOKEN,
                    servletContext.getAttribute(
                            IConstant.VZ_AUTHORIZATION_TOKEN).toString());
        }

    }
}