package com.gv.midway.processor.connectionInformation.deviceSessionBeginEndInfo

import com.gv.midway.attjasper.GetSessionInfoRequest
import com.gv.midway.pojo.connectionInformation.request.{ConnectionInformationRequest, ConnectionInformationRequestDataArea}
import com.gv.midway.pojo.verizon.DeviceId
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestATTJasperDeviceSessionBeginEndInfoPreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceId = new DeviceId()
      deviceId.setId("id1")
      deviceId.setKind("kind")

      val dataArea = new ConnectionInformationRequestDataArea
      dataArea.setDeviceId(deviceId)

      val connInfo = new ConnectionInformationRequest
      connInfo.setDataArea(dataArea)

      when(message.getBody).thenReturn(connInfo, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[GetSessionInfoRequest])

      new ATTJasperDeviceSessionBeginEndInfoPreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "GetSessionInfo"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/GetSessionInfo"
      )

      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getIccid.size() === 1)
      assert(request.getIccid.get(0) === deviceId.getId)
      assert(request.getLicenseKey === propertyValue(attJasperLicenseKey))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(attJasperVersion))

      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}