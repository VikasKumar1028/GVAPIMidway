package com.gv.midway.processor

import java.net.{ConnectException, NoRouteToHostException, SocketTimeoutException, UnknownHostException}

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.job.JobDetail
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.Exchange
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestKoreBatchExceptionProcessor extends TestMocks {

  List(
    ("UnknownHostException", new Exception(new UnknownHostException(IConstant.MIDWAY_CONNECTION_ERROR)))
    , ("ConnectException", new Exception(new ConnectException(IConstant.MIDWAY_CONNECTION_ERROR)))
    , ("NoRouteToHostException", new Exception(new NoRouteToHostException(IConstant.MIDWAY_CONNECTION_ERROR)))
    , ("SocketTimeoutException", new Exception(new SocketTimeoutException(IConstant.MIDWAY_CONNECTION_ERROR)))
    , ("Exception", new Exception(new Exception(IConstant.KORE_MISSING_SIM_ERROR)))
  ).foreach { case (name, exception) =>
    test(name) {

      val date = "2016-10-12"
      val netsuiteId: java.lang.Integer = 1900
      val jobId = "jobId"

      withMockExchangeAndMessage(OPT_VERIZON){ (exchange, message) =>

        val jobDetail = new JobDetail
        jobDetail.setDate(date)
        jobDetail.setJobId(jobId)

        val deviceId = mock[DeviceId]

        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
        when(exchange.getProperty("jobDetail")).thenReturn(jobDetail, Nil: _*)
        when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netsuiteId, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[DeviceUsage])

        new KoreBatchExceptionProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())

        val usage = captor.getValue
        assert(usage.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
        assert(usage.getDeviceId === deviceId)
        assert(usage.getDataUsed === 0)
        assert(usage.getDate === date)
        assert(usage.getTransactionErrorReason === exception.getCause.getMessage)
        assert(usage.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_ERROR)
        assert(usage.getNetSuiteId === netsuiteId)
        assert(usage.getIsValid)
        assert(usage.getJobId === jobId)
      }
    }
  }
}