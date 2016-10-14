package com.gv.midway.processor.jobScheduler

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Matchers._

class TestVerizonDeviceConnectionHistoryPreProcessor extends FunSuite with MockitoSugar {

  private val sessionToken = "sessionToken"
  private val authToken = "authToken"

  test("VerizonDeviceConnectionHistoryPreProcessor.process") {
    withMockExchangeAndMessage{ (exchange, message) =>

      val id = "my device id"
      val kind = "ESN"
      val earliest = "2016-10-01"
      val latest = "2016-10-31"

      val bodyCaptor = ArgumentCaptor.forClass(classOf[String])
      val deviceIdCaptor = ArgumentCaptor.forClass(classOf[DeviceId])

      when(exchange.getProperty("jobEndTime")).thenReturn(latest, Nil: _*)
      when(exchange.getProperty("jobStartTime")).thenReturn(earliest, Nil: _*)

      val deviceId = new DeviceId
      deviceId.setKind(kind)
      deviceId.setId(id)
      val deviceInfo = new DeviceInformation
      deviceInfo.setBs_carrier(IConstant.BSCARRIER_SERVICE_VERIZON)
      deviceInfo.setNetSuiteId(14)
      deviceInfo.setDeviceIds(Array(deviceId))
      when(message.getBody).thenReturn(deviceInfo, Nil: _*)

      new VerizonDeviceConnectionHistoryPreProcessor().process(exchange)

      verify(exchange, times(1)).setProperty(same("DeviceId"), deviceIdCaptor.capture())
      verify(exchange, times(1)).setProperty("CarrierName", deviceInfo.getBs_carrier)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory")
      verify(message, times(1)).setBody(bodyCaptor.capture())

      val capturedDeviceId = deviceIdCaptor.getValue
      val jsonBody = bodyCaptor.getValue

      assert(capturedDeviceId.getId === deviceId.getId)
      assert(capturedDeviceId.getKind === deviceId.getKind)
      assert(jsonBody === s"""{"deviceId":{"id":"$id","kind":"$kind"},"earliest":"$earliest","latest":"$latest"}""")
    }
  }

  private def withMockExchangeAndMessage(f: (Exchange, Message) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]

    when(exchange.getIn).thenReturn(message)
    when(exchange.getProperty(IConstant.VZ_SEESION_TOKEN)).thenReturn(sessionToken, Nil: _*)
    when(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)).thenReturn(authToken, Nil: _*)

    f(exchange, message)
  }
}