package com.gv.midway.processor.customFieldsDevice

import com.gv.midway.attjasper.EditTerminalRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.customFieldsDevice.request.{CustomFieldsDeviceRequest, CustomFieldsDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.pojo.{KeyValuePair, MidWayDeviceId, MidWayDevices}
import com.gv.midway.{ATTJasperSuite, TestMocks}
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestATTJasperCustomFieldDevicePreProcessor extends TestMocks with ATTJasperSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceNumber = "deviceNumber"

      val deviceId = new MidWayDeviceId("id1", "kind")

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(deviceId))
      val custom1 = new KeyValuePair("CustomField6", "value1")

      val dataArea = new CustomFieldsDeviceRequestDataArea
      dataArea.setDevices(Array(device1))
      dataArea.setCustomFieldsToUpdate(Array(custom1))

      val payload = new CustomFieldsDeviceRequest
      payload.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(transaction.getDevicePayload).thenReturn(payload, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[EditTerminalRequest])

      new ATTJasperCustomFieldDevicePreProcessor(environment).process(exchange)

      assertMessageHeaderATTJasper(message
        , "EditTerminal"
        , "http://api.jasperwireless.com/ws/schema"
        , "http://api.jasperwireless.com/ws/service/terminal/EditTerminal")

      verify(message, times(1)).setBody(captor.capture())
      val request = captor.getValue
      assert(request.getChangeType === 75)
      assert(request.getTargetValue === custom1.getValue)
      assert(request.getIccid === deviceId.getId)
      assert(request.getLicenseKey === propertyValue(IConstant.ATTJASPER_LICENSE_KEY))
      assert(request.getMessageId != null)
      assert(request.getVersion === propertyValue(IConstant.ATTJASPER_VERSION))

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, transaction.getDeviceNumber)
      verify(exchange, times(1)).setProperty(IConstant.ATT_CUSTOMFIELD_TO_UPDATE, custom1.getKey)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}