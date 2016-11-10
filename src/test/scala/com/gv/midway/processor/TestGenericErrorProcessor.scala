package com.gv.midway.processor

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse
import com.gv.midway.pojo.device.response.{BatchDeviceResponse, UpdateDeviceResponse}
import com.gv.midway.pojo.{BaseResponse, CarrierProvisioningDeviceResponse, Header}
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse
import com.gv.midway.pojo.usageInformation.response.{DevicesUsageByDayAndCarrierResponse, UsageInformationMidwayResponse, UsageInformationResponse}
import org.apache.camel.Endpoint
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.springframework.core.env.Environment

class TestGenericErrorProcessor extends TestMocks {

  List(
    TestCase("Endpoint[direct://deviceInformationCarrier]", classOf[DeviceInformationResponse])
    , TestCase("Endpoint[direct://getDeviceInformationDB]", classOf[DeviceInformationResponse])
    , TestCase("Endpoint[direct://updateDevicesDetailsBulk]", classOf[BatchDeviceResponse])
    , TestCase("Endpoint[direct://updateDeviceDetails]", classOf[UpdateDeviceResponse])
    , TestCase("Endpoint[direct://activateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://deactivateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://suspendDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://deviceConnectionStatus]", classOf[ConnectionStatusResponse])
    , TestCase("Endpoint[direct://deviceSessionBeginEndInfo]", classOf[SessionBeginEndResponse])
    , TestCase("Endpoint[direct://customFields]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://changeDeviceServicePlans]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://reactivateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://restoreDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://retrieveDeviceUsageHistoryCarrier]", classOf[UsageInformationResponse])
    , TestCase("Endpoint[direct://getDeviceUsageInfoDB]", classOf[UsageInformationMidwayResponse])
    , TestCase("Endpoint[direct://getDeviceConnectionHistoryInfoDB]", classOf[ConnectionInformationMidwayResponse])
    , TestCase("Endpoint[direct://getDevicesUsageByDayAndCarrierInfoDB]", classOf[DevicesUsageByDayAndCarrierResponse])
  ).foreach { case TestCase(endpoint, clazz) =>

    val responseCode: java.lang.Integer = 2
    val status = "Status"
    val description = "Description"

    test(endpoint) {
      withMockExchangeAndMessage{ (exchange, message) =>
        val header = mock[Header]
        when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)

        when(exchange.getProperty(IConstant.RESPONSE_CODE)).thenReturn(responseCode, Nil: _*)
        when(exchange.getProperty(IConstant.RESPONSE_STATUS)).thenReturn(status, Nil: _*)
        when(exchange.getProperty(IConstant.RESPONSE_DESCRIPTION)).thenReturn(description, Nil: _*)

        val endpointObj = mock[Endpoint]
        when(exchange.getFromEndpoint).thenReturn(endpointObj, Nil: _*)
        when(endpointObj.toString).thenReturn(endpoint, Nil: _*)

        new GenericErrorProcessor().process(exchange)

        val captor = ArgumentCaptor.forClass(clazz)

        verify(message, times(1)).setBody(captor.capture())

        val response = captor.getValue
        assert(response.getHeader === header)
        assert(response.getResponse.getResponseCode === responseCode)
        assert(response.getResponse.getResponseStatus === status)
        assert(response.getResponse.getResponseDescription === description)
      }
    }
  }

  case class TestCase[A <: BaseResponse](endpoint: String, clazz: Class[A])
}