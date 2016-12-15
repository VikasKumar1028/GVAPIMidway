package com.gv.midway.service.impl;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ISessionDao;
import com.gv.midway.pojo.session.SessionBean;
import com.gv.midway.service.ISessionService;
import com.gv.midway.utility.CommonUtil;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Service
public class SessionServiceImpl implements ISessionService, ServletContextAware {

    private static final Logger LOGGER = Logger.getLogger(SessionServiceImpl.class.getName());

    public SessionServiceImpl() {
    }

    //Added this constructor so that this class could be tested.
    public SessionServiceImpl(ISessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ServletContext servletContext;

    @Autowired
    private ISessionDao sessionDao;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String getContextVzSessionToken() {
        return servletContext.getAttribute(IConstant.VZ_SESSION_TOKEN) != null ?
                servletContext.getAttribute(IConstant.VZ_SESSION_TOKEN).toString() : "";
    }

    @Override
    public String getContextVzAuthorizationToken() {
        return servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN) != null ?
                servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN).toString() : "";
    }

    @Override
    public void setVzToken(Exchange exchange) {

        LOGGER.debug(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)
                + "Inside Session Set Attributes ***********************************"
                + exchange.getProperty(IConstant.VZ_SESSION_TOKEN));

        // Set to DB remove Servlet Context
        final SessionBean sessionBean = new SessionBean();
        sessionBean.setVzAuthorizationToken(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN).toString());
        sessionBean.setVzSessionToken(exchange.getProperty(IConstant.VZ_SESSION_TOKEN).toString());
        sessionBean.setIsValid("0");
        sessionDao.saveSessionBean(sessionBean);
    }

    @Override
    public void synchronizeDBContextToken() {

        LOGGER.debug("**************synchronizeDBContextToken*****************************");
        final SessionBean sessionBean = sessionDao.getSessionBean();

        if (sessionBean != null) {
            servletContext.setAttribute(IConstant.VZ_AUTHORIZATION_TOKEN, sessionBean.getVzAuthorizationToken());
            servletContext.setAttribute(IConstant.VZ_SESSION_TOKEN, sessionBean.getVzSessionToken());
        }
    }

    @Override
    public String checkToken(Exchange exchange) {
        final SessionBean sessionBean = sessionDao.getSessionBean();

        if (sessionBean == null) {
            return "true";
        }

        LOGGER.debug(sessionBean.getVzSessionToken() + "*******check Token*******" + getContextVzSessionToken());
        LOGGER.debug(sessionBean.getVzAuthorizationToken() + "*******check Token*******" + getContextVzAuthorizationToken());

        if (sessionBean.getVzSessionToken().equals(getContextVzSessionToken())
                && sessionBean.getVzAuthorizationToken().equals(getContextVzAuthorizationToken())) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public void setContextTokenInExchange(Exchange exchange) {

        if (servletContext.getAttribute(IConstant.VZ_SESSION_TOKEN) != null) {
            exchange.setProperty(IConstant.VZ_SESSION_TOKEN,
                    servletContext.getAttribute(IConstant.VZ_SESSION_TOKEN).toString());
        }
        if (servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN) != null) {
            exchange.setProperty(IConstant.VZ_AUTHORIZATION_TOKEN,
                    servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN).toString());
        }
    }

    @Override
    public void setTokenRequired() {
        // TODO Auto-generated method stub

        CommonUtil.setTokenRequired(false);
    }
}