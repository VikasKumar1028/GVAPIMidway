package com.gv.midway.utility

import com.gv.midway.constant.IConstant
import org.apache.camel.{Exchange, Message}
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

class TestMessageStuffer extends FunSuite with MockitoSugar {
  type VerizonStuffer = (Message, Environment, String) => Unit
  type VerizonStufferToken = (Message, String, String, Object) => Unit
  val VerizonPOST: VerizonStuffer = MessageStuffer.setVerizonPOSTRequest
  val VerizonPOSTToken: VerizonStufferToken = MessageStuffer.setVerizonPOSTRequest

  val VerizonTests: List[(String, VerizonStuffer, VerizonStufferToken)] = List(
    ("POST", VerizonPOST, VerizonPOSTToken)
  )

  VerizonTests.foreach { case (verb, f, fToken) =>
    val path = "path"
    val auth = "auth"
    val username = "username"
    val password = "password"
    val accessToken = "accessToken"
    val body = "body"

    val env = mock[Environment]
    when(env.getProperty(IConstant.VERIZON_AUTHENTICATION)).thenReturn(auth, Nil: _*)
    when(env.getProperty(IConstant.VERIZON_API_USERNAME)).thenReturn(username, Nil: _*)
    when(env.getProperty(IConstant.VERIZON_API_PASSWORD)).thenReturn(password, Nil: _*)

    test(s"MessageStuffer.setVerizonRequest - $verb - no token") {
      val message = mock[Message]
      MessageStuffer.setVerizonRequest(message, env, verb, path)
      verizonAsserts(message)
    }

    test(s"MessageStuffer.setVerizon${verb}Request - no token") {
      val message = mock[Message]
      f(message, env, path)
      verizonAsserts(message)
    }

    test(s"MessageStuffer.setVerizonRequest - $verb - w/ token") {
      val message = mock[Message]
      MessageStuffer.setVerizonRequest(message, accessToken, verb, path, body)
      verizonAssertsToken(message)
    }

    test(s"MessageStuffer.setVerizon${verb}Request - w/ token") {
      val message = mock[Message]
      fToken(message, accessToken, path, body)
      verizonAssertsToken(message)
    }

    def verizonAsserts(message: Message): Unit = {
      verify(message, times(1)).setHeader("Authorization", auth)
      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/x-www-form-urlencoded")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, verb)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
      verify(message, times(1)).setHeader(Exchange.HTTP_QUERY, "grant_type=client_credentials")
    }

    def verizonAssertsToken(message: Message): Unit = {
      verify(message, times(1)).setHeader("Authorization", s"Bearer $accessToken")
      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, verb)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
      verify(message, times(1)).setBody(body)
    }
  }

  type KoreStuffer = (Message, Environment, String, AnyRef) => Unit
  val KOREPost: KoreStuffer = MessageStuffer.setKorePOSTRequest(_, _, _, _)
  val KOREGet: KoreStuffer = MessageStuffer.setKoreGETRequest(_, _, _, _)
  val KOREPut: KoreStuffer = MessageStuffer.setKorePUTRequest(_, _, _, _)

  val KORETests: List[(String, KoreStuffer)] = List(
    ("POST", KOREPost)
    , ("GET", KOREGet)
    , ("PUT", KOREPut)
  )

  KORETests.foreach { case (verb, f) =>

    val path = "path"
    val body: Integer = 14
    val auth = "auth"

    val env = mock[Environment]
    when(env.getProperty(IConstant.KORE_AUTHENTICATION)).thenReturn(auth)

    test(s"MessageStuffer.setKoreRequest - $verb") {
      val message = mock[Message]
      MessageStuffer.setKoreRequest(message, env, verb, path, body)
      KOREAsserts(message)
    }

    test(s"MessageStuffer.setKore${verb}Request") {
      val message = mock[Message]
      f(message, env, path, body)
      KOREAsserts(message)
    }

    def KOREAsserts(message: Message): Unit = {
      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, verb)
      verify(message, times(1)).setHeader("Authorization", auth)
      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
      verify(message, times(1)).setBody(body)
    }
  }
}