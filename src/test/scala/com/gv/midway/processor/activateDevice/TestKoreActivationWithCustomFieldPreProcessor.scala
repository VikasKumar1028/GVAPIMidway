package com.gv.midway.processor.activateDevice

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore
import com.gv.midway.pojo.{MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.customFieldsDevice.request.{CustomFieldsDeviceRequest, CustomFieldsDeviceRequestDataArea}
import com.gv.midway.pojo.verizon.CustomFieldsToUpdate
import org.apache.camel.Exchange
import org.mockito.ArgumentCaptor
import org.springframework.core.env.Environment
import org.mockito.Mockito._

class TestKoreActivationWithCustomFieldPreProcessor extends TestMocks {

  test("process") {
    withMockExchangeAndMessage { (exchange, message) =>

      val customField1 = new CustomFieldsToUpdate("CustomField1", "v1")
      val customField2 = new CustomFieldsToUpdate("CustomField2", "v2")
      val customField3 = new CustomFieldsToUpdate("CustomField3", "v3")
      val customField4 = new CustomFieldsToUpdate("CustomField4", "v4")
      val customField5 = new CustomFieldsToUpdate("CustomField5", "v5")
      val customField6 = new CustomFieldsToUpdate("CustomField6", "v6")
      val customField7 = new CustomFieldsToUpdate("invalid key", "v7")

      val deviceId = new MidWayDeviceId
      deviceId.setId("id!")

      val midWayDevices = new MidWayDevices
      midWayDevices.setDeviceIds(Array(deviceId))

      val dataArea = new CustomFieldsDeviceRequestDataArea
      dataArea.setCustomFieldsToUpdate(Array(customField1, customField2, customField3, customField4, customField5, customField6, customField7))
      dataArea.setDevices(Array(midWayDevices))

      val payload = new CustomFieldsDeviceRequest
      payload.setDataArea(dataArea)

      when(exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMEFIELD_PAYLOAD)).thenReturn(payload, Nil: _*)

      val auth = "auth"

      val environment = mock[Environment]
      when(environment.getProperty(IConstant.KORE_AUTHENTICATION)).thenReturn(auth)

      val captor = ArgumentCaptor.forClass(classOf[CustomFieldsDeviceRequestKore])

      new KoreActivationWithCustomFieldPreProcessor(environment).process(exchange)

      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
      verify(message, times(1)).setHeader("Authorization", auth)
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
    }
  }
}