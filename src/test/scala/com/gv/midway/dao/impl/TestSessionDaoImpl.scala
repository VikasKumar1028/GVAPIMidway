package com.gv.midway.dao.impl

import com.gv.midway.pojo.session.SessionBean
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query

class TestSessionDaoImpl extends FunSuite with MockitoSugar {

  test("getSessionBean") {
    withDao { (template, dao) =>

      val mockSession = mock[SessionBean]

      when(template.findOne(any(classOf[Query]), same(classOf[SessionBean]))).thenReturn(mockSession)

      val session = dao.getSessionBean

      assert(session === mockSession)
    }
  }

  test("saveSessionBean - null") {
    withDao { (template, dao) =>
      val mockSessionBean = mock[SessionBean]

      when(template.findOne(any(classOf[Query]), same(classOf[SessionBean]))).thenReturn(null)

      dao.saveSessionBean(mockSessionBean)

      verify(mockSessionBean, times(1)).setIpAddress(anyString())
      verify(template, times(1)).save(mockSessionBean)
      verify(template, times(1)).save(any(classOf[SessionBean]))
    }
  }

  test("saveSessionBean - not null") {
    withDao { (template, dao) =>
      val mockSessionBean = mock[SessionBean]
      val dbMockSessionBean = mock[SessionBean]

      when(template.findOne(any(classOf[Query]), same(classOf[SessionBean]))).thenReturn(dbMockSessionBean)

      dao.saveSessionBean(mockSessionBean)

      verify(mockSessionBean, times(1)).setIpAddress(anyString())
      verify(dbMockSessionBean, times(1)).setIsValid("1")
      verify(template, times(1)).save(dbMockSessionBean)
      verify(template, times(1)).save(mockSessionBean)
      verify(template, times(2)).save(any(classOf[SessionBean]))
    }
  }


  private def withDao(f: (MongoTemplate, SessionDaoImpl) => Unit): Unit = {
    val template = mock[MongoTemplate]

    val dao = new SessionDaoImpl()
    dao.mongoTemplate = template

    f(template, dao)
  }

}