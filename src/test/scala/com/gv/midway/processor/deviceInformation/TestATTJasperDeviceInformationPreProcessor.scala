package com.gv.midway.processor.deviceInformation

import com.gv.midway.attjasper.GetTerminalDetailsRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.request.{DeviceInformationRequest, DeviceInformationRequestDataArea}
import com.gv.midway.pojo.verizon.DeviceId
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.apache.cxf.headers.Header
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestATTJasperDeviceInformationPreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceId = new DeviceId
      deviceId.setId("id1")
      deviceId.setKind("kind")

      val dataArea = new DeviceInformationRequestDataArea
      dataArea.setNetSuiteId(349834)
      dataArea.setDeviceId(deviceId)

      val deviceInformation = new DeviceInformationRequest
      deviceInformation.setDataArea(dataArea)

      when(message.getBody(classOf[DeviceInformationRequest])).thenReturn(deviceInformation, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[GetTerminalDetailsRequest])

      new ATTJasperDeviceInformationPreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "GetTerminalDetails"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/GetTerminalDetails")

      verify(message, times(1)).setHeader(same(Header.HEADER_LIST), anyString())
      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getIccids.getIccid.size() === 1)
      assert(request.getLicenseKey === propertyValue(IConstant.ATTJASPER_LICENSE_KEY))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(IConstant.ATTJASPER_VERSION))

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, dataArea.getNetSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}