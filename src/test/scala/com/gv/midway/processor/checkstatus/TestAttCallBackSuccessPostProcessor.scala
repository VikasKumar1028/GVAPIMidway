package com.gv.midway.processor.checkstatus

import com.gv.midway.constant.{IConstant, NetSuiteRequestType, RequestType}
import com.gv.midway.pojo.Header
import com.gv.midway.pojo.callback.Netsuite.{KafkaNetSuiteCallBackEvent, NetSuiteCallBackProvisioningRequest}
import com.gv.midway.pojo.changeDeviceServicePlans.request.{ChangeDeviceServicePlansRequest, ChangeDeviceServicePlansRequestDataArea}
import com.gv.midway.{NetSuiteSuite, TestMocks}
import org.apache.camel.{Exchange, ExchangePattern}
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestAttCallBackSuccessPostProcessor extends TestMocks with NetSuiteSuite {

  List(
    (RequestType.ACTIVATION, "Device successfully activated.", NetSuiteRequestType.ACTIVATION)
    , (RequestType.DEACTIVATION, "Device successfully DeActivated.", NetSuiteRequestType.DEACTIVATION)
    , (RequestType.REACTIVATION, "Device successfully ReActivated.", NetSuiteRequestType.REACTIVATION)
    , (RequestType.RESTORE, "Device successfully ReStored.", NetSuiteRequestType.RESTORATION)
    , (RequestType.SUSPEND, "Device successfully Suspended.", NetSuiteRequestType.SUSPENSION)
    , (RequestType.CHANGESERVICEPLAN, "Device Service Plan Changed successfully.", NetSuiteRequestType.SERVICE_PLAN)
    , (RequestType.CHANGECUSTOMFIELDS, "Device Custom Fields Changed successfully.", NetSuiteRequestType.CUSTOM_FIELDS)
  ).foreach{ case (requestType, response, netSuiteRequestType) =>

    test(s"process($requestType)") {

      val netSuiteId: Integer = 23443
      val deviceNumber = s"""[${deviceIdJson("id1", "kind1")}, ${deviceIdJson("id2", "kind2")}]"""
      val transId = "transId"
      val headerTransId = "headerTransId"
      val header = mock[Header]
      when(header.getTransactionId).thenReturn(headerTransId, Nil: _*)
      val dataArea = new ChangeDeviceServicePlansRequestDataArea
      dataArea.setCurrentServicePlan("currentPlan")
      dataArea.setServicePlan("servicePlan")
      val body = new ChangeDeviceServicePlansRequest
      body.setHeader(header)
      body.setDataArea(dataArea)

      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE)).thenReturn(requestType, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD)).thenReturn(body, Nil: _*)
        when(environment.getProperty(IConstant.NETSUITE_CALLBACKS_SCRIPT)).thenReturn(IConstant.NETSUITE_CALLBACKS_SCRIPT)

        val eventCaptor = ArgumentCaptor.forClass(classOf[KafkaNetSuiteCallBackEvent])
        val requestCaptor = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])

        new AttCallBackSuccessPostProcessor(environment).process(exchange)

        verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
        verify(message, times(1)).setHeader(same("Authorization"), anyString())
        verify(message, times(1)).setHeader(same(Exchange.HTTP_PATH), isNull)
        verify(message, times(1)).setBody(requestCaptor.capture())

        val request = requestCaptor.getValue
        assert(request.getStatus === "success")
        assert(request.getCarrierOrderNumber === transId)
        assert(request.getNetSuiteID === netSuiteId.toString)
        assert(request.getDeviceIds.length === 2)
        assert(request.getResponse === response)
        assert(request.getRequestType === netSuiteRequestType)

        verify(exchange, times(1)).setProperty(same(IConstant.KAFKA_OBJECT), eventCaptor.capture())
        verify(exchange, times(1)).setProperty("script", IConstant.NETSUITE_CALLBACKS_SCRIPT)
        verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

        val event = eventCaptor.getValue
        assert(event.getApp === "Midway")
        assert(event.getCategory === "ATT Jasper Call Back Success")
        assert(event.getId === requestType.toString)
        assert(event.getLevel === "Info")
        assert(event.getTimestamp > 0)
        assert(event.getVersion === "1")
        assert(event.getMsg === "Successful Call Back from ATT Jasper.")
        assert(event.getDesc != null)
        assert(event.getBody === body)
        assert(event.getKeyValues.length === 4)
        val keyValuesScalaMap = event.getKeyValues.map{kv => (kv.getK, kv.getV)}.toMap
        assert(keyValuesScalaMap("transactionId") === headerTransId)
        assert(keyValuesScalaMap("orderNumber") === transId)
        assert(keyValuesScalaMap("deviceIds") === deviceNumber)
        assert(keyValuesScalaMap("netSuiteID") === netSuiteId.toString)

        if (requestType == RequestType.CHANGESERVICEPLAN) {
          assert(request.getOldServicePlan === dataArea.getCurrentServicePlan)
          assert(request.getNewServicePlan === dataArea.getServicePlan)
        }
      }
    }
  }
}