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

class TestKoreCheckStatusPostProcessor extends TestMocks with NetSuiteSuite {

  List(
    (RequestType.ACTIVATION, NetSuiteRequestType.ACTIVATION, "Device successfully activated.")
    , (RequestType.DEACTIVATION, NetSuiteRequestType.DEACTIVATION, "Device successfully DeActivated.")
    , (RequestType.REACTIVATION, NetSuiteRequestType.REACTIVATION, "Device successfully ReActivated.")
    , (RequestType.RESTORE, NetSuiteRequestType.RESTORATION, "Device successfully ReStored.")
    , (RequestType.SUSPEND, NetSuiteRequestType.SUSPENSION, "Device successfully Suspended.")
    , (RequestType.CHANGESERVICEPLAN, NetSuiteRequestType.SERVICE_PLAN, "Device Service Plan Changed successfully.")
    , (RequestType.CHANGECUSTOMFIELDS, NetSuiteRequestType.CUSTOM_FIELDS, "Device Custom Fields Changed successfully.")
  ).foreach { case (requestType, netSuiteRequestType, response) =>

    test(s"process($requestType)") {

      val deviceNumber = s"""[${deviceIdJson("id1", "kind1")}, ${deviceIdJson("id2", "kind2")}]"""
      val transId = "transId"
      val netSuiteId: Integer = 349343
      val dataArea = new ChangeDeviceServicePlansRequestDataArea
      dataArea.setCurrentServicePlan("currentPlan")
      dataArea.setServicePlan("servicePlan")
      val payload = new ChangeDeviceServicePlansRequest
      payload.setDataArea(dataArea)
      val headerTransId = "headerTransId"

      withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

        val header = mock[Header]
        when(header.getTransactionId).thenReturn(headerTransId, Nil: _*)
        payload.setHeader(header)

        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_DEVICE_NUMBER)).thenReturn(deviceNumber, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_REQUEST_TYPE)).thenReturn(requestType, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_PAYLOAD)).thenReturn(payload, Nil: _*)
        when(environment.getProperty(IConstant.NETSUITE_CALLBACKS_SCRIPT)).thenReturn(IConstant.NETSUITE_CALLBACKS_SCRIPT)

        val requestCaptor = ArgumentCaptor.forClass(classOf[NetSuiteCallBackProvisioningRequest])
        val eventCaptor = ArgumentCaptor.forClass(classOf[KafkaNetSuiteCallBackEvent])

        new KoreCheckStatusPostProcessor(environment).process(exchange)

        verify(message, times(1)).setBody(requestCaptor.capture())
        verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
        verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
        verify(message, times(1)).setHeader(same("Authorization"), anyString())
        verify(message, times(1)).setHeader(same(Exchange.HTTP_PATH), isNull)
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
        assert(event.getCategory === "Kore Call Back Success")
        assert(event.getId === requestType.toString)
        assert(event.getLevel === "Info")
        assert(event.getTimestamp > 0)
        assert(event.getVersion === "1")
        assert(event.getMsg === "Successful Call Back from Kore.")
        assert(event.getDesc != null)
        assert(event.getBody === payload)
        assert(event.getKeyValues.length === 4)
        val keyValuesScalaMap = event.getKeyValues.map{kv => (kv.getK, kv.getV)}.toMap
        assert(keyValuesScalaMap("transactionId") === headerTransId)
        assert(keyValuesScalaMap("orderNumber") === transId)
        assert(keyValuesScalaMap("deviceIds") === deviceNumber)
        assert(keyValuesScalaMap("netSuiteID") === netSuiteId.toString)

        if (requestType === RequestType.CHANGESERVICEPLAN) {
          assert(request.getOldServicePlan === dataArea.getCurrentServicePlan)
          assert(request.getNewServicePlan === dataArea.getServicePlan)
        }
      }
    }
  }

}