package com.gv.midway.processor.customFieldsDevice

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.customFieldsDevice.request.{CustomFieldsDeviceRequest, CustomFieldsDeviceRequestDataArea}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.springframework.core.env.Environment

class TestKoreCustomFieldsPreProcessor extends TestMocks {

  test("process") {
    withMockExchangeAndMessage { (exchange, message) =>

      val deviceId = new MidWayDeviceId()
      deviceId.setId("id")
      deviceId.setKind("kind")

      val device1 = new MidWayDevices
      device1.setDeviceIds(Array(deviceId))

      val customField1 = new CustomFieldsToUpdate("CustomField1", "v1")
      val customField2 = new CustomFieldsToUpdate("CustomField2", "v2")
      val customField3 = new CustomFieldsToUpdate("CustomField3", "v3")
      val customField4 = new CustomFieldsToUpdate("CustomField4", "v4")
      val customField5 = new CustomFieldsToUpdate("CustomField5", "v5")
      val customField6 = new CustomFieldsToUpdate("CustomField6", "v6")
      val customField7 = new CustomFieldsToUpdate("invalid key", "v7")

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

      val koreAuth = "koreAuth"
      val environment = mock[Environment]
      when(environment.getProperty(IConstant.KORE_AUTHENTICATION)).thenReturn(koreAuth)

      val captor = ArgumentCaptor.forClass(classOf[CustomFieldsDeviceRequestKore])

      new KoreCustomFieldsPreProcessor(environment).process(exchange)

      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
      verify(message, times(1)).setHeader("Authorization", koreAuth)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/json/modifyDeviceCustomInfo")
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