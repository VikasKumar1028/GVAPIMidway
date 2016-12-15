package com.gv.midway.processor.jobScheduler

import java.util.{Map => JMap}

import com.fasterxml.jackson.databind.ObjectMapper
import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.job.JobDetail
import com.gv.midway.pojo.usageInformation.verizon.response.{UsageHistory, VerizonUsageInformationResponse}
import com.gv.midway.pojo.verizon.DeviceId
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._

class TestVerizonDeviceUsageHistoryPostProcessor extends TestMocks {

  test("happy flow") {

    withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

      val date = "2016-10-17"
      val jobId = "jobId"
      val netSuiteId: java.lang.Integer = 923232

      val jobDetail = new JobDetail
      jobDetail.setDate(date)
      jobDetail.setJobId(jobId)

      val deviceId = mock[DeviceId]

      when(exchange.getProperty("jobDetail")).thenReturn(jobDetail, Nil: _*)
      when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)

      when(message.getBody(any)).thenReturn(informationResponse, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceUsage])

      new VerizonDeviceUsageHistoryPostProcessor().process(exchange)

      verify(message, times(1)).setBody(captor.capture())

      val usage = captor.getValue

      assert(usage.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
      assert(usage.getDeviceId === deviceId)
      assert(usage.getDataUsed === 24l)
      assert(usage.getDate === date)
      assert(usage.getTransactionErrorReason === null)
      assert(usage.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_SUCCESS)
      assert(usage.getNetSuiteId === netSuiteId)
      assert(usage.getIsValid)
      assert(usage.getJobId === jobId)

    }
  }

  private def informationResponse: VerizonUsageInformationResponse = {

    val usage1 = new UsageHistory
    usage1.setBytesUsed(10l)

    val usage2 = new UsageHistory
    usage2.setBytesUsed(14l)

    val response = new VerizonUsageInformationResponse
    response.setUsageHistory(Array(usage1, usage2))

    val mapper = new ObjectMapper()
    mapper.convertValue(response, classOf[VerizonUsageInformationResponse])
  }
}