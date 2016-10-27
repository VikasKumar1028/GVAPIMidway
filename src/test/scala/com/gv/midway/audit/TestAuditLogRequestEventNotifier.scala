package com.gv.midway.audit

import java.util.EventObject

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.audit.Audit
import com.gv.midway.pojo.{BaseRequest, Header}
import org.apache.camel.Endpoint
import org.apache.camel.management.event.ExchangeCreatedEvent
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.springframework.data.mongodb.core.MongoTemplate

class TestAuditLogRequestEventNotifier extends TestMocks {

  test("notify - happy flow") {
    withMockExchangeAndMessage { (exchange, message) =>

      val header = new Header
      header.setBsCarrier(IConstant.BSCARRIER_SERVICE_VERIZON)
      header.setSourceName("sourceName")
      header.setApplicationName("appName")
      header.setRegion("region")
      header.setTimestamp("timestamp")
      header.setOrganization("organization")
      header.setTransactionId("transId")
      val request = new BaseRequest
      request.setHeader(header)
      when(message.getBody).thenReturn(request, Nil: _*)

      val endpointName = "endpoint"
      val endpointUrl = s"test//$endpointName"
      val endpoint = mock[Endpoint]
      when(endpoint.toString).thenReturn(endpointUrl, Nil: _*)

      when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)
      when(exchange.getProperty(IConstant.GV_TRANSACTION_ID)).thenReturn(header.getTransactionId, Nil: _*)
      when(exchange.getProperty(IConstant.GV_HOSTNAME)).thenReturn("hostname", Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[Audit])

      val event: EventObject = new ExchangeCreatedEvent(exchange)
      val notifier = new AuditLogRequestEventNotifer()
      val template = mock[MongoTemplate]
      notifier.mongoTemplate = template
      notifier.notify(event)

      verify(exchange, times(1)).setProperty(same(IConstant.AUDIT_TRANSACTION_ID), anyString())
      verify(exchange, times(1)).setProperty(IConstant.HEADER, header)
      verify(exchange, times(1)).setProperty(same(IConstant.GV_HOSTNAME), anyString())
      verify(template, times(1)).save(captor.capture())

      val audit = captor.getValue
      assert(audit.getApiOperationName === s"GV_${endpointName}_ProxyRequest")
      assert(audit.getFrom === header.getSourceName)
      assert(audit.getTo === endpointUrl)
      assert(audit.getTimeStamp != null)
      assert(audit.getAuditTransactionId != null)
      assert(audit.getGvTransactionId != null)
      assert(audit.getHostName != null)
      assert(audit.getPayload === request)
    }
  }

  test("notify - AUDIT_TRANSACTION_ID present") {

    withMockExchangeAndMessage { (exchange, message) =>

      val request = new BaseRequest

      when(message.getBody).thenReturn(request, Nil: _*)
      when(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)).thenReturn("TRANS_ID", Nil: _*)

      val event: EventObject = new ExchangeCreatedEvent(exchange)

      new AuditLogRequestEventNotifer().notify(event)

      verify(exchange, times(0)).setProperty(same(IConstant.AUDIT_TRANSACTION_ID), anyString())
    }
  }
}