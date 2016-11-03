package com.gv.midway

import com.gv.midway.constant.IConstant
import com.gv.midway.utility.NetSuiteOAuthUtil
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

  def properties: List[String] = List.empty[String]

  protected def withMockExchange(f: Exchange => Unit): Unit = withMockExchange(None)(f)

  protected def withMockExchange(carrier: Option[String] = None)(f: Exchange => Unit): Unit = {
    val exchange = mock[Exchange]

    carrier.foreach{c =>
      //TODO We need to make this consistently done in a single way
      when(exchange.getProperty("carrierName")).thenReturn(c, Nil: _*)
      when(exchange.getProperty("CarrierName")).thenReturn(c, Nil: _*)
    }

    f(exchange)
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

      properties.foreach { s =>
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

  protected val attJasperVersion = "attJasper.version"
  protected val attJasperLicenseKey = "attJasper.licenseKey"
  protected val attJasperUsername = "attJasper.userName"
  protected val attJasperPassword = "attJasper.password"

  override val properties = List(attJasperVersion, attJasperLicenseKey, attJasperUsername, attJasperPassword)

  protected def assertMessageHeaderATTJasper(message: Message, operationName: String, operationNamespace: String, soapAction: String): Unit = {
    verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAME, operationName)
    verify(message, times(1)).setHeader(CxfConstants.OPERATION_NAMESPACE, operationNamespace)
    verify(message, times(1)).setHeader("soapAction", soapAction)
    verify(message, times(1)).setHeader(same(Header.HEADER_LIST), anyString())
  }
}

trait NetSuiteSuite {
  this: TestMocks =>

  protected val netSuiteOAuthTokenId = "netSuite.oauthTokenId"
  protected val netSuiteOAuthTokenSecret = "netSuite.oauthTokenSecret"
  protected val netSuiteConsumerKey = "netSuite.oauthConsumerKey"
  protected val netSuiteOAuthConsumerSecret = "netSuite.oauthConsumerSecret"
  protected val netSuiteRealm = "netSuite.realm"
  protected val netSuiteEndpoint = "netSuite.endPoint"

  override val properties =
    List(netSuiteOAuthTokenId, netSuiteOAuthTokenSecret, netSuiteConsumerKey, netSuiteOAuthConsumerSecret, netSuiteRealm, netSuiteEndpoint)
}