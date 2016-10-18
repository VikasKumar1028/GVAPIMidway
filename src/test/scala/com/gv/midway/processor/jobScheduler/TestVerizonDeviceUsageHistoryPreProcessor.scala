package com.gv.midway.processor.jobScheduler

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestVerizonDeviceUsageHistoryPreProcessor extends TestMocks {

  test("happy flow") {
    withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

      val startTime = "2016-10-01"
      val endTime = "2016-10-10"
      val netSuiteId: java.lang.Integer = 900
      val currentServicePlan = "currentServicePlan"
      val id = "id"
      val kind = "kind"
      val deviceId = new DeviceId
      deviceId.setKind(kind)
      deviceId.setId(id)

      val deviceInfo = new DeviceInformation
      deviceInfo.setDeviceIds(Array(deviceId))
      deviceInfo.setBs_carrier(IConstant.BSCARRIER_SERVICE_VERIZON)
      deviceInfo.setNetSuiteId(netSuiteId)
      deviceInfo.setCurrentServicePlan(currentServicePlan)

      when(exchange.getProperty(IConstant.VZ_SEESION_TOKEN)).thenReturn("sessionToken", Nil: _*)
      when(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)).thenReturn("authToken", Nil: _*)
      when(exchange.getProperty("jobStartTime")).thenReturn(startTime, Nil: _*)
      when(exchange.getProperty("jobEndTime")).thenReturn(endTime, Nil: _*)

      when(message.getBody).thenReturn(deviceInfo, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceId])

      new VerizonDeviceUsageHistoryPreProcessor().process(exchange)

      verify(exchange, times(1)).setProperty(same("DeviceId"), captor.capture())
      val deviceIdAnswer = captor.getValue
      assert(deviceIdAnswer.getId === deviceId.getId)
      assert(deviceIdAnswer.getKind === deviceId.getKind)

      verify(exchange, times(1)).setProperty("CarrierName", IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)
      verify(exchange, times(1)).setProperty("ServicePlan", currentServicePlan)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list")
      verify(message, times(1)).setBody(s"""{"deviceId":{"id":"$id","kind":"$kind"},"earliest":"$startTime","latest":"$endTime"}""")
    }
  }
}