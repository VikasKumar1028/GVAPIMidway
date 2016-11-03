package com.gv.midway.processor.callbacks

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, NetSuiteRequestType}
import com.gv.midway.pojo.callback.Netsuite.{KafkaNetSuiteCallBackError, NetSuiteCallBackProvisioningRequest}
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestCallbackPostProcessor extends TestMocks {

  List(
    NetSuiteRequestType.ACTIVATION
    , NetSuiteRequestType.DEACTIVATION
    , NetSuiteRequestType.SUSPENSION
    , NetSuiteRequestType.RESTORATION
    , NetSuiteRequestType.SERVICE_PLAN
    , NetSuiteRequestType.CUSTOM_FIELDS
  ).foreach { `type` =>
    test(s"process - ${`type`}") {

      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        Map(
          "netSuite.oauthConsumerKey" -> "oauthKey"
          , "netSuite.oauthTokenId" -> "tokenId"
          , "netSuite.oauthTokenSecret" -> "tokenSecret"
          , "netSuite.oauthConsumerSecret" -> "consumerSecret"
          , "netSuite.realm" -> "realm"
          , "netSuite.endPoint" -> "endPoint"
        ).foreach { case (k, v) =>
          when(environment.getProperty(k)).thenReturn(v, Nil: _*)
        }

        val request = new NetSuiteCallBackProvisioningRequest
        request.setRequestType(`type`)

        when(message.getBody).thenReturn(request, Nil: _*)

        new CallbackPostProcessor(environment).process(exchange)

        commonAsserts(exchange, message)
        verify(message, times(1)).setHeader(same("Authorization"), notNull)

        verify(exchange, times(1)).setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-alerts")
      }
    }
  }

  test("process - REACTIVATION") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>
      val request = new NetSuiteCallBackProvisioningRequest
      request.setRequestType(NetSuiteRequestType.REACTIVATION)

      val kafkaObj = new KafkaNetSuiteCallBackError

      when(message.getBody).thenReturn(request, Nil: _*)
      when(exchange.getProperty(IConstant.KAFKA_OBJECT)).thenReturn(kafkaObj, Nil: _*)

      new CallbackPostProcessor(environment).process(exchange)

      commonAsserts(exchange, message)
      verify(message, times(1)).setHeader(same("Authorization"), isNull)

      verify(exchange, times(1)).setProperty(IConstant.KAFKA_TOPIC_NAME, "midway-app-errors")
    }
  }

  private def commonAsserts(exchange: Exchange, message: Message): Unit = {
    verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")

    verify(exchange, times(1)).setProperty("script", "539")
    verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
  }
}