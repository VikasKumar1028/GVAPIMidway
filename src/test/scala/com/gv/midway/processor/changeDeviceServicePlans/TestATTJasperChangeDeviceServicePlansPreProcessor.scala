package com.gv.midway.processor.changeDeviceServicePlans

import com.gv.midway.attjasper.EditTerminalRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.changeDeviceServicePlans.request.{ChangeDeviceServicePlansRequest, ChangeDeviceServicePlansRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestATTJasperChangeDeviceServicePlansPreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val servicePlan = "servicePlan"
      val deviceNumber = "deviceNumber"

      val id1 = new MidWayDeviceId("id1", null)

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(id1))

      val dataArea = new ChangeDeviceServicePlansRequestDataArea
      dataArea.setServicePlan(servicePlan)
      dataArea.setDevices(Array(device1))

      val payload = new ChangeDeviceServicePlansRequest
      payload.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getDevicePayload).thenReturn(payload, Nil: _*)
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction)

      val captor = ArgumentCaptor.forClass(classOf[EditTerminalRequest])

      new ATTJasperChangeDeviceServicePlansPreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "EditTerminal"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/EditTerminal"
      )
      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getChangeType === IConstant.ATTJASPER_RATE_PLAN_CHANGETYPE)
      assert(request.getTargetValue === servicePlan)
      assert(request.getIccid === id1.getId)
      assert(request.getLicenseKey === propertyValue(attJasperLicenseKey))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(attJasperVersion))

      verify(exchange, times(1)).setProperty(IConstant.ATT_SERVICEPLAN_TO_UPDATE, servicePlan)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}