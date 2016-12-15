package com.gv.midway.processor.jobScheduler

import java.util.{Map => JMap}

import com.fasterxml.jackson.databind.ObjectMapper
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.job.JobDetail
import com.gv.midway.pojo.usageInformation.kore.response.{D, Usage, UsageInformationKoreResponse}
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, Message}
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.mockito.Matchers._

class TestKoreDeviceUsageHistoryPostProcessor extends FunSuite with MockitoSugar {

  private val jobDetailDate = "2016-10-01"

  private val validResponse = getValidResponse

  private val deviceId = {
    val di = new DeviceId
    di.setId("14")
    di.setKind("kind")
    di
  }

  private val netSuiteId: java.lang.Integer = 1400

  test("KoreDeviceUsageHistoryPostProcessor - single day match") {

    val date = "2016-10-10"
    val jobId = "jobId"


    withMockExchangeAndMessage(validResponse) { (exchange, message) =>

      val jobDetail = mock[JobDetail]
      when(jobDetail.getDate).thenReturn(date, Nil: _*)
      when(jobDetail.getJobId).thenReturn(jobId, Nil: _*)
      when(exchange.getProperty("jobDetail")).thenReturn(jobDetail, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceUsage])

      new KoreDeviceUsageHistoryPostProcessor().process(exchange)

      verify(message, times(1)).setBody(captor.capture())

      val usage = captor.getValue
      assert(usage.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
      assert(usage.getDeviceId === deviceId)
      assert(usage.getDataUsed === 15l)
      assert(usage.getDate === date)
      assert(usage.getTransactionErrorReason === null)
      assert(usage.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS)
      assert(usage.getNetSuiteId === netSuiteId)
      assert(usage.getIsValid === true)
      assert(usage.getJobId === jobId)
    }
  }

  private def withMockExchangeAndMessage(koreResponse: UsageInformationKoreResponse)(f: (Exchange, Message) => Unit): Unit = {
    val exchange = mock[Exchange]
    val message = mock[Message]

    when(exchange.getIn).thenReturn(message)
    when(exchange.getProperty("jobDetailDate")).thenReturn(jobDetailDate, Nil: _*)
    when(exchange.getProperty("CarrierName")).thenReturn(IConstant.BSCARRIER_SERVICE_VERIZON, Nil: _*)
    when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
    when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
    when(message.getBody(any)).thenReturn(koreResponse, Nil: _*)

    f(exchange, message)
  }

  private def getValidResponse: UsageInformationKoreResponse = {
    import collection.JavaConversions._
    val usage1 = new Usage()
    usage1.set__type("Element 1 type")
    usage1.setDataInBytes(14.0)
    usage1.setSms(10l)
    usage1.setUsageDate("/Date(1475215200000-0000)/")

    val usage2 = new Usage()
    usage2.set__type("Element 2 type")
    usage2.setDataInBytes(15.0)
    usage2.setSms(11l)
    usage2.setUsageDate("/Date(1475301600000-0000)/")  //10-01-2016 - Same as jobDetailDate

    val usage3 = new Usage()
    usage3.set__type("Element 3 type")
    usage3.setDataInBytes(100.0)
    usage3.setSms(0l)
    usage3.setUsageDate("/Date(1475388000000-0000)/")

    val list = List(usage1, usage2, usage3)
    val d = new D
    d.setUsage(list)
    val uikr = new UsageInformationKoreResponse
    uikr.setD(d)

    val mapper = new ObjectMapper()
    mapper.convertValue(uikr, classOf[UsageInformationKoreResponse])
  }
}