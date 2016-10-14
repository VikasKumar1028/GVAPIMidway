package com.gv.midway.processor

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.{CarrierProvisioningDeviceResponse, Header}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestCarrierProvisioningDevicePostProcessor extends TestMocks {

  private val transId = "transId"

  testIt("CarrierProvisioningDevicePostProcessor.process - success", "") { response =>
    assert(response.getResponse.getResponseCode === IResponse.SUCCESS_CODE)
    assert(response.getResponse.getResponseStatus === IResponse.SUCCESS_MESSAGE)
    assert(response.getResponse.getResponseDescription === IResponse.SUCCESS_DESCRIPTION_PROVISIONING_MIDWAY)
    assert(response.getDataArea.getOrderNumber === transId)
  }

  testIt("CarrierProvisioningDevicePostProcessor.process - error", "errorMessage=") { response =>
    assert(response.getResponse.getResponseCode === 400)
    assert(response.getResponse.getResponseStatus === IResponse.ERROR_MESSAGE)
    assert(response.getResponse.getResponseDescription === "errorMessage=")
  }

  private def testIt(name: String, body: String)(f: CarrierProvisioningDeviceResponse => Unit): Unit = {
    test(name) {
      val captor = ArgumentCaptor.forClass(classOf[CarrierProvisioningDeviceResponse])

      withMockExchangeAndMessage{ (exchange, message) =>

        val header = mock[Header]

        when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_TRANSACTION_ID)).thenReturn(transId, Nil: _*)
        when(message.getBody).thenReturn(body, Nil: _*)

        new CarrierProvisioningDevicePostProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())

        val response = captor.getValue
        assert(response.getHeader === header)
        f(response)
      }
    }
  }
}