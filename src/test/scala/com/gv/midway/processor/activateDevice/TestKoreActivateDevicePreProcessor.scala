package com.gv.midway.processor.activateDevice

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.activateDevice.kore.request.ActivateDeviceRequestKore
import com.gv.midway.pojo.activateDevice.request.{ActivateDeviceId, ActivateDeviceRequest, ActivateDeviceRequestDataArea, ActivateDevices}
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreActivateDevicePreProcessor extends TestMocks with KoreSuite {

  test("process") {
    val deviceNumber = "deviceNumber"

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val id1 = new ActivateDeviceId("id1id", "kind1kind")
      val devices = new ActivateDevices
      devices.setDeviceIds(Array(id1))
      devices.setServicePlan("servicePlan")
      val dataArea = new ActivateDeviceRequestDataArea
      dataArea.setDevices(devices)
      val request = new ActivateDeviceRequest
      request.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getDevicePayload).thenReturn(request, Nil: _*)
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction)

      val captor = ArgumentCaptor.forClass(classOf[ActivateDeviceRequestKore])

      new KoreActivateDevicePreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/activateDevice")
      verify(message, times(1)).setBody(captor.capture())

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

      val koreRequest = captor.getValue
      assert(koreRequest.getDeviceNumber === id1.getId)
      assert(koreRequest.getEAPCode === devices.getServicePlan)
    }
  }
}