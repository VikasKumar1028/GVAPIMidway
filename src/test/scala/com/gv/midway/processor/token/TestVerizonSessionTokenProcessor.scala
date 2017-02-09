package com.gv.midway.processor.token

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.token.VerizonAuthorizationResponse
import com.gv.midway.{TestMocks, VerizonSuite}
import org.mockito.Mockito._

class TestVerizonSessionTokenProcessor extends TestMocks with VerizonSuite{

  test("process") {
    withMockExchangeMessageAndEnvironment { (exchange, message, environment) =>

      val accessToken = "accessToken"
      val body = s"""{"username":"${propertyValue(verizonAPIUsername)}","password":"${propertyValue(verizonAPIPassword)}"}"""

      val response = new VerizonAuthorizationResponse
      response.setAccess_token(accessToken)

      when(message.getBody).thenReturn(response, Nil: _*)

      new VerizonSessionTokenProcessor(environment).process(exchange)

      assertVerizonRequestToken(message, "/m2m/v1/session/login", accessToken, body)
      verify(exchange, times(1)).setProperty(IConstant.VZ_AUTHORIZATION_TOKEN, accessToken)

    }
  }
}