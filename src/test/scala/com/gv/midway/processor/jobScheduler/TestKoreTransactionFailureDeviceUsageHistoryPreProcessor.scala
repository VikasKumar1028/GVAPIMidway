package com.gv.midway.processor.jobScheduler

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

class TestKoreTransactionFailureDeviceUsageHistoryPreProcessor extends FunSuite with MockitoSugar {

  private val KORE_AUTH = "KORE_AUTH"

  test("KoreTransactionFailureDeviceUsageHistoryPreProcessor - happy flow") {
    withMockExchangeMessageAndEnvironment{ (exchange, message, environment) =>
      val deviceId = new DeviceId
      deviceId.setId("14")
      deviceId.setKind("kind")
      val usage = new DeviceUsage
      usage.setDeviceId(deviceId)
      usage.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      usage.setNetSuiteId(1400)

      when(message.getBody).thenReturn(usage, Nil: _*)

      new KoreTransactionFailureDeviceUsageHistoryPreProcessor(environment).process(exchange)

      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
      verify(message, times(1)).setHeader("Authorization", KORE_AUTH)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/json/queryDeviceUsageBySimNumber")
      verify(message, times(1)).setBody("""{"simNumber":"14"}""")

      verify(exchange, times(1)).setProperty("DeviceId", deviceId)
      verify(exchange, times(1)).setProperty("CarrierName", IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, usage.getNetSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }

  private def withMockExchangeMessageAndEnvironment(f: (Exchange, Message, Environment) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]
    val environment = mock[Environment]

    when(exchange.getIn).thenReturn(message)
    when(environment.getProperty(IConstant.KORE_AUTHENTICATION)).thenReturn(KORE_AUTH)

    f(exchange, message, environment)
  }
}