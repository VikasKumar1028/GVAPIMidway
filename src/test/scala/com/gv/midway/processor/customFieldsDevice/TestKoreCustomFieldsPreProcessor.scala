package com.gv.midway.processor.customFieldsDevice

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore
import com.gv.midway.pojo.{KeyValuePair, MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.customFieldsDevice.request.{CustomFieldsDeviceRequest, CustomFieldsDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreCustomFieldsPreProcessor extends TestMocks with KoreSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val deviceId = new MidWayDeviceId("id", "kind")

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(deviceId))

      val customField1 = new KeyValuePair("CustomField1", "v1")
      val customField2 = new KeyValuePair("CustomField2", "v2")
      val customField3 = new KeyValuePair("CustomField3", "v3")
      val customField4 = new KeyValuePair("CustomField4", "v4")
      val customField5 = new KeyValuePair("CustomField5", "v5")
      val customField6 = new KeyValuePair("CustomField6", "v6")
      val customField7 = new KeyValuePair("invalid key", "v7")

      val dataArea = new CustomFieldsDeviceRequestDataArea
      dataArea.setDevices(Array(device1))
      dataArea.setCustomFieldsToUpdate(Array(customField1, customField2, customField3, customField4, customField5, customField6, customField7))

      val payload = new CustomFieldsDeviceRequest
      payload.setDataArea(dataArea)

      val deviceNumber = "deviceNumber"

      val trans = mock[Transaction]
      when(trans.getDevicePayload).thenReturn(payload, Nil: _*)
      when(trans.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(trans, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[CustomFieldsDeviceRequestKore])

      new KoreCustomFieldsPreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/modifyDeviceCustomInfo")
      verify(message, times(1)).setBody(captor.capture())

      val request = captor.getValue

      assert(request.getDeviceNumber === deviceId.getId)
      assert(request.getCustomField1 === customField1.getValue)
      assert(request.getCustomField2 === customField2.getValue)
      assert(request.getCustomField3 === customField3.getValue)
      assert(request.getCustomField4 === customField4.getValue)
      assert(request.getCustomField5 === customField5.getValue)
      assert(request.getCustomField6 === customField6.getValue)

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}