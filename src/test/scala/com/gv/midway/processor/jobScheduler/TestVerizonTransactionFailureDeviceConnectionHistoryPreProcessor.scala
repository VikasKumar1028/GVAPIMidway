package com.gv.midway.processor.jobScheduler

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceConnection
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestVerizonTransactionFailureDeviceConnectionHistoryPreProcessor extends TestMocks {

  test("happy flow") {
    withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

      val deviceId = new DeviceId
      deviceId.setId("id")
      deviceId.setKind("kind")

      val deviceConn = new DeviceConnection
      deviceConn.setDeviceId(deviceId)
      deviceConn.setNetSuiteId(9000)
      deviceConn.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      val startTime = "2016-10-01"
      val endTime = "2016-10-17"


      when(message.getBody).thenReturn(deviceConn, Nil: _*)

      when(exchange.getProperty("jobStartTime")).thenReturn(startTime, Nil: _*)
      when(exchange.getProperty("jobEndTime")).thenReturn(endTime, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceId])

      new VerizonTransactionFailureDeviceConnectionHistoryPreProcessor().process(exchange)

      verify(exchange, times(1)).setProperty(same("DeviceId"), captor.capture())

      val deviceIdResponse = captor.getValue

      assert(deviceIdResponse.getId === deviceId.getId)
      assert(deviceIdResponse.getKind === deviceId.getKind)

      verify(exchange, times(1)).setProperty("CarrierName", IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceConn.getNetSuiteId)
      verify(message, times(1)).setBody(s"""{"deviceId":{"id":"${deviceId.getId}","kind":"${deviceId.getKind}"},"earliest":"$startTime","latest":"$endTime"}""")
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/devices/connections/actions/listHistory")
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}