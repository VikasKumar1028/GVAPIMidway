package com.gv.midway.processor.activateDevice

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.customFieldsDevice.kore.request.CustomFieldsDeviceRequestKore
import com.gv.midway.pojo.{KeyValuePair, MidWayDeviceId, MidWayDevices}
import com.gv.midway.pojo.customFieldsDevice.request.{CustomFieldsDeviceRequest, CustomFieldsDeviceRequestDataArea}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreActivationWithCustomFieldPreProcessor extends TestMocks with KoreSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val customField1 = new KeyValuePair("CustomField1", "v1")
      val customField2 = new KeyValuePair("CustomField2", "v2")
      val customField3 = new KeyValuePair("CustomField3", "v3")
      val customField4 = new KeyValuePair("CustomField4", "v4")
      val customField5 = new KeyValuePair("CustomField5", "v5")
      val customField6 = new KeyValuePair("CustomField6", "v6")
      val customField7 = new KeyValuePair("invalid key", "v7")

      val deviceId = new MidWayDeviceId("id!", "kind!")

      val midWayDevices = new MidWayDevices
      midWayDevices.setDeviceIds(Array(deviceId))

      val dataArea = new CustomFieldsDeviceRequestDataArea
      dataArea.setCustomFieldsToUpdate(Array(customField1, customField2, customField3, customField4, customField5, customField6, customField7))
      dataArea.setDevices(Array(midWayDevices))

      val payload = new CustomFieldsDeviceRequest
      payload.setDataArea(dataArea)

      when(exchange.getProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_PAYLOAD)).thenReturn(payload, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[CustomFieldsDeviceRequestKore])

      new KoreActivationWithCustomFieldPreProcessor(environment).process(exchange)

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
    }
  }
}