package com.gv.midway.processor

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.exception.VerizonSessionTokenExpirationException
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse
import com.gv.midway.pojo.{BaseResponse, CarrierProvisioningDeviceResponse, Header}
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse
import org.apache.camel.{Endpoint, Exchange}
import org.apache.camel.component.cxf.CxfOperationException
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestVerizonGenericExceptionProcessor extends TestMocks {

  List(
    TestCase("Endpoint[direct://deviceInformationCarrier]", classOf[DeviceInformationResponse])
    , TestCase("Endpoint[direct://activateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://deactivateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://suspendDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://deviceConnectionStatus]", classOf[ConnectionStatusResponse])
    , TestCase("Endpoint[direct://deviceSessionBeginEndInfo]", classOf[SessionBeginEndResponse])
    , TestCase("Endpoint[direct://customeFields]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://changeDeviceServicePlans]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://restoreDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://retrieveDeviceUsageHistoryCarrier]", classOf[UsageInformationResponse])
  ).foreach { case TestCase(name, clazz) =>
    test(name) {
      val errorMessage = "This is my error message!"
      val responseBody = s"""{"errorCode":4,"errorMessage":"$errorMessage"}"""
      val statusCode: java.lang.Integer = 9000

      val exception = new CxfOperationException("", statusCode, "", "", new java.util.HashMap[String, String](), responseBody)

      val header = mock[Header]
      val endpoint = mock[Endpoint]

      when(endpoint.toString).thenReturn(name, Nil: _*)

      withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>


        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
        when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)
        when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)

        val captor = ArgumentCaptor.forClass(clazz)

        new VerizonGenericExceptionProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())
        val response = captor.getValue

        assert(response.getHeader === header)
        assert(response.getResponse.getResponseCode === IResponse.INVALID_PAYLOAD)
        assert(response.getResponse.getResponseStatus === IResponse.ERROR_MESSAGE)
        assert(response.getResponse.getResponseDescription === errorMessage)
      }
    }
  }

  test("VerizonSessionTokenExpirationException") {
    val exception = new CxfOperationException("", 401, "", "", new java.util.HashMap[String, String](), "responseBody")

    withMockExchangeAndMessage{ (exchange, message) =>
      val endpoint = mock[Endpoint]

      when(endpoint.toString).thenReturn("", Nil: _*)

      when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
      when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)

      assertThrows[VerizonSessionTokenExpirationException] {
        new VerizonGenericExceptionProcessor().process(exchange)
      }

      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "401")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Invalid Token")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, "Not able to retrieve  valid authentication token")
    }
  }

  private case class TestCase[A <: BaseResponse](name: String, clazz: Class[A])
}