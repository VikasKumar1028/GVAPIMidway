package com.gv.midway.processor.restoreDevice

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.restoreDevice.kore.request.RestoreDeviceRequestKore
import com.gv.midway.pojo.restoreDevice.request.{RestoreDeviceRequest, RestoreDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreRestoreDevicePreProcessor extends TestMocks with KoreSuite {

  test("process") {

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceNumber = "deviceNumber"

      val deviceId = new MidWayDeviceId
      deviceId.setId("id1")
      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(deviceId))
      val dataArea = new RestoreDeviceRequestDataArea
      dataArea.setDevices(Array(device1))
      val request = new RestoreDeviceRequest
      request.setDataArea(dataArea)

      val trans = mock[Transaction]
      when(trans.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(trans.getDevicePayload).thenReturn(request, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(trans, Nil: _*)
      val captor = ArgumentCaptor.forClass(classOf[RestoreDeviceRequestKore])

      new KoreRestoreDevicePreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/restoreDevice")
      verify(message, times(1)).setBody(captor.capture)
      val koreRequest = captor.getValue
      assert(koreRequest.getDeviceNumber === deviceId.getId)

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}