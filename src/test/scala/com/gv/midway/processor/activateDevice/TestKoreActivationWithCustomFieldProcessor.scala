package com.gv.midway.processor.activateDevice

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, NetSuiteRequestType, RequestType}
import com.gv.midway.pojo.callback.Netsuite.{KafkaNetSuiteCallBackEvent, NetSuiteCallBackProvisioningRequest}
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.springframework.core.env.Environment

class TestKoreActivationWithCustomFieldProcessor extends TestMocks {

  test("process") {
    withMockExchangeAndMessage { (exchange, message) =>

      val deviceNumber = s"""[${deviceIdJson("id1", "kind1")}, ${deviceIdJson("id2", "kind2")}]"""
      val transId = "transId"
      val netSuiteId: Integer = 9102323
      val payload = "payload!"
      val kafkaObject = new KafkaNetSuiteCallBackEvent

      when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
      when(exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD)).thenReturn(payload, Nil: _*)
      when(exchange.getProperty(IConstant.KAFKA_OBJECT)).thenReturn(kafkaObject, Nil: _*)

      val environment = mock[Environment]
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

      val captorKafkaEvent = ArgumentCaptor.forClass(classOf[KafkaNetSuiteCallBackEvent])
      val captorNetsuiteCallback = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])

      new KoreActivationWithCustomFieldProcessor(environment).process(exchange)

      verify(exchange, times(1)).setProperty(same(IConstant.KAFKA_OBJECT), captorKafkaEvent.capture())
      verify(exchange, times(1)).setProperty("script", "539")
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

      val kafkaEvent = captorKafkaEvent.getValue
      assert(kafkaEvent.getId === RequestType.CHANGECUSTOMFIELDS.toString)
      assert(kafkaEvent.getTimestamp != 0l)
      assert(kafkaEvent.getDesc != null)
      assert(kafkaEvent.getBody === payload)

      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
      verify(message, times(1)).setHeader(same("Authorization"), anyString())
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, null)
      verify(message, times(1)).setBody(captorNetsuiteCallback.capture())

      val callback = captorNetsuiteCallback.getValue
      assert(callback.getStatus === "success")
      assert(callback.getCarrierOrderNumber === transId)
      assert(callback.getNetSuiteID === netSuiteId.toString)
      assert(callback.getDeviceIds.length === 2)
      assert(callback.getResponse != null)
      assert(callback.getRequestType === NetSuiteRequestType.CUSTOM_FIELDS)
    }
  }

  private def deviceIdJson(id: String, kind: String) = s"""{"id":"$id","kind":"$kind"}"""
}