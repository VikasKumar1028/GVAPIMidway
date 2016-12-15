package com.gv.midway.processor.jobScheduler

import com.gv.midway.attjasper.GetTerminalDetailsRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import com.gv.midway.pojo.verizon.DeviceId
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestATTJasperDeviceUsageHistoryPreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceId = new DeviceId()
      deviceId.setId("id1")
      deviceId.setKind("ESN")

      val deviceInfo = new DeviceInformation
      deviceInfo.setDeviceIds(Array(deviceId))
      deviceInfo.setNetSuiteId(349834)
      deviceInfo.setBs_carrier(IConstant.BSCARRIER_SERVICE_ATTJASPER)

      val captor = ArgumentCaptor.forClass(classOf[GetTerminalDetailsRequest])
      val deviceCaptor = ArgumentCaptor.forClass(classOf[DeviceId])

      when(message.getBody()).thenReturn(deviceInfo, Nil: _*)

      new ATTJasperDeviceUsageHistoryPreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "GetTerminalDetails"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails")

      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getIccids.getIccid.size() === 1)
      assert(request.getLicenseKey === propertyValue(IConstant.ATTJASPER_LICENSE_KEY))
      assert(request.getVersion === propertyValue(IConstant.ATTJASPER_VERSION))
      assert(request.getMessageId != null)

      verify(exchange, times(1)).setProperty(same("DeviceId"), deviceCaptor.capture())
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, deviceInfo.getNetSuiteId)
      verify(exchange, times(1)).setProperty("CarrierName", deviceInfo.getBs_carrier)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
      assert(deviceCaptor.getValue.getId === deviceId.getId)
      assert(deviceCaptor.getValue.getKind === deviceId.getKind)
    }
  }
}