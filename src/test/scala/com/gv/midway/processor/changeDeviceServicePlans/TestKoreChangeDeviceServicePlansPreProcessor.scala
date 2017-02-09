package com.gv.midway.processor.changeDeviceServicePlans

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.changeDeviceServicePlans.kore.request.ChangeDeviceServicePlansRequestKore
import com.gv.midway.pojo.changeDeviceServicePlans.request.{ChangeDeviceServicePlansRequest, ChangeDeviceServicePlansRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.{KoreSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreChangeDeviceServicePlansPreProcessor extends TestMocks with KoreSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceNumber = "deviceNumber"
      val deviceId = new MidWayDeviceId
      deviceId.setId("id1")
      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(deviceId))
      val dataArea = new ChangeDeviceServicePlansRequestDataArea
      dataArea.setDevices(Array(device1))
      dataArea.setServicePlan("servicePlan")
      val request = new ChangeDeviceServicePlansRequest
      request.setDataArea(dataArea)

      val trans = mock[Transaction]
      when(trans.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(trans.getDevicePayload).thenReturn(request, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(trans, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[ChangeDeviceServicePlansRequestKore])

      new KoreChangeDeviceServicePlansPreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/modifyDevicePlanForNextPeriod")
      verify(message, times(1)).setBody(captor.capture)
      val koreRequest = captor.getValue
      assert(koreRequest.getDeviceNumber === deviceId.getId)
      assert(koreRequest.getPlanCode === dataArea.getServicePlan)

      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
    }
  }
}