package com.gv.midway.processor.deactivateDevice

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deactivateDevice.kore.request.DeactivateDeviceRequestKore
import com.gv.midway.pojo.deactivateDevice.request.{DeactivateDeviceId, DeactivateDeviceRequest, DeactivateDeviceRequestDataArea, DeactivateDevices}
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreDeactivateDevicePreProcessor extends TestMocks with KoreSuite {

  List(
    Some(true)
    , Some(false)
    , None
  ).foreach { optB =>
    test(s"process - flag scrap => $optB") {
      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        val deviceNumber = "deviceNumber"

        val deviceId = new DeactivateDeviceId
        deviceId.setId("id1")
        optB.foreach{ deviceId.setFlagScrap(_) }
        val device1 = new DeactivateDevices
        device1.setDeviceIds(Array(deviceId))
        val dataArea = new DeactivateDeviceRequestDataArea
        dataArea.setDevices(Array(device1))
        val request = new DeactivateDeviceRequest
        request.setDataArea(dataArea)
        val trans = mock[Transaction]
        when(trans.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
        when(trans.getDevicePayload).thenReturn(request, Nil: _*)

        when(message.getBody(classOf[Transaction])).thenReturn(trans, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[DeactivateDeviceRequestKore])
        new KoreDeactivateDevicePreProcessor(environment).process(exchange)

        assertKoreRequest(message, "/json/deactivateDevice")
        verify(message, times(1)).setBody(captor.capture())
        val koreRequest = captor.getValue
        assert(koreRequest.getDeviceNumber === deviceId.getId)
        optB match {
          case Some(b) =>
            assert(koreRequest.getFlagScrap === b)
          case None =>
            assert(!koreRequest.getFlagScrap)
        }

        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
        verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
      }
    }
  }
}