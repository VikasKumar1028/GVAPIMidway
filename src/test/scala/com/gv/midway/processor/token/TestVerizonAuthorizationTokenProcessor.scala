package com.gv.midway.processor.token

import com.gv.midway.{TestMocks, VerizonSuite}
import org.apache.camel.ExchangePattern
import org.mockito.Mockito._

class TestVerizonAuthorizationTokenProcessor extends TestMocks with VerizonSuite {

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      new VerizonAuthorizationTokenProcessor(environment).process(exchange)

      assertVerizonRequest(message, "/ts/v1/oauth2/token")

      verify(exchange, times(1)).setPattern(ExchangePattern.InOut)
    }
  }
}