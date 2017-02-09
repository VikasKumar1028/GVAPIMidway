package com.gv.midway.service.impl

import javax.servlet.ServletContext

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.dao.ISessionDao
import com.gv.midway.pojo.session.SessionBean
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.mockito._

class TestSessionServiceImpl extends TestMocks {

  override def exchangeProperties: List[String] = List(IConstant.VZ_AUTHORIZATION_TOKEN, IConstant.VZ_SESSION_TOKEN)

  private val defaultAttributes: List[(String, String)] = List(
    IConstant.VZ_SESSION_TOKEN -> propertyValue(IConstant.VZ_SESSION_TOKEN)
    , IConstant.VZ_AUTHORIZATION_TOKEN -> propertyValue(IConstant.VZ_AUTHORIZATION_TOKEN)
  )

  test("getContextVzSessionToken - null") {
    withSessionServiceContextAndSessionDAO(List()) { (service, context, sessionDAO) =>
      val token = service.getContextVzSessionToken
      assert(token === "")
    }
  }

  test("getContextVzSessionToken - not null") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      val token = service.getContextVzSessionToken
      assert(token === propertyValue(IConstant.VZ_SESSION_TOKEN))
    }
  }

  test("getContextVzAuthorizationToken - null") {
    withSessionServiceContextAndSessionDAO(List()) { (service, context, sessionDAO) =>
      val token = service.getContextVzAuthorizationToken
      assert(token === "")
    }
  }

  test("getContextVzAuthorizationToken - not null") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      val token = service.getContextVzAuthorizationToken
      assert(token === propertyValue(IConstant.VZ_AUTHORIZATION_TOKEN))
    }
  }

  test("setVzToken") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      withMockExchange { exchange =>
        val captor = ArgumentCaptor.forClass(classOf[SessionBean])
        service.setVzToken(exchange)
        verify(sessionDAO, times(1)).saveSessionBean(captor.capture())
        val sessionBean = captor.getValue
        assert(sessionBean.getVzAuthorizationToken === propertyValue(IConstant.VZ_AUTHORIZATION_TOKEN))
        assert(sessionBean.getVzSessionToken === propertyValue(IConstant.VZ_SESSION_TOKEN))
        assert(sessionBean.getIsValid === "0")
      }
    }
  }

  test("synchronizeDBContextToken - null SessionBean") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      when(sessionDAO.getSessionBean).thenReturn(null, Nil: _*)
      service.synchronizeDBContextToken()
      verify(context, times(0)).setAttribute(anyString(), any())
    }
  }

  test("synchronizeDBContextToken - non-null SessionBean") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      val authToken = "authToken"
      val sessionToken = "sessionToken"
      val sessionBean = mock[SessionBean]
      when(sessionBean.getVzAuthorizationToken).thenReturn(authToken, Nil: _*)
      when(sessionBean.getVzSessionToken).thenReturn(sessionToken, Nil: _*)
      when(sessionDAO.getSessionBean).thenReturn(sessionBean, Nil: _*)
      service.synchronizeDBContextToken()
      verify(context, times(1)).setAttribute(IConstant.VZ_AUTHORIZATION_TOKEN, authToken)
      verify(context, times(1)).setAttribute(IConstant.VZ_SESSION_TOKEN, sessionToken)
    }
  }

  test("checkToken - null SessionBean") {
    testCheckToken(null, "true")
  }

  test("checkToken - non null SessionBean, matching tokens") {
    val sessionBean = mock[SessionBean]
    when(sessionBean.getVzAuthorizationToken).thenReturn(propertyValue(IConstant.VZ_AUTHORIZATION_TOKEN), Nil: _*)
    when(sessionBean.getVzSessionToken).thenReturn(propertyValue(IConstant.VZ_SESSION_TOKEN), Nil: _*)
    testCheckToken(sessionBean, "true")
  }

  test("checkToken - non null SessionBean, non-matching tokens") {
    val sessionBean = mock[SessionBean]
    when(sessionBean.getVzAuthorizationToken).thenReturn(IConstant.VZ_AUTHORIZATION_TOKEN, Nil: _*)
    when(sessionBean.getVzSessionToken).thenReturn(IConstant.VZ_SESSION_TOKEN, Nil: _*)
    testCheckToken(sessionBean, "false")
  }

  test("setContextTokenInExchange - null tokens") {
    withSessionServiceContextAndSessionDAO(List()) { (service, context, sessionDAO) =>
      withMockExchange { exchange =>
        service.setContextTokenInExchange(exchange)
        verify(exchange, times(0)).setProperty(anyString(), any())
      }
    }
  }

  test("setContextTokenInExchange - non null tokens") {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      withMockExchange { exchange =>
        service.setContextTokenInExchange(exchange)
        verify(exchange, times(1)).setProperty(IConstant.VZ_SESSION_TOKEN, propertyValue(IConstant.VZ_SESSION_TOKEN))
        verify(exchange, times(1)).setProperty(IConstant.VZ_AUTHORIZATION_TOKEN, propertyValue(IConstant.VZ_AUTHORIZATION_TOKEN))
      }
    }
  }

  private def testCheckToken(sessionBean: SessionBean, expected: String): Unit = {
    withSessionServiceContextAndSessionDAO() { (service, context, sessionDAO) =>
      withMockExchange { exchange =>
        when(sessionDAO.getSessionBean).thenReturn(sessionBean, Nil: _*)
        assert(service.checkToken(exchange) === expected)
      }
    }
  }

  private def withSessionServiceContextAndSessionDAO(attributes: List[(String, String)] = defaultAttributes)
                                                    (f: (SessionServiceImpl, ServletContext, ISessionDao) => Unit): Unit = {

    val servletContext = mock[ServletContext]
    attributes.foreach { case (k, v) =>
      when(servletContext.getAttribute(k)).thenReturn(v, Nil: _*)
    }
    val sessionDAO = mock[ISessionDao]

    val service = new SessionServiceImpl(sessionDAO)
    service.setServletContext(servletContext)

    f(service, servletContext, sessionDAO)
  }
}