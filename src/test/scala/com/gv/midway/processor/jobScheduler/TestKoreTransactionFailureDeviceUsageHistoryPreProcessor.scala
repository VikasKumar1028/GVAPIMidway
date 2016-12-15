package com.gv.midway.processor.jobScheduler

import com.gv.midway.{KoreSuite, TestMocks}
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.DeviceUsage
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.ExchangePattern
import org.mockito.Mockito._

class TestKoreTransactionFailureDeviceUsageHistoryPreProcessor extends TestMocks with KoreSuite {

  test("KoreTransactionFailureDeviceUsageHistoryPreProcessor - happy flow") {
    withMockExchangeMessageAndEnvironment{ (exchange, message, environment) =>
      val deviceId = new DeviceId
      deviceId.setId("14")
      deviceId.setKind("kind")
      val usage = new DeviceUsage
      usage.setDeviceId(deviceId)
      usage.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      usage.setNetSuiteId(1400)

      when(message.getBody).thenReturn(usage, Nil: _*)

      new KoreTransactionFailureDeviceUsageHistoryPreProcessor(environment).process(exchange)

      assertKoreRequest(message, "/json/queryDeviceUsageBySimNumber")
      verify(message, times(1)).setBody("""{"simNumber":"14"}""")

      verify(exchange, times(1)).setProperty("DeviceId", deviceId)
      verify(exchange, times(1)).setProperty("CarrierName", IConstant.BSCARRIER_SERVICE_VERIZON)
      verify(exchange, times(1)).setProperty(IConstant.MIDWAY_NETSUITE_ID, usage.getNetSuiteId)
      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}