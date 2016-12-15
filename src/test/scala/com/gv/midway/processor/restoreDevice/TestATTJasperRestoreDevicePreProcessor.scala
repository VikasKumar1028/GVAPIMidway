package com.gv.midway.processor.restoreDevice

import com.gv.midway.attjasper.EditTerminalRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.restoreDevice.request.{RestoreDeviceRequest, RestoreDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestATTJasperRestoreDevicePreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val netSuiteId: Integer = 324342
      val deviceNumber = "deviceNumber"

      val id1 = new MidWayDeviceId("id1", "kind")

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(id1))

      val dataArea = new RestoreDeviceRequestDataArea
      dataArea.setDevices(Array(device1))

      val payload = new RestoreDeviceRequest
      payload.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getNetSuiteId).thenReturn(netSuiteId, Nil: _*)
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(transaction.getDevicePayload).thenReturn(payload, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction)

      val captor = ArgumentCaptor.forClass(classOf[EditTerminalRequest])

      new ATTJasperRestoreDevicePreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "EditTerminal"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/EditTerminal"
      )

      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getIccid === id1.getId)
      assert(request.getChangeType === IConstant.ATTJASPER_SIM_CHANGETYPE)
      assert(request.getTargetValue === IConstant.ATTJASPER_ACTIVATED)
      assert(request.getLicenseKey === propertyValue(IConstant.ATTJASPER_LICENSE_KEY))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(IConstant.ATTJASPER_VERSION))

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}