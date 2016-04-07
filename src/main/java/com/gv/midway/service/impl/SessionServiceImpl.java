package com.gv.midway.service.impl;

import javax.servlet.ServletContext;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.ISessionDao;
import com.gv.midway.pojo.session.SessionBean;
import com.gv.midway.service.ISessionService;

@Service
public class SessionServiceImpl implements ISessionService,ServletContextAware {

//	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ISessionDao sessionDao;
	
	
	public void setServletContext(ServletContext servletContext) {
	     this.servletContext = servletContext;
	}

	public String getContextVzSessionToken() {

		return servletContext.getAttribute(IConstant.VZ_SEESION_TOKEN) != null ? servletContext
				.getAttribute(IConstant.VZ_SEESION_TOKEN).toString() : "";

	}

	public String getContextVzAuthorizationToken() {

		return servletContext.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN) != null ? servletContext
				.getAttribute(IConstant.VZ_AUTHORIZATION_TOKEN).toString() : "";

	}

	public void setVzToken(Exchange exchange) {

		System.out.println(exchange
				.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)
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

	public void synchronizeDBContextToken() {
		
		System.out.println("**************synchronizeDBContextToken*****************************");

		SessionBean sessionBean = sessionDao.getSessionBean();

		if (sessionBean != null) {
			servletContext.setAttribute(IConstant.VZ_AUTHORIZATION_TOKEN,
					sessionBean.getVzAuthorizationToken());

			servletContext.setAttribute(IConstant.VZ_SEESION_TOKEN,
					sessionBean.getVzSessionToken());
		}

	}

	public String checkToken(Exchange exchange) {

		SessionBean sessionBean = sessionDao.getSessionBean();
		if (sessionBean == null)
			return "true";

		System.out.println(sessionBean.getVzSessionToken()
				+ "*******check Token*******" + getContextVzSessionToken());
		System.out.println(sessionBean.getVzAuthorizationToken()
				+ "*******check Token*******"
				+ getContextVzAuthorizationToken());

		if (sessionBean.getVzSessionToken().equals(getContextVzSessionToken())
				&& sessionBean.getVzAuthorizationToken().equals(
						getContextVzAuthorizationToken()))
			return "true";
		else
			return "false";

	}

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