package com.gv.midway.processor.jobScheduler

import com.gv.midway.constant.IConstant
import com.gv.midway.exception.KoreSimMissingException
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

class TestKoreDeviceUsageHistoryPreProcessor extends FunSuite with MockitoSugar {

  private val TEST_KORE_AUTH = "TEST_KORE_AUTH"
  private val netSuiteId = 14

  test("missing SIM exception") {
    withMockExchangeMessageAndEnvironment(getDeviceInformation("Non-SIM")) { (exchange, message, env) =>
      assertThrows[KoreSimMissingException] {
        new KoreDeviceUsageHistoryPreProcessor(env).process(exchange)
        fail("Expected exception not thrown")
      }
    }
  }

  test("valid SIM") {
    val info = getDeviceInformation()

    withMockExchangeMessageAndEnvironment(info) { (exchange, message, env) =>

      new KoreDeviceUsageHistoryPreProcessor(env).process(exchange)

      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
      verify(message, times(1)).setHeader("Authorization", TEST_KORE_AUTH)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/json/queryDeviceUsageBySimNumber")
      val captor = ArgumentCaptor.forClass(classOf[String])
      verify(message, times(1)).setBody(captor.capture())
      assert(captor.getValue === """{"simNumber":"1"}""")

      verify(exchange, times(1)).setProperty("DeviceId", info.getDeviceIds.toList.head)
      verify(exchange, times(1)).setProperty("CarrierName", IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }

  private def withMockExchangeMessageAndEnvironment(info: DeviceInformation = getDeviceInformation())(f: (Exchange, Message, Environment) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]
    val environment = mock[Environment]

    when(exchange.getIn).thenReturn(message)
    when(message.getBody).thenReturn(info, Nil: _*)
    when(environment.getProperty(IConstant.KORE_AUTHENTICATION)).thenReturn(TEST_KORE_AUTH)

    f(exchange, message, environment)
  }

  private def getDeviceIdArray(kind: String = "SIM"): Array[DeviceId] = {
    val deviceId = new DeviceId
    deviceId.setId("1")
    deviceId.setKind(kind)
    Array(deviceId)
  }

  private def getDeviceInformation(kind: String = "SIM"): DeviceInformation = {
    val deviceInformation = new DeviceInformation
    deviceInformation.setDeviceIds(getDeviceIdArray(kind))
    deviceInformation.setBs_carrier(IConstant.BSCARRIER_SERVICE_VERIZON)
    deviceInformation.setNetSuiteId(netSuiteId)
    deviceInformation
  }
}