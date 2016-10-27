package com.gv.midway.audit

import java.util.EventObject

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.{BaseResponse, Header, Response}
import com.gv.midway.pojo.audit.Audit
import com.gv.midway.pojo.callback.TargetResponse
import com.gv.midway.pojo.job.JobinitializedResponse
import org.apache.camel.Endpoint
import org.apache.camel.management.event.ExchangeCompletedEvent
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.springframework.data.mongodb.core.MongoTemplate

class TestAuditLogResponseEventNotifier extends TestMocks {

  test("notify - invalid event type") {

    withMockExchangeAndMessage { (exchange, message) =>

      val event = mock[EventObject]

      when(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)).thenThrow(new RuntimeException("Boom!"))  //This should not trigger

      new AuditLogResponseEventNotifer().notify(event)

    }
  }

  List(
    ("string", "")
    , ("JobinitializedResponse", new JobinitializedResponse)
    , ("TargetResponse", new TargetResponse)
  ).foreach{ case (name, response) =>

    test(s"notify - invalid response - $name") {
      withMockExchangeAndMessage { (exchange, message) =>
        val event = new ExchangeCompletedEvent(exchange)

        when(message.getBody).thenReturn(response, Nil: _*)

        when(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)).thenThrow(new RuntimeException("Boom!"))  //This should not trigger

        new AuditLogResponseEventNotifer().notify(event)
      }
    }
  }


  testNotify("happy flow", "endpoint", IResponse.SUCCESS_CODE)

  testNotify("non-success code", "endpoint", IResponse.DB_ERROR_CODE)

  testNotify("non-success code and jobResponse endpoint", "endpoint", IResponse.DB_ERROR_CODE)

  private def testNotify(name: String, endpointName: String, responseCode: Integer): Unit = {
    test(s"notify - $name") {
      withMockExchangeAndMessage { (exchange, message) =>

        val header = new Header
        header.setSourceName("sourceName")

        val response = new Response()
        response.setResponseCode(responseCode)
        response.setResponseDescription("DESC")
        response.setResponseStatus("status")

        val baseResponse = new BaseResponse
        baseResponse.setHeader(header)
        baseResponse.setResponse(response)
        val transId = "transId"
        val gvTransId = "gvTransId"
        val hostName = "hostName"

        val endpointName = "endpoint"
        val endpointUrl = s"test//$endpointName"
        val endpoint = mock[Endpoint]
        when(endpoint.toString).thenReturn(endpointUrl, Nil: _*)
        when(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
        when(exchange.getProperty(IConstant.GV_TRANSACTION_ID)).thenReturn(gvTransId, Nil: _*)
        when(exchange.getProperty(IConstant.GV_HOSTNAME)).thenReturn(hostName, Nil: _*)
        when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)
        when(message.getBody).thenReturn(baseResponse, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[Audit])
        val event: EventObject = new ExchangeCompletedEvent(exchange)
        val template = mock[MongoTemplate]
        val notifier = new AuditLogResponseEventNotifer
        notifier.mongoTemplate = template
        notifier.notify(event)

        verify(template, times(1)).save(captor.capture())

        val audit = captor.getValue
        if ("jobResponse" == endpointName) {
          assert(audit.getApiOperationName === null)
        } else {
          assert(audit.getApiOperationName === s"GV_${endpointName}_ProxyResponse")
          assert(audit.getFrom === header.getSourceName)
          assert(audit.getTo === endpointUrl)
          assert(audit.getTimeStamp != null)
          assert(audit.getAuditTransactionId === transId)
          assert(audit.getGvTransactionId === gvTransId)
          assert(audit.getHostName === hostName)
          assert(audit.getPayload === baseResponse)
        }

        if (responseCode == IResponse.SUCCESS_CODE) {
          assert(audit.getErrorDetails === null)
          assert(audit.getErrorProblem === null)
          assert(audit.getErrorCode === null)
        } else {
          assert(audit.getErrorDetails === response.getResponseDescription)
          assert(audit.getErrorProblem === response.getResponseStatus)
          assert(audit.getErrorCode === responseCode)
        }
      }
    }
  }

}