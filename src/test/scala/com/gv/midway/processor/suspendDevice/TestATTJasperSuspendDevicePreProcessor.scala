package com.gv.midway.processor.suspendDevice

import com.gv.midway.attjasper.EditTerminalRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.suspendDevice.request.{SuspendDeviceRequest, SuspendDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestATTJasperSuspendDevicePreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceNumber = "deviceNumber"

      val id1 = new MidWayDeviceId("id1", null)

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(id1))

      val dataArea = new SuspendDeviceRequestDataArea
      dataArea.setDevices(Array(device1))

      val payload = new SuspendDeviceRequest
      payload.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(transaction.getDevicePayload).thenReturn(payload, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[EditTerminalRequest])

      new ATTJasperSuspendDevicePreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "EditTerminal"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/EditTerminal"
      )

      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getChangeType === IConstant.ATTJASPER_SIM_CHANGETYPE)
      assert(request.getTargetValue === IConstant.ATTJASPER_DEACTIVATED)
      assert(request.getIccid === id1.getId)
      assert(request.getLicenseKey === propertyValue(attJasperLicenseKey))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(attJasperVersion))

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}