package com.gv.midway.service.impl

import com.gv.midway.TestMocks
import com.gv.midway.dao.IAuditDao
import org.apache.camel.Exchange
import org.mockito.Mockito._

class TestAuditServiceImpl extends TestMocks {

  testThisClass("auditExternalRequestCall") { (service, dao) => exchange =>
    service.auditExternalRequestCall(exchange)
    verify(dao, times(1)).auditExternalRequestCall(exchange)
  }

  testThisClass("auditExternalResponseCall") { (service, dao) => exchange =>
    service.auditExternalResponseCall(exchange)
    verify(dao, times(1)).auditExternalResponseCall(exchange)
  }

  testThisClass("auditExternalSOAPResponseCall") { (service, dao) => exchange =>
    service.auditExternalSOAPResponseCall(exchange)
    verify(dao, times(1)).auditExternalSOAPResponseCall(exchange)
  }

  testThisClass("auditExternalExceptionResponseCall") { (service, dao) => exchange =>
    service.auditExternalExceptionResponseCall(exchange)
    verify(dao, times(1)).auditExternalExceptionResponseCall(exchange)
  }

  testThisClass("auditExternalSOAPExceptionResponseCall") { (service, dao) => exchange =>
    service.auditExternalSOAPExceptionResponseCall(exchange)
    verify(dao, times(1)).auditExternalSOAPExceptionResponseCall(exchange)
  }

  testThisClass("auditExternalConnectionExceptionResponseCall") { (service, dao) => exchange =>
    service.auditExternalConnectionExceptionResponseCall(exchange)
    verify(dao, times(1)).auditExternalConnectionExceptionResponseCall(exchange)
  }

  private def testThisClass(name: String)(f: (AuditServiceImpl, IAuditDao) => Exchange => Unit): Unit = {
    test(name) {
      val dao = mock[IAuditDao]
      val service = new AuditServiceImpl(dao)
      val exchange = mock[Exchange]

      f(service, dao)(exchange)
    }
  }
}