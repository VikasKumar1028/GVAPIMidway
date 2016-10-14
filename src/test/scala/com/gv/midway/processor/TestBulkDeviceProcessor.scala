package com.gv.midway.processor

import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.Header
import com.gv.midway.pojo.device.response.{BatchDeviceId, BatchDeviceResponse}
import org.apache.camel.impl.DefaultMessage
import org.apache.camel.{Exchange, ExchangePattern, Message}
import org.scalatest.FunSuite
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import java.util.{List => JList}

import collection.JavaConversions._

class TestBulkDeviceProcessor  extends FunSuite with MockitoSugar {

  private val id1 = "id1"
  private val id2 = "id2"
  private val id3 = "id3"
  private val id4 = "id4"

  private val errors = List(batchDeviceId(id1))
  private val successes = List(
    batchDeviceId(id2)
    , batchDeviceId(id3)
    , batchDeviceId(id4)
  )

  test("happy flow") {
    withMockExchange(){ (exchange, message, header) =>
      new BulkDeviceProcessor().process(exchange)

      verify(exchange, times(1)).getProperty(IConstant.BULK_ERROR_LIST)
      verify(exchange, times(1)).getProperty(IConstant.BULK_SUCCESS_LIST)
      verify(exchange, times(1)).getProperty(IConstant.HEADER)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)

      message.getBody match {
        case bdr: BatchDeviceResponse =>

          assert(bdr.getHeader === header)

          val response = bdr.getResponse
          assert(response.getResponseCode === IResponse.SUCCESS_CODE)
          assert(response.getResponseStatus === IResponse.SUCCESS_MESSAGE)
          assert(response.getResponseDescription === IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_CARRIER)

          val dataArea = bdr.getDataArea
          assert(dataArea.getFailedCount === errors.size)
          assert(dataArea.getSuccessCount === successes.size)
          assert(dataArea.getFailedDevices.length === errors.size)
          assert(dataArea.getSuccessDevices.length === successes.size)
          assert(dataArea.getFailedDevices.toList === errors)
          assert(dataArea.getSuccessDevices.toList === successes)
        case _ => fail("Invalid response object returned")
      }
    }
  }

  private def batchDeviceId(newSuiteId: String): BatchDeviceId = {
    val id = new BatchDeviceId
    id.setNetSuiteId(newSuiteId)
    id
  }

  private def withMockExchange(errors: JList[BatchDeviceId] = errors
                               , successes: JList[BatchDeviceId] = successes
                              )(f: (Exchange, Message, Header) => Unit) = {
    val exchange = mock[Exchange]
    val message: Message = new DefaultMessage()
    val header = new Header
    header.setApplicationName("SCALA TEST")

    when(exchange.getIn).thenReturn(message)
    when(exchange.getProperty(IConstant.BULK_ERROR_LIST)).thenReturn(errors, Nil: _*)
    when(exchange.getProperty(IConstant.BULK_SUCCESS_LIST)).thenReturn(successes, Nil: _*)
    when(exchange.getProperty(IConstant.HEADER)).thenReturn(header, Nil: _*)

    f(exchange, message, header)
  }
}