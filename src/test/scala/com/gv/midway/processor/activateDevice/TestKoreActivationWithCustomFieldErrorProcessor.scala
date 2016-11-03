package com.gv.midway.processor.activateDevice

import java.util

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, NetSuiteRequestType, RequestType}
import com.gv.midway.pojo.{BaseRequest, Header}
import com.gv.midway.pojo.callback.Netsuite.{KafkaNetSuiteCallBackError, NetSuiteCallBackProvisioningRequest}
import com.gv.midway.pojo.kore.KoreErrorResponse
import org.apache.camel.component.cxf.CxfOperationException
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.springframework.core.env.Environment
import org.mockito.Matchers._

class TestKoreActivationWithCustomFieldErrorProcessor extends TestMocks {

  private val deviceNumber = s"""[${deviceIdJson("id1", "kind1")}, ${deviceIdJson("id2", "kind2")}]"""
  private val transId = "transId"
  private val netSuiteId: Integer = 2328372
  private val kafkaObj = new KafkaNetSuiteCallBackError
  kafkaObj.setException("this is my kafka exception")
  private val errorMessage = "This is my error message"

  test("process - kafka object") {

    val payload = "payload"

    withMockExchangeMessageAndLoadedEnvironment(payload) { (exchange, message, environment) =>

      when(exchange.getProperty(IConstant.KAFKA_OBJECT)).thenReturn(kafkaObj, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])

      new KoreActivationWithCustomFieldErrorProcessor(environment).process(exchange)

      assert(kafkaObj.getId === RequestType.CHANGECUSTOMFIELDS.toString)
      assert(kafkaObj.getTimestamp != null)
      assert(kafkaObj.getDesc != null)
      assert(kafkaObj.getBody === payload)

      messageAsserts(message)
      verify(message, times(1)).setBody(captor.capture())

      requestAsserts(captor.getValue)

      verify(exchange, times(1)).setProperty("script", "539")
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }

  nonKafkaTest("process - general exception", new Exception("Boom!"))

  nonKafkaTest("process - CxfOperationException", new CxfOperationException("URI", 400, "status", "location", new util.HashMap[String, String](), s"""{"errorCode":"14","errorMessage":"$errorMessage"}"""))

  def nonKafkaTest(name: String, errorObject: Exception): Unit = {
    test(name) {
      val header = new Header
      header.setTransactionId(transId)
      val payload = new BaseRequest
      payload.setHeader(header)

      withMockExchangeMessageAndLoadedEnvironment(payload) { (exchange, message, environment) =>

        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(errorObject, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])
        val callbackCaptor = ArgumentCaptor.forClass(classOf[KafkaNetSuiteCallBackError])

        new KoreActivationWithCustomFieldErrorProcessor(environment).process(exchange)

        messageAsserts(message)
        verify(message, times(1)).setBody(captor.capture())
        requestAsserts(captor.getValue)

        verify(exchange, times(1)).setProperty("script", "539")
        verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
        verify(exchange, times(1)).setProperty(same(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERROR_DESCRIPTION), anyString())
        verify(exchange, times(1)).setProperty(same(IConstant.KAFKA_OBJECT), callbackCaptor.capture())

        val callback = callbackCaptor.getValue
        assert(callback.getApp === "Midway")
        assert(callback.getCategory === "Kore Call Back Error")
        assert(callback.getId === RequestType.CHANGECUSTOMFIELDS.toString)
        assert(callback.getLevel === "Error")
        assert(callback.getTimestamp != null)
        assert(callback.getVersion === "1")
        assert(callback.getException != null)
        assert(callback.getMsg === "Error in Call Back from Kore.")
        assert(callback.getDesc != null)
        assert(callback.getBody === payload)
        val keyValues = callback.getKeyValues
        assert(keyValues != null)
        assert(keyValues.length === 4)
        assert(keyValues.exists{kv => kv.getK == "transactionId" && kv.getV == header.getTransactionId})

        errorObject match {
          case cxf: CxfOperationException =>
            verify(exchange, times(1)).setProperty(same(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_ERRORPAYLOAD), any(classOf[KoreErrorResponse]))
          case _ =>
            //Nothing extra
        }
      }
    }
  }

  private def withMockExchangeMessageAndLoadedEnvironment(payload: Object)(f: (Exchange, Message, Environment) => Unit): Unit = {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
      when(exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD)).thenReturn(payload, Nil: _*)

      List(
        ("netSuite.oauthConsumerKey", "key")
        , ("netSuite.oauthTokenId", "tokenId")
        , ("netSuite.oauthTokenSecret", "tokenSecret")
        , ("netSuite.oauthConsumerSecret", "consumerSecret")
        , ("netSuite.realm", "realm")
        , ("netSuite.endPoint", "endpoint")
      ).foreach { case (k, v) =>
        when(environment.getProperty(k)).thenReturn(v, Nil: _*)
      }

      f(exchange, message, environment)
    }
  }

  private def messageAsserts(message: Message): Unit = {
    verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    verify(message, times(1)).setHeader(same("Authorization"), anyString())
    verify(message, times(1)).setHeader(Exchange.HTTP_PATH, null)
  }

  private def requestAsserts(request: NetSuiteCallBackProvisioningRequest): Unit = {
    assert(request.getResponse != null)
    assert(request.getStatus === "fail")
    assert(request.getCarrierOrderNumber === transId)
    assert(request.getNetSuiteID === netSuiteId.toString)
    assert(request.getDeviceIds.length === 2)
    assert(request.getRequestType === NetSuiteRequestType.CUSTOM_FIELDS)
  }
}