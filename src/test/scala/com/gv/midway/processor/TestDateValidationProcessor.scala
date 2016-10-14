package com.gv.midway.processor

import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.exception.MissingParameterException
import com.gv.midway.pojo.connectionInformation.request.{ConnectionInformationRequest, ConnectionInformationRequestDataArea}
import com.gv.midway.pojo.usageInformation.request.{UsageInformationRequest, UsageInformationRequestDataArea}
import org.apache.camel.{Endpoint, Exchange, Message}
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class TestDateValidationProcessor extends FunSuite with MockitoSugar {

  private val validEarliestDate = "2016-09-01T00:00:00"
  private val validLatestDate = "2016-10-01T00:00:00"

  testDateValidationProcessor("Endpoint[direct://retrieveDeviceUsageHistoryCarrier]", usageRequest)

  testDateValidationProcessor("Endpoint[direct://deviceConnectionStatus]", connectionRequest)

  testDateValidationProcessor("Endpoint[direct://deviceSessionBeginEndInfo]", connectionRequest)

  private def testDateValidationProcessor[A](name: String, f: (String, String) => (A, Class[A])): Unit = {
    test(s"$name valid") {
      val (a, clazz) = f(validEarliestDate, validLatestDate)
      withMockExchangeAndMessage(name) { (exchange, message) =>
        when(message.getBody(clazz)).thenReturn(a)

        new DateValidationProcessor().process(exchange)
      }
    }

    test(s"$name invalid with nulls") {
      val (a, clazz) = f(null, null)
      withMockExchangeAndMessage(name) { (exchange, message) =>
        when(message.getBody(clazz)).thenReturn(a)
        assertMissingParameterException(exchange)
      }
    }

    test(s"$name invalid with invalid earliest date") {
      val (a, clazz) = f("abc", validLatestDate)
      withMockExchangeAndMessage(name) { (exchange, message) =>
        when(message.getBody(clazz)).thenReturn(a)
        assertMissingParameterException(exchange)
      }
    }

    test(s"$name invalid with invalid latest date") {
      val (a, clazz) = f(validEarliestDate, "abc")
      withMockExchangeAndMessage(name) { (exchange, message) =>
        when(message.getBody(clazz)).thenReturn(a)
        assertMissingParameterException(exchange)
      }
    }
  }

  private def assertMissingParameterException(exchange: Exchange) = {
    assertThrows[MissingParameterException] {
      new DateValidationProcessor().process(exchange)
    }
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD)
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_MESSAGE)
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_DESCRIPTION_DATE_MIDWAYDB)
  }

  private def usageRequest(earliest: String, latest: String): (UsageInformationRequest, Class[UsageInformationRequest]) = {
    val dataArea = new UsageInformationRequestDataArea()
    dataArea.setEarliest(earliest)
    dataArea.setLatest(latest)
    val usageRequest = new UsageInformationRequest()
    usageRequest.setDataArea(dataArea)
    (usageRequest, classOf[UsageInformationRequest])
  }

  private def connectionRequest(earliest: String, latest: String): (ConnectionInformationRequest, Class[ConnectionInformationRequest]) = {
    val dataArea = new ConnectionInformationRequestDataArea
    dataArea.setEarliest(earliest)
    dataArea.setLatest(latest)
    val connectionRequest = new ConnectionInformationRequest
    connectionRequest.setDataArea(dataArea)
    (connectionRequest, classOf[ConnectionInformationRequest])
  }

  private def withMockExchangeAndMessage(endpointName: String)(f: (Exchange, Message) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]
    val endpoint = mock[Endpoint]

    when(exchange.getIn()).thenReturn(message)
    when(endpoint.toString).thenReturn("generic endpoint")
    when(exchange.getFromEndpoint).thenReturn(endpoint)
    when(endpoint.toString).thenReturn(endpointName)

    f(exchange, message)
  }
}