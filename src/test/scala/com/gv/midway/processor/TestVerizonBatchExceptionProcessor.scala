package com.gv.midway.processor

import java.net.{ConnectException, NoRouteToHostException, SocketTimeoutException, UnknownHostException}

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, JobName}
import com.gv.midway.exception.VerizonSessionTokenExpirationException
import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceUsage}
import com.gv.midway.pojo.job.JobDetail
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.Exchange
import org.apache.camel.component.cxf.CxfOperationException
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestVerizonBatchExceptionProcessor extends TestMocks {

  private val jobId = "jobId"
  private val date = "2016-10-12"
  private val netSuiteId: java.lang.Integer = 900

  List(
    new UnknownHostException()
    , new ConnectException()
    , new NoRouteToHostException()
    , new SocketTimeoutException()
  ).foreach{ e =>
    test(e.getClass.getName) {

      withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

        val deviceId = mock[DeviceId]

        when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(new Exception(e), Nil: _*)
        when(exchange.getProperty("jobDetail")).thenReturn(jobDetail(jobId, date, JobName.VERIZON_DEVICE_USAGE), Nil: _*)
        when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)

        val captor = ArgumentCaptor.forClass(classOf[DeviceUsage])
        new VerizonBatchExceptionProcessor().process(exchange)

        verify(message, times(1)).setBody(captor.capture())

        val usage = captor.getValue
        assert(usage.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
        assert(usage.getDeviceId === deviceId)
        assert(usage.getDataUsed === 0)
        assert(usage.getDate === date)
        assert(usage.getTransactionErrorReason === IConstant.MIDWAY_CONNECTION_ERROR)
        assert(usage.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_ERROR)
        assert(usage.getNetSuiteId === netSuiteId)
        assert(usage.getIsValid)
        assert(usage.getJobId === jobId)
      }
    }
  }

  test("Connection history - over 3 tries") {
    val tokenError: java.lang.Integer = 3

    withMockExchangeAndMessage(OPT_VERIZON){ (exchange, message) =>
      val deviceId = mock[DeviceId]

      val exception = new CxfOperationException("", 401, "", "", new java.util.HashMap[String, String](), "responseBody")

      when(exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR)).thenReturn(tokenError, Nil: _*)
      when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
      when(exchange.getProperty("jobDetail")).thenReturn(jobDetail(jobId, date, JobName.VERIZON_CONNECTION_HISTORY), Nil: _*)
      when(exchange.getProperty("DeviceId")).thenReturn(deviceId, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceConnection])

      new VerizonBatchExceptionProcessor().process(exchange)

      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "401")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Invalid Token")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, "Not able to retrieve valid authentication token")
      verify(message, times(1)).setBody(captor.capture())

      val conn = captor.getValue
      assert(conn.getCarrierName === IConstant.BSCARRIER_SERVICE_VERIZON)
      assert(conn.getDeviceId === deviceId)
      assert(conn.getDate === date)
      assert(conn.getTransactionErrorReason === "Not able to retrieve valid authentication token")
      assert(conn.getTransactionStatus === IConstant.MIDWAY_TRANSACTION_STATUS_ERROR)
      assert(conn.getNetSuiteId === netSuiteId)
      assert(conn.getIsValid)
      assert(conn.getEvent === null)
      assert(conn.getJobId === jobId)
    }
  }

  test("Connection history - second try") {
    withMockExchangeAndMessage(OPT_VERIZON) { (exchange, message) =>

      val exception = new CxfOperationException("", 403, "", "", new java.util.HashMap[String, String](), "UnifiedWebService.REQUEST_FAILED.SessionToken.Expired")
      when(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)).thenReturn(exception, Nil: _*)
      when(exchange.getProperty(IConstant.VERIZON_BATCH_SESSION_TOKENERROR)).thenReturn(2: java.lang.Integer, Nil: _*)

      assertThrows[VerizonSessionTokenExpirationException] {
        new VerizonBatchExceptionProcessor().process(exchange)
      }
    }
  }

  private def jobDetail(jobId: String, date: String, name: JobName): JobDetail = {
    val jd = new JobDetail
    jd.setJobId(jobId)
    jd.setDate(date)
    jd.setName(name)
    jd
  }
}