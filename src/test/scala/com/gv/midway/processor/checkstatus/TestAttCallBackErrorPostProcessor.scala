package com.gv.midway.processor.checkstatus

import com.gv.midway.constant.{IConstant, NetSuiteRequestType, RequestType}
import com.gv.midway.pojo.Header
import com.gv.midway.pojo.callback.Netsuite.{KafkaNetSuiteCallBackError, NetSuiteCallBackProvisioningRequest}
import com.gv.midway.pojo.changeDeviceServicePlans.request.{ChangeDeviceServicePlansRequest, ChangeDeviceServicePlansRequestDataArea}
import com.gv.midway.{NetSuiteSuite, TestMocks}
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestAttCallBackErrorPostProcessor extends TestMocks with NetSuiteSuite {

  List(
    (RequestType.ACTIVATION, NetSuiteRequestType.ACTIVATION)
    , (RequestType.DEACTIVATION, NetSuiteRequestType.DEACTIVATION)
    , (RequestType.REACTIVATION, NetSuiteRequestType.REACTIVATION)
    , (RequestType.RESTORE, NetSuiteRequestType.RESTORATION)
    , (RequestType.SUSPEND, NetSuiteRequestType.SUSPENSION)
    , (RequestType.CHANGESERVICEPLAN, NetSuiteRequestType.SERVICE_PLAN)
    , (RequestType.CHANGECUSTOMFIELDS, NetSuiteRequestType.CUSTOM_FIELDS)
  ).foreach { case (requestType, netSuiteRequestType) =>

    test(s"process($requestType)") {

      val netSuiteId: Integer = 342348
      val transId = "transId"
      val deviceNumber = s"""[${deviceIdJson("id1", "kind1")}, ${deviceIdJson("id2", "kind2")}]"""
      val errorDesc = "error!$%"
      val headerTransId = "headerTransId"
      val header = mock[Header]
      when(header.getTransactionId).thenReturn(headerTransId, Nil: _*)
      val dataArea = new ChangeDeviceServicePlansRequestDataArea
      dataArea.setCurrentServicePlan("currentPlan")
      dataArea.setServicePlan("servicePlan")
      val payload = new ChangeDeviceServicePlansRequest
      payload.setHeader(header)
      payload.setDataArea(dataArea)
      val customFieldDesc = "customFieldDesc"

      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE)).thenReturn(requestType, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_CARRIER_ERROR_DESC)).thenReturn(errorDesc, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD)).thenReturn(payload, Nil: _*)
        when(exchange.getProperty(IConstant.ATTJASPER_CUSTOM_FIELD_DEC)).thenReturn(customFieldDesc, Nil: _*)
        when(environment.getProperty(IConstant.NETSUITE_CALLBACKS_SCRIPT)).thenReturn(IConstant.NETSUITE_CALLBACKS_SCRIPT)

        val requestCaptor = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])
        val errorCaptor = ArgumentCaptor.forClass(classOf[KafkaNetSuiteCallBackError])

        new AttCallBackErrorPostProcessor(environment).process(exchange)

        verify(message, times(1)).setBody(requestCaptor.capture())
        verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
        verify(message, times(1)).setHeader(same("Authorization"), anyString())
        verify(message, times(1)).setHeader(same(Exchange.HTTP_PATH), isNull)

        val request = requestCaptor.getValue
        assert(request.getStatus === "fail")
        assert(request.getCarrierOrderNumber === transId)
        assert(request.getNetSuiteID === netSuiteId.toString)
        assert(request.getRequestType === netSuiteRequestType)
        if (requestType == RequestType.CHANGECUSTOMFIELDS) {
          assert(request.getResponse === customFieldDesc)
        } else {
          assert(request.getResponse === errorDesc)
        }
        assert(request.getDeviceIds.length === 2)

        verify(exchange, times(1)).setProperty(same(IConstant.KAFKA_OBJECT), errorCaptor.capture())
        verify(exchange, times(1)).setProperty("script", IConstant.NETSUITE_CALLBACKS_SCRIPT)
        verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
        val error = errorCaptor.getValue
        assert(error.getApp === "Midway")
        assert(error.getCategory === "ATT Jasper Call Back Error")
        assert(error.getId === requestType.toString)
        assert(error.getLevel === "Error")
        assert(error.getTimestamp > 0)
        assert(error.getVersion === "1")
        assert(error.getMsg === "Error in Call Back from ATT Jasper.")
        assert(error.getDesc != null)
        assert(error.getBody === payload)
        if (requestType == RequestType.CHANGECUSTOMFIELDS) {
          assert(error.getException === customFieldDesc)
        } else {
          assert(error.getException === errorDesc)
        }
        assert(error.getKeyValues.length === 4)
        val keyValuesAsScalaMap = error.getKeyValues.map{kv => (kv.getK, kv.getV)}.toMap
        assert(keyValuesAsScalaMap("transactionId") === headerTransId)
        assert(keyValuesAsScalaMap("orderNumber") === transId)
        assert(keyValuesAsScalaMap("deviceIds") === deviceNumber)
        assert(keyValuesAsScalaMap("netSuiteID") === netSuiteId.toString)

        if (requestType == RequestType.CHANGESERVICEPLAN) {
          assert(request.getOldServicePlan === dataArea.getCurrentServicePlan)
          assert(request.getNewServicePlan === dataArea.getServicePlan)
        }
      }
    }
  }
}