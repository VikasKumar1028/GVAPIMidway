package com.gv.midway.processor

import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.exception.MissingParameterException
import com.gv.midway.pojo.deviceInformation.request.{DeviceInformationRequest, DeviceInformationRequestDataArea}
import org.apache.camel.{Exchange, Message}
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class TestNetSuiteIdValidationProcessor extends FunSuite with MockitoSugar {

  test("happy flow") {
    val id = 14
    withMockExchange(request(dataArea(Some(id)))) { exchange =>
      new NetSuiteIdValidationProcessor().process(exchange)
    }
  }

  test("null data area") {
    withMockExchange(request(null)) { e =>
      assertThrows[MissingParameterException] {
        new NetSuiteIdValidationProcessor().process(e)
      }
      verify(e, times(1)).setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD)
      verify(e, times(1)).setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB)
      verify(e, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_MESSAGE)
    }
  }

  test("null id") {
    withMockExchange(request(dataArea(None))) { e =>
      assertThrows[MissingParameterException] {
        new NetSuiteIdValidationProcessor().process(e)
      }
      verify(e, times(1)).setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD)
      verify(e, times(1)).setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB)
      verify(e, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_MESSAGE)
    }
  }

  private def dataArea(netSuiteId: Option[Int]): DeviceInformationRequestDataArea = {
    val dirda = new DeviceInformationRequestDataArea
    netSuiteId.foreach{ i => dirda.setNetSuiteId(i)}
    dirda
  }

  private def request(dataArea: DeviceInformationRequestDataArea): DeviceInformationRequest = {

    val dir = new DeviceInformationRequest
    dir.setDataArea(dataArea)
    dir
  }

  private def withMockExchange(request: DeviceInformationRequest)(f: => Exchange => Unit) = {
    val exchange = mock[Exchange]
    val message = mock[Message]

    when(exchange.getIn()).thenReturn(message)
    when(message.getBody(classOf[DeviceInformationRequest])).thenReturn(request)

    f(exchange)
  }
}