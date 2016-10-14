package com.gv.midway.processor.jobScheduler

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceEvent}
import com.gv.midway.pojo.job.JobDetail
import org.apache.camel.{Exchange, Message}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import java.util.{Map => JMap}

import org.mockito.Matchers._
import com.fasterxml.jackson.databind.ObjectMapper
import com.gv.midway.pojo.connectionInformation.verizon.response.{ConnectionEvent, ConnectionHistory, ConnectionInformationResponse}
import com.gv.midway.pojo.verizon.DeviceId

class TestVerizonDeviceConnectionHistoryPostProcessor extends FunSuite with MockitoSugar {

  test("VerizonDeviceConnectionHistoryPostProcessor.process") {
    val captor = ArgumentCaptor.forClass(classOf[DeviceConnection])
    withMockExchangeAndMessage{ (exchange, message) =>

      val jobDetail = new JobDetail
      jobDetail.setDate("2016-10-10")
      jobDetail.setJobId("jobId")

      val deviceId = new DeviceId
      deviceId.setId("id")
      deviceId.setKind("kind")

      when(exchange.getProperty("jobDetail")).thenReturn(jobDetail, Nil: _*)
      when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
      when(message.getBody(any)).thenReturn(getMap, Nil: _*)

      new VerizonDeviceConnectionHistoryPostProcessor().process(exchange)

      verify(message, times(1)).setBody(captor.capture())

      val connection = captor.getValue
      assert(connection.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
      assert(connection.getDeviceId === deviceId)
      assert(connection.getDate === jobDetail.getDate)
      assert(connection.getTransactionErrorReason === null)
      assert(connection.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS)
      assert(connection.getNetSuiteId === 14)
      assert(connection.getIsValid)
      assert(connection.getJobId === jobDetail.getJobId)
      assert(connection.getEvent != null)

      val events: Array[DeviceEvent] = connection.getEvent
      assert(events.length === 2)
      val event1 = events.find(_.getEventType == "Download")
      assert(event1.isDefined)
      assert(event1.get.getBytesUsed === "400")
      assert(event1.get.getOccurredAt === "2016-10-01")
      val event2 = events.find(_.getEventType == "Upload")
      assert(event2.isDefined)
      assert(event2.get.getBytesUsed === "9000")
      assert(event2.get.getOccurredAt === "2016-10-02")
    }
  }

  private def withMockExchangeAndMessage(f: (Exchange, Message) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]

    when(exchange.getIn).thenReturn(message)
    when(exchange.getProperty("CarrierName")).thenReturn(IConstant.BSCARRIER_SERVICE_VERIZON, Nil: _*)
    when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(14: java.lang.Integer, Nil: _*)

    f(exchange, message)
  }

  private def getMap: JMap[_, _] = {
    val response = new ConnectionInformationResponse
    val hist1 = connHist("2016-10-01", "400", "Download")
    val hist2 = connHist("2016-10-02", "9000", "Upload")
    response.setConnectionHistory(Array(hist1, hist2))

    val mapper: ObjectMapper = new ObjectMapper
    mapper.convertValue(response, classOf[JMap[_, _]])
  }

  private def connHist(occurred: String, bytes: String, event: String): ConnectionHistory = {
    val hist = new ConnectionHistory
    hist.setConnectionEventAttributes(Array(
      connEvent("BytesUsed", bytes)
      , connEvent("Some random key", "some random value")
      , connEvent("Event", event)
    ))
    hist.setOccurredAt(occurred)
    hist
  }

  private def connEvent(key: String, value: String): ConnectionEvent = {
    val event = new ConnectionEvent
    event.setKey(key)
    event.setValue(value)
    event
  }
}