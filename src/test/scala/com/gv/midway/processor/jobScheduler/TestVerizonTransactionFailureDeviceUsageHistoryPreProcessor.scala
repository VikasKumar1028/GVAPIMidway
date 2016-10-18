package com.gv.midway.processor.jobScheduler

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestVerizonTransactionFailureDeviceUsageHistoryPreProcessor extends TestMocks {

  test("happy flow") {

    withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

      val deviceId = new DeviceId
      deviceId.setId("id")
      deviceId.setKind("kind")

      val deviceUsage = new DeviceUsage
      deviceUsage.setDeviceId(deviceId)
      deviceUsage.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      deviceUsage.setNetSuiteId(83939)
      val startTime = "2016-10-02"
      val endTime = "2016-10-17"

      when(exchange.getProperty("jobStartTime")).thenReturn(startTime, Nil: _*)
      when(exchange.getProperty("jobEndTime")).thenReturn(endTime, Nil: _*)
      when(message.getBody).thenReturn(deviceUsage, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceId])

      new VerizonTransactionFailureDeviceUsageHistoryPreProcessor().process(exchange)

      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/devices/usage/actions/list")
      verify(message, times(1)).setBody(s"""{"deviceId":{"id":"${deviceId.getId}","kind":"${deviceId.getKind}"},"earliest":"$startTime","latest":"$endTime"}""")

      verify(exchange, times(1)).setProperty(same("DeviceId"), captor.capture())
      verify(exchange, times(1)).setProperty("CarrierName", deviceUsage.getCarrierName)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceUsage.getNetSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}