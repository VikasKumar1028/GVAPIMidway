package com.gv.midway

import com.gv.midway.constant.IConstant
import org.apache.camel.component.cxf.common.message.CxfConstants
import org.apache.camel.{Exchange, Message}
import org.apache.cxf.headers.Header
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.FunSuiteLike
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

trait TestMocks extends FunSuiteLike with MockitoSugar {

  protected val OPT_VERIZON = Some(IConstant.BSCARRIER_SERVICE_VERIZON)
  protected val OPT_KORE = Some(IConstant.BSCARRIER_SERVICE_KORE)
  protected val OPT_ATT = Some(IConstant.BSCARRIER_SERVICE_ATTJASPER)

  def environmentProperties: List[String] = List()

  def exchangeProperties: List[String] = List()

  protected def withMockExchange(f: Exchange => Unit): Unit = withMockExchange(None)(f)

  protected def withMockExchange(carrier: Option[String] = None)(f: Exchange => Unit): Unit = {
    val exchange = mock[Exchange]

    exchangeProperties.foreach { p =>
      when(exchange.getProperty(p)).thenReturn(propertyValue(p), Nil: _*)
    }

    carrier.foreach{c =>
      //TODO We need to make this consistently done in a single way
      when(exchange.getProperty("carrierName")).thenReturn(c, Nil: _*)
      when(exchange.getProperty("CarrierName")).thenReturn(c, Nil: _*)
    }

    f(exchange)
  }

  protected def withMockMessage(f: Message => Unit): Unit = {
    val message = mock[Message]

    f(message)
  }

  protected def withMockExchangeAndMessage(f: (Exchange, Message) => Unit): Unit = withMockExchangeAndMessage(None)(f)

  protected def withMockExchangeAndMessage(carrier: Option[String] = None)(f: (Exchange, Message) => Unit): Unit = {
    withMockExchange(carrier){ exchange =>
      val message = mock[Message]

      when(exchange.getIn).thenReturn(message, Nil: _*)

      f(exchange, message)
    }
  }

  protected def withMockExchangeMessageAndEnvironment(f: (Exchange, Message, Environment) => Unit): Unit = withMockExchangeMessageAndEnvironment(None)(f)

  protected def withMockExchangeMessageAndEnvironment(carrier: Option[String] = None)(f: (Exchange, Message, Environment) => Unit): Unit = {
    withMockExchangeAndMessage(carrier){ (exchange, message) =>
      val environment = mock[Environment]

      environmentProperties.foreach { s =>
        when(environment.getProperty(s)).thenReturn(propertyValue(s), Nil: _*)
      }

      f(exchange, message, environment)
    }
  }

  protected def propertyValue(k: String): String = s"$k-$k"

  protected def deviceIdJson(id: String, kind: String) = s"""{"id":"$id","kind":"$kind"}"""
}

trait ATTJasperSuite {

  this: TestMocks =>

  override val environmentProperties = List(
    IConstant.ATTJASPER_VERSION, IConstant.ATTJASPER_LICENSE_KEY, IConstant.ATTJASPER_USERNAME, IConstant.ATTJASPER_PASSWORD
  )

  protected def assertMessageHeaderATTJasper(message: Message, operationName: String, operationNamespace: String, soapAction: String): Unit = {
    verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAME, operationName)
    verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAMESPACE, operationNamespace)
    verify(message, times(1)).setHeader("soapAction", soapAction)
    verify(message, times(1)).setHeader(same(Header.HEADER_LIST), anyString())
  }
}

trait NetSuiteSuite {
  this: TestMocks =>

  override val environmentProperties = List(
      IConstant.NETSUITE_OAUTH_CONSUMER_KEY
    , IConstant.NETSUITE_OAUTH_TOKEN_ID
    , IConstant.NETSUITE_OAUTH_TOKEN_SECRET
    , IConstant.NETSUITE_OAUTH_CONSUMER_SECRET
    , IConstant.NETSUITE_REALM
    , IConstant.NETSUITE_END_POINT
  )
}

trait KoreSuite {
  this: TestMocks =>

  protected val koreAuth = IConstant.KORE_AUTHENTICATION

  override val environmentProperties = List(koreAuth)

  protected def assertKoreRequest(message: Message, path: String): Unit = {
    verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    verify(message, times(1)).setHeader("Authorization", propertyValue(koreAuth))
    verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
  }
}

trait VerizonSuite {
  this: TestMocks =>

  protected val verizonAuth = IConstant.VERIZON_AUTHENTICATION
  protected val verizonAPIUsername = IConstant.VERIZON_API_USERNAME
  protected val verizonAPIPassword = IConstant.VERIZON_API_PASSWORD

  override val environmentProperties = List(verizonAuth, verizonAPIUsername, verizonAPIPassword)

  protected def assertVerizonRequest(message: Message, path: String): Unit = {
    verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/x-www-form-urlencoded")
    verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    verify(message, times(1)).setHeader("Authorization", propertyValue(verizonAuth))
    verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
    verify(message, times(1)).setHeader(Exchange.HTTP_QUERY, "grant_type=client_credentials")
  }

  protected def assertVerizonRequestToken(message: Message, path: String, token: String, body: AnyRef): Unit = {
    verify(message, times(1)).setHeader("Authorization", s"Bearer $token")
    verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
    verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    verify(message, times(1)).setHeader(Exchange.HTTP_PATH, path)
    verify(message, times(1)).setBody(body)
  }

}