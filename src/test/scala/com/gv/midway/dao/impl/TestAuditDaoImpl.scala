package com.gv.midway.dao.impl

import javax.xml.namespace.QName

import com.gv.midway.attjasper.ActivateSimPPUResponse
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.audit.Audit
import com.sun.org.apache.xpath.internal.NodeSet
import org.apache.camel.component.cxf.CxfOperationException
import org.apache.camel.{Endpoint, Exchange, Message}
import org.apache.cxf.binding.soap.SoapFault
import org.apache.cxf.message.MessageContentsList
import org.mockito.ArgumentCaptor
import org.scalatest.FunSuite
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.springframework.data.mongodb.core.MongoTemplate
import org.w3c.dom.Element

class TestAuditDaoImpl extends FunSuite with MockitoSugar {
  private val _BusinessResponse = "_BusinessResponse"
  private val _BusinessRequest = "_BusinessRequest"
  private val _BusinessExternalError = "_BusinessExternalError"
  private val _BusinessConnectionError = "_BusinessConnectionError"
  private val stringPayload = "test"

  private val errorJson = """{"errorCode" : "14", "errorMessage": "This is my error code"}"""

  private val SOAPPayload = {
    val t = new ActivateSimPPUResponse()
    t.setCorrelationId("1")

    new MessageContentsList(t)
  }

  private val exception = {
    val e = mock[CxfOperationException]
    when(e.getResponseBody).thenReturn(errorJson, Nil: _*)
    e
  }

  testAudit("auditExternalRequestCall", _BusinessRequest, stringPayload)(_.auditExternalRequestCall(_))

  testAudit("auditExternalResponseCall", _BusinessResponse, stringPayload)(_.auditExternalResponseCall(_))

  testAudit("auditExternalSOAPResponseCall", _BusinessResponse, SOAPPayload)(_.auditExternalSOAPResponseCall(_))

  testAuditException("auditExternalSOAPExceptionResponseCall", _BusinessExternalError, mockSoapFault("141414", "404"))(_.auditExternalSOAPExceptionResponseCall(_)){ audit =>
    assert(audit.getErrorProblem === IConstant.CARRIER_TRANSACTION_STATUS_ERROR)
    assert(audit.getErrorCode === 141414)
    assert(audit.getErrorDetails === IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE)
  }

  testAuditException("auditExternalConnectionExceptionResponseCall", _BusinessConnectionError, "Some error")(_.auditExternalConnectionExceptionResponseCall(_)){ audit =>
    assert(audit.getErrorProblem === IConstant.RESPONSE_STATUS)
    assert(audit.getErrorCode === IResponse.CONNECTION_ERROR_CODE)
    assert(audit.getErrorDetails === IConstant.RESPONSE_DESCRIPTION)
  }

  testAuditException("auditExternalExceptionResponseCall - Verizon", _BusinessExternalError, exception)(_.auditExternalExceptionResponseCall(_)){ audit =>
    assert(audit.getErrorCode === 14)
    assert(audit.getErrorProblem === IConstant.CARRIER_TRANSACTION_STATUS_ERROR)
    assert(audit.getErrorDetails === "This is my error code")
  }

  testAuditException("auditExternalExceptionResponseCall - Kore", _BusinessExternalError, exception, IConstant.BSCARRIER_SERVICE_KORE)(_.auditExternalExceptionResponseCall(_)){ audit =>
    assert(audit.getErrorCode === 14)
    assert(audit.getErrorProblem === IConstant.CARRIER_TRANSACTION_STATUS_ERROR)
    assert(audit.getErrorDetails === "This is my error code")
  }

  private def mockSoapFault(message: String, faultCode: String): SoapFault = {

    val fault = mock[SoapFault]
    val faultDetail = mock[Element]

    when(fault.getMessage).thenReturn(message)
    when(fault.getFaultCode).thenReturn(new QName(faultCode))
    when(fault.getDetail).thenReturn(faultDetail)
    when(faultDetail.getChildNodes).thenReturn(new NodeSet())
    fault
  }

  private def withMocks(name: String, payload: AnyRef)(f: (Props, Exchange, MongoTemplate) => Unit): Unit = {
    val sourceName = "source name"
    val endpointName = s"test//$name]"
    val transactionId = "audit transaction id"
    val gvTransactionId = "gv transaction id"
    val gvHostName = "gv host name"
    val deviceNumber = "141414"

    val mongoTemplate = mock[MongoTemplate]
    val exchange = mock[Exchange]
    val endpoint = mock[Endpoint]
    val message = mock[Message]
    when(endpoint.toString).thenReturn(endpointName)
    when(exchange.getFromEndpoint).thenReturn(endpoint)
    when(exchange.getIn).thenReturn(message)
    when(exchange.getProperty(IConstant.SOURCE_NAME)).thenReturn(sourceName, Nil: _*)
    when(exchange.getProperty(IConstant.AUDIT_TRANSACTION_ID)).thenReturn(transactionId, Nil: _*)
    when(exchange.getProperty(IConstant.GV_TRANSACTION_ID)).thenReturn(gvTransactionId, Nil: _*)
    when(exchange.getProperty(IConstant.GV_HOSTNAME)).thenReturn(gvHostName, Nil: _*)
    when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
    when(message.getBody).thenReturn(payload, Nil: _*)

    f(Props(sourceName, endpointName, transactionId, gvTransactionId, gvHostName, deviceNumber), exchange, mongoTemplate)
  }

  private def withAudit(mongoTemplate: MongoTemplate, exchange: Exchange)(e: (AuditDaoImpl, Exchange) => Unit)(f: Audit => Unit): Unit = {
    val dao = new AuditDaoImpl
    dao.mongoTemplate = mongoTemplate

    val auditCaptor = ArgumentCaptor.forClass(classOf[Audit])

    e(dao, exchange)

    verify(mongoTemplate, times(1)).insert(auditCaptor.capture())

    f(auditCaptor.getValue)
  }

  private def withAuditSave(mongoTemplate: MongoTemplate, exchange: Exchange)(e: (AuditDaoImpl, Exchange) => Unit)(f: Audit => Unit): Unit = {
    val dao = new AuditDaoImpl
    dao.mongoTemplate = mongoTemplate

    val auditCaptor = ArgumentCaptor.forClass(classOf[Audit])

    e(dao, exchange)

    verify(mongoTemplate, times(1)).save(auditCaptor.capture())

    f(auditCaptor.getValue)
  }

  private def testAudit(name: String, reqResDistinction: String, payload: AnyRef)(f: (AuditDaoImpl, Exchange) => Unit): Unit = {
    test(s"test $name happy flow") {
      withMocks(name, payload) { (props, exchange, mongoTemplate) =>
        withAudit(mongoTemplate, exchange)(f) { audit =>
          assertStandardAudit(audit, props, s"GV_$name${reqResDistinction}_deviceNumber_${props.deviceNumber}")
        }
      }
    }
  }

  private def testAuditException(name: String, reqResDistinction: String, exception: AnyRef, carrier: String = IConstant.BSCARRIER_SERVICE_VERIZON)
                                (f: (AuditDaoImpl, Exchange) => Unit)
                                (additionalAuditAssertions: Audit => Unit) = {
    test(s"test $name happy flow") {
      withMocks(name, "This isn't used") { (props, exchange, mongoTemplate) =>

        when(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME)).thenReturn(carrier, Nil: _*)
        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
        when(exchange.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE)).thenReturn(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE, Nil: _*)
        when(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION)).thenReturn(IConstant.RESPONSE_DESCRIPTION, Nil: _*)
        when(exchange.getProperty(IConstant.RESPONSE_STATUS)).thenReturn(IConstant.RESPONSE_STATUS, Nil: _*)

        withAuditSave(mongoTemplate, exchange)(f) { audit =>
          assertStandardAudit(audit, props, s"GV_$name${reqResDistinction}_deviceNumber_${props.deviceNumber}")

          assert(audit.getErrorProblem != null)
          assert(audit.getErrorCode != null)
          assert(audit.getErrorDetails != null)
          additionalAuditAssertions(audit)
        }
      }
    }
  }

  private def assertStandardAudit(audit: Audit, props: Props, operationName: String): Unit = {
    assert(audit.getApiOperationName === operationName)
    assert(audit.getFrom === props.sourceName)
    assert(audit.getTo === props.endpointName)
    assert(audit.getTimeStamp != null)
    assert(audit.getAuditTransactionId === props.transactionId)
    assert(audit.getGvTransactionId === props.gvTransactionId)
    assert(audit.getHostName === props.gvHostName)
    assert(audit.getPayload != null)
  }
}

private case class Props(sourceName: String, endpointName: String, transactionId: String, gvTransactionId: String, gvHostName: String, deviceNumber: String)