package com.gv.midway.processor

import java.util

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse
import com.gv.midway.pojo.{BaseResponse, CarrierProvisioningDeviceResponse, Header}
import org.apache.camel.{Endpoint, Exchange}
import org.apache.camel.component.cxf.CxfOperationException
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreGenericExceptionProcessor extends TestMocks {

  List(
    TestCase("Endpoint[direct://deviceInformationCarrier]", classOf[DeviceInformationResponse])
    , TestCase("Endpoint[direct://activateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://deactivateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://suspendDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://customeFields]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://changeDeviceServicePlans]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://reactivateDevice]", classOf[CarrierProvisioningDeviceResponse])
    , TestCase("Endpoint[direct://restoreDevice]", classOf[CarrierProvisioningDeviceResponse])
  ).foreach{ case TestCase(name, clazz) =>

    test(name) {

      val errorMessage = "This is my error message"
      val responseBody = s"""{"errorCode":140,"errorMessage":"$errorMessage"}"""

      withMockExchangeAndMessage{ (exchange, message) =>

        val header = mock[Header]
        val exception = new CxfOperationException("", 1, "", "", new util.HashMap(), responseBody)
        val endpoint = mock[Endpoint]

        when(endpoint.toString).thenReturn(name, Nil: _*)

        when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)
        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
        when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)

        val captor = ArgumentCaptor.forClass(clazz)

        new KoreGenericExceptionProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())

        val response = captor.getValue
        assert(response.getHeader === header)
        assert(response.getResponse.getResponseCode === IResponse.INVALID_PAYLOAD)
        assert(response.getResponse.getResponseStatus === IResponse.ERROR_MESSAGE)
        assert(response.getResponse.getResponseDescription === errorMessage)
      }
    }
  }

  private case class TestCase[A <: BaseResponse](name: String, clazz: Class[A])
}