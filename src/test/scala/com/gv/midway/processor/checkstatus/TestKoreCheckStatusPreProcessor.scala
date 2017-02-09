package com.gv.midway.processor.checkstatus

import com.gv.midway.constant.{IConstant, ITransaction, RecordType, RequestType}
import com.gv.midway.pojo.KeyValuePair
import com.gv.midway.pojo.activateDevice.request.{ActivateDeviceId, ActivateDeviceRequest, ActivateDeviceRequestDataArea, ActivateDevices}
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.{KoreSuite, TestMocks}
import net.sf.json.JSONObject
import org.apache.camel.ExchangePattern
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestKoreCheckStatusPreProcessor extends TestMocks with KoreSuite {

  List(
    (IConstant.CARRIER_TRANSACTION_STATUS_ERROR, RequestType.ACTIVATION, RecordType.PRIMARY)
    , (IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS, RequestType.ACTIVATION, RecordType.SECONDARY)
    , (IConstant.CARRIER_TRANSACTION_STATUS_PENDING, RequestType.CHANGECUSTOMFIELDS, RecordType.PRIMARY)
  ).foreach { case (status, requestType, recordType) =>

    test(s"process - $requestType - $recordType") {

      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        val deviceNumber = "deviceNumber"
        val midwayTransId = "midwayTransId"
        val netSuiteId: Integer = 42398349
        val errorDesc = "errorDesc"
        val deviceId = new ActivateDeviceId("id1", "kind1")
        val field1 = new KeyValuePair("k1", "v1")
        val devices = new ActivateDevices
        devices.setNetSuiteId(netSuiteId)
        devices.setDeviceIds(Array(deviceId))
        devices.setCustomFields(Array(field1))
        val dataArea = new ActivateDeviceRequestDataArea
        dataArea.setDevices(devices)
        val payload = new ActivateDeviceRequest
        payload.setDataArea(dataArea)
        val carrierTransId = "carrierTransId"
        val callbackPayload = "callbackPayload"

        val trans = mock[Transaction]
        when(trans.getDeviceNumber).thenReturn(deviceNumber, Nil: _*)
        when(trans.getMidwayTransactionId).thenReturn(midwayTransId, Nil: _*)
        when(trans.getRequestType).thenReturn(requestType, Nil: _*)
        when(trans.getCarrierStatus).thenReturn(status, Nil: _*)
        when(trans.getCarrierErrorDescription).thenReturn(errorDesc, Nil: _*)
        when(trans.getRecordType).thenReturn(recordType, Nil: _*)
        when(trans.getDevicePayload).thenReturn(payload, Nil: _*)
        when(trans.getNetSuiteId).thenReturn(netSuiteId, Nil: _*)
        when(trans.getCarrierTransactionId).thenReturn(carrierTransId, Nil: _*)
        when(trans.getCallBackPayload).thenReturn(callbackPayload, Nil: _*)

        when(message.getBody(classOf[Transaction])).thenReturn(trans, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[JSONObject])
        val customFieldsCaptor = ArgumentCaptor.forClass(classOf[CustomFieldsDeviceRequest])
        new KoreCheckStatusPreProcessor(environment).process(exchange)

        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD, payload)
        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER, deviceNumber)
        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_ID, midwayTransId)
        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE, requestType)
        verify(exchange, times(1)).setProperty(ITransaction.CARRIER_STATUS, status)
        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC, errorDesc)
        verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, netSuiteId)

        if (RequestType.ACTIVATION.equals(requestType) && RecordType.PRIMARY.equals(recordType)) {
          verify(exchange, times(1)).setProperty(same(IConstant.KORE_ACTIVATION_CUSTOMFIELD_PAYLOAD), customFieldsCaptor.capture)
          val customFieldsDeviceRequest = customFieldsCaptor.getValue
          assert(customFieldsDeviceRequest.getDataArea != null)
          assert(customFieldsDeviceRequest.getDataArea.getCustomFieldsToUpdate.length === 1)
          assert(customFieldsDeviceRequest.getDataArea.getCustomFieldsToUpdate()(0).getKey === field1.getKey)
          assert(customFieldsDeviceRequest.getDataArea.getCustomFieldsToUpdate()(0).getValue === field1.getValue)
          assert(customFieldsDeviceRequest.getDataArea.getDevices.length === 1)
          assert(customFieldsDeviceRequest.getDataArea.getDevices()(0).getNetSuiteId === netSuiteId)
          assert(customFieldsDeviceRequest.getDataArea.getDevices()(0).getDeviceIds.length === 1)
          assert(customFieldsDeviceRequest.getDataArea.getDevices()(0).getDeviceIds()(0).getId === deviceId.getId)
          assert(customFieldsDeviceRequest.getDataArea.getDevices()(0).getDeviceIds()(0).getKind === deviceId.getKind)

          verify(exchange, times(1)).setProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_ERROR_DESCRIPTION, errorDesc)
          verify(exchange, times(1)).setProperty(IConstant.KORE_ACTIVATION_CUSTOMFIELD_ERRORPAYLOAD, callbackPayload)
        }

        status match {
          case IConstant.CARRIER_TRANSACTION_STATUS_ERROR =>
            verify(message, times(1)).setHeader(IConstant.KORE_CHECK_STATUS, "error")
          case IConstant.CARRIER_TRANSACTION_STATUS_SUCCESS =>
            verify(message, times(1)).setHeader(IConstant.KORE_CHECK_STATUS, "change")
          case _ =>
            verify(message, times(1)).setHeader(IConstant.KORE_CHECK_STATUS, "forward")
            assertKoreRequest(message, "/json/queryProvisioningRequestStatus")
            verify(message, times(1)).setBody(captor.capture)
            val json = captor.getValue
            assert(json.get("trackingNumber") === carrierTransId)

            verify(exchange, times(1)).setProperty(IConstant.CARRIER_TRANSACTION_ID, carrierTransId)
            verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
        }
      }
    }
  }
}