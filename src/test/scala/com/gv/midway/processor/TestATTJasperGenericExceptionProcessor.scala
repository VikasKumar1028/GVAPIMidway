package com.gv.midway.processor

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse
import com.gv.midway.pojo.{BaseResponse, CarrierProvisioningDeviceResponse, Header}
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse
import com.gv.midway.pojo.usageInformation.response.UsageInformationResponse
import org.apache.camel.{Endpoint, Exchange}
import org.apache.cxf.binding.soap.SoapFault
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestATTJasperGenericExceptionProcessor extends TestMocks {

  List(
    TestCase("Endpoint[direct://deviceInformationCarrier]", classOf[DeviceInformationResponse])
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
  ).foreach{ case TestCase(name, clazz) =>

    test(name) {
      val soapFaultMessage = "14"
      val responseDescription = "responseDescription"

      withMockExchangeAndMessage{ (exchange, message) =>

        val soapFault = mock[SoapFault]
        when(soapFault.getMessage).thenReturn(soapFaultMessage, Nil: _*)

        val endpoint = mock[Endpoint]
        when(endpoint.toString).thenReturn(name, Nil: _*)

        val header = mock[Header]
        when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)
        when(exchange.getProperty(IConstant.ATTJASPER_SOAP_FAULT_ERRORMESSAGE)).thenReturn(responseDescription, Nil: _*)
        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(soapFault, Nil: _*)
        when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)

        val captor = ArgumentCaptor.forClass(clazz)
        new ATTJasperGenericExceptionProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())

        val response = captor.getValue

        assert(response.getHeader === header)
        assert(response.getResponse.getResponseCode === soapFaultMessage.toInt)
        assert(response.getResponse.getResponseStatus === IResponse.ERROR_MESSAGE)
        assert(response.getResponse.getResponseDescription === responseDescription)

      }
    }
  }

  private case class TestCase[A <: BaseResponse](name: String, clazz: Class[A])
}