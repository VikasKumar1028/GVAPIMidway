package com.gv.midway.processor

import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.exception.{InvalidParameterException, MissingParameterException}
import org.apache.camel.{Endpoint, Exchange, Message}
import org.scalatest.FunSuite
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Matchers._

class TestHeaderProcessor extends FunSuite with MockitoSugar {

  private val properties = Map(
    IConstant.BSCARRIER -> IConstant.BSCARRIER_SERVICE_VERIZON
    , IConstant.SOURCE_NAME -> IConstant.SOURCE_NAME
    //TODO This looks incorrect to me (Jeff). Why is the property called Date Format in the header, and that value is passed to a date parser?
    , IConstant.DATE_FORMAT -> "2016-10-04T00:00:00"
    , IConstant.ORGANIZATION -> IConstant.ORGANIZATION
    , IConstant.GV_TRANSACTION_ID -> IConstant.GV_TRANSACTION_ID
  )

  test("test happy flow") {
    withMockExchangeMessageAndEndpoint(){(exchange, message, _) =>
      new HeaderProcessor().process(exchange)

      verify(exchange, times(1)).setProperty(same(IConstant.MIDWAY_TRANSACTION_ID), anyString())
      verify(message, times(1)).setHeader(IConstant.MIDWAY_DERIVED_CARRIER_NAME, IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME, IConstant.BSCARRIER_SERVICE_VERIZON)
    }
  }

  properties.keys.foreach{ prop =>
    test(s"missing $prop property") {
      withMockExchangeMessageAndEndpoint(Some(properties - prop)){ (exchange, _, _) =>
        assertThrows[MissingParameterException] {
          new HeaderProcessor().process(exchange)

          verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "400")
          verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Missing Parameter")
          verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, "Pass all the required header parameters. ")
        }
      }
    }
  }

  test("invalid date format") {
    withMockExchangeMessageAndEndpoint(Some(properties + (IConstant.DATE_FORMAT -> "invalid date"))) { (exchange, _, _) =>
      assertThrows[MissingParameterException] {
        new HeaderProcessor().process(exchange)

        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD)
        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_MESSAGE)
        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB)
      }
    }
  }

  test("invalid carrier name") {
    withMockExchangeMessageAndEndpoint(Some(properties + (IConstant.BSCARRIER -> "Testing"))) { (exchange, _, _) =>
      assertThrows[InvalidParameterException] {
        new HeaderProcessor().process(exchange)

        assertInvalidParameterExceptionCalls(exchange)
      }
    }
  }

  Map(
    IConstant.BSCARRIER_SERVICE_KORE -> List(
      "deviceConnectionStatus"
      , "deviceSessionBeginEndInfo"
      , "retrieveDeviceUsageHistoryCarrier"
      , "getDeviceConnectionHistoryInfoDB"
    )
    , IConstant.BSCARRIER_SERVICE_VERIZON -> List(
      "reactivateDevice"
    )
  ).foreach{ case(carrier, endpoints) =>
    endpoints.foreach{ endpointName =>
      test(s"invalid carrier endpoint: $carrier - $endpointName") {
        withMockExchangeMessageAndEndpoint(Some(properties + (IConstant.BSCARRIER -> carrier))) { (exchange, _, endpoint) =>
          when(endpoint.toString).thenReturn(endpointName)

          assertThrows[InvalidParameterException] {
            new HeaderProcessor().process(exchange)

            assertInvalidParameterExceptionCalls(exchange)
          }
        }
      }
    }
  }

  private def assertInvalidParameterExceptionCalls(exchange: Exchange): Unit = {
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "400")
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter")
    verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, "Invalid bsCarrier field value")
  }

  private def withMockExchangeMessageAndEndpoint(props: Option[Map[String, String]] = Some(properties))(f: (Exchange, Message, Endpoint) => Unit) = {
    val exchange = mock[Exchange]
    val message = mock[Message]
    val endpoint = mock[Endpoint]

    when(exchange.getIn()).thenReturn(message)
    when(endpoint.toString).thenReturn("generic endpoint")
    when(exchange.getFromEndpoint).thenReturn(endpoint)

    props.foreach{m =>
      m.foreach{ case (k, v) =>
        when(exchange.getProperty(k)).thenReturn(v, Nil: _*)
      }
    }

    f(exchange, message, endpoint)
  }
}