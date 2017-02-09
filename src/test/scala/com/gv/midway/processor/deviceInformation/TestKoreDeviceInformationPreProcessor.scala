package com.gv.midway.processor.deviceInformation

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.request.{DeviceInformationRequest, DeviceInformationRequestDataArea}
import com.gv.midway.pojo.verizon.DeviceId
import net.sf.json.JSONObject
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreDeviceInformationPreProcessor extends TestMocks with KoreSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val netSuiteId: Integer = 121212

      val deviceId = new DeviceId
      deviceId.setId("id1")
      val dataArea = new DeviceInformationRequestDataArea
      dataArea.setDeviceId(deviceId)
      dataArea.setNetSuiteId(netSuiteId)
      val request = new DeviceInformationRequest
      request.setDataArea(dataArea)

      when(message.getBody(classOf[DeviceInformationRequest])).thenReturn(request, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[JSONObject])

      new KoreDeviceInformationPreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/queryDevice")
      verify(message, times(1)).setBody(captor.capture())
      val json = captor.getValue
      assert(json.get("deviceNumber") ===  deviceId.getId)

      verify(exchange, times(1)).setProperty(IConstant.KORE_SIM_NUMBER, deviceId.getId)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)
    }
  }
}