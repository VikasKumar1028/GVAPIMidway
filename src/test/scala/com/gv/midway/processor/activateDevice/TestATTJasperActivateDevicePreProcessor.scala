package com.gv.midway.processor.activateDevice

import org.apache.cxf.binding.soap.SoapHeader
import com.gv.midway.TestMocks
import com.gv.midway.attjasper.EditTerminalRequest
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.activateDevice.request.{ActivateDeviceId, ActivateDeviceRequest, ActivateDeviceRequestDataArea, ActivateDevices}
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.ExchangePattern
import org.apache.camel.component.cxf.common.message.CxfConstants
import org.mockito.ArgumentCaptor
import org.apache.cxf.headers.Header
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestATTJasperActivateDevicePreProcessor extends TestMocks {

  test("process") {
    val deviceNumber = "deviceNumber"
    val netSuiteId: Integer = 349349
    val version = "version"
    val licenseKey = "licenseKey"

    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>
      List(
        ("attJasper.version", version)
        , ("attJasper.licenseKey", licenseKey)
        , ("attJasper.userName", "user")
        , ("attJasper.password", "password")
      ).foreach { case (k, v) =>
          when(environment.getProperty(k)).thenReturn(v, Nil: _*)
      }

      val id1 = new ActivateDeviceId("id", "kind")
      val devices = new ActivateDevices
      devices.setDeviceIds(Array(id1))
      val dataArea = new ActivateDeviceRequestDataArea
      dataArea.setDevices(devices)
      val adRequest = new ActivateDeviceRequest
      adRequest.setDataArea(dataArea)

      val transaction = mock[Transaction]
      when(transaction.getDevicePayload).thenReturn(adRequest, Nil: _*)
      when(transaction.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
      when(transaction.getNetSuiteId).thenReturn(netSuiteId, Nil: _*)

      when(message.getBody(classOf[Transaction])).thenReturn(transaction, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[EditTerminalRequest])

      new ATTJasperActivateDevicePreProcessor(environment).process(exchange)

      verify(message, times(1)).setBody(captor.capture())
      verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAME, "EditTerminal")
      verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAMESPACE, "http://api.jasperwireless.com/ws/schema")
      verify(message, times(1)).setHeader("soapAction", "http://api.jasperwireless.com/ws/service/terminal/EditTerminal")
      verify(message, times(1)).setHeader(same(Header.HEADER_LIST), any(classOf[List[SoapHeader]]))

      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

      val request = captor.getValue
      assert(request.getIccid === id1.getId)
      assert(request.getChangeType === IConstant.ATTJASPER_SIM_CHANGETYPE)
      assert(request.getTargetValue === IConstant.ATTJASPER_ACTIVATED)
      assert(request.getLicenseKey === licenseKey)
      assert(request.getMessageId != null)
      assert(request.getVersion === version)
    }
  }
}